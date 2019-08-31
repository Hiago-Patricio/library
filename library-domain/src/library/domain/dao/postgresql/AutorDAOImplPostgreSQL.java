package library.domain.dao.postgresql;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import library.domain.dao.IAutorDAO;
import library.domain.entidades.Autor;


public class AutorDAOImplPostgreSQL implements IAutorDAO{
    
    private static int lastId = getLastIdDb();
    
    public static int getLastIdDb(){
        // SELECT MAX (id) FROM author;
        String sql = "SELECT MAX (id) as id from autor;";
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
    
    public void insere(Autor ent) {
        Connection con = createConnection();
        String sql = "INSERT INTO autor VALUES ("
                + getLastId()
                + ", '" + ent.getNome() + "'"
                + ", '" + ent.getNacionalidade() + "'"
                + ");";
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
        lastId++;
    }

    public void atualiza(Autor ent) {
        Connection con = createConnection();
        String sql = "update autor set"
                + " nome = '" + ent.getNome() + "'"
                + ", nacionalidade = '" + ent.getNacionalidade() + "'"
                + " where id = " + ent.getId() + ";";

        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }

    public void remove(int id) {
        Connection con = createConnection();
        String sql = "delete from autor where "
                + "id = " + id;
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }

    public List<Autor> consulta() {
        try{
            Connection con = createConnection();
            List<Autor> list = new ArrayList<>();
            
            String sql = "select * from autor";
            ResultSet res = con.createStatement().executeQuery(sql);
            
            while(res.next()){
                Autor a = new Autor();
                a.setId(res.getInt("id"));
                a.setNome(res.getString("nome"));
                a.setNacionalidade(res.getString("nacionalidade"));
                list.add(a);
            }
            return list;
        }catch(Exception erro){
            erro.printStackTrace();
        }
        
        return null;
    }
    
}
