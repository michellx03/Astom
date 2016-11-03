package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.PedidoFinanciado;
import modelo.Veiculo;

public class PedidoFinanciadoDao {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Contrutor
    public PedidoFinanciadoDao() throws SQLException{
            this.conexao = ConexaoBD.getConexao();  
    }
      
    public void adicionaPedido(PedidoFinanciado pedido) throws SQLException{
        //Metodo para adicionar contatos
        String slq = "insert into pedido_financiado(id_cliente, id_veiculo, valor_financiado, total_financiado,"
                + " parcelas, vendedor_financiado, comissao_financiado, quantidade_veiculo, mes_venda_financiada)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
            PreparedStatement stmt = conexao.prepareStatement(slq);
            
            stmt.setInt(1, pedido.getId_cliente());
            //stmt.setInt(2, pedido.getId_funcionario());
            stmt.setInt(2, pedido.getId_veiculo());
            stmt.setDouble(3, pedido.getValor_financiado());
            stmt.setDouble(4, pedido.getTotal_financiado());
            stmt.setInt(5, pedido.getParcelas());
            stmt.setString(6, pedido.getVendedor_financiado()); 
            stmt.setInt(7, pedido.getComissao_financiado());
            stmt.setInt(8, pedido.getQuantidade_veiculo());
            stmt.setString(9, pedido.getMes_venda_financiada());            
            stmt.execute();
            stmt.close();
    }
}
