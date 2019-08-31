package controller;

import library.domain.dao.ILivroDAO;
import library.domain.dao.postgresql.LivroDAOImplPostgreSQL;
import library.domain.entidades.Livro;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/livro")
public class LivroController {
    private ILivroDAO banco = new LivroDAOImplPostgreSQL(); 
   
    @GET
    @Path("/insere/{id_autor}&{id_categoria}&{id_volume}&{nome}&{descricao}&{nacionalidade}&{numero_edicao}&{estante}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insere(
        @PathParam("id_autor")int id_autor,
        @PathParam("id_categoria")int id_categoria,
        @PathParam("id_volume")int id_volume,
        @PathParam("nome")String nome,
        @PathParam("descricao")String descricao,
        @PathParam("nacionalidade")String nacionalidade,
        @PathParam("numero_edicao")int numero_edicao,
        @PathParam("estante")String estante
        ){
        
        Livro nova = new Livro();
        nova.setId_autor(id_autor);
        nova.setId_categoria(id_categoria);
        nova.setId_volume(id_volume);
        nova.setNome(nome);
        nova.setDescricao(descricao);
        nova.setNacionalidade(nacionalidade);
        nova.setNumero_edicao(numero_edicao);
        nova.setEstante(estante);
        banco.insere(nova);
    }
    
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> consulta(){
        return banco.consulta();
    }
    
    @GET
    @Path("/busca/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Livro busca(@PathParam("id")int id){
        for(Livro aut: banco.consulta()){
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
    @Path("/empresta/{id_livro}&{id_estudante}")
    @Produces(MediaType.APPLICATION_JSON)
    public void empresta(
            @PathParam("id_livro")int id_livro,
            @PathParam("id_estudante")int id_estudante
            ){
        banco.empresta(id_livro, id_estudante);
    }
    
    @GET
    @Path("/devolve/{id_livro}")
    @Produces(MediaType.APPLICATION_JSON)
    public void devolte(
            @PathParam("id_livro")int id_livro
            ){
        banco.devolve(id_livro);
    }

    @GET
    @Path("/atualiza/{id}&{id_autor}&{id_categoria}&{id_volume}&{nome}&{descricao}&{nacionalidade}&{numero_edicao}&{estante}")
    @Produces(MediaType.APPLICATION_JSON)
    public void atualiza(
        @PathParam("id")int id,
        @PathParam("id_autor")int id_autor,
        @PathParam("id_categoria")int id_categoria,
        @PathParam("id_volume")int id_volume,
        @PathParam("nome")String nome,
        @PathParam("descricao")String descricao,
        @PathParam("nacionalidade")String nacionalidade,
        @PathParam("numero_edicao")int numero_edicao,
        @PathParam("estante")String estante
    ){
        Livro nova = new Livro();
        nova.setId(id);
        nova.setId_autor(id_autor);
        nova.setId_categoria(id_categoria);
        nova.setId_volume(id_volume);
        nova.setNome(nome);
        nova.setDescricao(descricao);
        nova.setNacionalidade(nacionalidade);
        nova.setNumero_edicao(numero_edicao);
        nova.setEstante(estante);
        banco.atualiza(nova);
    }
            
}