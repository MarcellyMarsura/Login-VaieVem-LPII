
package br.edu.fesa.vaievem.service.interfaces;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import java.util.List;

public interface IUsuarioService extends IService<Usuario> {
    
    Usuario listarPorEmail(Usuario usuario) throws PersistenciaException;
    
    List<Usuario> listarAtivos() throws PersistenciaException;
    
    boolean autenticaUsuario(Usuario usuario) throws PersistenciaException;
}
