package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Pedido;

public class PedidoDao {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Contrutor
    public PedidoDao() throws SQLException{
            this.conexao = ConexaoBD.getConexao();  
    }
    
    public void adicionaPedido(Pedido pedido) throws SQLException{
        //Metodo para adicionar contatos
        String slq = "insert into pedido(id_cliente, id_funcionario, id_veiculo, valor_avista,"
                + "desconto, vendedor_avista, valor_financiado, total_financiado, parcelas, vendedor_financiado)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
            PreparedStatement stmt = conexao.prepareStatement(slq);
            
            stmt.setInt(1, pedido.getId_cliente());
            stmt.setInt(2, pedido.getId_funcionario());
            stmt.setInt(3, pedido.getId_veiculo());
            stmt.setDouble(4, pedido.getValor_avista());
            stmt.setDouble(5, pedido.getDesconto());
            stmt.setString(6, pedido.getVendedor_avista());
            stmt.setDouble(7, pedido.getValor_financiado());
            stmt.setDouble(8, pedido.getTotal_financiado());
            stmt.setString(9, pedido.getParcelas());
            stmt.setString(10, pedido.getVendedor_financiado());            
            
            stmt.execute();
            stmt.close();
    }
}
