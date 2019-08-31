package library.domain.dao.postgresql;

import library.domain.dao.ICategoriaDAO;
import library.domain.entidades.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImplPostgreSQL implements ICategoriaDAO{
    
    private static int lastId = getLastIdDb();
    
    public static int getLastIdDb(){
        // SELECT MAX (id) FROM autor;
        String sql = "SELECT MAX (id) as id from categoria;";
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
    
    
    public void insere(Categoria ent){
        Connection con = createConnection();
        String sql = "INSERT INTO categoria VALUES ("
                + getLastId()
                + ", '" + ent.getNome() + "'"
                + ", '" + ent.getDescricao() + "'"
                + ", '" + ent.getLocalizacao() + "'"
                + ");";
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
        lastId++;
    }
    
    
    public void atualiza(Categoria ent){
        Connection con = createConnection();
        String sql = "update categoria set"
                + " nome = '" + ent.getNome() + "'"
                + ", descricao = '" + ent.getDescricao() + "'"
                + ", localizacao = '" + ent.getLocalizacao() + "'"
                + " where id = " + ent.getId() + ";";

        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public void remove(int id){
        Connection con = createConnection();
        String sql = "delete from categoria where "
                + "id = " + id;
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public List<Categoria> consulta() {
        try{
            Connection con = createConnection();
            List<Categoria> list = new ArrayList<>();
            
            String sql = "select * from categoria";
            ResultSet res = con.createStatement().executeQuery(sql);
            
            while(res.next()){
                Categoria a = new Categoria();
                a.setId(res.getInt("id"));
                a.setNome(res.getString("nome"));
                a.setDescricao(res.getString("descricao"));
                a.setLocalizacao(res.getString("localizacao"));
                list.add(a);
            }
            return list;
        }catch(Exception erro){
            erro.printStackTrace();
        }
        
        return null;
    }
    
}
