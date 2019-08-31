package library.domain.dao.postgresql;

import library.domain.dao.IEstudanteDAO;
import library.domain.entidades.Estudante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAOImplPostgreSQL implements IEstudanteDAO{
    
    private static int lastId = getLastIdDb();
    
    public static int getLastIdDb(){
        String sql = "SELECT MAX (id) as id from estudante;";
        int lastId;
        try{
            Connection con = null;
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "123456");
            ResultSet res = con.createStatement().executeQuery(sql);
            con.close();
            res.next();
            lastId = res.getInt("id");
        }catch(Exception erro){
            lastId = 1;
            erro.printStackTrace();
        }
        return lastId + 1;
    }
    
    public int getLastId(){
        return lastId;
    }

    public void setLastId(int lastId){
        this.lastId = lastId;
    }
    
    private Connection createConnection(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "123456");
        }catch(Exception erro){
            erro.printStackTrace(); 
        }
        
        return connection;
    }
    
    public void insere(Estudante ent){
        Connection con = createConnection();
        String sql = "INSERT INTO estudante VALUES ("
                + getLastId()
                + ", '" + ent.getNome() + "'"
                + ", '" + ent.getTelefone() + "'"
                + ", '" + ent.getCpf() + "'"
                + ", '" + ent.getRg() + "'"
                + ", '" + ent.getEndereco() + "'"
                + ");";
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
        lastId++;
    }
 
    public void atualiza(Estudante ent){
        Connection con = createConnection();

        String sql = "update estudante set"
                + " nome = '" + ent.getNome() + "'"
                + ", telefone = '" + ent.getTelefone() + "'"
                + ", rg = '" + ent.getRg() + "'"
                + ", cpf = '" + ent.getCpf() + "'"
                + ", endereco = '" + ent.getEndereco() + "'"
                + " where id = " + ent.getId()
                + ";";

        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }

    
    public void remove(int id){
        Connection con = createConnection();
        String sql = "delete from estudante where "
                + "id = " + id;
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public List<Estudante> consulta() {
        try{
            Connection con = createConnection();
            List<Estudante> list = new ArrayList<>();
            
            String sql = "select * from estudante";
            ResultSet res = con.createStatement().executeQuery(sql);
            
            while(res.next()){
                Estudante a = new Estudante();
                a.setId(res.getInt("id"));
                a.setNome(res.getString("nome"));
                a.setTelefone(res.getString("telefone"));
                a.setCpf(res.getString("cpf"));
                a.setRg(res.getString("rg"));
                a.setEndereco(res.getString("endereco"));
                list.add(a);
            }
            return list;
        }catch(Exception erro){
            erro.printStackTrace();
        }
        
        return null;
    }
    
}
