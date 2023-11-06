package br.edu.fesa.vaievem.controller;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.UsuarioService;
import br.edu.fesa.vaievem.utils.MessageBox;
import br.edu.fesa.vaievem.utils.Tela;
import br.edu.fesa.vaievem.utils.ViewConfiguration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {

    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtSenha;
    
    UsuarioService _usuarioService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _usuarioService = new UsuarioService();
        ViewConfiguration.setPossuiMenu(false);
    }
    
    @FXML
    private void onMouseClicked_lnkCadastrar() throws IOException{
        try {
            ViewConfiguration.mudaTela(Tela.CADASTRO_USUARIO.getNome());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
        
    }
    
    @FXML
    private void onMouseClicked_btnEntrar() throws IOException{
        try {
            String email = txtEmail.getText().trim();
            String senha = txtSenha.getText().trim();
            
            if(email.isEmpty() || senha.isEmpty()) {
                throw new PersistenciaException("Preencha todos os campos.");
            }
            else {
                if(!_usuarioService.autenticaUsuario(new Usuario(email, senha))){
                    txtSenha.clear();
                    throw new PersistenciaException("Usuário/senha incorretos.");
                }
                ViewConfiguration.mudaTela(Tela.PERFIL.getNome());
            }
            
        }
        catch (PersistenciaException erro) {
            MessageBox.exibeAlerta("Erro ao autenticar usuário", erro.getMessage());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
}
