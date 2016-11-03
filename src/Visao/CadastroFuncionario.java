package Visao;

import controle.ConexaoBD;
import controle.FuncionarioDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Funcionario;

public class CadastroFuncionario extends javax.swing.JFrame {
    
    private Connection conexao;//Cria metodo de conexao

    //Construtor
    public CadastroFuncionario() {
        initComponents();
        desabilitaCampos();
        this.setLocationRelativeTo(null);
        setIcon();
    }
    
    private void setIcon(){
        //Seta icone no sistema
        URL url = this.getClass().getResource("../imagens/ParkingLot.png"); 
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url); 
        this.setIconImage(imagemTitulo);
    }
    
    public void exportaTelaPrincipal(Funcionario funcionario){
        //Da tela principal importa para este metodo onde e setado os valores nos campos
        Long id = funcionario.getId_funcionario();
        String usuario = funcionario.getUsuario();
        
        jLabelGetId.setText(String.valueOf(id));
        jLabelGetUsuario.setText(usuario);
        jLabelGetId.setVisible(false);
    }
    
    //Altera funcionario
    public void alteraFuncionario() throws SQLException{
        Funcionario funcionario = new Funcionario();
        FuncionarioDao dao = new FuncionarioDao();        
        
        funcionario.setNome_funcionario(jTextFieldNome.getText());
        funcionario.setSobrenome_funcionario(jTextFieldSobrenome.getText());
        funcionario.setData_nascimento_funcionario(jFormattedTextFieldDataNascimento.getText());
        funcionario.setEndereco_funcionario(jTextFieldRua.getText());
        funcionario.setBairro_funcionario(jTextFieldBairro.getText());
        funcionario.setNumero_casa_funcinario(Integer.valueOf(jTextFieldNumero.getText()));
        funcionario.setComplemento_funcionario(jTextFieldComplemento.getText());
        funcionario.setCargo_funcionario(jTextFieldCargo.getText());
        funcionario.setSalario_funcionario(Double.valueOf(jTextFieldSalario.getText()));        
        funcionario.setUsuario(jTextFieldUsuario.getText());
        funcionario.setSenha(jPasswordFieldSenha.getText());
        funcionario.setId_funcionario(Long.valueOf(jTextFieldIdFuncionario.getText()));
        
        dao.alteraFuncionario(funcionario);
        JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso!");
    }
    
    //Exclui cliente
    public void excluirFuncionario() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_NO_OPTION) {
            this.conexao = ConexaoBD.getConexao();
            String nome = jTextFieldNome.getText();
            String sql = "delete from funcionario where nome_funcionario=?";
            try {
                PreparedStatement stmt = this.conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Funcionario excluido com sucesso!");
                
                limpaCampos();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario! = " + ex);
            }
        }
    }

    //Salva funcionario
    public void salvaFuncionario() {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome_funcionario(jTextFieldNome.getText());
        funcionario.setSobrenome_funcionario(jTextFieldSobrenome.getText());
        funcionario.setData_nascimento_funcionario(jFormattedTextFieldDataNascimento.getText());
        funcionario.setEndereco_funcionario(jTextFieldRua.getText());
        funcionario.setBairro_funcionario(jTextFieldBairro.getText());
        funcionario.setNumero_casa_funcinario(Integer.parseInt(jTextFieldNumero.getText()));
        funcionario.setComplemento_funcionario(jTextFieldComplemento.getText());
        funcionario.setCargo_funcionario(jTextFieldCargo.getText());
        funcionario.setSalario_funcionario(Double.parseDouble(jTextFieldSalario.getText()));
        funcionario.setUsuario(jTextFieldUsuario.getText());
        funcionario.setSenha(jPasswordFieldSenha.getText());

        try {
            FuncionarioDao dao = new FuncionarioDao();
            dao.adicionaFuncionario(funcionario);
            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
            desabilitaCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionario! = " + ex);
        }
    }
    
    public void pesquisaFuncionario(){
        //Pesquisa funcionario no JDialog
        PesquisaFuncionario pesquisa = new PesquisaFuncionario(this, true);
        pesquisa.setVisible(true);
        
        Long id = pesquisa.getId_funcionario();
        String nome = pesquisa.getNome_funcionario();
        String sobrenome = pesquisa.getSobrenome_funcionario();
        String dataNasc = pesquisa.getData_nascimento_funcionario();
        String endereco = pesquisa.getEndereco_funcionario();
        String bairro = pesquisa.getBairro_funcionario();
        int numero = pesquisa.getNumero_casa_funcinario();
        String compelemento = pesquisa.getComplemento_funcionario();
        String cargo = pesquisa.getCargo_funcionario();
        Double salario = pesquisa.getSalario_funcionario();
        String usuario = pesquisa.getUsuario();
        String senha = pesquisa.getSenha();
        
        jTextFieldIdFuncionario.setText(String.valueOf(id));
        jTextFieldNome.setText(nome);
        jTextFieldSobrenome.setText(sobrenome);
        jFormattedTextFieldDataNascimento.setText(dataNasc);
        jTextFieldRua.setText(endereco);
        jTextFieldBairro.setText(bairro);
        jTextFieldNumero.setText(String.valueOf(numero));
        jTextFieldComplemento.setText(compelemento);
        jTextFieldCargo.setText(cargo);
        jTextFieldSalario.setText(String.valueOf(salario));
        jTextFieldUsuario.setText(usuario);
        jPasswordFieldSenha.setText(senha);
        
        habilitaCampos();
    }

    //Limpa os campos
    public void limpaCampos() {
        jTextFieldIdFuncionario.setText(null);
        jTextFieldNome.setText(null);
        jTextFieldSobrenome.setText(null);
        jFormattedTextFieldDataNascimento.setText(null);
        jTextFieldRua.setText(null);
        jTextFieldBairro.setText(null);
        jTextFieldNumero.setText(null);
        jTextFieldComplemento.setText(null);
        jTextFieldCargo.setText(null);
        jTextFieldSalario.setText(null);
        jTextFieldUsuario.setText(null);
        jPasswordFieldSenha.setText(null);
    }

    //Desabilta campos
    public void desabilitaCampos() {
        jTextFieldIdFuncionario.setEditable(false);
        jTextFieldNome.setEditable(false);
        jTextFieldSobrenome.setEditable(false);
        jFormattedTextFieldDataNascimento.setEditable(false);
        jTextFieldRua.setEditable(false);
        jTextFieldBairro.setEditable(false);
        jTextFieldNumero.setEditable(false);
        jTextFieldComplemento.setEditable(false);
        jTextFieldCargo.setEditable(false);
        jTextFieldSalario.setEditable(false);
        jTextFieldUsuario.setEditable(false);
        jPasswordFieldSenha.setEditable(false);
    }

    //Habilta campos
    public void habilitaCampos() {
        jTextFieldNome.setEditable(true);
        jTextFieldSobrenome.setEditable(true);
        jFormattedTextFieldDataNascimento.setEditable(true);
        jTextFieldRua.setEditable(true);
        jTextFieldBairro.setEditable(true);
        jTextFieldNumero.setEditable(true);
        jTextFieldComplemento.setEditable(true);
        jTextFieldCargo.setEditable(true);
        jTextFieldSalario.setEditable(true);
        jTextFieldUsuario.setEditable(true);
        jPasswordFieldSenha.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanelDadosPessoais = new javax.swing.JPanel();
        jLabelIdFuncionario = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelSobrenome = new javax.swing.JLabel();
        jLabelDataNascimento = new javax.swing.JLabel();
        jLabelRua = new javax.swing.JLabel();
        jLabelBairro = new javax.swing.JLabel();
        jLabelNumero = new javax.swing.JLabel();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldIdFuncionario = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldSobrenome = new javax.swing.JTextField();
        jTextFieldRua = new javax.swing.JTextField();
        jTextFieldBairro = new javax.swing.JTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jTextFieldComplemento = new javax.swing.JTextField();
        jFormattedTextFieldDataNascimento = new javax.swing.JFormattedTextField();
        jPanelInformacoes = new javax.swing.JPanel();
        jLabelCargo = new javax.swing.JLabel();
        jLabelSalario = new javax.swing.JLabel();
        jTextFieldCargo = new javax.swing.JTextField();
        jTextFieldSalario = new javax.swing.JTextField();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jLabelGetId = new javax.swing.JLabel();
        jLabelGetUsuario = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();

        jButton6.setText("jButton6");

        jLabel9.setText("jLabel9");

        jLabel12.setText("jLabel12");

        jTextField11.setText("jTextField11");

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de funcionario");

        jPanelDadosPessoais.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));

        jLabelIdFuncionario.setText("Id Funcionário");

        jLabelNome.setText("Nome");

        jLabelSobrenome.setText("Sobrenome");

        jLabelDataNascimento.setText("Data Nascimento");

        jLabelRua.setText("Endereço");

        jLabelBairro.setText("Bairro");

        jLabelNumero.setText("Número");

        jLabelComplemento.setText("Complemento");

        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyPressed(evt);
            }
        });

        jTextFieldSobrenome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSobrenomeKeyPressed(evt);
            }
        });

        jTextFieldRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRuaKeyPressed(evt);
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
            jFormattedTextFieldDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDataNascimentoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDadosPessoaisLayout = new javax.swing.GroupLayout(jPanelDadosPessoais);
        jPanelDadosPessoais.setLayout(jPanelDadosPessoaisLayout);
        jPanelDadosPessoaisLayout.setHorizontalGroup(
            jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelRua)
                            .addComponent(jLabelComplemento)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jTextFieldRua)
                            .addComponent(jTextFieldIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldBairro)
                            .addComponent(jTextFieldSobrenome)
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBairro)
                                    .addComponent(jLabelSobrenome))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDataNascimento)
                                    .addComponent(jLabelNumero)))
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdFuncionario)
                            .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelDadosPessoaisLayout.setVerticalGroup(
            jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIdFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jLabelSobrenome)
                    .addComponent(jLabelDataNascimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRua)
                    .addComponent(jLabelBairro)
                    .addComponent(jLabelNumero))
                .addGap(7, 7, 7)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelComplemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelInformacoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));

        jLabelCargo.setText("Cargo");

        jLabelSalario.setText("Salario");

        jTextFieldCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCargoKeyPressed(evt);
            }
        });

        jTextFieldSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSalarioKeyPressed(evt);
            }
        });

        jLabelUsuario.setText("Usuário");

        jLabelSenha.setText("Senha");

        jTextFieldUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldUsuarioKeyPressed(evt);
            }
        });

        jPasswordFieldSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldSenhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInformacoesLayout = new javax.swing.GroupLayout(jPanelInformacoes);
        jPanelInformacoes.setLayout(jPanelInformacoesLayout);
        jPanelInformacoesLayout.setHorizontalGroup(
            jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInformacoesLayout.createSequentialGroup()
                        .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCargo)
                            .addComponent(jTextFieldCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)
                        .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUsuario)
                            .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelInformacoesLayout.createSequentialGroup()
                        .addComponent(jLabelSalario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSenha)
                        .addGap(164, 164, 164))))
        );
        jPanelInformacoesLayout.setVerticalGroup(
            jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCargo)
                    .addComponent(jLabelUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSalario)
                    .addComponent(jLabelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelDadosPessoais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelNomeUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelGetId, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonVoltar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelGetUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDadosPessoais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInformacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonVoltar)
                    .addComponent(jButtonNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGetId, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGetUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeUsuario)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        pesquisaFuncionario();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitaCampos();
        limpaCampos();
        jTextFieldNome.requestFocus();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvaFuncionario();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            excluirFuncionario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario! = " + ex);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            alteraFuncionario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar funcionario! = " + ex);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTextFieldNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldSobrenome.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldNomeKeyPressed

    private void jTextFieldSobrenomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSobrenomeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jFormattedTextFieldDataNascimento.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldSobrenomeKeyPressed

    private void jFormattedTextFieldDataNascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataNascimentoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldRua.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFieldDataNascimentoKeyPressed

    private void jTextFieldRuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRuaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldBairro.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldRuaKeyPressed

    private void jTextFieldBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBairroKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldNumero.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldBairroKeyPressed

    private void jTextFieldNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumeroKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldComplemento.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldNumeroKeyPressed

    private void jTextFieldComplementoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldComplementoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldCargo.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldComplementoKeyPressed

    private void jTextFieldCargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCargoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldSalario.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldCargoKeyPressed

    private void jTextFieldSalarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSalarioKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jTextFieldUsuario.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldSalarioKeyPressed

    private void jTextFieldUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jPasswordFieldSenha.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldUsuarioKeyPressed

    private void jPasswordFieldSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonSalvar.requestFocus();
        }
    }//GEN-LAST:event_jPasswordFieldSenhaKeyPressed

    private void jButtonSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSalvarKeyPressed
        salvaFuncionario();
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
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataNascimento;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCargo;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelDataNascimento;
    private javax.swing.JLabel jLabelGetId;
    private javax.swing.JLabel jLabelGetUsuario;
    private javax.swing.JLabel jLabelIdFuncionario;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelRua;
    private javax.swing.JLabel jLabelSalario;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelSobrenome;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelDadosPessoais;
    private javax.swing.JPanel jPanelInformacoes;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCargo;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldIdFuncionario;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRua;
    private javax.swing.JTextField jTextFieldSalario;
    private javax.swing.JTextField jTextFieldSobrenome;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
