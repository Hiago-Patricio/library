package controller;

import library.domain.dao.IEstudanteDAO;
import library.domain.dao.postgresql.EstudanteDAOImplPostgreSQL;
import library.domain.entidades.Estudante;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/estudante")
public class EstudanteController {
    private IEstudanteDAO banco = new EstudanteDAOImplPostgreSQL(); 
   
    @GET
    @Path("/insere/{nome}&{telefone}&{cpf}&{rg}&{endereco}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insere(
        @PathParam("nome")String nome,
        @PathParam("telefone")String telefone,
        @PathParam("cpf")String cpf,
        @PathParam("rg")String rg,
        @PathParam("endereco")String endereco
        ){
        
        Estudante nova = new Estudante();
        nova.setNome(nome);
        nova.setTelefone(telefone);
        nova.setCpf(cpf);
        nova.setRg(rg);
        nova.setEndereco(endereco);
        banco.insere(nova);
    }

    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudante> consulta(){
        return banco.consulta();
    }
    
    @GET
    @Path("/busca/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estudante busca(@PathParam("id")int id){
        for(Estudante aut: banco.consulta()){
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
    @Path("/atualiza/{id}&{nome}&{telefone}&{cpf}&{rg}&{endereco}")
    @Produces(MediaType.APPLICATION_JSON)
    public void atualiza(
        @PathParam("id")int id,
        @PathParam("nome")String nome,
        @PathParam("telefone")String telefone,
        @PathParam("cpf")String cpf,
        @PathParam("rg")String rg,
        @PathParam("endereco")String endereco
    ){
        Estudante nova = new Estudante();
        nova.setId(id);
        nova.setNome(nome);
        nova.setTelefone(telefone);
        nova.setCpf(cpf);
        nova.setRg(rg);
        nova.setEndereco(endereco);
        banco.atualiza(nova);
    }
            
}