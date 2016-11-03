package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.PedidoAVista;

public class PedidoAVistaDao {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Contrutor
    public PedidoAVistaDao() throws SQLException{
            this.conexao = ConexaoBD.getConexao();  
    }
    
    public void adicionaPedido(PedidoAVista pedido) throws SQLException{
        //Metodo para adicionar contatos
        String slq = "insert into pedido_avista(id_cliente, id_veiculo, valor_avista, desconto, vendedor_avista, "
                + "comissao_vendendor_avista, quantidade_veiculo, mes_venda_avista) values(?, ?, ?, ?, ?, ?, ?, ?)";
        
            PreparedStatement stmt = conexao.prepareStatement(slq);
            
            stmt.setInt(1, pedido.getId_cliente());
            //stmt.setInt(2, pedido.getId_funcionario());
            stmt.setInt(2, pedido.getId_veiculo());
            stmt.setDouble(3, pedido.getValor_avista());
            stmt.setDouble(4, pedido.getDesconto());
            stmt.setString(5, pedido.getVendedor_avista());  
            stmt.setInt(6, pedido.getComissao_vendedor_avista());
            stmt.setInt(7, pedido.getQuantidade_veiculo());
            stmt.setString(8, pedido.getMes_venda_avista());
            
            stmt.execute();
            stmt.close();
    }
}
