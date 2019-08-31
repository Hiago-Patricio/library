package library.domain.dao.postgresql;

import library.domain.dao.IVolumeDAO;
import library.domain.entidades.Volume;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VolumeDAOImplPostgreSQL implements IVolumeDAO{
    
    private static int lastId = getLastIdDb();
    
    public static int getLastIdDb(){
        String sql = "SELECT MAX (id) as id from volume;";
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
    
    
    public void insere(Volume ent){
        Connection con = createConnection();
        String sql = "INSERT INTO Volume VALUES ("
                + getLastId()
                + ", '" + ent.getEditora() + "'"
                + ", " + ent.getAno_publicacao()
                + ", '" + ent.getDescricao() + "'"
                + ");";
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
        lastId++;
    }
    
    
    public void atualiza(Volume ent){
        Connection con = createConnection();
        String sql = "update Volume set"
                + " editora = '" + ent.getEditora() + "'"
                + ", ano_publicacao = '" + ent.getAno_publicacao() + "'"
                + ", descricao = '" + ent.getDescricao() + "'"
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
        String sql = "delete from Volume where "
                + "id = " + id;
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public List<Volume> consulta() {
        try{
            Connection con = createConnection();
            List<Volume> list = new ArrayList<>();
            
            String sql = "select * from volume";
            ResultSet res = con.createStatement().executeQuery(sql);
            
            while(res.next()){
                Volume a = new Volume();
                a.setId(res.getInt("id"));
                a.setEditora(res.getString("editora"));
                a.setAno_publicacao(res.getInt("ano_publicacao"));
                a.setDescricao(res.getString("descricao"));
                list.add(a);
            }
            return list;
        }catch(Exception erro){
            erro.printStackTrace();
        }
        
        return null;
    }
    
}
