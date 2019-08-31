
package library.domain.dao;

import library.domain.entidades.Volume;
import java.util.List;

public interface IVolumeDAO {
    
    public void insere(Volume ent);
    
    public void atualiza(Volume ent);
    
    public void remove(int id);    
    
    public List<Volume> consulta();
}

