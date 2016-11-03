package Visao;

import controle.PedidoDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Pedido;

public class Vendas extends javax.swing.JFrame {
    
    //Construtor
    public Vendas() {
        initComponents();
        desabilitaCamposCliente();
        desabilitaCamposVeiculo();
        desabilitaTopo();
        desabilitaFinanciamento();
        desabilitaAVista();
        this.setLocationRelativeTo(null);
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
    }

    //Habilita campos
    public void habilitaCamposveiculo() {
        jTextFieldIdVeiculo.setEditable(true);
        jTextFieldModelo.setEditable(true);
        jTextFieldAno.setEditable(true);
        jTextFieldEstado.setEditable(true);
        jTextFieldPlaca.setEditable(true);
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
        
        //Seta os valores nos campos
        jTextFieldIdVeiculo.setText(String.valueOf(id));
        jTextFieldModelo.setText(modelo);
        jTextFieldAno.setText(String.valueOf(ano));
        jTextFieldPlaca.setText(placa);
        jTextFieldEstado.setText(estado);
        jComboBoxMarca.setSelectedItem(marca);
        jComboBoxTipoVeiculo.setSelectedItem(tipo);
    }
    
    //---------------------------------------------------------------------------------------------------------
    //                                          VENDA
    
    //Salva Venda
    public void salvaVenda() {
        Pedido pedido = new Pedido();

        //pedido.setId_pedido(Long.valueOf(jTextFieldIdPedido.getText()));
        pedido.setId_cliente(Integer.parseInt(jTextFieldIdCliente.getText()));
        pedido.setId_funcionario(Integer.parseInt(jTextFieldIdFuncionario.getText()));
        pedido.setId_veiculo(Integer.parseInt(jTextFieldIdVeiculo.getText()));      
        pedido.setValor_avista(Double.parseDouble(jTextFieldValorAVista.getText()));
        pedido.setDesconto(Double.parseDouble(jTextFieldDesconto.getText()));
        pedido.setVendedor_avista(jTextFieldVendedorAVista.getText());
        pedido.setValor_financiado(Double.parseDouble(jTextFieldValorFinanciamento.getText()));
        pedido.setTotal_financiado(Double.parseDouble(jTextFieldValorFinanciamento.getText()));
        pedido.setParcelas(jFormattedTextFieldParcelas.getText());
        pedido.setVendedor_financiado(jTextFieldVendedorFinanciamento.getName());

        try {
            PedidoDao dao = new PedidoDao();
            dao.adicionaPedido(pedido);
            JOptionPane.showMessageDialog(null, "Venda cadastrado com sucesso!");
            desabilitaCamposVeiculo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda! = " + ex);
        }
    }
    
    //Desabilita campos
    public void desabilitaTopo(){
        jTextFieldIdPedido.setEditable(false);
        jTextFieldUsuario.setEditable(false);
        jTextFieldIdFuncionario.setEditable(false);
    }
    
    //Habilita campos
    public void habilitaTopo(){
        jTextFieldIdPedido.setEditable(true);
        jTextFieldUsuario.setEditable(true);
        jTextFieldIdFuncionario.setEditable(true);
    }
    
    //Desabilita campos
    public void desabilitaFinanciamento(){
        jTextFieldValorFinanciamento.setEditable(false);
        jTextFieldTotalFinanciado.setEditable(false);
        jFormattedTextFieldParcelas.setEditable(false);
        jTextFieldVendedorFinanciamento.setEditable(false);
    }
    
    //Habilita campos
    public void habilitaFinanciamento(){
        jTextFieldValorFinanciamento.setEditable(true);
        jTextFieldTotalFinanciado.setEditable(true);
        jFormattedTextFieldParcelas.setEditable(true);
        jTextFieldVendedorFinanciamento.setEditable(true);
    }
    
    public void desabilitaAVista(){
        jTextFieldValorAVista.setEditable(false);
        jTextFieldDesconto.setEditable(false);
        jTextFieldVendedorAVista.setEditable(false);
    }
    
