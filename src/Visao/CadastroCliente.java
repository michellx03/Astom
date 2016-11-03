package Visao;

import controle.ClienteDao;
import controle.ConexaoBD;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Funcionario;

public class CadastroCliente extends javax.swing.JFrame {

    private Connection conexao;//Cria metodo de conexao

    //Construtor
    public CadastroCliente() {
        initComponents();
        desabilitaCampos();
        this.setLocationRelativeTo(null);
        setIcon();
    }
    
    private void setIcon(){
        //Setar icone no sistema
        URL url = this.getClass().getResource("../imagens/ParkingLot.png"); 
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url); 
        this.setIconImage(imagemTitulo);
    }
    
    public void exportaTelaPrincipal(Funcionario funcionario){
        //Da tela principal importa para este metodo onde e setado os valores nos campos
        Long id = funcionario.getId_funcionario();
        String usuario = funcionario.getUsuario();
        
        jLabelIdFuncionario.setText(String.valueOf(id));
        jLabelUsuario.setText(usuario);
        jLabelIdFuncionario.setVisible(false);
    }
    
    //Altera cliente
    public void alteraCliente() throws SQLException{       
        Cliente cliente = new Cliente();
        ClienteDao dao = new ClienteDao();        
        
        cliente.setNome_cliente(jTextFieldNome.getText());
        cliente.setCpf_cliente(jFormattedTextFieldCpf.getText());
        cliente.setData_nascimento_cliente(jFormattedTextFieldDataNasc.getText());
        cliente.setSexo_cliente((String) jComboBoxSexo.getSelectedItem());
        cliente.setEndereco_cliente(jTextFieldEndereco.getText());
        cliente.setBairro_cliente(jTextFieldBairro.getText());
        cliente.setComplemento_cliente(jTextFieldComplemento.getText());       
        cliente.setTelefone_residencial_cliente(jFormattedTextFieldTelResidencial.getText());
        cliente.setTelefone_celular_cliente(jFormattedTextFieldTelCelular.getText());
        cliente.setNumero_casa_cliente(Integer.valueOf(jTextFieldNumero.getText()));
        cliente.setId_cliente(Long.valueOf(jTextFieldIdCliente.getText()));
        cliente.setCidade(jTextFieldCidade.getText());
        
        dao.alteraCliente(cliente);
        JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
    }

    //Exclui cliente
    public void excluirCliente() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_NO_OPTION) {
            this.conexao = ConexaoBD.getConexao();
            String nome = jTextFieldNome.getText();
            String sql = "delete from cliente where nome_cliente=?";
            try {
                PreparedStatement stmt = this.conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
                
                limpaCampos();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir cliente, a registro de vendas!");
            }
        }
    }
    
    public void pesquisaCliente(){
        //Cria as variaveis e seta os valores dos get and set
        PesquisaCliente pesquisa = new PesquisaCliente(this,true);
        pesquisa.setVisible(true);
        Long id = pesquisa.getId_cliente();
        String nome = pesquisa.getNome_cliente();
        String dataNasc = pesquisa.getData_nascimento_cliente();
        String cpf = pesquisa.getCpf_cliente();
        String sexo = pesquisa.getSexo_cliente();
        String endereco = pesquisa.getEndereco_cliente();
        int numero = pesquisa.getNumero_casa_cliente();
        String bairro = pesquisa.getBairro_cliente();
        String complemento = pesquisa.getComplemento_cliente();
        String telefoneCelular = pesquisa.getTelefone_celular_cliente();
        String telefoneResidencial = pesquisa.getTelefone_residencial_cliente();
        String cidade = pesquisa.getCidade();
        
        //Seta os valores nos campos
        jTextFieldIdCliente.setText(String.valueOf(id));
        jTextFieldNome.setText(nome);
        jFormattedTextFieldDataNasc.setText(dataNasc);
        jFormattedTextFieldCpf.setText(cpf);
        jComboBoxSexo.setSelectedItem(sexo);
        jTextFieldEndereco.setText(endereco);
        jTextFieldNumero.setText(String.valueOf(numero));
        jTextFieldBairro.setText(bairro);
        jTextFieldComplemento.setText(complemento);
        jFormattedTextFieldTelCelular.setText(telefoneCelular);
        jFormattedTextFieldTelResidencial.setText(telefoneResidencial);
        jTextFieldCidade.setText(cidade);
        
        habilitaCampos();
    }

    //Salvar cliente no banco
    public void salvaCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome_cliente(jTextFieldNome.getText());
        cliente.setData_nascimento_cliente(jFormattedTextFieldDataNasc.getText());
        cliente.setCpf_cliente(jFormattedTextFieldCpf.getText());
        cliente.setSexo_cliente((String) jComboBoxSexo.getSelectedItem());
        cliente.setEndereco_cliente(jTextFieldEndereco.getText());
        cliente.setNumero_casa_cliente(Integer.parseInt(jTextFieldNumero.getText()));
        cliente.setBairro_cliente(jTextFieldBairro.getText());
        cliente.setComplemento_cliente(jTextFieldComplemento.getText());
        cliente.setTelefone_residencial_cliente(jFormattedTextFieldTelResidencial.getText());
        cliente.setTelefone_celular_cliente(jFormattedTextFieldTelCelular.getText());
        cliente.setCidade(jTextFieldCidade.getText());

        try {
            ClienteDao dao = new ClienteDao();
            dao.adicionaCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            desabilitaCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente! = " + ex);
        }
    }

    //Limpa campos cliente
    public void limpaCampos() {
        jTextFieldIdCliente.setText(null);
        jTextFieldNome.setText(null);
        jFormattedTextFieldDataNasc.setText(null);
        jFormattedTextFieldCpf.setText(null);
        jTextFieldEndereco.setText(null);
        jTextFieldBairro.setText(null);
        jTextFieldNumero.setText(null);
        jTextFieldComplemento.setText(null);
        jFormattedTextFieldTelResidencial.setText(null);
        jFormattedTextFieldTelCelular.setText(null);
        jComboBoxSexo.setSelectedItem("Selecione");
        jTextFieldCidade.setText(null);
    }

    //Desabilitar campos
    public void desabilitaCampos() {
        jTextFieldIdCliente.setEditable(false);
        jTextFieldNome.setEditable(false);
        jFormattedTextFieldDataNasc.setEditable(false);
        jComboBoxSexo.setEditable(false);
        jFormattedTextFieldCpf.setEditable(false);
        jTextFieldEndereco.setEditable(false);
        jTextFieldBairro.setEditable(false);
        jTextFieldNumero.setEditable(false);
        jTextFieldComplemento.setEditable(false);
        jFormattedTextFieldTelResidencial.setEditable(false);
        jFormattedTextFieldTelCelular.setEditable(false);
        jTextFieldCidade.setEditable(false);
    }

    //Habilitar campos
    public void habilitaCampos() {
        jTextFieldNome.setEditable(true);
        jFormattedTextFieldDataNasc.setEditable(true);
        jComboBoxSexo.setEditable(true);
        jFormattedTextFieldCpf.setEditable(true);
        jTextFieldEndereco.setEditable(true);
        jTextFieldBairro.setEditable(true);
        jTextFieldNumero.setEditable(true);
        jTextFieldComplemento.setEditable(true);
        jFormattedTextFieldTelResidencial.setEditable(true);
        jFormattedTextFieldTelCelular.setEditable(true);
        jTextFieldCidade.setEditable(true);
        jTextFieldCidade.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanelDadosPessoais = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jLabelDataNascimento = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        jLabelSexo = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox();
        jTextFieldNome = new javax.swing.JTextField();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataNasc = new javax.swing.JFormattedTextField();
        jLabelIdCliente = new javax.swing.JLabel();
        jTextFieldIdCliente = new javax.swing.JTextField();
        jPanelContato = new javax.swing.JPanel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabelBairro = new javax.swing.JLabel();
        jLabelNumero = new javax.swing.JLabel();
        jLabelComplemento = new javax.swing.JLabel();
        jLabelTelResidencial = new javax.swing.JLabel();
        jLabelTelCelular = new javax.swing.JLabel();
        jTextFieldEndereco = new javax.swing.JTextField();
        jTextFieldBairro = new javax.swing.JTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jTextFieldComplemento = new javax.swing.JTextField();
        jFormattedTextFieldTelCelular = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelResidencial = new javax.swing.JFormattedTextField();
        jLabelCidade = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelIdFuncionario = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de cliente");

        jPanelDadosPessoais.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));

        jLabelNome.setText("Nome");

        jLabelDataNascimento.setText("Data Nascimento");

        jLabelCPF.setText("CPF");

        jLabelSexo.setText("Sexo");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Masculino", "Feminino " }));
        jComboBoxSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxSexoKeyPressed(evt);
            }
        });

        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyPressed(evt);
            }
        });

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCpfKeyPressed(evt);
            }
        });

        try {
            jFormattedTextFieldDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataNasc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDataNascKeyPressed(evt);
            }
        });

        jLabelIdCliente.setText("Id Cliente");

        javax.swing.GroupLayout jPanelDadosPessoaisLayout = new javax.swing.GroupLayout(jPanelDadosPessoais);
        jPanelDadosPessoais.setLayout(jPanelDadosPessoaisLayout);
        jPanelDadosPessoaisLayout.setHorizontalGroup(
            jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNome)
                                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelDataNascimento)))
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCPF)
                                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(148, 148, 148)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                        .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabelSexo)
                                        .addGap(26, 26, 26)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdCliente)
                            .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelDadosPessoaisLayout.setVerticalGroup(
            jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                .addComponent(jLabelIdCliente)
                .addGap(4, 4, 4)
                .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jLabelDataNascimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCPF)
                    .addComponent(jLabelSexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextFieldCpf)
                    .addComponent(jComboBoxSexo))
                .addContainerGap())
        );

        jPanelContato.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));

        jLabelEndereco.setText("Endereço");

        jLabelBairro.setText("Bairro");

        jLabelNumero.setText("Número");

        jLabelComplemento.setText("Complemento");

        jLabelTelResidencial.setText("Telefone Residencial");

        jLabelTelCelular.setText("Telefone Celular");

        jTextFieldEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldEnderecoKeyPressed(evt);
            }
        });

        jTextFieldBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBairroKeyPressed(evt);
            }
        });

        jTextFieldNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroKeyPressed(evt);
            }
        });

        jTextFieldComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldComplementoKeyPressed(evt);
            }
        });

        try {
            jFormattedTextFieldTelCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTelCelularKeyPressed(evt);
            }
        });

        try {
            jFormattedTextFieldTelResidencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelResidencial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTelResidencialKeyPressed(evt);
            }
        });

        jLabelCidade.setText("Cidade");

        jTextFieldCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCidadeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelContatoLayout = new javax.swing.GroupLayout(jPanelContato);
        jPanelContato.setLayout(jPanelContatoLayout);
        jPanelContatoLayout.setHorizontalGroup(
            jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContatoLayout.createSequentialGroup()
                        .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldTelResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelResidencial))
                        .addGap(120, 120, 120)
                        .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContatoLayout.createSequentialGroup()
                                .addComponent(jLabelTelCelular)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanelContatoLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelContatoLayout.createSequentialGroup()
                        .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContatoLayout.createSequentialGroup()
                                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEndereco))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNumero)))
                            .addGroup(jPanelContatoLayout.createSequentialGroup()
                                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBairro)
                                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCidade))
                                .addGap(33, 33, 33)
                                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelComplemento)
                                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelContatoLayout.setVerticalGroup(
            jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEndereco)
                    .addComponent(jLabelNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBairro)
                    .addComponent(jLabelComplemento)
                    .addComponent(jLabelCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelResidencial)
                    .addComponent(jLabelTelCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldTelResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pencil.png"))); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        jButtonAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonAlterarKeyPressed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        jButtonExcluir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonExcluirKeyPressed(evt);
            }
        });

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });
        jButtonPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonPesquisarKeyPressed(evt);
            }
        });

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/arrow_left.png"))); // NOI18N
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });
        jButtonVoltar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonVoltarKeyPressed(evt);
            }
        });

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jButtonNovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonNovoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonNovoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButtonNovoKeyTyped(evt);
            }
        });

        jLabelNomeUsuario.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelContato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDadosPessoais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPesquisar)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelNomeUsuario)
                                .addGap(1, 1, 1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonVoltar))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanelDadosPessoais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanelContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitaCampos();
        limpaCampos();
        jTextFieldNome.requestFocus();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvaCliente();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            excluirCliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente! = " + ex);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            alteraCliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar cliente! = " + ex);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTextFieldNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldDataNasc.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldNomeKeyPressed

    private void jFormattedTextFieldDataNascKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataNascKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldCpf.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldDataNascKeyPressed

    private void jComboBoxSexoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxSexoKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldEndereco.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxSexoKeyPressed

    private void jTextFieldEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEnderecoKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldNumero.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldEnderecoKeyPressed

    private void jTextFieldNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumeroKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldBairro.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldNumeroKeyPressed

    private void jTextFieldBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBairroKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldCidade.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldBairroKeyPressed

    private void jTextFieldComplementoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldComplementoKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldTelResidencial.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldComplementoKeyPressed

    private void jFormattedTextFieldTelResidencialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelResidencialKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldTelCelular.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldTelResidencialKeyPressed

    private void jFormattedTextFieldTelCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelCelularKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonSalvar.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldTelCelularKeyPressed

    private void jFormattedTextFieldCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ENTER){
            jComboBoxSexo.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldCpfKeyPressed

    private void jButtonNovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonNovoKeyPressed
       //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_F2){
            limpaCampos();
            habilitaCampos();
        } 
    }//GEN-LAST:event_jButtonNovoKeyPressed

    private void jButtonSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSalvarKeyPressed
        salvaCliente();
    }//GEN-LAST:event_jButtonSalvarKeyPressed

    private void jButtonExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonExcluirKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_F4){
            try {
                excluirCliente();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonExcluirKeyPressed

    private void jButtonAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAlterarKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_F5){
            try {
                alteraCliente();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAlterarKeyPressed

    private void jButtonPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonPesquisarKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_F6){
            pesquisaCliente();
        }
    }//GEN-LAST:event_jButtonPesquisarKeyPressed

    private void jButtonVoltarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonVoltarKeyPressed
        //Passar de campos ao apertar ENTER
        if(evt.getKeyCode() == evt.VK_ESCAPE){
            dispose();
        }
    }//GEN-LAST:event_jButtonVoltarKeyPressed

    private void jButtonNovoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonNovoKeyReleased
        
    }//GEN-LAST:event_jButtonNovoKeyReleased

    private void jButtonNovoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonNovoKeyTyped
       
    }//GEN-LAST:event_jButtonNovoKeyTyped

    private void jTextFieldCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCidadeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldComplemento.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldCidadeKeyPressed

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
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataNasc;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelResidencial;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelDataNascimento;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelIdFuncionario;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTelCelular;
    private javax.swing.JLabel jLabelTelResidencial;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelContato;
    private javax.swing.JPanel jPanelDadosPessoais;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    // End of variables declaration//GEN-END:variables
}
