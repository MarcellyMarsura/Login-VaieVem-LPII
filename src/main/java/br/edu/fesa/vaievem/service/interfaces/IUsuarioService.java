
package br.edu.fesa.vaievem.service.interfaces;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import java.util.List;

public interface IUsuarioService extends IService<Usuario> {
    
    boolean autenticaUsuario(Usuario usuario) throws PersistenciaException;

    void cadastraUsuario(Usuario usuario) throws PersistenciaException;
    
    void atualizaDados(Usuario usuario) throws PersistenciaException;
    
    void atualizaSenha(String senhaAntiga, String senhaNova) throws PersistenciaException;

    void inativar() throws PersistenciaException;
    
    Usuario listarPorEmail(Usuario usuario) throws PersistenciaException;
    
    List<Usuario> listarAtivos() throws PersistenciaException;
    
}
