
package br.edu.fesa.vaievem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Conexao {
    
    private final ResourceBundle DAO = ResourceBundle.getBundle("dao", new Locale("pt", "BR"));
    private static Conexao conexao;
    
    private Conexao(){
    }
    
    public static Conexao getInstance(){
        if(conexao == null){
            conexao = new Conexao();
        }
        
        return conexao;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DAO.getString("driver"));
        Connection connection = DriverManager.getConnection(DAO.getString("url"), DAO.getString("usuario"), DAO.getString("senha"));
        return connection;
    }
}
