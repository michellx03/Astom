package Main;

import Visao.Loguin;
import controle.ConexaoBD;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProjetoVendaDeCarros {

    public static void main(String[] args) throws SQLException {
        
        new Loguin().setVisible(true);//Chama a tela de loguin        
        
        //Conexao com o banco de dados
        try {
            Connection conexao =  new ConexaoBD().getConexao();
            conexao.close(); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conecatar ao banco" + ex);
        }        
    }    
}
