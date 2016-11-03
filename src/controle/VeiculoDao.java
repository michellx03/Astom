package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Veiculo;

public class VeiculoDao {
    private Connection conexao;//Cria metodo de conexao
    
    //Contrutor
    public VeiculoDao() throws SQLException{
        this.conexao = ConexaoBD.getConexao();  
    }
    
    public void adicionaVeiculo(Veiculo veiculo) throws SQLException{
        //Metodo para adicionar veiculos
        String slq = "insert into veiculo(modelo_veiculo, cor_veiculo, ano_veiculo, placa_veiculo, estado_veiculo,"
                + "marca_veiculo, observacoes_veiculo, valor_minimo_veiculo, valor_medio_veiculo, valor_maximo_veiculo,"
                + "quantidade_veiculo, tipo_veiculo) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
            PreparedStatement stmt = conexao.prepareStatement(slq);
            
            stmt.setString(1, veiculo.getModelo_veiculo());
            stmt.setString(2, veiculo.getCor_veiculo());
            stmt.setInt(3, veiculo.getAno_veiculo());
            stmt.setString(4, veiculo.getPlaca_veiculo());
            stmt.setString(5, veiculo.getEstado_veiculo());
            stmt.setString(6, veiculo.getMarca_veiculo());
            stmt.setString(7, veiculo.getObservacoes_veiculo());
            stmt.setDouble(8, veiculo.getValor_minimo_veiculo());
            stmt.setDouble(9, veiculo.getValor_medio_veiculo());
            stmt.setDouble(10, veiculo.getValor_maximo_veiculo());
            stmt.setInt(11, veiculo.getQuantidade_veiculo());
            stmt.setString(12, veiculo.getTipo_veiculo());
            
            stmt.execute();
            stmt.close();
    }
    
    public List<Veiculo> getListaVeiculo(String nome) throws SQLException{
        //Meotodo para pesquisa veiculos
        String sql = "select * from veiculo where modelo_veiculo like ?";
        
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
            
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                
                veiculo.setId_veiculo(Long.valueOf(rs.getString("id_veiculo")));
                veiculo.setModelo_veiculo(rs.getString("modelo_veiculo"));
                veiculo.setCor_veiculo(rs.getString("cor_veiculo"));
                veiculo.setAno_veiculo(rs.getInt("ano_veiculo"));
                veiculo.setPlaca_veiculo(rs.getString("placa_veiculo"));
                veiculo.setEstado_veiculo(rs.getString("estado_veiculo"));
                veiculo.setMarca_veiculo(rs.getString("marca_veiculo"));
                veiculo.setObservacoes_veiculo(rs.getString("observacoes_veiculo"));
                veiculo.setValor_minimo_veiculo(rs.getDouble("valor_minimo_veiculo"));
                veiculo.setValor_medio_veiculo(rs.getDouble("valor_medio_veiculo"));
                veiculo.setValor_maximo_veiculo(rs.getDouble("valor_maximo_veiculo"));
                veiculo.setQuantidade_veiculo(rs.getInt("quantidade_veiculo"));
                veiculo.setTipo_veiculo(rs.getString("tipo_veiculo"));
                
                listaVeiculo.add(veiculo);     
            }
                rs.close();
                stmt.close();
                return listaVeiculo;       
    }
    
    public List<Veiculo> getListaVeiculoExecao(String nome) throws SQLException{
        //Meotodo para pesquisa veiculos
        String sql = "select * from veiculo where quantidade_veiculo > 0";
        
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            //stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
            
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                
                veiculo.setId_veiculo(Long.valueOf(rs.getString("id_veiculo")));
                veiculo.setModelo_veiculo(rs.getString("modelo_veiculo"));
                veiculo.setCor_veiculo(rs.getString("cor_veiculo"));
                veiculo.setAno_veiculo(rs.getInt("ano_veiculo"));
                veiculo.setPlaca_veiculo(rs.getString("placa_veiculo"));
                veiculo.setEstado_veiculo(rs.getString("estado_veiculo"));
                veiculo.setMarca_veiculo(rs.getString("marca_veiculo"));
                veiculo.setObservacoes_veiculo(rs.getString("observacoes_veiculo"));
                veiculo.setValor_minimo_veiculo(rs.getDouble("valor_minimo_veiculo"));
                veiculo.setValor_medio_veiculo(rs.getDouble("valor_medio_veiculo"));
                veiculo.setValor_maximo_veiculo(rs.getDouble("valor_maximo_veiculo"));
                veiculo.setQuantidade_veiculo(rs.getInt("quantidade_veiculo"));
                veiculo.setTipo_veiculo(rs.getString("tipo_veiculo"));
                
                listaVeiculo.add(veiculo);     
            }
                rs.close();
                stmt.close();
                return listaVeiculo;       
    }
    
    public void alteraVeiculo(Veiculo veiculo) throws SQLException{
        //Metodo para altera veiculos
        String sql = "update veiculo set modelo_veiculo=?, cor_veiculo=?, ano_veiculo=?, placa_veiculo=?, estado_veiculo=?,"
                + "marca_veiculo=?, observacoes_veiculo=?, valor_minimo_veiculo=?, valor_medio_veiculo=?, valor_maximo_veiculo=?,"
                + "quantidade_veiculo=?, tipo_veiculo=? where id_veiculo=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
            stmt.setLong(13, veiculo.getId_veiculo());
            stmt.setString(1, veiculo.getModelo_veiculo());
            stmt.setString(2, veiculo.getCor_veiculo());
            stmt.setInt(3, veiculo.getAno_veiculo());
            stmt.setString(4, veiculo.getPlaca_veiculo());
            stmt.setString(5, veiculo.getEstado_veiculo());
            stmt.setString(6, veiculo.getMarca_veiculo());
            stmt.setString(7, veiculo.getObservacoes_veiculo());
            stmt.setDouble(8, veiculo.getValor_minimo_veiculo()); 
            stmt.setDouble(9, veiculo.getValor_medio_veiculo());
            stmt.setDouble(10, veiculo.getValor_maximo_veiculo());
            stmt.setDouble(11, veiculo.getQuantidade_veiculo());
            stmt.setString(12, veiculo.getTipo_veiculo());
            
            stmt.execute();
            stmt.close();
    }
    
    /*
    public void excluiVeiculo(Veiculo veiculo) throws SQLException{
        //Metodo para excluir veiculo
        String sql = "delete from veiculo where id_veiculo=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setLong(1, veiculo.getId_veiculo());
        
        stmt.execute();
        stmt.close();
    }
    */
}
