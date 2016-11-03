package Visao;

import controle.ConexaoBD;
import controle.ConexaoExecutaSql;
import controle.VeiculoDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.Veiculo;

public class CadastroDeVeiculos extends javax.swing.JFrame {

    private Connection conexao;//Cria metodo de conexao

    //Construtior
    public CadastroDeVeiculos() throws SQLException{
        initComponents();
        desabilitaCampos();
        this.setLocationRelativeTo(null);
        setIcon();
        //this.jComboBoxTipo.removeAllItems();
        //this.jComboBoxTipo.addItem("carro");
        //this.jComboBoxTipo.addItem("moto");
    } 
    
    public void exportaTelaPrincipal(Funcionario funcionario){
        //Da tela principal importa para este metodo onde e setado os valores nos campos
        Long id = funcionario.getId_funcionario();
        String usuario = funcionario.getUsuario();
        
        jLabelIdFuncionario.setText(String.valueOf(id));
        jLabelUsuario.setText(usuario);
        jLabelIdFuncionario.setVisible(false);
    }
    
    private void setIcon(){
        //Seta icone no sistema
        URL url = this.getClass().getResource("../imagens/ParkingLot.png"); 
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url); 
        this.setIconImage(imagemTitulo);
    }
    
    //Altera veiculo
    public void alteraVeiculo() throws SQLException{
        Veiculo veiculo = new Veiculo();
        VeiculoDao dao = new VeiculoDao();
        
        veiculo.setId_veiculo(Long.valueOf(jTextFieldIdVeiculo.getText()));
        veiculo.setModelo_veiculo(jTextFieldModelo.getText());
        veiculo.setCor_veiculo(jTextFieldCor.getText());
        veiculo.setAno_veiculo(Integer.valueOf(jTextFieldAno.getText()));
        veiculo.setPlaca_veiculo(jFormattedTextFieldPlaca.getText());
        veiculo.setEstado_veiculo(jTextFieldEstado.getText());
        veiculo.setMarca_veiculo((String)jComboBoxMarca.getSelectedItem());
        veiculo.setObservacoes_veiculo(jTextFieldObservacoes.getText());
        veiculo.setValor_minimo_veiculo(Double.valueOf(jFormattedTextFieldValorMin.getText()));
        veiculo.setValor_medio_veiculo(Double.valueOf(jFormattedTextFieldValorMed.getText()));
        veiculo.setValor_maximo_veiculo(Double.valueOf(jFormattedTextFieldValorMax.getText()));
        veiculo.setQuantidade_veiculo(Integer.valueOf(jTextFieldQuantidade.getText()));
        veiculo.setTipo_veiculo((String)jComboBoxTipo.getSelectedItem());
        
        dao.alteraVeiculo(veiculo);
        JOptionPane.showMessageDialog(null, "Veiculo alterado com sucesso!");
    }

    //Exclui veiculo
    public void excluirVeiculo() throws SQLException {
        
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_NO_OPTION) {
            
            this.conexao = ConexaoBD.getConexao();
            String modelo = jTextFieldModelo.getText();
            String sql = "delete from veiculo where modelo_veiculo=?";
            
            try {
                
                PreparedStatement stmt = this.conexao.prepareStatement(sql);
                stmt.setString(1, modelo);
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Veiculo excluido com sucesso!");

                limpaCampo();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir veiculo! = " + ex);
            }
        }
    }

    //Salva Veiculos
    public void salvaVeiculo() {
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo_veiculo(jTextFieldModelo.getText());
        veiculo.setCor_veiculo(jTextFieldCor.getText());
        veiculo.setAno_veiculo(Integer.parseInt(jTextFieldAno.getText()));
        veiculo.setPlaca_veiculo(jFormattedTextFieldPlaca.getText());
        veiculo.setEstado_veiculo(jTextFieldEstado.getText());
        veiculo.setMarca_veiculo((String) jComboBoxMarca.getSelectedItem());
        veiculo.setObservacoes_veiculo(jTextFieldObservacoes.getText());
        veiculo.setValor_minimo_veiculo(Double.parseDouble(jFormattedTextFieldValorMin.getText()));
        veiculo.setValor_medio_veiculo(Double.parseDouble(jFormattedTextFieldValorMed.getText()));
        veiculo.setValor_maximo_veiculo(Double.parseDouble(jFormattedTextFieldValorMax.getText()));
        veiculo.setQuantidade_veiculo(Integer.parseInt(jTextFieldQuantidade.getText()));
        veiculo.setTipo_veiculo((String)jComboBoxTipo.getSelectedItem());

        try {
            VeiculoDao dao = new VeiculoDao();
            dao.adicionaVeiculo(veiculo);
            JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");
            desabilitaCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar veiculo! = " + ex);
        }
    }
    
    public void pesquisaVeiculo(){
        //Cria as variaveis e seta os valores dos get and set
        PesquisaVeiculo pesquisa = new PesquisaVeiculo(this,true);
        pesquisa.setVisible(true);
        
        Long id = pesquisa.getId_veiculo();
        String modelo = pesquisa.getModelo_veiculo();
        String cor = pesquisa.getCor_veiculo();
        int ano = pesquisa.getAno_veiculo();
        String placa = pesquisa.getPlaca_veiculo();
        String estado = pesquisa.getEstado_veiculo();
        String marca = pesquisa.getMarca_veiculo();
        String observacoe = pesquisa.getObservacoes_veiculo();
        Double valorMin = pesquisa.getValor_minimo_veiculo();
        Double valorMed = pesquisa.getValor_medio_veiculo();
        Double valorMax = pesquisa.getValor_maximo_veiculo();
        int quantidade = pesquisa.getQuantidade_veiculo();
        String tipo = pesquisa.getTipo_veiculo();
        //JOptionPane.showMessageDialog(rootPane, tipo);
        
        if(quantidade==0){
        //Seta os valores nos campos
        jTextFieldIdVeiculo.setText(String.valueOf(id));
        jTextFieldModelo.setText(modelo);
        jTextFieldCor.setText(cor);
        jTextFieldAno.setText(String.valueOf(ano));
        jFormattedTextFieldPlaca.setText(placa);
        jTextFieldEstado.setText(estado);
        jComboBoxMarca.setSelectedItem(marca);
        jTextFieldObservacoes.setText(observacoe);
        jFormattedTextFieldValorMin.setText(String.format("%.3f",(valorMin)));
        jFormattedTextFieldValorMed.setText(String.format("%.3f", (valorMed)));
        jFormattedTextFieldValorMax.setText(String.format("%.3f", (valorMax)));
        jTextFieldQuantidade.setText(String.valueOf(quantidade));
        jComboBoxTipo.setSelectedItem(tipo);
        
        desabilitaCampos();
        desabilitaBotoes();
        }else{
        //Seta os valores nos campos
        jTextFieldIdVeiculo.setText(String.valueOf(id));
        jTextFieldModelo.setText(modelo);
        jTextFieldCor.setText(cor);
        jTextFieldAno.setText(String.valueOf(ano));
        jFormattedTextFieldPlaca.setText(placa);
        jTextFieldEstado.setText(estado);
        jComboBoxMarca.setSelectedItem(marca);
        jTextFieldObservacoes.setText(observacoe);
        jFormattedTextFieldValorMin.setText(String.valueOf(valorMin));
        jFormattedTextFieldValorMed.setText(String.valueOf(valorMed));
        jFormattedTextFieldValorMax.setText(String.valueOf(valorMax));
        jTextFieldQuantidade.setText(String.valueOf(quantidade));
        jComboBoxTipo.setSelectedItem(tipo); 
        
        habilitaCampos();
        habilitaBotoes();
        }
    }
    
    public void habilitaBotoes(){
        jButtonAlterar.setEnabled(true);
        jButtonExcluir.setEnabled(true);
        jButtonSalvar.setEnabled(true);
    }
    
    public void desabilitaBotoes(){
        jButtonAlterar.setEnabled(false);
        jButtonExcluir.setEnabled(false);
        jButtonSalvar.setEnabled(false);
    }

    //Limpa campos
    public void limpaCampo() {
        jTextFieldIdVeiculo.setText(null);
        jTextFieldModelo.setText(null);
        jTextFieldCor.setText(null);
        jTextFieldAno.setText(null);
        jFormattedTextFieldPlaca.setText(null);
        jTextFieldEstado.setText(null);
        jTextFieldObservacoes.setText(null);
        jFormattedTextFieldValorMin.setText(null);
        jFormattedTextFieldValorMed.setText(null);
        jFormattedTextFieldValorMax.setText(null);
        jTextFieldQuantidade.setText(null);
        jComboBoxTipo.setSelectedItem("Selecione      ");
        jComboBoxMarca.setSelectedItem("Selecione      ");
    }

    //Desabilita campos
    public void desabilitaCampos() {
        jTextFieldIdVeiculo.setEditable(false);
        jTextFieldModelo.setEditable(false);
        jTextFieldCor.setEditable(false);
        jTextFieldAno.setEditable(false);
        jFormattedTextFieldPlaca.setEditable(false);
        jTextFieldEstado.setEditable(false);
        jTextFieldObservacoes.setEditable(false);
        jFormattedTextFieldValorMin.setEditable(false);
        jFormattedTextFieldValorMed.setEditable(false);
        jFormattedTextFieldValorMax.setEditable(false);
        jTextFieldQuantidade.setEditable(false);
        jComboBoxMarca.setEditable(false);
        jComboBoxTipo.setEditable(false);
    }

    //Habilita campos
    public void habilitaCampos() {
        jTextFieldModelo.setEditable(true);
        jTextFieldCor.setEditable(true);
        jTextFieldAno.setEditable(true);
        jFormattedTextFieldPlaca.setEditable(true);
        jTextFieldEstado.setEditable(true);
        jTextFieldObservacoes.setEditable(true);
        jFormattedTextFieldValorMin.setEditable(true);
        jFormattedTextFieldValorMed.setEditable(true);
        jFormattedTextFieldValorMax.setEditable(true);
        jTextFieldQuantidade.setEditable(true);
        jComboBoxMarca.setEditable(true);
        jComboBoxTipo.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        buttonGroupSexo = new javax.swing.ButtonGroup();
        jPanelInfoVeiculo = new javax.swing.JPanel();
        jLabelIdVeiculo = new javax.swing.JLabel();
        jLabelModelo = new javax.swing.JLabel();
        jLabelPlaca = new javax.swing.JLabel();
        jLabelCor = new javax.swing.JLabel();
        jLabelAno = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMarca = new javax.swing.JLabel();
        jLabelObservacao = new javax.swing.JLabel();
        jTextFieldIdVeiculo = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldCor = new javax.swing.JTextField();
        jTextFieldAno = new javax.swing.JTextField();
        jTextFieldObservacoes = new javax.swing.JTextField();
        jComboBoxMarca = new javax.swing.JComboBox();
        jTextFieldEstado = new javax.swing.JTextField();
        jFormattedTextFieldPlaca = new javax.swing.JFormattedTextField();
        jLabelTipo = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox();
        jPanelPrecosVeiculo = new javax.swing.JPanel();
        jLabelValorMin = new javax.swing.JLabel();
        jLabelValorMed = new javax.swing.JLabel();
        jLabelValorMax = new javax.swing.JLabel();
        jLabelQuantidade = new javax.swing.JLabel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jFormattedTextFieldValorMin = new javax.swing.JFormattedTextField();
        jFormattedTextFieldValorMed = new javax.swing.JFormattedTextField();
        jFormattedTextFieldValorMax = new javax.swing.JFormattedTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jLabelIdFuncionario = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        jLabel11.setText("jLabel11");

        jTextField7.setText("jTextField7");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("jLabel14");

        jTextField11.setText("jTextField11");

        jLabel17.setText("jLabel17");

        jButton6.setText("jButton6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de veiculos");

        jPanelInfoVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Informações sobre o veiculo")));

        jLabelIdVeiculo.setText("Id Veiculo");

        jLabelModelo.setText("Modelo do veículo");

        jLabelPlaca.setText("Placa do veículo");

        jLabelCor.setText("Cor do veículo");

        jLabelAno.setText("Ano do veículo");

        jLabelEstado.setText("Estado/Cidade do veículo");

        jLabelMarca.setText("Marca do veículo");

        jLabelObservacao.setText("Observações");

        jTextFieldModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldModeloKeyPressed(evt);
            }
        });

        jTextFieldCor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCorKeyPressed(evt);
            }
        });

        jTextFieldAno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAnoKeyPressed(evt);
            }
        });

        jTextFieldObservacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldObservacoesKeyPressed(evt);
            }
        });

        jComboBoxMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione      ", "Wolskvagem     ", "Fiat           ", "Huyndai        ", "Honda          ", "Sundown        ", "Jeep           " }));

        jTextFieldEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldEstadoKeyPressed(evt);
            }
        });

        jFormattedTextFieldPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPlacaKeyPressed(evt);
            }
        });

        jLabelTipo.setText("Tipo do veículo");

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione      ", "Carro          ", "Moto          " }));

        javax.swing.GroupLayout jPanelInfoVeiculoLayout = new javax.swing.GroupLayout(jPanelInfoVeiculo);
        jPanelInfoVeiculo.setLayout(jPanelInfoVeiculoLayout);
        jPanelInfoVeiculoLayout.setHorizontalGroup(
            jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldModelo)
                            .addComponent(jLabelIdVeiculo)
                            .addComponent(jLabelModelo)
                            .addComponent(jTextFieldIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAno)
                                    .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelPlaca)
                                    .addComponent(jFormattedTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelCor)
                                        .addComponent(jTextFieldCor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoVeiculoLayout.createSequentialGroup()
                                    .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelEstado)
                                        .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(jTextFieldEstado)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelMarca))
                                    .addContainerGap()))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoVeiculoLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))))
                    .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                        .addComponent(jLabelObservacao)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                        .addComponent(jTextFieldObservacoes)
                        .addContainerGap())))
        );
        jPanelInfoVeiculoLayout.setVerticalGroup(
            jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoVeiculoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelIdVeiculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTipo)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelModelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAno)
                    .addComponent(jLabelPlaca)
                    .addComponent(jLabelMarca)
                    .addComponent(jLabelEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelObservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelPrecosVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder("Preços"));

        jLabelValorMin.setText("Valor mínimo");

        jLabelValorMed.setText("Valor médio");

        jLabelValorMax.setText("Valor máximo");

        jLabelQuantidade.setText("Quantidade");

        jTextFieldQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantidadeKeyPressed(evt);
            }
        });

        jFormattedTextFieldValorMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldValorMinKeyPressed(evt);
            }
        });

        jFormattedTextFieldValorMed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldValorMedKeyPressed(evt);
            }
        });

        jFormattedTextFieldValorMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldValorMaxKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPrecosVeiculoLayout = new javax.swing.GroupLayout(jPanelPrecosVeiculo);
        jPanelPrecosVeiculo.setLayout(jPanelPrecosVeiculoLayout);
        jPanelPrecosVeiculoLayout.setHorizontalGroup(
            jPanelPrecosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrecosVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrecosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextFieldValorMax, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldValorMed, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabelValorMin)
                    .addComponent(jLabelValorMed)
                    .addComponent(jLabelValorMax)
                    .addComponent(jLabelQuantidade)
                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldValorMin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPrecosVeiculoLayout.setVerticalGroup(
            jPanelPrecosVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrecosVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValorMin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextFieldValorMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabelValorMed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextFieldValorMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabelValorMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextFieldValorMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelQuantidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disk.png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
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

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pencil.png"))); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/arrow_left.png"))); // NOI18N
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jLabelNomeUsuario.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonPesquisar))
                            .addComponent(jPanelInfoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonVoltar))
                            .addComponent(jPanelPrecosVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelNomeUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelInfoVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelPrecosVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonVoltar)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelNomeUsuario)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            alteraVeiculo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar veiculo! = " + ex);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitaCampos();
        limpaCampo();
        habilitaBotoes();
        jTextFieldModelo.requestFocus();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvaVeiculo();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        pesquisaVeiculo();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            excluirVeiculo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir veiculo ou veiculo com movimentação em vendas!");
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTextFieldModeloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldModeloKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldCor.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldModeloKeyPressed

    private void jTextFieldCorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCorKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldAno.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldCorKeyPressed

    private void jTextFieldAnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAnoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldPlaca.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldAnoKeyPressed

    private void jFormattedTextFieldPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPlacaKeyPressed
       if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldEstado.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldPlacaKeyPressed

    private void jTextFieldEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEstadoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldObservacoes.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldEstadoKeyPressed

    private void jTextFieldObservacoesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldObservacoesKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldValorMin.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldObservacoesKeyPressed

    private void jFormattedTextFieldValorMinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldValorMinKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldValorMed.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldValorMinKeyPressed

    private void jFormattedTextFieldValorMedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldValorMedKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldValorMax.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldValorMedKeyPressed

    private void jFormattedTextFieldValorMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldValorMaxKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldQuantidade.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldValorMaxKeyPressed

    private void jTextFieldQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonSalvar.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldQuantidadeKeyPressed

    private void jButtonSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSalvarKeyPressed
        salvaVeiculo();
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
            java.util.logging.Logger.getLogger(CadastroDeVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDeVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDeVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDeVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroDeVeiculos().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroDeVeiculos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBoxMarca;
    private javax.swing.JComboBox jComboBoxTipo;
    private javax.swing.JFormattedTextField jFormattedTextFieldPlaca;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorMax;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorMed;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorMin;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelCor;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelIdFuncionario;
    private javax.swing.JLabel jLabelIdVeiculo;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelObservacao;
    private javax.swing.JLabel jLabelPlaca;
    private javax.swing.JLabel jLabelQuantidade;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelValorMax;
    private javax.swing.JLabel jLabelValorMed;
    private javax.swing.JLabel jLabelValorMin;
    private javax.swing.JPanel jPanelInfoVeiculo;
    private javax.swing.JPanel jPanelPrecosVeiculo;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldCor;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldIdVeiculo;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldObservacoes;
    private javax.swing.JTextField jTextFieldQuantidade;
    // End of variables declaration//GEN-END:variables
}
