
package br.edu.fesa.vaievem.utils;

import br.edu.fesa.vaievem.model.Usuario;


public class Session {
    
    private static Usuario usuarioLogado;
    

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Session.usuarioLogado = usuarioLogado;
    }
    
    public static void RemoveUsuarioLogado(){
        usuarioLogado = null;
    }
    
    public static boolean UsuarioEstaLogado(){
        return usuarioLogado != null;
    }
    
}
