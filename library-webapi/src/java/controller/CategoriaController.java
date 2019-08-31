package controller;

import library.domain.dao.ICategoriaDAO;
import library.domain.dao.postgresql.CategoriaDAOImplPostgreSQL;
import library.domain.entidades.Categoria;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/categoria")
public class CategoriaController {
    private ICategoriaDAO banco = new CategoriaDAOImplPostgreSQL(); 

    @GET
    @Path("/insere/{nome}&{descricao}&{localizacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insere(
        @PathParam("nome")String nome,
        @PathParam("descricao")String descricao,
        @PathParam("localizacao")String localizacao
        ){
        
        Categoria nova = new Categoria();
        nova.setNome(nome);
        nova.setDescricao(descricao);
        nova.setLocalizacao(localizacao);
        banco.insere(nova);
    }
    
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> consulta(){
        return banco.consulta();
    }
    
    @GET
    @Path("/busca/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria busca(@PathParam("id")int id){
        for(Categoria aut: banco.consulta()){
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
    @Path("/atualiza/{id}&{nome}&{descricao}&{localizacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public void atualiza(
        @PathParam("id")int id,
        @PathParam("nome")String nome,
        @PathParam("descricao")String descricao,
        @PathParam("localizacao")String localizacao
    ){
        Categoria nova = new Categoria();
        nova.setId(id);
        nova.setNome(nome);
        nova.setDescricao(descricao);
        nova.setLocalizacao(localizacao);
        banco.atualiza(nova);
    }
            
}