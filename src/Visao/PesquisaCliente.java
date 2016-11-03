package Visao;

import controle.ClienteDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;

public class PesquisaCliente extends javax.swing.JDialog {

    //Construtor
    public PesquisaCliente(java.awt.Frame parent, boolean modal) {
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
   
    private Long id_cliente;
    private String nome_cliente;
    private String cpf_cliente;
    private String data_nascimento_cliente;
    private String sexo_cliente;
    private String endereco_cliente;
    private String bairro_cliente;
    private String complemento_cliente;
    private String telefone_residencial_cliente;
    private String telefone_celular_cliente;
    private int numero_casa_cliente;
    private String cidade;
    
    //GETTERS AND SETTERS
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getData_nascimento_cliente() {
        return data_nascimento_cliente;
    }
    
    public void setData_nascimento_cliente(String data_nascimento_cliente) {    
        this.data_nascimento_cliente = data_nascimento_cliente;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getSexo_cliente() {
        return sexo_cliente;
    }

    public void setSexo_cliente(String sexo_cliente) {
        this.sexo_cliente = sexo_cliente;
    }

    public String getEndereco_cliente() {
        return endereco_cliente;
    }

    public void setEndereco_cliente(String endereco_cliente) {
        this.endereco_cliente = endereco_cliente;
    }

    public String getBairro_cliente() {
        return bairro_cliente;
    }

    public void setBairro_cliente(String bairro_cliente) {
        this.bairro_cliente = bairro_cliente;
    }

    public String getComplemento_cliente() {
        return complemento_cliente;
    }

    public void setComplemento_cliente(String complemento_cliente) {
        this.complemento_cliente = complemento_cliente;
    }

    public String getTelefone_residencial_cliente() {
        return telefone_residencial_cliente;
    }

    public void setTelefone_residencial_cliente(String telefone_residencial_cliente) {
        this.telefone_residencial_cliente = telefone_residencial_cliente;
    }

    public String getTelefone_celular_cliente() {
        return telefone_celular_cliente;
    }

    public void setTelefone_celular_cliente(String telefone_celular_cliente) {
        this.telefone_celular_cliente = telefone_celular_cliente;
    }

    public int getNumero_casa_cliente() {
        return numero_casa_cliente;
    }

    public void setNumero_casa_cliente(int numero_casa_cliente) {
        this.numero_casa_cliente = numero_casa_cliente;
    } 
    
    //----------------------------------------------------------------------------------------------------
    
    
    DefaultTableModel tmCliente = new DefaultTableModel(null, new String[]{"Id", "Nome","Data Nasc.", "CPF" , "S"
            + "exo", "Endereco", "Nº", "Bairro", "Complemento", "Tel. Celular", "Tel.Residencial", "Cidade"});
    List<Cliente> cliente;
    ListSelectionModel lsCliente;
    
    //Metodo para mostrar pesquisa no JTable
    public void mostraPesquisa(List<Cliente> cliente) {
        while(tmCliente.getRowCount()>0){
            tmCliente.removeRow(0);
        }
        if (cliente.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!");
        } else {
            String[] linhaCliente = new String[]{null, null, null, null, null, null, null, null, null, null, null, null};

            for (int i = 0; i < cliente.size(); i++) {
                tmCliente.addRow(linhaCliente);

                tmCliente.setValueAt(cliente.get(i).getId_cliente(), i, 0);
                tmCliente.setValueAt(cliente.get(i).getNome_cliente(), i, 1);
                tmCliente.setValueAt(cliente.get(i).getData_nascimento_cliente(), i, 2);
                tmCliente.setValueAt(cliente.get(i).getCpf_cliente(), i, 3);
                tmCliente.setValueAt(cliente.get(i).getSexo_cliente(), i, 4);
                tmCliente.setValueAt(cliente.get(i).getEndereco_cliente(), i, 5);
                tmCliente.setValueAt(cliente.get(i).getNumero_casa_cliente(), i, 6);
                tmCliente.setValueAt(cliente.get(i).getBairro_cliente(), i, 7);
                tmCliente.setValueAt(cliente.get(i).getComplemento_cliente(), i, 8);
                tmCliente.setValueAt(cliente.get(i).getTelefone_celular_cliente(), i, 9);
                tmCliente.setValueAt(cliente.get(i).getTelefone_residencial_cliente(), i, 10); 
                tmCliente.setValueAt(cliente.get(i).getCidade(), i, 11);
            }
        }
    }

    //Campo de pesquisa
    public void pesquisaCampo() {
        try {
            ClienteDao dao = new ClienteDao();
            cliente = dao.getListaCliente("%" + jTextFieldPesquisar.getText() + "%");
            mostraPesquisa(cliente);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar! = " + ex);
        }
    }
    
    //Seta os valores nos get and set
    public void setarDados() {
        int linhaselecionada = jTablePesquisarCliente.getSelectedRow();

        if (linhaselecionada != -1) {
            id_cliente = Long.valueOf(jTablePesquisarCliente.getValueAt(linhaselecionada, 0).toString());
            nome_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 1).toString();
            data_nascimento_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 2).toString();
            cpf_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 3).toString(); 
            sexo_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 4).toString();            
            endereco_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 5).toString();
            numero_casa_cliente = Integer.valueOf(jTablePesquisarCliente.getValueAt(linhaselecionada, 6).toString());
            bairro_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 7).toString();
            complemento_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 8).toString();
            telefone_residencial_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 9).toString();
            telefone_celular_cliente = jTablePesquisarCliente.getValueAt(linhaselecionada, 10).toString();
            cidade = jTablePesquisarCliente.getValueAt(linhaselecionada, 11).toString();

            dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum cliente selecionado!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPesquisar = new javax.swing.JButton();
        jLabelPesquisar = new javax.swing.JLabel();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisarCliente = new javax.swing.JTable();
        jButtonDetalhes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa cliente");

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

        jTablePesquisarCliente.setModel(tmCliente);
        jTablePesquisarCliente.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTablePesquisarCliente);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPesquisar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDetalhes)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        pesquisaCampo();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalhesActionPerformed
        setarDados();
    }//GEN-LAST:event_jButtonDetalhesActionPerformed

    private void jTextFieldPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonPesquisar.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyPressed

    private void jButtonPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonPesquisarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            pesquisaCampo();
        }
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
            java.util.logging.Logger.getLogger(PesquisaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisaCliente dialog = new PesquisaCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable jTablePesquisarCliente;
    private javax.swing.JTextField jTextFieldPesquisar;
    // End of variables declaration//GEN-END:variables
}
