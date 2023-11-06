
package br.edu.fesa.vaievem.service;

import br.edu.fesa.vaievem.dao.UsuarioDAO;
import br.edu.fesa.vaievem.dao.interfaces.IUsuarioDAO;
import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.interfaces.IUsuarioService;
import br.edu.fesa.vaievem.utils.Security;
import br.edu.fesa.vaievem.utils.Session;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Validação de senha e validação de email (regex)
//TODO: Verificar se a senha antiga informada na tela é a mesma que a cadastrada (atualizaSenha) 
public class UsuarioService implements IUsuarioService {
    
    private final IUsuarioDAO usuarioDao;
    
    private String emailRegex;
    private Pattern pattern;
    
    public UsuarioService(){
        usuarioDao = new UsuarioDAO();
        
        iniciaRegex();
    }
    
    // Métodos privados auxiliares
    private void iniciaRegex(){
        emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        pattern = Pattern.compile(emailRegex);
    }
    
    private void realizaValidacoesUsuario(Usuario usuario) throws PersistenciaException {
        
        // Valida se o e-mail está no padrão do Regex
        if(emailInvalido(usuario)){
            throw new PersistenciaException("E-mail inválido");
        }
        
        // Valida se o e-mail já está cadastrado
        if(emailJaCadastrado(usuario)){
            throw new PersistenciaException("E-mail já cadastrado");
        }   
        
        if(senhaInvalida(usuario)){
            throw new PersistenciaException("A senha deve ter no mínimo 3 caracteres!");
        }    
    }
    
    private boolean emailInvalido(Usuario usuario) throws PersistenciaException {
        return !pattern.matcher(usuario.getEmail()).matches();
    }
    
    private boolean emailJaCadastrado(Usuario usuario) throws PersistenciaException {
        
        Usuario usuarioBD = listarPorEmail(usuario);        
        
        return usuarioBD != null && !usuarioBD.getIdUsuario().equals(usuario.getIdUsuario());
    }
    
    private boolean senhaInvalida(Usuario usuario){
        return usuario.getSenha().length() < 3;
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
    
    
    // Métodos especializados do service
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
        
        realizaValidacoesUsuario(novoUsuario);
        
        encriptaSenha(novoUsuario);
        inserir(novoUsuario);
        
        Session.setUsuarioLogado(listarPorEmail(novoUsuario));
    }
    
    @Override
    public void atualizaDados(Usuario usuarioAlterado) throws PersistenciaException {
        
        Usuario usuarioLogado = retornaUsuarioSession();

        usuarioAlterado.setIdUsuario(usuarioLogado.getIdUsuario());
        usuarioAlterado.setSenha(usuarioLogado.getSenha());
        usuarioAlterado.setAtivo(usuarioLogado.isAtivo());
        
        realizaValidacoesUsuario(usuarioAlterado);

        usuarioDao.alterar(usuarioAlterado);    

        Session.setUsuarioLogado(listarPorId(usuarioAlterado));
    }
    
    @Override
    public void atualizaSenha(String senhaAntiga, String senhaNova) throws PersistenciaException {
        
        Usuario usuarioLogado = retornaUsuarioSession();
        Usuario usuarioAlterado = new Usuario(usuarioLogado.getIdUsuario(), usuarioLogado.getNome(), usuarioLogado.getEmail(), senhaNova);
        
        if(!Security.EncriptaString(senhaAntiga).equals(usuarioLogado.getSenha())){
            throw new PersistenciaException("Senha antiga está incorreta.");
        }
        
        realizaValidacoesUsuario(usuarioAlterado); 
        encriptaSenha(usuarioAlterado);
        
        usuarioDao.alterar(usuarioAlterado); 
        
        Session.setUsuarioLogado(listarPorId(usuarioAlterado));
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
