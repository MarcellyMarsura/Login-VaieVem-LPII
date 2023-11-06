package br.edu.fesa.vaievem.controller;

import br.edu.fesa.vaievem.utils.MessageBox;
import br.edu.fesa.vaievem.utils.Session;
import br.edu.fesa.vaievem.utils.Tela;
import br.edu.fesa.vaievem.utils.ViewConfiguration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class MenuController implements Initializable {

    @FXML
    private Text txtNome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String nomeCompleto = Session.getUsuarioLogado().getNome();
        String[] nomes = nomeCompleto.split(" ");
        txtNome.setText(nomes[0]);
    }
    
    @FXML
    private void onMouseClicked_btnHome() throws IOException {
        try {
            throw new UnsupportedOperationException("Funcionalidade não implementada ainda.");
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    @FXML
    private void onMouseClicked_btnPerfil() throws IOException {
        try {
            ViewConfiguration.mudaTela(Tela.PERFIL.getNome());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    @FXML
    private void onMouseClicked_btnContas() throws IOException {
        try {
            throw new UnsupportedOperationException("Funcionalidade não implementada ainda.");
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    @FXML
    private void onMouseClicked_btnCartoes() throws IOException {
        try {
            throw new UnsupportedOperationException("Funcionalidade não implementada ainda.");
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    @FXML
    private void onMouseClicked_btnSobre() throws IOException {
        try {
            ViewConfiguration.mudaTela(Tela.SOBRE.getNome());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
    @FXML
    private void onMouseClicked_btnSair() throws IOException {
        try {
            Session.RemoveUsuarioLogado();
            ViewConfiguration.mudaTela(Tela.LOGIN.getNome());
        }
        catch (Exception erro){
            MessageBox.exibeMensagemErro(erro);
        }
    }
    
}
