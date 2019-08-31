
package library.domain.dao;

import library.domain.entidades.Categoria;
import java.util.List;

public interface ICategoriaDAO {
    
    public void insere(Categoria ent);
    
    public void atualiza(Categoria ent);
    
    public void remove(int id);    
    
    public List<Categoria> consulta();
}

