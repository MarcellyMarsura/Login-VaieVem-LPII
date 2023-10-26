
package br.edu.fesa.vaievem.dao.interfaces;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;

public interface IUsuarioDAO extends IGenericoDAO<Usuario> {
    
    Usuario listarPorEmail(Usuario e) throws PersistenciaException;
    
}
