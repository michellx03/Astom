package Visao;

import controle.ConexaoBD;
import controle.PedidoFinanciadoDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.PedidoFinanciado;
import modelo.Veiculo;

public class VendasFinanciadas extends javax.swing.JFrame {
    
    private Connection conexao;//Cria metodo de conexao
    
    //Construtor
    public VendasFinanciadas() throws SQLException {
        initComponents();
        desabilitaCamposCliente();
        desabilitaCamposVeiculo();
        desabilitaTopo();
        this.setLocationRelativeTo(null);
        this.conexao = ConexaoBD.getConexao();
        setIcon();
    }
    
    private void setIcon(){
        //Seta icone no sistema
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
        jLabelIdFuncionario.setVisible(false);
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
            
            PedidoFinanciado pedido = new PedidoFinanciado();
            
            //pedido.setId_pedido(Long.valueOf(jTextFieldIdPedido.getText()));
            pedido.setId_cliente(Integer.parseInt(jTextFieldIdCliente.getText()));
            //pedido.setId_funcionario(Integer.parseInt(jTextFieldIdFuncionario.getText()));
            pedido.setId_veiculo(Integer.parseInt(jTextFieldIdVeiculo.getText()));
            pedido.setValor_financiado(Double.parseDouble(jTextFieldValorFinanciamento.getText()));
            pedido.setTotal_financiado(Double.parseDouble(jTextFieldValorFinanciamento.getText()));
            pedido.setParcelas(Integer.parseInt(jFormattedTextFieldParcelas.getText()));
            pedido.setVendedor_financiado(jTextFieldVendedorFinanciamento.getText());
            pedido.setComissao_financiado(Integer.parseInt(jTextFieldComissaoFinanciado.getText()));
            pedido.setQuantidade_veiculo(Integer.parseInt(jTextFieldQuantidade.getText()));
            pedido.setMes_venda_financiada(jFormattedTextFieldMesVenda.getText());
            
            try {
                PedidoFinanciadoDao dao = new PedidoFinanciadoDao();
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
    }
    
    //Habilita campos
    public void habilitaTopo(){
        jTextFieldIdPedido.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabelNomeUsuario = new javax.swing.JLabel();
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
        jPanelFinanciamento = new javax.swing.JPanel();
        jLabelValorFinanciamento = new javax.swing.JLabel();
        jLabelTotalFinanciado = new javax.swing.JLabel();
        jLabelParcelas = new javax.swing.JLabel();
        jLabelVendedorFinanciamento = new javax.swing.JLabel();
        jTextFieldValorFinanciamento = new javax.swing.JTextField();
        jTextFieldTotalFinanciado = new javax.swing.JTextField();
        jTextFieldVendedorFinanciamento = new javax.swing.JTextField();
        jFormattedTextFieldParcelas = new javax.swing.JFormattedTextField();
        jTextFieldComissaoFinanciado = new javax.swing.JTextField();
        jLabelComissao = new javax.swing.JLabel();
        jLabelDataDaVenda = new javax.swing.JLabel();
        jFormattedTextFieldMesVenda = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonVoltar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabelIdFuncionario = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();

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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabelNomeUsuario.setText("Usuário");

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

        jLabelQuantidade.setText("Quantidade");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
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
                                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelMarca)
                                .addComponent(jLabelPesquisaVeiculo))
                            .addComponent(jLabelTipoVeiculo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        jPanelFinanciamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do pagamento"));

        jLabelValorFinanciamento.setText("Total da venda");

        jLabelTotalFinanciado.setText("Total financiado");

        jLabelParcelas.setText("Parcelas");

        jLabelVendedorFinanciamento.setText("Vendedor");

        jTextFieldValorFinanciamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldValorFinanciamentoKeyPressed(evt);
            }
        });

        jTextFieldTotalFinanciado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTotalFinanciadoKeyPressed(evt);
            }
        });

        jTextFieldVendedorFinanciamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldVendedorFinanciamentoKeyPressed(evt);
            }
        });

        jFormattedTextFieldParcelas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jFormattedTextFieldParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldParcelasKeyPressed(evt);
            }
        });

        jTextFieldComissaoFinanciado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldComissaoFinanciadoKeyPressed(evt);
            }
        });

        jLabelComissao.setText("Comissão");

        jLabelDataDaVenda.setText("Data da venda");

        try {
            jFormattedTextFieldMesVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldMesVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldMesVendaKeyPressed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Mês/Ano");

        javax.swing.GroupLayout jPanelFinanciamentoLayout = new javax.swing.GroupLayout(jPanelFinanciamento);
        jPanelFinanciamento.setLayout(jPanelFinanciamentoLayout);
        jPanelFinanciamentoLayout.setHorizontalGroup(
            jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTotalFinanciado)
                            .addComponent(jLabelValorFinanciamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldValorFinanciamento, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jTextFieldTotalFinanciado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDataDaVenda)
                            .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldMesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))))
                    .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelParcelas)
                            .addComponent(jLabelVendedorFinanciamento)
                            .addComponent(jLabelComissao))
                        .addGap(34, 34, 34)
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldVendedorFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldComissaoFinanciado, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 189, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelFinanciamentoLayout.setVerticalGroup(
            jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorFinanciamento)
                    .addComponent(jTextFieldValorFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalFinanciado)
                    .addComponent(jTextFieldTotalFinanciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelParcelas)
                    .addComponent(jFormattedTextFieldParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVendedorFinanciamento)
                    .addComponent(jTextFieldVendedorFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldComissaoFinanciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelComissao))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                .addComponent(jLabelDataDaVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldMesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/arrow_left.png"))); // NOI18N
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButtonSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jLabelNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelDadosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIdPedido)
                            .addComponent(jTextFieldIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanelDadosCliente, jPanelDadosVeiculo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelIdPedido)
                        .addGap(25, 25, 25))
                    .addComponent(jTextFieldIdPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanelDadosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanelFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanelDadosCliente, jPanelDadosVeiculo});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDetalhesClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalhesClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDetalhesClienteActionPerformed

    private void jButtonPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarClienteActionPerformed
        pesquisaCliente();
        jTextFieldValorFinanciamento.requestFocus();
    }//GEN-LAST:event_jButtonPesquisarClienteActionPerformed

    private void jButtonPesquisarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarVeiculoActionPerformed
        pesquisaVeiculo();
    }//GEN-LAST:event_jButtonPesquisarVeiculoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvaVenda();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new EscolhaDeVenda().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jTextFieldValorFinanciamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorFinanciamentoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldTotalFinanciado.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldValorFinanciamentoKeyPressed

    private void jTextFieldTotalFinanciadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalFinanciadoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldParcelas.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldTotalFinanciadoKeyPressed

    private void jFormattedTextFieldParcelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldParcelasKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldVendedorFinanciamento.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldParcelasKeyPressed

    private void jTextFieldVendedorFinanciamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldVendedorFinanciamentoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldComissaoFinanciado.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldVendedorFinanciamentoKeyPressed

    private void jTextFieldComissaoFinanciadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldComissaoFinanciadoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldMesVenda.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldComissaoFinanciadoKeyPressed

    private void jFormattedTextFieldMesVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldMesVendaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonSalvar.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldMesVendaKeyPressed

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
            java.util.logging.Logger.getLogger(VendasFinanciadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendasFinanciadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendasFinanciadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendasFinanciadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VendasFinanciadas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VendasFinanciadas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalhesCliente;
    private javax.swing.JButton jButtonDetalhesVeiculo;
    private javax.swing.JButton jButtonPesquisarCliente;
    private javax.swing.JButton jButtonPesquisarVeiculo;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox jComboBoxMarca;
    private javax.swing.JComboBox jComboBoxTipoVeiculo;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldMesVenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldParcelas;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelResidencial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelComissao;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelDataDaVenda;
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
    private javax.swing.JLabel jLabelParcelas;
    private javax.swing.JLabel jLabelPesquisaCliente;
    private javax.swing.JLabel jLabelPesquisaVeiculo;
    private javax.swing.JLabel jLabelPlaca;
    private javax.swing.JLabel jLabelQuantidade;
    private javax.swing.JLabel jLabelTelCelular;
    private javax.swing.JLabel jLabelTelResidencial;
    private javax.swing.JLabel jLabelTipoVeiculo;
    private javax.swing.JLabel jLabelTotalFinanciado;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelValorFinanciamento;
    private javax.swing.JLabel jLabelVendedorFinanciamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDadosCliente;
    private javax.swing.JPanel jPanelDadosVeiculo;
    private javax.swing.JPanel jPanelFinanciamento;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldComissaoFinanciado;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldIdPedido;
    private javax.swing.JTextField jTextFieldIdVeiculo;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldTotalFinanciado;
    private javax.swing.JTextField jTextFieldValorFinanciamento;
    private javax.swing.JTextField jTextFieldVendedorFinanciamento;
    // End of variables declaration//GEN-END:variables
}
