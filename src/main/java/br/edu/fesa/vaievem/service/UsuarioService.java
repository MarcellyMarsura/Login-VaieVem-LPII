
package br.edu.fesa.vaievem.service;

import br.edu.fesa.vaievem.dao.UsuarioDAO;
import br.edu.fesa.vaievem.dao.interfaces.IUsuarioDAO;
import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.interfaces.IUsuarioService;
import br.edu.fesa.vaievem.utils.Security;
import java.util.List;


public class UsuarioService implements IUsuarioService {
    
    private final IUsuarioDAO usuarioDao;
    
    public UsuarioService(){
        usuarioDao = new UsuarioDAO();
    }
    
    private void PreparaUsuario(Usuario usuario) throws PersistenciaException {
        
        if(usuarioDao.listarPorEmail(usuario) != null){
            throw new PersistenciaException("E-mail já cadastrado");
        }  
        
        usuario.setSenha(Security.EncriptaString(usuario.getSenha()));
    }
    
    @Override
    public List<Usuario> listar() throws PersistenciaException {
        return usuarioDao.listar();
    }
    
    @Override
    public Usuario listarPorId(Usuario usuario) throws PersistenciaException {
        return usuarioDao.listarPorId(usuario);
    }
    
    @Override
    public Usuario listarPorEmail(Usuario usuario) throws PersistenciaException {
        return usuarioDao.listarPorEmail(usuario);
    }

    @Override
    public List<Usuario> listarAtivos() throws PersistenciaException {
        return usuarioDao.listarAtivos();
    }

    
    @Override
    public void inserir(Usuario usuario) throws PersistenciaException {
        
        PreparaUsuario(usuario);
        
        usuarioDao.inserir(usuario);    
    }

    @Override
    public void alterar(Usuario usuario) throws PersistenciaException {
        
        PreparaUsuario(usuario);

        usuarioDao.alterar(usuario);    
    }

    @Override
    public void remover(Usuario usuario) throws PersistenciaException {
        usuarioDao.remover(usuario);    
    }
    
    
    public boolean autenticaUsuario(Usuario usuarioForm) throws PersistenciaException{
        
        Usuario usuarioDB = listarPorEmail(usuarioForm);
        
        if(usuarioDB == null){
            return false;
        }
        
        return Security.ComparaSenha(usuarioDB.getSenha(), usuarioForm.getSenha());
    }

}
