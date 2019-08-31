
package library.domain.dao;

import library.domain.entidades.Livro;
import java.util.List;

public interface ILivroDAO {
    
    public void insere(Livro ent);
    
    public void atualiza(Livro ent);
    
    public void remove(int id);    
    
    public void empresta(int id_livro, int id_estudante);
    
    public void devolve(int id_livro);
    
    public List<Livro> consulta();
}

