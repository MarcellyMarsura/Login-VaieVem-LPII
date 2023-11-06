package br.edu.fesa.vaievem.controller;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.UsuarioService;
import br.edu.fesa.vaievem.utils.MessageBox;
import br.edu.fesa.vaievem.utils.Session;
import br.edu.fesa.vaievem.utils.Tela;
import br.edu.fesa.vaievem.utils.ViewConfiguration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;


public class PerfilController implements Initializable {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtSenhaAntiga;
    
    @FXML
    private TextField txtNovaSenha;
    
    private UsuarioService _usuarioService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _usuarioService = new UsuarioService();
        preencheCampos();
    }    
    
    private void preencheCampos(){
        if(Session.getUsuarioLogado() != null){
            txtNome.setText(Session.getUsuarioLogado().getNome());
            txtEmail.setText(Session.getUsuarioLogado().getEmail());
        }
    }
    
    @FXML
    private void onMouseClicked_btnSalvarDados() throws IOException {
        try {
            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            
            if(nome.isEmpty() || email.isEmpty()) {
                throw new PersistenciaException("Preencha todos os campos");
            }
            
            _usuarioService.atualizaDados(new Usuario(nome, email, ""));
            ViewConfiguration.mudaTela(Tela.PERFIL.getNome());
            
            MessageBox.exibeInformacao("Sucesso!", "Dados atualizados com sucesso.");
        }
        catch (PersistenciaException erro) {
            MessageBox.exibeAlerta("Erro ao atualizar dados.", erro.getMessage());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    
    @FXML
    private void onMouseClicked_btnSalvarSenha() throws IOException {
        try {
            String senhaAntiga = txtSenhaAntiga.getText().trim();
            String NovaSenha = txtNovaSenha.getText().trim();
            
            if(senhaAntiga.isEmpty() || NovaSenha.isEmpty()) {
                throw new PersistenciaException("Preencha todos os campos");
            }
            
            _usuarioService.atualizaSenha(new Usuario("", "", NovaSenha));
            
            MessageBox.exibeInformacao("Sucesso!", "Senha atualizada com sucesso.");
        }
        catch (PersistenciaException erro) {
            MessageBox.exibeAlerta("Erro ao atualizar senha.", erro.getMessage());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    @FXML
    private void onMouseClicked_btnInativarConta() throws IOException {
        try {
            var resultado = MessageBox.exibeConfirmacao("Inativar conta", "Deseja inativar a sua conta? A operação não poderá ser desfeita.");
            
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                
            _usuarioService.inativar();
            ViewConfiguration.mudaTela(Tela.LOGIN.getNome());
            }
        }
        catch (PersistenciaException erro) {
            MessageBox.exibeAlerta("Erro ao atualizar senha.", erro.getMessage());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
}
