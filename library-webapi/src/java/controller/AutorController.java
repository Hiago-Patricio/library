package controller;

import library.domain.dao.IAutorDAO;
import library.domain.dao.postgresql.AutorDAOImplPostgreSQL;
import library.domain.entidades.Autor;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/autor")
public class AutorController {
    private IAutorDAO banco = new AutorDAOImplPostgreSQL(); 
   
    @GET
    @Path("/insere/{nome}&{nacionalidade}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insere(
        @PathParam("nome")String nome,
        @PathParam("nacionalidade")String nacionalidade
        ){
        
        Autor nova = new Autor();
        nova.setNome(nome);
        nova.setNacionalidade(nacionalidade);
        banco.insere(nova);
    }
    
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> consulta(){
        return banco.consulta();
    }
    
    @GET
    @Path("/busca/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor busca(@PathParam("id")int id){
        for(Autor aut: banco.consulta()){
            if(aut.getId() == id){
                return aut;
            }
        }
        return null;
    }
    
    @GET
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id")int id){
        banco.remove(id);
    }
    
    @GET
    @Path("/atualiza/{id}&{nome}&{nacionalidade}")
    @Produces(MediaType.APPLICATION_JSON)
    public void atualiza(
        @PathParam("id")int id,
        @PathParam("nome")String nome,
        @PathParam("nacionalidade")String nacionalidade
    ){
        Autor nova = new Autor();
        nova.setId(id);
        nova.setNome(nome);
        nova.setNacionalidade(nacionalidade);
        banco.atualiza(nova);
    }
            
}