package controller;

import library.domain.dao.IVolumeDAO;
import library.domain.dao.postgresql.VolumeDAOImplPostgreSQL;
import library.domain.entidades.Volume;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/volume")
public class VolumeController {
    private IVolumeDAO banco = new VolumeDAOImplPostgreSQL(); 

    @GET
    @Path("/insere/{editora}&{ano_publicacao}&{descricao}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insere(
        @PathParam("editora")String editora,
        @PathParam("ano_publicacao")int ano_publicacao,
        @PathParam("descricao")String descricao
        ){
        
        Volume nova = new Volume();
        nova.setEditora(editora);
        nova.setAno_publicacao(ano_publicacao);
        nova.setDescricao(descricao);
        banco.insere(nova);
    }
    
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Volume> consulta(){
        return banco.consulta();
    }
    
    @GET
    @Path("/busca/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Volume busca(@PathParam("id")int id){
        for(Volume aut: banco.consulta()){
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
    @Path("/atualiza/{id}&{editora}&{ano_publicacao}&{descricao}")
    @Produces(MediaType.APPLICATION_JSON)
    public void atualiza(
        @PathParam("id")int id,
        @PathParam("editora")String editora,
        @PathParam("ano_publicacao")int ano_publicacao,
        @PathParam("descricao")String descricao
    ){
        Volume nova = new Volume();
        nova.setId(id);
        nova.setEditora(editora);
        nova.setAno_publicacao(ano_publicacao);
        nova.setDescricao(descricao);
        banco.atualiza(nova);
    }
            
}