package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Funcionario;

public class FuncionarioDao {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Contrutor
    public FuncionarioDao() throws SQLException{
        this.conexao = ConexaoBD.getConexao();  
    }
    
    public void adicionaFuncionario(Funcionario funcionario) throws SQLException{
        //Metodo para adicionar funcionarios
        String slq = "insert into funcionario(nome_funcionario, sobrenome_funcionario, data_nascimento_funcionario,"
                + "endereco_funcionario, bairro_funcionario, numero_casa_funcionario, complemento_funcionario, cargo_funcionario,"
                + "salario_funcionario, usuario, senha) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
            PreparedStatement stmt = conexao.prepareStatement(slq);
            
            stmt.setString(1, funcionario.getNome_funcionario());
            stmt.setString(2, funcionario.getSobrenome_funcionario());
            stmt.setString(3, funcionario.getData_nascimento_funcionario());
            stmt.setString(4, funcionario.getEndereco_funcionario());
            stmt.setString(5, funcionario.getBairro_funcionario());
            stmt.setInt(6, funcionario.getNumero_casa_funcinario());
            stmt.setString(7, funcionario.getComplemento_funcionario());
            stmt.setString(8, funcionario.getCargo_funcionario());
            stmt.setDouble(9, funcionario.getSalario_funcionario());
            stmt.setString(10, funcionario.getUsuario());
            stmt.setString(11, funcionario.getSenha());
            
            stmt.execute();
            stmt.close();
    }
    
    public List<Funcionario> getListaFuncionario(String nome) throws SQLException{
        //Meotodo para pesquisa funcionarios
        String sql = "select * from funcionario where nome_funcionario like ?";
        
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
            
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId_funcionario(Long.valueOf(rs.getString("id_funcionario")));
                funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
                funcionario.setSobrenome_funcionario(rs.getString("sobrenome_funcionario"));
                funcionario.setData_nascimento_funcionario(rs.getString("data_nascimento_funcionario"));
                funcionario.setEndereco_funcionario(rs.getString("endereco_funcionario"));
                funcionario.setBairro_funcionario(rs.getString("bairro_funcionario"));
                funcionario.setNumero_casa_funcinario(rs.getInt("numero_casa_funcionario"));
                funcionario.setComplemento_funcionario(rs.getString("complemento_funcionario"));
                funcionario.setCargo_funcionario(rs.getString("cargo_funcionario"));
                funcionario.setSalario_funcionario(rs.getDouble("salario_funcionario"));
                funcionario.setUsuario(rs.getString("usuario"));
                funcionario.setSenha(rs.getString("senha"));
                
                listaFuncionario.add(funcionario);     
            }
                rs.close();
                stmt.close();
                return listaFuncionario;       
    }
    
    public void alteraFuncionario(Funcionario funcionario) throws SQLException{
        //Metodo para altera funcionario
        String sql = "update funcionario set nome_funcionario=?, sobrenome_funcionario=?, data_nascimento_funcionario=?,"
                + "endereco_funcionario=?, bairro_funcionario=?, numero_casa_funcionario=?, complemento_funcionario=?, cargo_funcionario=?,"
                + "salario_funcionario=?, usuario=?, senha=? where id_funcionario=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
            stmt.setString(1, funcionario.getNome_funcionario());
            stmt.setString(2, funcionario.getSobrenome_funcionario());
            stmt.setString(3, funcionario.getData_nascimento_funcionario());
            stmt.setString(4, funcionario.getEndereco_funcionario());
            stmt.setString(5, funcionario.getBairro_funcionario());
            stmt.setInt(6, funcionario.getNumero_casa_funcinario());
            stmt.setString(7, funcionario.getComplemento_funcionario());
            stmt.setString(8, funcionario.getCargo_funcionario()); 
            stmt.setDouble(9, funcionario.getSalario_funcionario());            
            stmt.setString(10, funcionario.getUsuario());
            stmt.setString(11, funcionario.getSenha());
            stmt.setLong(12, funcionario.getId_funcionario());
            
            stmt.execute();
            stmt.close();
    }
    
    /*
    public void excluiFuncionario(Funcionario funcionario) throws SQLException{
        //Metodo para excluir funcionario
        String sql = "delete from funcionario where id_funcionario=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setLong(1, funcionario.getId_funcionario());
        
        stmt.execute();
        stmt.close();
    }
    */
}
