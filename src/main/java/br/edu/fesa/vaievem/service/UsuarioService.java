
package br.edu.fesa.vaievem.service;

import br.edu.fesa.vaievem.dao.UsuarioDAO;
import br.edu.fesa.vaievem.dao.interfaces.IUsuarioDAO;
import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.interfaces.IUsuarioService;
import java.util.List;


public class UsuarioService implements IUsuarioService {
    
    private final IUsuarioDAO usuarioDao;
    
    public UsuarioService(){
        usuarioDao = new UsuarioDAO();
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
        
        if(usuarioDao.listarPorEmail(usuario) != null){
            throw new PersistenciaException("E-mail já cadastrado");
        }    
        
        // TODO Encriptar a senha
        
        usuarioDao.inserir(usuario);    
    }

    @Override
    public void alterar(Usuario usuario) throws PersistenciaException {
        
        // TODO Encriptar a senha

        usuarioDao.inserir(usuario);    
    }

    @Override
    public void remover(Usuario usuario) throws PersistenciaException {
        usuarioDao.inserir(usuario);    
    }
    
    
    public boolean autenticaUsuario(Usuario usuario) throws PersistenciaException{
        // TODO Implementar
        throw new PersistenciaException("Não implementado");
    }

}
