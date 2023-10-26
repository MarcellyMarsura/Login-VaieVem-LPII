
package br.edu.fesa.vaievem.main;

import br.edu.fesa.vaievem.dao.UsuarioDAO;
import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    private static UsuarioDAO usuarioDAO = null;
    
    public static void main(String[] args) {
        usuarioDAO = new UsuarioDAO();
        
        Listar();
        //ListarPorId();
        //ListarPorEmail();
        //Inserir();
        //Alterar();
        //Excluir();
    }

    
    // EXEMPLOS DE USOS DA DAO
    public static void Inserir(){
        
        try {
            Usuario novo = new Usuario((long)1, "Vinicius", "Vinicius@email.com", "SenhaSegura");
            usuarioDAO.inserir(novo);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void Alterar(){
        
        try {
            Usuario alterar = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            usuarioDAO.alterar(alterar);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Excluir(){
        try {
            // Ou envia o objeto completo, ou apenas com id
            //Usuario excluir = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            Usuario excluir = new Usuario((long)2);
            usuarioDAO.remover(excluir);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Listar(){
        
        try {
            List<Usuario> usuarios = usuarioDAO.listar();
             
            for(Usuario usuario : usuarios){
                System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha());
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ListarPorId(){
        
        try {
            // Ou envia o objeto completo, ou apenas com id
            //Usuario pesquisado = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            Usuario pesquisado = new Usuario((long)1);
            Usuario encontrado = usuarioDAO.listarPorId(pesquisado);  
            
            System.out.println(encontrado.getIdUsuario() + " | " + encontrado.getNome() + " | " + encontrado.getEmail() + " | " + encontrado.getSenha());
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ListarPorEmail(){
        
        try {
            // Ou envia o objeto completo, ou apenas com id
            //Usuario pesquisado = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            Usuario pesquisado = new Usuario("Marcelly@email.com");
            Usuario encontrado = usuarioDAO.listarPorEmail(pesquisado);  
            
            System.out.println(encontrado.getIdUsuario() + " | " + encontrado.getNome() + " | " + encontrado.getEmail() + " | " + encontrado.getSenha());
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
