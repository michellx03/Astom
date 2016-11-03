package Visao;

import controle.ConexaoBD;
import controle.PedidoAVistaDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.PedidoAVista;
import modelo.Veiculo;

public class VendasAVista extends javax.swing.JFrame {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Construtor
    public VendasAVista() throws SQLException {
        initComponents();
        desabilitaCamposCliente();
        desabilitaCamposVeiculo();
        desabilitaTopo();
        this.setLocationRelativeTo(null);
        this.conexao = ConexaoBD.getConexao();
        setIcon();
    }
    
    private void setIcon(){
        //Seleciona icone do sistema
        URL url = this.getClass().getResource("../imagens/ParkingLot.png"); 
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url); 
        this.setIconImage(imagemTitulo);
    }
    
    public void exportaEscolha(Funcionario funcionario){
        //Da tela principal importa para este metodo onde e setado os valores nos campos
        Long id = funcionario.getId_funcionario();
        String usuario = funcionario.getUsuario();
        
        jLabelIdFuncionario.setText(String.valueOf(id));
        jLabelUsuario.setText(usuario);
        //jLabelIdFuncionario.setVisible(false);
    }
    
    public void pesquisaCliente(){
        //Cria as variaveis e seta os valores dos get and set
        VendasPesquisaCliente pesquisa = new VendasPesquisaCliente(this,true);
        pesquisa.setVisible(true);
        Long id = pesquisa.getId_cliente();
        String nome = pesquisa.getNome_cliente();
        String cpf = pesquisa.getCpf_cliente();
        String endereco = pesquisa.getEndereco_cliente();
        String telefoneCelular = pesquisa.getTelefone_celular_cliente();
        String telefoneResidencial = pesquisa.getTelefone_residencial_cliente();
        
        //Seta os valores nos campos
        jTextFieldIdCliente.setText(String.valueOf(id));
        jTextFieldNome.setText(nome);
        jFormattedTextFieldCpf.setText(cpf);
        jTextFieldEndereco.setText(endereco);
        jFormattedTextFieldTelCelular.setText(telefoneCelular);
        jFormattedTextFieldTelResidencial.setText(telefoneResidencial);
    }
    
        public List<Cliente> getListaCliente(String nome) throws SQLException{
        //Meotodo para pesquisa cliente
        String sql = "select * from cliente where nome_cliente like'"+jTextFieldNome+"'";
        
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
                
                listaCliente.add(cliente); 
                DetalhesCliente t = new DetalhesCliente();
                t.setVisible(true);
            }
                rs.close();
                stmt.close();
                return listaCliente;
                
    
    }    
            
    //Desabilitar campos
    public void desabilitaCamposCliente() {
        jTextFieldIdCliente.setEditable(false);
        jTextFieldNome.setEditable(false);
        jFormattedTextFieldCpf.setEditable(false);
        jTextFieldEndereco.setEditable(false);
        jFormattedTextFieldTelResidencial.setEditable(false);
        jFormattedTextFieldTelCelular.setEditable(false);
    }

    //Habilitar campos
    public void habilitaCamposCliente() {
        jTextFieldIdCliente.setEditable(true);
        jTextFieldNome.setEditable(true);
        jFormattedTextFieldCpf.setEditable(true);
        jTextFieldEndereco.setEditable(true);
        jFormattedTextFieldTelResidencial.setEditable(true);
        jFormattedTextFieldTelCelular.setEditable(true);
    }
    
    //---------------------------------------------------------------------------------------------------------
    //                                          VEICULO
    
    //Desabilita campos
    public void desabilitaCamposVeiculo() {
        jTextFieldIdVeiculo.setEditable(false);
        jTextFieldModelo.setEditable(false);
        jTextFieldAno.setEditable(false);
        jTextFieldEstado.setEditable(false);
        jTextFieldPlaca.setEditable(false);
        jTextFieldQuantidade.setEditable(false);
    }

    //Habilita campos
    public void habilitaCamposveiculo() {
        jTextFieldIdVeiculo.setEditable(true);
        jTextFieldModelo.setEditable(true);
        jTextFieldAno.setEditable(true);
        jTextFieldEstado.setEditable(true);
        jTextFieldPlaca.setEditable(true);
        jTextFieldQuantidade.setEditable(true);
    }
    
    public void pesquisaVeiculo(){
        //Cria as variaveis e seta os valores dos get and set
        VendasPesquisaVeiculo pesquisa = new VendasPesquisaVeiculo(this,true);
        pesquisa.setVisible(true);
        Long id = pesquisa.getId_veiculo();
        String modelo = pesquisa.getModelo_veiculo();
        int ano = pesquisa.getAno_veiculo();
        String placa = pesquisa.getPlaca_veiculo();
        String estado = pesquisa.getEstado_veiculo();
        String marca = pesquisa.getMarca_veiculo();
        String tipo = pesquisa.getTipo_veiculo();
        int quantidade = pesquisa.getQuantidade_veiculo();
        
        //Seta os valores nos campos
        jTextFieldIdVeiculo.setText(String.valueOf(id));
        jTextFieldModelo.setText(modelo);
        jTextFieldAno.setText(String.valueOf(ano));
        jTextFieldPlaca.setText(placa);
        jTextFieldEstado.setText(estado);
        jComboBoxMarca.setSelectedItem(marca);
        jComboBoxTipoVeiculo.setSelectedItem(tipo);
        jTextFieldQuantidade.setText(String.valueOf(quantidade));
    }
    
    //---------------------------------------------------------------------------------------------------------
    //                                          VENDA
    
    public void reduzQuantidade(Veiculo veiculo) throws SQLException{
        String sql = "UPDATE veiculo SET quantidade_veiculo='0' WHERE placa_veiculo='"+jTextFieldPlaca.getText()+"'";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.execute();
        stmt.close();      
    }
    
    //Salva Venda
    public void salvaVenda() {
        PedidoAVista pedido = new PedidoAVista();

        pedido.setId_cliente(Integer.parseInt(jTextFieldIdCliente.getText()));
        //pedido.setId_funcionario(Integer.parseInt(jTextFieldIdFuncionario.getText()));
        pedido.setId_veiculo(Integer.parseInt(jTextFieldIdVeiculo.getText()));      
        pedido.setValor_avista(Double.parseDouble(jTextFieldValorAVista.getText()));
        pedido.setDesconto(Double.parseDouble(jTextFieldDesconto.getText()));
        pedido.setVendedor_avista(jTextFieldVendedorAVista.getText());
        pedido.setComissao_vendedor_avista(Integer.parseInt(jTextFieldComissaoAvista.getText()));
        pedido.setQuantidade_veiculo(Integer.parseInt(jTextFieldQuantidade.getText()));
        pedido.setMes_venda_avista(jFormattedTextFieldDataVenda.getText());

        try {
            PedidoAVistaDao dao = new PedidoAVistaDao();
            dao.adicionaPedido(pedido);
            reduzQuantidade(null);
            JOptionPane.showMessageDialog(null, "Venda cadastrado com sucesso!");
            desabilitaCamposVeiculo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda! = " + ex);
        }
    }
    
    //Desabilita campos
    public void desabilitaTopo(){
        jTextFieldIdPedido.setEditable(false); 
        jLabelIdFuncionario.setVisible(false);
    }
    
    //Habilita campos
    public void habilitaTopo(){
        jTextFieldIdPedido.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabelIdPedido = new javax.swing.JLabel();
        jPanelDadosCliente = new javax.swing.JPanel();
        jButtonDetalhesCliente = new javax.swing.JButton();
        jLabelPesquisaCliente = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelCpf = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabelTelCelular = new javax.swing.JLabel();
        jLabelTelResidencial = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldEndereco = new javax.swing.JTextField();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelCelular = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelResidencial = new javax.swing.JFormattedTextField();
        jButtonPesquisarCliente = new javax.swing.JButton();
        jTextFieldIdCliente = new javax.swing.JTextField();
        jLabelIdCliente = new javax.swing.JLabel();
        jTextFieldIdPedido = new javax.swing.JTextField();
        jPanelDadosVeiculo = new javax.swing.JPanel();
        jButtonDetalhesVeiculo = new javax.swing.JButton();
        jLabelModelo = new javax.swing.JLabel();
        jLabelAno = new javax.swing.JLabel();
        jLabelPlaca = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMarca = new javax.swing.JLabel();
        jLabelPesquisaVeiculo = new javax.swing.JLabel();
        jButtonPesquisarVeiculo = new javax.swing.JButton();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldPlaca = new javax.swing.JTextField();
        jTextFieldAno = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        jComboBoxMarca = new javax.swing.JComboBox();
        jLabelTipoVeiculo = new javax.swing.JLabel();
        jComboBoxTipoVeiculo = new javax.swing.JComboBox();
        jLabelIdVeiculo = new javax.swing.JLabel();
        jTextFieldIdVeiculo = new javax.swing.JTextField();
        jLabelQuantidade = new javax.swing.JLabel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jPanelAVista = new javax.swing.JPanel();
        jLabelValorAVista = new javax.swing.JLabel();
        jLabelDesconto = new javax.swing.JLabel();
        jLabelVendedorAVista = new javax.swing.JLabel();
        jTextFieldValorAVista = new javax.swing.JTextField();
        jTextFieldDesconto = new javax.swing.JTextField();
        jTextFieldVendedorAVista = new javax.swing.JTextField();
        jLabelComissao = new javax.swing.JLabel();
        jTextFieldComissaoAvista = new javax.swing.JTextField();
        jLabelDataVenda = new javax.swing.JLabel();
        jFormattedTextFieldDataVenda = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabelNomeUsuario = new javax.swing.JLabel();
        jLabelIdFuncionario = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();

        jLabel10.setText("jLabel10");

        jTextField10.setText("jTextField10");

        jLabel13.setText("jLabel13");

        jLabel18.setText("jLabel18");

        jTextField16.setText("jTextField16");

        jFormattedTextField4.setText("jFormattedTextField4");

        jLabel24.setText("jLabel24");

        jLabel26.setText("jLabel26");

        jTextField19.setText("jTextField19");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");

        jLabelIdPedido.setText("Id pedido");

        jPanelDadosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados cliente"));

        jButtonDetalhesCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/book_open.png"))); // NOI18N
        jButtonDetalhesCliente.setText("Detalhes");
        jButtonDetalhesCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetalhesClienteActionPerformed(evt);
            }
        });

        jLabelPesquisaCliente.setText("Pesquisar");

        jLabelNome.setText("Nome");

        jLabelCpf.setText("CPF");

        jLabelEndereco.setText("Endereço");

        jLabelTelCelular.setText("Telefone celular");

        jLabelTelResidencial.setText("Telefone residencial");

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldTelCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldTelResidencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButtonPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
        jButtonPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarClienteActionPerformed(evt);
            }
        });

        jLabelIdCliente.setText("Id");

        javax.swing.GroupLayout jPanelDadosClienteLayout = new javax.swing.GroupLayout(jPanelDadosCliente);
        jPanelDadosCliente.setLayout(jPanelDadosClienteLayout);
        jPanelDadosClienteLayout.setHorizontalGroup(
            jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosClienteLayout.createSequentialGroup()
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelCelular))
                        .addGap(61, 61, 61)
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDadosClienteLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldTelResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonPesquisarCliente))
                            .addGroup(jPanelDadosClienteLayout.createSequentialGroup()
                                .addComponent(jLabelTelResidencial)
                                .addGap(69, 69, 69)
                                .addComponent(jLabelPesquisaCliente)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDetalhesCliente))
                    .addGroup(jPanelDadosClienteLayout.createSequentialGroup()
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdCliente)
                            .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCpf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEndereco))))
                .addContainerGap())
        );

        jPanelDadosClienteLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextFieldTelCelular, jFormattedTextFieldTelResidencial});

        jPanelDadosClienteLayout.setVerticalGroup(
            jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jLabelCpf)
                    .addComponent(jLabelEndereco)
                    .addComponent(jLabelIdCliente))
                .addGap(8, 8, 8)
                .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelCelular)
                    .addComponent(jLabelTelResidencial)
                    .addComponent(jLabelPesquisaCliente))
                .addGap(8, 8, 8)
                .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonDetalhesCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldTelResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonPesquisarCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDadosVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do veículo"));

        jButtonDetalhesVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/book_open.png"))); // NOI18N
        jButtonDetalhesVeiculo.setText("Detalhes");

        jLabelModelo.setText("Modelo");

        jLabelAno.setText("Ano");

        jLabelPlaca.setText("Placa");

        jLabelEstado.setText("Cidade/Estado");

        jLabelMarca.setText("Marca");

        jLabelPesquisaVeiculo.setText("Pesquisar");

        jButtonPesquisarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
        jButtonPesquisarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarVeiculoActionPerformed(evt);
            }
        });

        jComboBoxMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione      ", "Wolskvagem     ", "Fiat           ", "Huyndai        ", "Honda          ", "Sundown        ", "Jeep           " }));

        jLabelTipoVeiculo.setText("Tipo do veículo");

        jComboBoxTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione      ", "Carro          ", "Moto           " }));

        jLabelIdVeiculo.setText("Id");

        jLabelQuantidade.setText("Qunatidade");

        javax.swing.GroupLayout jPanelDadosVeiculoLayout = new javax.swing.GroupLayout(jPanelDadosVeiculo);
        jPanelDadosVeiculo.setLayout(jPanelDadosVeiculoLayout);
        jPanelDadosVeiculoLayout.setHorizontalGroup(
            jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addComponent(jTextFieldIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addComponent(jLabelIdVeiculo)
                                .addGap(59, 59, 59)
                                .addComponent(jLabelModelo))
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelMarca))
                                .addGap(32, 32, 32)
                                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTipoVeiculo)
                                    .addComponent(jComboBoxTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabelAno))
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelQuantidade)
                                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPlaca))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEstado))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addComponent(jButtonPesquisarVeiculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDetalhesVeiculo)
                        .addContainerGap())
                    .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addComponent(jLabelPesquisaVeiculo)
                        .addContainerGap())))
        );
        jPanelDadosVeiculoLayout.setVerticalGroup(
            jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEstado)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelIdVeiculo)
                        .addComponent(jLabelModelo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAno)
                        .addComponent(jLabelPlaca)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPesquisaVeiculo)
                            .addComponent(jLabelMarca)
                            .addComponent(jLabelTipoVeiculo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDetalhesVeiculo)
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonPesquisarVeiculo)))
                    .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                        .addComponent(jLabelQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        jPanelAVista.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do pagamento"));

        jLabelValorAVista.setText("Valor");

        jLabelDesconto.setText("Desconto");

        jLabelVendedorAVista.setText("Vendedor");

        jTextFieldValorAVista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldValorAVistaKeyPressed(evt);
            }
        });

        jTextFieldDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDescontoKeyPressed(evt);
            }
        });

        jTextFieldVendedorAVista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldVendedorAVistaKeyPressed(evt);
            }
        });

        jLabelComissao.setText("Comissão");

        jTextFieldComissaoAvista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldComissaoAvistaKeyPressed(evt);
            }
        });

        jLabelDataVenda.setText("Data da venda");

        try {
            jFormattedTextFieldDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDataVendaKeyPressed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Mês/Ano");

        javax.swing.GroupLayout jPanelAVistaLayout = new javax.swing.GroupLayout(jPanelAVista);
        jPanelAVista.setLayout(jPanelAVistaLayout);
        jPanelAVistaLayout.setHorizontalGroup(
            jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAVistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDesconto)
                    .addComponent(jLabelValorAVista)
                    .addComponent(jLabelVendedorAVista)
                    .addComponent(jLabelComissao))
                .addGap(28, 28, 28)
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAVistaLayout.createSequentialGroup()
                        .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldVendedorAVista, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldComissaoAvista, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanelAVistaLayout.createSequentialGroup()
                        .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jTextFieldValorAVista, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDataVenda)
                            .addGroup(jPanelAVistaLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGap(8, 8, 8))))
        );
        jPanelAVistaLayout.setVerticalGroup(
            jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAVistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorAVista)
                    .addComponent(jTextFieldValorAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDesconto)
                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVendedorAVista)
                    .addComponent(jTextFieldVendedorAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelComissao)
                    .addComponent(jTextFieldComissaoAvista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanelAVistaLayout.createSequentialGroup()
                .addComponent(jLabelDataVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disk.png"))); // NOI18N
        jButtonSalvar.setText("Salvar venda");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jButtonSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonSalvarKeyPressed(evt);
            }
        });

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/door_out.png"))); // NOI18N
        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/arrow_left.png"))); // NOI18N
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelNomeUsuario.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabelNomeUsuario)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelIdPedido)
                            .addGap(26, 26, 26))
                        .addComponent(jTextFieldIdPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelDadosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIdPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jTextFieldIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDadosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeUsuario)
                            .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanelAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarClienteActionPerformed
        pesquisaCliente();
        jTextFieldValorAVista.requestFocus();
    }//GEN-LAST:event_jButtonPesquisarClienteActionPerformed

    private void jButtonDetalhesClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalhesClienteActionPerformed
        //detalhesCliente(null);
    }//GEN-LAST:event_jButtonDetalhesClienteActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvaVenda();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonPesquisarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarVeiculoActionPerformed
        pesquisaVeiculo();
    }//GEN-LAST:event_jButtonPesquisarVeiculoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new EscolhaDeVenda().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldValorAVistaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorAVistaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldDesconto.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldValorAVistaKeyPressed

    private void jTextFieldDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescontoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldVendedorAVista.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldDescontoKeyPressed

    private void jTextFieldVendedorAVistaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldVendedorAVistaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldComissaoAvista.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldVendedorAVistaKeyPressed

    private void jTextFieldComissaoAvistaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldComissaoAvistaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldDataVenda.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldComissaoAvistaKeyPressed

    private void jFormattedTextFieldDataVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataVendaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonSalvar.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldDataVendaKeyPressed

    private void jButtonSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSalvarKeyPressed
       salvaVenda();
    }//GEN-LAST:event_jButtonSalvarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendasAVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendasAVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendasAVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendasAVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VendasAVista().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VendasAVista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDetalhesCliente;
    private javax.swing.JButton jButtonDetalhesVeiculo;
    private javax.swing.JButton jButtonPesquisarCliente;
    private javax.swing.JButton jButtonPesquisarVeiculo;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxMarca;
    private javax.swing.JComboBox jComboBoxTipoVeiculo;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataVenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelResidencial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelComissao;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelDataVenda;
    private javax.swing.JLabel jLabelDesconto;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelIdFuncionario;
    private javax.swing.JLabel jLabelIdPedido;
    private javax.swing.JLabel jLabelIdVeiculo;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelPesquisaCliente;
    private javax.swing.JLabel jLabelPesquisaVeiculo;
    private javax.swing.JLabel jLabelPlaca;
    private javax.swing.JLabel jLabelQuantidade;
    private javax.swing.JLabel jLabelTelCelular;
    private javax.swing.JLabel jLabelTelResidencial;
    private javax.swing.JLabel jLabelTipoVeiculo;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelValorAVista;
    private javax.swing.JLabel jLabelVendedorAVista;
    private javax.swing.JPanel jPanelAVista;
    private javax.swing.JPanel jPanelDadosCliente;
    private javax.swing.JPanel jPanelDadosVeiculo;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldComissaoAvista;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldIdPedido;
    private javax.swing.JTextField jTextFieldIdVeiculo;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldValorAVista;
    private javax.swing.JTextField jTextFieldVendedorAVista;
    // End of variables declaration//GEN-END:variables
}
