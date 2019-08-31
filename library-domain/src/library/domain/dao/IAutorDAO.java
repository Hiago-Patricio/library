
package library.domain.dao;

import library.domain.entidades.Autor;
import java.util.List;

public interface IAutorDAO {
    
    public void insere(Autor ent);
    
    public void atualiza(Autor ent);
    
    public void remove(int id);    
    
    public List<Autor> consulta();
}

