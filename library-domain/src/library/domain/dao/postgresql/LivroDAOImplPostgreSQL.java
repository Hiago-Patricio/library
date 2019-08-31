package library.domain.dao.postgresql;

import library.domain.dao.ILivroDAO;
import library.domain.entidades.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImplPostgreSQL implements ILivroDAO{
    
    private static int lastId = getLastIdDb();
    
    public static int getLastIdDb(){
        String sql = "SELECT MAX (id) as id from Livro;";
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
    
    public void insere(Livro ent){
        Connection con = createConnection();
        String sql = "INSERT INTO Livro VALUES ("
                + getLastId()
                + ", " + ent.getId_autor()
                + ", " + ent.getId_categoria()
                + ", null"
                + ", " + ent.getId_volume()
                + ", '" + ent.getNome() + "'"
                + ", '" + ent.getDescricao() + "'"
                + ", '" + ent.getNacionalidade() + "'"
                + ", " + ent.getNumero_edicao()
                + ", '" + ent.getEstante() + "'"
                + ");";
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
        lastId++;
    }
    
    public void empresta(int id_livro, int id_estudante){
        Connection con = createConnection();
        String sql = "update livro set id_estudante = " + id_estudante
                + " where id = " + id_livro;
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public void devolve(int id_livro){
        Connection con = createConnection();
        String sql = "update livro set id_estudante = null "
                + " where id = " + id_livro;
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public void atualiza(Livro ent){
        Connection con = createConnection();
        String sql = "update Livro set"
                + " id_autor = " + ent.getId_autor()
                + ", id_categoria = " + ent.getId_categoria()
                + ", id_estudante = " + ent.getId_estudante()
                + ", id_volume = " + ent.getId_volume()
                + ", nome = '" + ent.getNome() + "'"
                + ", descricao = '" + ent.getDescricao() + "'"
                + ", nacionalidade = '" + ent.getNacionalidade() + "'"
                + ", numero_edicao = " + ent.getNumero_edicao()
                + ", estante = '" + ent.getEstante() + "'"
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
        String sql = "delete from Livro where "
                + "id = " + id;
        
        try{
            con.createStatement().execute(sql);
            con.close();
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    
    public List<Livro> consulta() {
        try{
            Connection con = createConnection();
            List<Livro> list = new ArrayList<>();
            
            String sql = "select * from livro";
            ResultSet res = con.createStatement().executeQuery(sql);
            
            while(res.next()){
                Livro a = new Livro();
                a.setId(res.getInt("id"));
                a.setId_autor(res.getInt("id_autor"));
                a.setId_categoria(res.getInt("id_categoria"));
                a.setId_estudante(res.getInt("id_estudante"));
                a.setId_volume(res.getInt("id_volume"));
                a.setNome(res.getString("nome"));
                a.setDescricao(res.getString("descricao"));
                a.setNacionalidade(res.getString("nacionalidade"));
                a.setNumero_edicao(res.getInt("numero_edicao"));
                a.setEstante(res.getString("estante"));
                list.add(a);
            }
            return list;
        }catch(Exception erro){
            erro.printStackTrace();
        }
        
        return null;
    }
    
}