    public void habilitaAVista(){
        jTextFieldValorAVista.setEditable(true);
        jTextFieldDesconto.setEditable(true);
        jTextFieldVendedorAVista.setEditable(true);
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
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
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
        jRadioButtonAVista = new javax.swing.JRadioButton();
        jRadioButtonFinanciamento = new javax.swing.JRadioButton();
        jPanelFinanciamento = new javax.swing.JPanel();
        jLabelValorFinanciamento = new javax.swing.JLabel();
        jLabelTotalFinanciado = new javax.swing.JLabel();
        jLabelParcelas = new javax.swing.JLabel();
        jLabelVendedorFinanciamento = new javax.swing.JLabel();
        jTextFieldValorFinanciamento = new javax.swing.JTextField();
        jTextFieldTotalFinanciado = new javax.swing.JTextField();
        jTextFieldVendedorFinanciamento = new javax.swing.JTextField();
        jFormattedTextFieldParcelas = new javax.swing.JFormattedTextField();
        jPanelAVista = new javax.swing.JPanel();
        jLabelValorAVista = new javax.swing.JLabel();
        jLabelDesconto = new javax.swing.JLabel();
        jLabelVendedorAVista = new javax.swing.JLabel();
        jTextFieldValorAVista = new javax.swing.JTextField();
        jTextFieldDesconto = new javax.swing.JTextField();
        jTextFieldVendedorAVista = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jTextFieldIdFuncionario = new javax.swing.JTextField();

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

        jLabelEndereco.setText("Endereco");

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

        jLabelUsuario.setText("Usuario");

        jPanelDadosVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do veiuclo"));

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

        jComboBoxMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Wolskvage", "Fiat", "Huyndai", "Honda" }));

        jLabelTipoVeiculo.setText("Tipo do veiculo");

        jComboBoxTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Carro", "Moto" }));

        jLabelIdVeiculo.setText("Id");

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
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jComboBoxTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addComponent(jLabelMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTipoVeiculo))
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addComponent(jLabelIdVeiculo)
                                .addGap(59, 59, 59)
                                .addComponent(jLabelModelo)))
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabelAno))
                            .addGroup(jPanelDadosVeiculoLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonPesquisarVeiculo)
                                    .addComponent(jLabelPesquisaVeiculo))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(jButtonDetalhesVeiculo)
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
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMarca)
                    .addComponent(jLabelPesquisaVeiculo)
                    .addComponent(jLabelTipoVeiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDetalhesVeiculo)
                    .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonPesquisarVeiculo)
                        .addGroup(jPanelDadosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

        jRadioButtonAVista.setText("A vista");
        jRadioButtonAVista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAVistaActionPerformed(evt);
            }
        });

        jRadioButtonFinanciamento.setText("Financiamento");
        jRadioButtonFinanciamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFinanciamentoActionPerformed(evt);
            }
        });

        jPanelFinanciamento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelValorFinanciamento.setText("Valor");

        jLabelTotalFinanciado.setText("Total finaciado");

        jLabelParcelas.setText("Parcelas");

        jLabelVendedorFinanciamento.setText("Vendedor");

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
                            .addComponent(jTextFieldTotalFinanciado)))
                    .addGroup(jPanelFinanciamentoLayout.createSequentialGroup()
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelParcelas)
                            .addComponent(jLabelVendedorFinanciamento))
                        .addGap(34, 34, 34)
                        .addGroup(jPanelFinanciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldVendedorFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
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
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanelAVista.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelValorAVista.setText("Valor");

        jLabelDesconto.setText("Desconto");

        jLabelVendedorAVista.setText("Vendedor");

        javax.swing.GroupLayout jPanelAVistaLayout = new javax.swing.GroupLayout(jPanelAVista);
        jPanelAVista.setLayout(jPanelAVistaLayout);
        jPanelAVistaLayout.setHorizontalGroup(
            jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAVistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDesconto)
                    .addComponent(jLabelValorAVista)
                    .addComponent(jLabelVendedorAVista))
                .addGap(28, 28, 28)
                .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldVendedorAVista, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addComponent(jTextFieldValorAVista, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(57, Short.MAX_VALUE))
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
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disk.png"))); // NOI18N
        jButtonSalvar.setText("Salvar venda");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/arrow_left.png"))); // NOI18N
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonAVista))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonFinanciamento)
                                .addGap(167, 167, 167))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(44, 44, 44))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelIdPedido)
                                        .addGap(26, 26, 26))
                                    .addComponent(jTextFieldIdPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelDadosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanelAVista, jPanelFinanciamento});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelUsuario)
                            .addComponent(jTextFieldIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelIdPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDadosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonAVista)
                    .addComponent(jRadioButtonFinanciamento))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelFinanciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSalvar)
                        .addGap(33, 33, 33)
                        .addComponent(jButtonVoltar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarClienteActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jButtonPesquisarClienteActionPerformed

    private void jRadioButtonFinanciamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFinanciamentoActionPerformed
        habilitaFinanciamento();
    }//GEN-LAST:event_jRadioButtonFinanciamentoActionPerformed

    private void jButtonDetalhesClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalhesClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDetalhesClienteActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jRadioButtonAVistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAVistaActionPerformed
        habilitaAVista();
    }//GEN-LAST:event_jRadioButtonAVistaActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvaVenda();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonPesquisarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarVeiculoActionPerformed
        pesquisaVeiculo();
    }//GEN-LAST:event_jButtonPesquisarVeiculoActionPerformed

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
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalhesCliente;
    private javax.swing.JButton jButtonDetalhesVeiculo;
    private javax.swing.JButton jButtonPesquisarCliente;
    private javax.swing.JButton jButtonPesquisarVeiculo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox jComboBoxMarca;
    private javax.swing.JComboBox jComboBoxTipoVeiculo;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldParcelas;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelResidencial;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelDesconto;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelIdPedido;
    private javax.swing.JLabel jLabelIdVeiculo;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelParcelas;
    private javax.swing.JLabel jLabelPesquisaCliente;
    private javax.swing.JLabel jLabelPesquisaVeiculo;
    private javax.swing.JLabel jLabelPlaca;
    private javax.swing.JLabel jLabelTelCelular;
    private javax.swing.JLabel jLabelTelResidencial;
    private javax.swing.JLabel jLabelTipoVeiculo;
    private javax.swing.JLabel jLabelTotalFinanciado;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelValorAVista;
    private javax.swing.JLabel jLabelValorFinanciamento;
    private javax.swing.JLabel jLabelVendedorAVista;
    private javax.swing.JLabel jLabelVendedorFinanciamento;
    private javax.swing.JPanel jPanelAVista;
    private javax.swing.JPanel jPanelDadosCliente;
    private javax.swing.JPanel jPanelDadosVeiculo;
    private javax.swing.JPanel jPanelFinanciamento;
    private javax.swing.JRadioButton jRadioButtonAVista;
    private javax.swing.JRadioButton jRadioButtonFinanciamento;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldIdFuncionario;
    private javax.swing.JTextField jTextFieldIdPedido;
    private javax.swing.JTextField jTextFieldIdVeiculo;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldTotalFinanciado;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JTextField jTextFieldValorAVista;
    private javax.swing.JTextField jTextFieldValorFinanciamento;
    private javax.swing.JTextField jTextFieldVendedorAVista;
    private javax.swing.JTextField jTextFieldVendedorFinanciamento;
    // End of variables declaration//GEN-END:variables
}
