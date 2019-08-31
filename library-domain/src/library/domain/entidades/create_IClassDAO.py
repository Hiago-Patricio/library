import os

content = '''
package library.dao;

import library.entidades.{0};
import java.util.List;

public interface I{0}DAO OPEN
    
    public void insere({0} ent);
    
    public void atualiza({0} ent);
    
    public void remove(int id);    
    
    public List<{0}> consulta();
CLOSE
'''

files = os.listdir()
for entity in files:
	if '.java' in entity:
		file_entity = 'I' + entity.replace('.java', 'DAO.java')
		entity_name = entity.replace('.java', '')
		path = 'cd ..; cd dao;'
		echo_content = 'echo \'' + content.format(entity_name).replace('OPEN', '{').replace('CLOSE', '}') + '\' >' + file_entity
		os.system(path + echo_content)