package controle;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoExecutaSql {
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/ProjetoVeiculos";
    private String usuario = "postgres";
    private String senha = "root";
    public Connection con;

    //Metodo para oriar conexao com o banco de dados.
    public void conexao(){
        
        try {
            System.setProperty("jdbc.Drivers", driver);
            con=DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Banco conectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + ex);
        }
    } 
    
    //Metodo para desconectar do banco de dados.
    public void desconecta(){
        try {
            con.close();
            //JOptionPane.showMessageDialog(null, "Banco desconectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar do banco!" + ex);
        }
    }
    
         public void executasql(String sql){
             //Executa as sql de comando no banco de dados
        try {
            stm = con.createStatement( rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao executar o comando SQL!" + ex);
        }
    }
}

