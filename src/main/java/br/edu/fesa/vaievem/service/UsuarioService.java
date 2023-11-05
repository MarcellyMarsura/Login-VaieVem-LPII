
package br.edu.fesa.vaievem.service;

import br.edu.fesa.vaievem.dao.UsuarioDAO;
import br.edu.fesa.vaievem.dao.interfaces.IUsuarioDAO;
import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.interfaces.IUsuarioService;
import br.edu.fesa.vaievem.utils.Security;
import br.edu.fesa.vaievem.utils.Session;
import java.util.List;


public class UsuarioService implements IUsuarioService {
    
    private final IUsuarioDAO usuarioDao;
    
    public UsuarioService(){
        usuarioDao = new UsuarioDAO();
    }
    
    // Métodos privados auxiliares
    
    private boolean emailJaCadastrado(Usuario usuario)  throws PersistenciaException {
        return usuarioDao.listarPorEmail(usuario) != null;
    }
    
    private void encriptaSenha(Usuario usuario) throws PersistenciaException {
        usuario.setSenha(Security.EncriptaString(usuario.getSenha()));
    }
    
    private Usuario retornaUsuarioSession() throws PersistenciaException { 
        Usuario retorno = Session.getUsuarioLogado();
        
        if(retorno == null){
            throw new PersistenciaException("Nenhum usuário logado na Session");
        }
        
        return retorno;
    }
    
    
    // Métodos especiliazados do service
    
    @Override
    public boolean autenticaUsuario(Usuario usuarioForm) throws PersistenciaException{
        
        Session.RemoveUsuarioLogado();

        Usuario usuarioDB = listarPorEmail(usuarioForm);
        
        if(usuarioDB == null || !usuarioDB.isAtivo()){
            return false;
        }
        
        
        boolean usuarioValido = Security.ComparaSenha(usuarioDB.getSenha(), usuarioForm.getSenha());
        
        if(usuarioValido){
            Session.setUsuarioLogado(usuarioDB);
        }
        
        return usuarioValido;
    }
    
    @Override
    public void cadastraUsuario(Usuario novoUsuario) throws PersistenciaException {    
        Session.RemoveUsuarioLogado();
        
        if(emailJaCadastrado(novoUsuario)){
            throw new PersistenciaException("E-mail já cadastrado!");
        }
        
        encriptaSenha(novoUsuario);
        
        inserir(novoUsuario);
        
        Session.setUsuarioLogado(listarPorEmail(novoUsuario));
    }
    
    @Override
    public void atualizaDados(Usuario usuario) throws PersistenciaException {
        
        Usuario usuarioLogado = retornaUsuarioSession();

        usuarioLogado.setNome(usuario.getNome());
        usuarioLogado.setEmail(usuario.getEmail());
        
        Usuario usuarioBD = listarPorEmail(usuarioLogado);        
        
        if(usuarioBD != null && !usuarioBD.getIdUsuario().equals(usuarioLogado.getIdUsuario())){
            throw new PersistenciaException("E-mail já cadastrado!");
        }

        usuarioDao.alterar(usuarioLogado);    

        Session.setUsuarioLogado(listarPorId(usuarioLogado));
    }
    
    @Override
    public void atualizaSenha(Usuario usuario) throws PersistenciaException {
        
        Usuario usuarioLogado = retornaUsuarioSession();
        
        usuarioLogado.setSenha(usuario.getSenha());
        
        encriptaSenha(usuarioLogado);

        usuarioDao.alterar(usuarioLogado);    

        Session.setUsuarioLogado(listarPorId(usuarioLogado));
    }
    
    @Override
    public void inativar() throws PersistenciaException {
        Usuario usuarioLogado = retornaUsuarioSession();
        
        usuarioLogado.setAtivo(false);

        usuarioDao.alterar(usuarioLogado);    
        
        Session.RemoveUsuarioLogado();
    }
    
    
    // Método Genéricos
    
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
        usuarioDao.inserir(usuario);    
    }

    @Override
    public void alterar(Usuario usuario) throws PersistenciaException {
        usuarioDao.alterar(usuario);    
    }

    @Override
    public void remover(Usuario usuario) throws PersistenciaException {
        usuarioDao.remover(usuario);    
    }
}
