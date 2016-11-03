package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDao {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Contrutor
    public ClienteDao() throws SQLException{
            this.conexao = ConexaoBD.getConexao();  
    }
    
    public void adicionaCliente(Cliente cliente) throws SQLException{
        //Metodo para adicionar contatos
        String slq = "insert into cliente(nome_cliente, cpf_cliente, data_nascimento_cliente, sexo_cliente, endereco_cliente," +
           "bairro_cliente, complemento_cliente, telefone_residencial_cliente, telefone_celular_cliente," +
            "numero_casa_cliente, cidade ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
            PreparedStatement stmt = conexao.prepareStatement(slq);
            
            stmt.setString(1, cliente.getNome_cliente());
            stmt.setString(2, cliente.getCpf_cliente());
            stmt.setString(3, cliente.getData_nascimento_cliente());
            stmt.setString(4, cliente.getSexo_cliente());
            stmt.setString(5, cliente.getEndereco_cliente());
            stmt.setString(6, cliente.getBairro_cliente());
            stmt.setString(7, cliente.getComplemento_cliente());
            stmt.setString(8, cliente.getTelefone_residencial_cliente());
            stmt.setString(9, cliente.getTelefone_celular_cliente());
            stmt.setInt(10, cliente.getNumero_casa_cliente());  
            stmt.setString(11, cliente.getCidade());
            
            stmt.execute();
            stmt.close();
    }
    
    public List<Cliente> getListaCliente(String nome) throws SQLException{
        //Meotodo para pesquisa cliente
        String sql = "select * from cliente where nome_cliente like ?";
        
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listaCliente = new ArrayList<Cliente>();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId_cliente(Long.valueOf(rs.getString("Id_cliente")));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                cliente.setSexo_cliente(rs.getString("sexo_cliente"));
                cliente.setData_nascimento_cliente(rs.getString("data_nascimento_cliente"));
                cliente.setEndereco_cliente(rs.getString("endereco_cliente"));
                cliente.setBairro_cliente(rs.getString("bairro_cliente"));
                cliente.setComplemento_cliente(rs.getString("complemento_cliente"));
                cliente.setNumero_casa_cliente(rs.getInt("numero_casa_cliente"));
                cliente.setCpf_cliente(rs.getString("cpf_cliente"));
                cliente.setTelefone_residencial_cliente(rs.getString("telefone_residencial_cliente"));
                cliente.setTelefone_celular_cliente(rs.getString("telefone_celular_cliente"));
                cliente.setCidade(rs.getString("cidade"));
                
                listaCliente.add(cliente);     
            }
                rs.close();
                stmt.close();
                return listaCliente;       
    }
    
    public void alteraCliente(Cliente cliente) throws SQLException{
        //Metodo para altera cliente
        String sql = "update cliente set nome_cliente=?, cpf_cliente=?, data_nascimento_cliente=?, sexo_cliente=?, endereco_cliente=?," +
           "bairro_cliente=?, complemento_cliente=?, telefone_residencial_cliente=?, telefone_celular_cliente=?," +
            "numero_casa_cliente=?, cidade=? where id_cliente=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
            stmt.setString(1, cliente.getNome_cliente());
            stmt.setString(2, cliente.getCpf_cliente());
            stmt.setString(3, cliente.getData_nascimento_cliente());
            stmt.setString(4, cliente.getSexo_cliente());
            stmt.setString(5, cliente.getEndereco_cliente());
            stmt.setString(6, cliente.getBairro_cliente());
            stmt.setString(7, cliente.getComplemento_cliente());
            stmt.setString(8, cliente.getTelefone_residencial_cliente());
            stmt.setString(9, cliente.getTelefone_celular_cliente());
            stmt.setInt(10, cliente.getNumero_casa_cliente());
            stmt.setString(11, cliente.getCidade());
            stmt.setLong(12, cliente.getId_cliente());
            
            
            stmt.execute();
            stmt.close();
    }
    
    /*
    public void excluiCliente(Cliente cliente) throws SQLException{
        //Metodo para excluir cliente
        String sql = "delete from cliente where id_cliente=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setLong(1, cliente.getId_cliente());
        
        stmt.execute();
        stmt.close();
    }
    */
}
