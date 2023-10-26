
package br.edu.fesa.vaievem.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }
    
    public Usuario(Long idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public Usuario(String email) {
        this.email = email;
    }

    
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}