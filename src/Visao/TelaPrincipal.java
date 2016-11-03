package Visao;

import controle.ConexaoBD;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.Funcionario;

//import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipal extends javax.swing.JFrame {
    
    public Long id_funcionario;
    public String usuario;

    public Long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
//--------------------------------------------------------------------------------------------------------------    

    private Connection conexao;//Cria metodo de conexao
    public Statement stm;
    public ResultSet rs;
    
    
    //Construtor
    public TelaPrincipal() throws SQLException {
        initComponents();
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
    
    public void executasql(String sql){
             //Executa as sql de comando no banco de dados
        try {
            stm = conexao.createStatement( rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao executar o comando SQL!" + ex);
        }
    } 
    
    public void exportaLoguin(Funcionario funcionario){ 
        //Do loguin exporta dados para este metodo onde e setado o valor nos campos
        Long id = funcionario.getId_funcionario();
        String usuario = funcionario.getUsuario();
        //JOptionPane.showMessageDialog(rootPane, "Usuario: "+usuario+" Id :"+id+"");
        jLabelId.setText(String.valueOf(id));
        jLabelUsuario.setText(usuario);  
        jLabelId.setVisible(false);
    }
    
    public void obtemUsuarioParaCadVeiculo() throws SQLException{
        //Obtem usuario e id para setar no Frame de cadastro de veiculo
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(Long.valueOf(jLabelId.getText()));
        funcionario.setUsuario(jLabelUsuario.getText());
        
        CadastroDeVeiculos veiculo = new CadastroDeVeiculos();
        veiculo.exportaTelaPrincipal(funcionario);
        veiculo.setVisible(true);
    }
    
    public void obtemUsuarioParaCadFuncionario(){
        //Obtem usuario e id para setar no Frame de cadastro de veiculo
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(Long.valueOf(jLabelId.getText()));
        funcionario.setUsuario(jLabelUsuario.getText());
        
        CadastroFuncionario cad = new CadastroFuncionario();
        cad.exportaTelaPrincipal(funcionario);
        cad.setVisible(true);
    }
    
    public void obtemUsuarioParaCadCliente(){
        //Obtem usuario e id para setar no Frame de cadastro de veiculo
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(Long.valueOf(jLabelId.getText()));
        funcionario.setUsuario(jLabelUsuario.getText());
        
        CadastroCliente cliente = new CadastroCliente();
        cliente.exportaTelaPrincipal(funcionario);
        cliente.setVisible(true);
    }
    
    public void obtemUsuarioParaEscolhaVenda(){
        //Obtem usuario e id para setar no Frame de cadastro de veiculo
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(Long.valueOf(jLabelId.getText()));
        funcionario.setUsuario(jLabelUsuario.getText());
        
        EscolhaDeVenda venda = new EscolhaDeVenda();
        venda.exportaTelaPrincipal(funcionario);
        venda.setVisible(true);
    }
    
    public void pesquisaRelatorioFuncionario() throws SQLException{
        try {
        executasql("select * from funcionario");
        //rs.first();
        JRResultSetDataSource relatResul = new JRResultSetDataSource(rs);
        JasperPrint jpPrint = JasperFillManager.fillReport("src/Relatorios/Funcionario.jasper", new HashMap(), relatResul);
        
        //JasperViewer.viewReport(jpPrint);
        
        JasperViewer  jv = new JasperViewer(jpPrint, false);
        jv.setVisible(true);
        jv.toFront();
        
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao criar relatorio! = " + ex);
        }        
    }
    
    public void pesquisaRelatorioPedidoAvistaMes() throws SQLException{
        try {
        executasql("SELECT\n" +
"     pedido_avista.\"valor_avista\" AS valor_avista,\n" +
"     pedido_avista.\"desconto\" AS desconto,\n" +
"     pedido_avista.\"vendedor_avista\" AS vendedor,\n" +
"     pedido_avista.\"mes_venda_avista\" AS mes_venda,\n" +
"     cliente.\"nome_cliente\" AS nome_cliente,\n" +
"     veiculo.\"modelo_veiculo\" AS modelo,\n" +
"     veiculo.\"ano_veiculo\" AS ano,\n" +
"     veiculo.\"placa_veiculo\" AS placa,\n" +
"     veiculo.\"tipo_veiculo\" AS tipo\n" +
"FROM\n" +
"     \"public\".\"cliente\" cliente INNER JOIN \"public\".\"pedido_avista\" pedido_avista ON cliente.\"id_cliente\" = pedido_avista.\"id_cliente\"\n" +
"     INNER JOIN \"public\".\"veiculo\" veiculo ON pedido_avista.\"id_veiculo\" = veiculo.\"id_veiculo\"");
        //rs.first();
        JRResultSetDataSource relatResul = new JRResultSetDataSource(rs);
        JasperPrint jpPrint = JasperFillManager.fillReport("src/Relatorios/PedidoAvistaMes.jasper", new HashMap(), relatResul);
        
        //JasperViewer.viewReport(jpPrint);
        
        JasperViewer  jv = new JasperViewer(jpPrint, false);
        jv.setVisible(true);
        jv.toFront();
        
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao criar relatorio! = " + ex);
        }        
    }
    
    public void pesquisaRelatorioPedidoAvistaVendedor() throws SQLException{
        try {
        executasql("SELECT\n" +
"     pedido_avista.\"valor_avista\" AS valor,\n" +
"     pedido_avista.\"desconto\" AS desconto,\n" +
"     pedido_avista.\"vendedor_avista\" AS vendedor,\n" +
"     pedido_avista.\"mes_venda_avista\" AS mes_venda,\n" +
"     cliente.\"nome_cliente\" AS nome_cliente,\n" +
"     veiculo.\"placa_veiculo\" AS placa,\n" +
"     veiculo.\"tipo_veiculo\" AS tipo,\n" +
"     veiculo.\"modelo_veiculo\" AS modelo,\n" +
"     veiculo.\"ano_veiculo\" AS ano\n" +
"FROM\n" +
"     \"public\".\"cliente\" cliente INNER JOIN \"public\".\"pedido_avista\" pedido_avista ON cliente.\"id_cliente\" = pedido_avista.\"id_cliente\"\n" +
"     INNER JOIN \"public\".\"veiculo\" veiculo ON pedido_avista.\"id_veiculo\" = veiculo.\"id_veiculo\"");
        //rs.first();
        JRResultSetDataSource relatResul = new JRResultSetDataSource(rs);
        JasperPrint jpPrint = JasperFillManager.fillReport("src/Relatorios/PedidoAvistaVendedor.jasper", new HashMap(), relatResul);
        
        //JasperViewer.viewReport(jpPrint);
        
        JasperViewer  jv = new JasperViewer(jpPrint, false);
        jv.setVisible(true);
        jv.toFront();
        
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao criar relatorio! = " + ex);
        }        
    }
    
    public void pesquisaRelatorioPedidoFInanciadoMes() throws SQLException{
        try {
        executasql("SELECT\n" +
"     pedido_financiado.\"valor_financiado\" AS valor_financiado,\n" +
"     pedido_financiado.\"total_financiado\" AS total_financiado,\n" +
"     pedido_financiado.\"parcelas\" AS parcelas,\n" +
"     pedido_financiado.\"vendedor_financiado\" AS vendedor,\n" +
"     pedido_financiado.\"mes_venda_financiada\" AS mes_venda,\n" +
"     cliente.\"nome_cliente\" AS nome_cliente,\n" +
"     veiculo.\"modelo_veiculo\" AS modelo,\n" +
"     veiculo.\"ano_veiculo\" AS ano,\n" +
"     veiculo.\"placa_veiculo\" AS placa,\n" +
"     veiculo.\"tipo_veiculo\" AS tipo\n" +
"FROM\n" +
"     \"public\".\"cliente\" cliente INNER JOIN \"public\".\"pedido_financiado\" pedido_financiado ON cliente.\"id_cliente\" = pedido_financiado.\"id_cliente\"\n" +
"     INNER JOIN \"public\".\"veiculo\" veiculo ON pedido_financiado.\"id_veiculo\" = veiculo.\"id_veiculo\"");
        //rs.first();
        JRResultSetDataSource relatResul = new JRResultSetDataSource(rs);
        JasperPrint jpPrint = JasperFillManager.fillReport("src/Relatorios/PedidoFinanciadoMes.jasper", new HashMap(), relatResul);
        
        //JasperViewer.viewReport(jpPrint);
        
        JasperViewer  jv = new JasperViewer(jpPrint, false);
        jv.setVisible(true);
        jv.toFront();
        
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao criar relatorio! = " + ex);
        }        
    }
    
    public void pesquisaRelatorioPedidoFinanciadoVendedor() throws SQLException{
        try {
        executasql("SELECT\n" +
"     pedido_financiado.\"valor_financiado\" AS valor_financiado,\n" +
"     pedido_financiado.\"total_financiado\" AS total_financiado,\n" +
"     pedido_financiado.\"parcelas\" AS parcelas,\n" +
"     pedido_financiado.\"vendedor_financiado\" AS vendedor,\n" +
"     pedido_financiado.\"mes_venda_financiada\" AS mes_venda,\n" +
"     cliente.\"nome_cliente\" AS nome_cliente,\n" +
"     veiculo.\"modelo_veiculo\" AS modelo,\n" +
"     veiculo.\"ano_veiculo\" AS ano,\n" +
"     veiculo.\"placa_veiculo\" AS placa,\n" +
"     veiculo.\"tipo_veiculo\" AS tipo\n" +
"FROM\n" +
"     \"public\".\"cliente\" cliente INNER JOIN \"public\".\"pedido_financiado\" pedido_financiado ON cliente.\"id_cliente\" = pedido_financiado.\"id_cliente\"\n" +
"     INNER JOIN \"public\".\"veiculo\" veiculo ON pedido_financiado.\"id_veiculo\" = veiculo.\"id_veiculo\"");
        //rs.first();
        JRResultSetDataSource relatResul = new JRResultSetDataSource(rs);
        JasperPrint jpPrint = JasperFillManager.fillReport("src/Relatorios/PedidoFinanciadoVendedor.jasper", new HashMap(), relatResul);
        
        //JasperViewer.viewReport(jpPrint);
        
        JasperViewer  jv = new JasperViewer(jpPrint, false);
        jv.setVisible(true);
        jv.toFront();
        
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao criar relatorio! = " + ex);
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelId = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();
        jMenuBarTelaPrincipal = new javax.swing.JMenuBar();
        jMenuCadastro = new javax.swing.JMenu();
        jMenuItemCadastroVeiculo = new javax.swing.JMenuItem();
        jMenuItemCadastroCliente = new javax.swing.JMenuItem();
        jMenuItemCadastroFuncionario = new javax.swing.JMenuItem();
        jMenuVendas = new javax.swing.JMenu();
        jMenuItemVendas = new javax.swing.JMenuItem();
        jMenuFinanceiro = new javax.swing.JMenu();
        jMenuRelatorios = new javax.swing.JMenu();
        jMenuItemVendasPorCliente = new javax.swing.JMenuItem();
        jMenuItemVendasPorVendedor = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItemFuncionario = new javax.swing.JMenuItem();
        jMenuSobre = new javax.swing.JMenu();
        jMenuItemContatoSuporte = new javax.swing.JMenuItem();
        jMenuItemSobre = new javax.swing.JMenuItem();
        jMenuSistemaLavajato = new javax.swing.JMenu();
        jMenuItemLavaJato = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");

        jLabelNomeUsuario.setText("Usuario:");

        jMenuCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jMenuCadastro.setText("Cadastro");

        jMenuItemCadastroVeiculo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCadastroVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/car.png"))); // NOI18N
        jMenuItemCadastroVeiculo.setText("Cadastro de Veículos");
        jMenuItemCadastroVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroVeiculoActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemCadastroVeiculo);

        jMenuItemCadastroCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCadastroCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user.png"))); // NOI18N
        jMenuItemCadastroCliente.setText("Cadastro de Cliente");
        jMenuItemCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroClienteActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemCadastroCliente);

        jMenuItemCadastroFuncionario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCadastroFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_gray.png"))); // NOI18N
        jMenuItemCadastroFuncionario.setText("Cadastro Funcionário");
        jMenuItemCadastroFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroFuncionarioActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemCadastroFuncionario);

        jMenuBarTelaPrincipal.add(jMenuCadastro);

        jMenuVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/coins.png"))); // NOI18N
        jMenuVendas.setText("Vendas");

        jMenuItemVendas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/lorry_go.png"))); // NOI18N
        jMenuItemVendas.setText("Vendas");
        jMenuItemVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendasActionPerformed(evt);
            }
        });
        jMenuVendas.add(jMenuItemVendas);

        jMenuBarTelaPrincipal.add(jMenuVendas);

        jMenuFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/money_dollar.png"))); // NOI18N
        jMenuFinanceiro.setText("Financeiro");
        jMenuBarTelaPrincipal.add(jMenuFinanceiro);

        jMenuRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/article-24.png"))); // NOI18N
        jMenuRelatorios.setText("Relatórios");

        jMenuItemVendasPorCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVendasPorCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/page_white_text.png"))); // NOI18N
        jMenuItemVendasPorCliente.setText("Vendas por mes avista");
        jMenuItemVendasPorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendasPorClienteActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemVendasPorCliente);

        jMenuItemVendasPorVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVendasPorVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/page_white_text.png"))); // NOI18N
        jMenuItemVendasPorVendedor.setText("Vendas por vendedor avista");
        jMenuItemVendasPorVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendasPorVendedorActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemVendasPorVendedor);

        jMenuItem2.setText("Vendas por mes financiado");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem2);

        jMenuItem3.setText("Vendas por vendedor financiado");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem3);

        jMenuItemFuncionario.setText("Funcionario");
        jMenuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuncionarioActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemFuncionario);

        jMenuBarTelaPrincipal.add(jMenuRelatorios);

        jMenuSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        jMenuSobre.setText("Sobre");

        jMenuItemContatoSuporte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemContatoSuporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vcard.png"))); // NOI18N
        jMenuItemContatoSuporte.setText("Contato para Suporte");
        jMenuItemContatoSuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContatoSuporteActionPerformed(evt);
            }
        });
        jMenuSobre.add(jMenuItemContatoSuporte);

        jMenuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/help.png"))); // NOI18N
        jMenuItemSobre.setText("Sobre!");
        jMenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSobreActionPerformed(evt);
            }
        });
        jMenuSobre.add(jMenuItemSobre);

        jMenuBarTelaPrincipal.add(jMenuSobre);

        jMenuSistemaLavajato.setText("Sistema de lavajato");

        jMenuItemLavaJato.setText("Lava-Jato");
        jMenuSistemaLavajato.add(jMenuItemLavaJato);

        jMenuBarTelaPrincipal.add(jMenuSistemaLavajato);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disconnect.png"))); // NOI18N
        jMenu2.setText("Sair");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/door_out.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBarTelaPrincipal.add(jMenu2);

        setJMenuBar(jMenuBarTelaPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(731, Short.MAX_VALUE)
                .addComponent(jLabelNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeUsuario))
                .addContainerGap(534, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroClienteActionPerformed
        obtemUsuarioParaCadCliente();
    }//GEN-LAST:event_jMenuItemCadastroClienteActionPerformed

    private void jMenuItemCadastroFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroFuncionarioActionPerformed
        obtemUsuarioParaCadFuncionario();
    }//GEN-LAST:event_jMenuItemCadastroFuncionarioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendasActionPerformed
        obtemUsuarioParaEscolhaVenda();
    }//GEN-LAST:event_jMenuItemVendasActionPerformed

    private void jMenuItemCadastroVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroVeiculoActionPerformed
        try {
            obtemUsuarioParaCadVeiculo();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCadastroVeiculoActionPerformed

    private void jMenuItemContatoSuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContatoSuporteActionPerformed
        new ContatoSuporte().setVisible(true);
    }//GEN-LAST:event_jMenuItemContatoSuporteActionPerformed

    private void jMenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSobreActionPerformed
       new Sobre().setVisible(true);
    }//GEN-LAST:event_jMenuItemSobreActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            pesquisaRelatorioPedidoFInanciadoMes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatorio! = " + ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemVendasPorVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendasPorVendedorActionPerformed
        try {
            pesquisaRelatorioPedidoAvistaVendedor();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatorio! = " + ex);
        }
    }//GEN-LAST:event_jMenuItemVendasPorVendedorActionPerformed

    private void jMenuItemVendasPorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendasPorClienteActionPerformed
        try {
            pesquisaRelatorioPedidoAvistaMes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatorio! = " + ex);
        }
    }//GEN-LAST:event_jMenuItemVendasPorClienteActionPerformed

    private void jMenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFuncionarioActionPerformed
        try {
            pesquisaRelatorioFuncionario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatorio! = " + ex);
        }
    }//GEN-LAST:event_jMenuItemFuncionarioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            pesquisaRelatorioPedidoFinanciadoVendedor();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatorio! = " + ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaPrincipal().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBarTelaPrincipal;
    private javax.swing.JMenu jMenuCadastro;
    private javax.swing.JMenu jMenuFinanceiro;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemCadastroCliente;
    private javax.swing.JMenuItem jMenuItemCadastroFuncionario;
    private javax.swing.JMenuItem jMenuItemCadastroVeiculo;
    private javax.swing.JMenuItem jMenuItemContatoSuporte;
    private javax.swing.JMenuItem jMenuItemFuncionario;
    private javax.swing.JMenuItem jMenuItemLavaJato;
    private javax.swing.JMenuItem jMenuItemSobre;
    private javax.swing.JMenuItem jMenuItemVendas;
    private javax.swing.JMenuItem jMenuItemVendasPorCliente;
    private javax.swing.JMenuItem jMenuItemVendasPorVendedor;
    private javax.swing.JMenu jMenuRelatorios;
    private javax.swing.JMenu jMenuSistemaLavajato;
    private javax.swing.JMenu jMenuSobre;
    private javax.swing.JMenu jMenuVendas;
    // End of variables declaration//GEN-END:variables
}
