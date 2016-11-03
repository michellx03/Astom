package Visao;

import controle.FuncionarioDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Funcionario;

public class PesquisaFuncionario extends javax.swing.JDialog {
    
    private Long id_funcionario;
    private String nome_funcionario;
    private String sobrenome_funcionario;
    private String data_nascimento_funcionario;
    private String endereco_funcionario;
    private String bairro_funcionario;
    private int numero_casa_funcinario;
    private String complemento_funcionario;
    private String cargo_funcionario;
    private double salario_funcionario;
    private String usuario;
    private String senha;

    //GETTER AND SETTER
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {    
        this.senha = senha;
    }

    public Long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getSobrenome_funcionario() {
        return sobrenome_funcionario;
    }

    public void setSobrenome_funcionario(String sobrenome_funcionario) {
        this.sobrenome_funcionario = sobrenome_funcionario;
    }

    public String getData_nascimento_funcionario() {
        return data_nascimento_funcionario;
    }

    public void setData_nascimento_funcionario(String data_nascimento_funcionario) {
        this.data_nascimento_funcionario = data_nascimento_funcionario;
    }

    public String getEndereco_funcionario() {
        return endereco_funcionario;
    }

    public void setEndereco_funcionario(String endereco_funcionario) {
        this.endereco_funcionario = endereco_funcionario;
    }

    public String getBairro_funcionario() {
        return bairro_funcionario;
    }

    public void setBairro_funcionario(String bairro_funcionario) {
        this.bairro_funcionario = bairro_funcionario;
    }

    public int getNumero_casa_funcinario() {
        return numero_casa_funcinario;
    }

    public void setNumero_casa_funcinario(int numero_casa_funcinario) {
        this.numero_casa_funcinario = numero_casa_funcinario;
    }

    public String getComplemento_funcionario() {
        return complemento_funcionario;
    }

    public void setComplemento_funcionario(String complemento_funcionario) {
        this.complemento_funcionario = complemento_funcionario;
    }

    public String getCargo_funcionario() {
        return cargo_funcionario;
    }

    public void setCargo_funcionario(String cargo_funcionario) {
        this.cargo_funcionario = cargo_funcionario;
    }

    public double getSalario_funcionario() {
        return salario_funcionario;
    }

    public void setSalario_funcionario(double salario_funcionario) {
        this.salario_funcionario = salario_funcionario;
    }    
    //-------------------------------------------------------------------------------------------------------

    DefaultTableModel tmFuncionario = new DefaultTableModel(null, new String[]{"Id", "Nome", "Sobrenome","Data Nasc.","Endereco",
        "Bairro", "Numero", "Complemento", "Cargo", "Salario", "Usuario", "Senha"});
    List<Funcionario> funcionario;
    ListSelectionModel lsFuncionario;

    //Construtor
    public PesquisaFuncionario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setIcon();
    }
    
    private void setIcon(){
        URL url = this.getClass().getResource("../imagens/ParkingLot.png"); 
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url); 
        this.setIconImage(imagemTitulo);
    }
    
    //Metodo para mostrar pesquisa no JTable
    public void mostraPesquisa(List<Funcionario> funcionario) {
        while(tmFuncionario.getRowCount()>0){
            tmFuncionario.removeRow(0);
        }
        if (funcionario.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionario cadastrado!");
        } else {
            String[] linhaFuncionario = new String[]{null, null, null, null, null, null, null, null, null, null, null, null};

            for (int i = 0; i < funcionario.size(); i++) {
                tmFuncionario.addRow(linhaFuncionario);

                tmFuncionario.setValueAt(funcionario.get(i).getId_funcionario(), i, 0);
                tmFuncionario.setValueAt(funcionario.get(i).getNome_funcionario(), i, 1);
                tmFuncionario.setValueAt(funcionario.get(i).getSobrenome_funcionario(), i, 2);
                tmFuncionario.setValueAt(funcionario.get(i).getData_nascimento_funcionario(), i, 3);
                tmFuncionario.setValueAt(funcionario.get(i).getEndereco_funcionario(), i, 4);
                tmFuncionario.setValueAt(funcionario.get(i).getBairro_funcionario(), i, 5);
                tmFuncionario.setValueAt(funcionario.get(i).getNumero_casa_funcinario(), i, 6);
                tmFuncionario.setValueAt(funcionario.get(i).getComplemento_funcionario(), i, 7);
                tmFuncionario.setValueAt(funcionario.get(i).getCargo_funcionario(), i, 8);
                tmFuncionario.setValueAt(funcionario.get(i).getSalario_funcionario(), i, 9);
                tmFuncionario.setValueAt(funcionario.get(i).getUsuario(), i, 10);
                tmFuncionario.setValueAt(funcionario.get(i).getSenha(), i, 11);
                
                jTablePesquisarFuncionario.getColumnModel().getColumn(11).setPreferredWidth(0);//Minimza a coluna da senha
            }
        }
    }
    
    public void setaDados(){
         int linhaselecionada = jTablePesquisarFuncionario.getSelectedRow();
        
        if (linhaselecionada!=-1) {
                id_funcionario = Long.valueOf(jTablePesquisarFuncionario.getValueAt(linhaselecionada, 0).toString());
                nome_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 1).toString();
                sobrenome_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 2).toString();
                data_nascimento_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 3).toString();
                endereco_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 4).toString();
                bairro_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 5).toString();
                numero_casa_funcinario = Integer.valueOf(jTablePesquisarFuncionario.getValueAt(linhaselecionada, 6).toString());
                complemento_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 7).toString();
                cargo_funcionario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 8).toString();
                salario_funcionario = Double.valueOf(jTablePesquisarFuncionario.getValueAt(linhaselecionada, 9).toString());
                usuario = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 10).toString();
                senha = jTablePesquisarFuncionario.getValueAt(linhaselecionada, 11).toString();
                
                dispose();
            }   else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum funcionario selecionado!");
        }
    }
    
    //Campo de pesquisa
    public void campoPesquisa() {
        try {
            FuncionarioDao dao = new FuncionarioDao();
            funcionario = dao.getListaFuncionario("%" + jTextFieldPesquisar.getText() + "%");
            mostraPesquisa(funcionario);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar! = " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPesquisar = new javax.swing.JButton();
        jLabelPesquisar = new javax.swing.JLabel();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisarFuncionario = new javax.swing.JTable();
        jButtonDetalhes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa funcionario");

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
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

        jLabelPesquisar.setText("Pesquisar");

        jTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyPressed(evt);
            }
        });

        jTablePesquisarFuncionario.setModel(tmFuncionario);
        jTablePesquisarFuncionario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTablePesquisarFuncionario);

        jButtonDetalhes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/book_open.png"))); // NOI18N
        jButtonDetalhes.setText("Detalhes");
        jButtonDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetalhesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDetalhes)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPesquisar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPesquisar)
                        .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDetalhes)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        campoPesquisa();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalhesActionPerformed
        setaDados();
    }//GEN-LAST:event_jButtonDetalhesActionPerformed

    private void jTextFieldPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonPesquisar.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyPressed

    private void jButtonPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonPesquisarKeyPressed
        campoPesquisa();
    }//GEN-LAST:event_jButtonPesquisarKeyPressed

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
            java.util.logging.Logger.getLogger(PesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisaFuncionario dialog = new PesquisaFuncionario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalhes;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JLabel jLabelPesquisar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePesquisarFuncionario;
    private javax.swing.JTextField jTextFieldPesquisar;
    // End of variables declaration//GEN-END:variables
}
