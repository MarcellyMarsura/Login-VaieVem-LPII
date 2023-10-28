
package br.edu.fesa.vaievem.main;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.UsuarioService;
import br.edu.fesa.vaievem.service.interfaces.IUsuarioService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    private static IUsuarioService usuarioService = null;
    
    public static void main(String[] args) {
        usuarioService = new UsuarioService();
        
        //Listar();
        //ListarPorId();
        //ListarPorEmail();
        //ListarAtivos();
        //Inserir();
        //Alterar();
        //Excluir();
        Autenticar();

    }

    
    // EXEMPLOS DE USOS DA DAO
    public static void Inserir(){
        
        try {
            Usuario novo = new Usuario((long)1, "Analuz", "Analuz@email.com", "SenhaSegura");
            usuarioService.inserir(novo);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void Alterar(){
        
        try {
            Usuario alterar = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura", false);
            usuarioService.alterar(alterar);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Excluir(){
        try {
            // Ou envia o objeto completo, ou apenas com id
            //Usuario excluir = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            Usuario excluir = new Usuario((long)1);
            usuarioService.remover(excluir);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Listar(){
        
        try {
            List<Usuario> usuarios = usuarioService.listar();
             
            for(Usuario usuario : usuarios){
                System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ListarPorId(){
        
        try {
            // Ou envia o objeto completo, ou apenas com id
            //Usuario pesquisado = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            Usuario pesquisado = new Usuario((long)2);
            Usuario encontrado = usuarioService.listarPorId(pesquisado);  
            
            if(encontrado == null){
                System.out.println("Usuario não encontrado");
            } else{
                System.out.println(encontrado.getIdUsuario() + " | " + encontrado.getNome() + " | " + encontrado.getEmail() + " | " + encontrado.getSenha() + " | " + encontrado.isAtivo());
            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ListarPorEmail(){
        
        try {
            // Ou envia o objeto completo, ou apenas com id
            //Usuario pesquisado = new Usuario((long)1, "Vinicius Benevides", "ViniciusBenevides@email.com", "SenhaNãoTãoSegura");
            Usuario pesquisado = new Usuario("Vinicius@email.com");
            Usuario encontrado = usuarioService.listarPorEmail(pesquisado);  
            
            if(encontrado == null){
                System.out.println("Usuario não encontrado");
            } else{
                System.out.println(encontrado.getIdUsuario() + " | " + encontrado.getNome() + " | " + encontrado.getEmail() + " | " + encontrado.getSenha() + " | " + encontrado.isAtivo());
            }            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ListarAtivos(){
        
        try {
            List<Usuario> usuarios = usuarioService.listarAtivos();
             
            for(Usuario usuario : usuarios){
                System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Autenticar(){
        try {
            Usuario usuario = new Usuario("Nicolas@email.com", "SenhaSegura");
            
            if(usuarioService.autenticaUsuario(usuario)) {
                System.out.println("Autenticado");
            } else{
                System.out.println("Invalido");
            }
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
