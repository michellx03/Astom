package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexaoBD {  
        
    //Caminho do banco de dados
    private static final String url = "jdbc:postgresql://localhost:5432/ProjetoVeiculos";
    private static final String usuario = "postgres";
    private static final String senha = "root";
    
    public Statement stm;
    public ResultSet rs;
    public Connection con;
    
    //Metodo para obter uma conexao
    public static Connection getConexao() throws SQLException{
        
        try {
            Class.forName("org.postgresql.Driver");// código a ser executado.
            System.out.println("Banco de dados conectado com sucesso");
        } catch (ClassNotFoundException e){// tratamento da exceção.
            e.printStackTrace();//Imprime na saída de erro padrão.
            System.out.println("Banco de dados nao conectou com sucesso");
        }
       
        return DriverManager.getConnection(url, usuario, senha);
    }    
}
/*
Try catch e pra tratamento de exceções, entenda try como um "TENTAR EXECUTAR UM TRECHO DE CODIGO", 
e se ele nao conseguir executar esse trecho de codigo, ele vai pro catch. Catch e onde as exceções sao 
tratadas.
*/