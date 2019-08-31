
package library.domain.dao;

import library.domain.entidades.Estudante;
import java.util.List;

public interface IEstudanteDAO {
    
    public void insere(Estudante ent);
    
    public void atualiza(Estudante ent);
    
    public void remove(int id);    
    
    public List<Estudante> consulta();
}

