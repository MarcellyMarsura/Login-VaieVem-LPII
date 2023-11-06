
package br.edu.fesa.vaievem.main;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import br.edu.fesa.vaievem.model.Usuario;
import br.edu.fesa.vaievem.service.UsuarioService;
import br.edu.fesa.vaievem.service.interfaces.IUsuarioService;
import br.edu.fesa.vaievem.utils.Session;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    private static IUsuarioService usuarioService = null;
    
    public static void main(String[] args) {
        usuarioService = new UsuarioService();
        
        //listar();
        autenticaUsuario();
        //cadastraUsuario();
        //atualizaDados();
        //atualizaSenha();
        //inativar();
    }

    public static void listar(){
        
        try {
            List<Usuario> usuarios = usuarioService.listar();
             
            for(Usuario usuario : usuarios){
                System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void autenticaUsuario(){
        try {
            Usuario usuario = new Usuario("Nicolas@email.com", "abc");
            
            if(usuarioService.autenticaUsuario(usuario)) {
                System.out.println("Autenticado");
            } else{
                System.out.println("Invalido");
            }
            
            System.out.println("\nSession:");
            
            usuario = Session.getUsuarioLogado();
            
            if(usuario == null){
                return;
            }
            
            System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cadastraUsuario(){
        try {
            Usuario usuario = new Usuario("Analuz", "Analuz@email.com", "SenhaSegura");
            
            usuarioService.cadastraUsuario(usuario);
            
            listar();
            
            System.out.println("\nSession:");
            
            usuario = Session.getUsuarioLogado();
            
            System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void atualizaDados(){
        try {
            Usuario usuario = new Usuario("Nicolas", "Nicolas@email.com", "abc");
            
            usuarioService.atualizaDados(usuario);
            
            listar();
            
            System.out.println("\nSession:");
            
            usuario = Session.getUsuarioLogado();
            
            System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void atualizaSenha(){
        try {
            Usuario usuario = new Usuario("Nicolas@email.com", "abc");
            
            usuarioService.atualizaSenha("abc", "abc");
            
            listar();
            
            System.out.println("\nSession:");
            
            usuario = Session.getUsuarioLogado();
            
            System.out.println(usuario.getIdUsuario() + " | " + usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.isAtivo());
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void inativar(){
        try {
            
            usuarioService.inativar();
            
            listar();
            
            if(Session.UsuarioEstaLogado()){
                System.out.println("Usuario logado");
            } else {
                System.out.println("Usuario NÃO logado");
            }
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
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

}
