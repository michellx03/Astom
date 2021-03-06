package Visao;

import controle.VeiculoDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Veiculo;

public class VendasPesquisaVeiculo extends javax.swing.JDialog {
    
    private Long id_veiculo;
    private String modelo_veiculo;
    private String cor_veiculo;
    private int ano_veiculo;
    private String placa_veiculo;
    private String estado_veiculo;
    private String marca_veiculo;
    private String observacoes_veiculo;
    private double valor_minimo_veiculo;
    private double valor_medio_veiculo;
    private double valor_maximo_veiculo;
    private int quantidade_veiculo;
    private String tipo_veiculo;
    
    public String getTipo_veiculo() {
        return tipo_veiculo;
    }

    //GETTER AND SETTER
    public void setTipo_veiculo(String tipo_veiculo) {    
        this.tipo_veiculo = tipo_veiculo;
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getCor_veiculo() {
        return cor_veiculo;
    }

    public void setCor_veiculo(String cor_veiculo) {
        this.cor_veiculo = cor_veiculo;
    }

    public int getAno_veiculo() {
        return ano_veiculo;
    }

    public void setAno_veiculo(int ano_veiculo) {
        this.ano_veiculo = ano_veiculo;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }

    public String getEstado_veiculo() {
        return estado_veiculo;
    }

    public void setEstado_veiculo(String estado_veiculo) {
        this.estado_veiculo = estado_veiculo;
    }

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public String getObservacoes_veiculo() {
        return observacoes_veiculo;
    }

    public void setObservacoes_veiculo(String observacao_veiculo) {
        this.observacoes_veiculo = observacao_veiculo;
    }

    public double getValor_minimo_veiculo() {
        return valor_minimo_veiculo;
    }

    public void setValor_minimo_veiculo(double valor_minimo_veiculo) {
        this.valor_minimo_veiculo = valor_minimo_veiculo;
    }

    public double getValor_medio_veiculo() {
        return valor_medio_veiculo;
    }

    public void setValor_medio_veiculo(double valor_medio_veiculo) {
        this.valor_medio_veiculo = valor_medio_veiculo;
    }

    public double getValor_maximo_veiculo() {
        return valor_maximo_veiculo;
    }

    public void setValor_maximo_veiculo(double valor_maximo_veiculo) {
        this.valor_maximo_veiculo = valor_maximo_veiculo;
    }

    public int getQuantidade_veiculo() {
        return quantidade_veiculo;
    }

    public void setQuantidade_veiculo(int quantidade_veiculo) {
        this.quantidade_veiculo = quantidade_veiculo;
    }
    
    //-------------------------------------------------------------------------------------------------------

     DefaultTableModel tmVeiculo = new DefaultTableModel(null, new String[]{"Id", "Modelo", "Ano", "Placa", "Estado",
        "Marca", "Tipo", "Quantidade"});
    List<Veiculo> veiculo;
    ListSelectionModel lsVeiculo;

    //Construtor
    public VendasPesquisaVeiculo(java.awt.Frame parent, boolean modal) {
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
    public void mostraPesquisa(List<Veiculo> veiculo) {
        while(tmVeiculo.getRowCount()>0){
            tmVeiculo.removeRow(0);
        }
        if (veiculo.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum veiculo cadastrado!");
        } else {
            String[] linhaVeiculo = new String[]{null, null, null, null, null, null, null, null};

            for (int i = 0; i < veiculo.size(); i++) {
                tmVeiculo.addRow(linhaVeiculo);

                tmVeiculo.setValueAt(veiculo.get(i).getId_veiculo(), i, 0);
                tmVeiculo.setValueAt(veiculo.get(i).getModelo_veiculo(), i, 1);
                tmVeiculo.setValueAt(veiculo.get(i).getAno_veiculo(), i, 2);
                tmVeiculo.setValueAt(veiculo.get(i).getPlaca_veiculo(), i, 3);
                tmVeiculo.setValueAt(veiculo.get(i).getEstado_veiculo(), i, 4);
                tmVeiculo.setValueAt(veiculo.get(i).getMarca_veiculo(), i, 5);
                tmVeiculo.setValueAt(veiculo.get(i).getTipo_veiculo(), i, 6);
                tmVeiculo.setValueAt(veiculo.get(i).getQuantidade_veiculo(), i, 7);
            }
        }
    }

    //Campo de pesquisa
    public void campoPesquisa() {
        try {
            VeiculoDao dao = new VeiculoDao();
            veiculo = dao.getListaVeiculoExecao("%" + jTextFieldPesquisar.getText() + "%");
            mostraPesquisa(veiculo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar! = " + ex);
        }
    }
    
    //Seta os valores nos get and set
    public void setarDados() {
        int linhaselecionada = jTablePesquisarVeiculo.getSelectedRow();
        
        if (linhaselecionada!=-1) {
           
                id_veiculo = Long.valueOf(jTablePesquisarVeiculo.getValueAt(linhaselecionada, 0).toString());
                modelo_veiculo = jTablePesquisarVeiculo.getValueAt(linhaselecionada, 1).toString();
                ano_veiculo = Integer.valueOf(jTablePesquisarVeiculo.getValueAt(linhaselecionada, 2).toString());
                placa_veiculo = jTablePesquisarVeiculo.getValueAt(linhaselecionada, 3).toString();
                estado_veiculo = jTablePesquisarVeiculo.getValueAt(linhaselecionada, 4).toString();
                marca_veiculo = jTablePesquisarVeiculo.getValueAt(linhaselecionada, 5).toString();
                tipo_veiculo = jTablePesquisarVeiculo.getValueAt(linhaselecionada, 6).toString();
                quantidade_veiculo = Integer.valueOf(jTablePesquisarVeiculo.getValueAt(linhaselecionada, 7).toString());
                
                dispose();      
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum veiculo selecionado!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPesquisar = new javax.swing.JLabel();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisarVeiculo = new javax.swing.JTable();
        jButtonOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa veiculo");

        jLabelPesquisar.setText("Pesquisar");

        jTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyPressed(evt);
            }
        });

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

        jTablePesquisarVeiculo.setModel(tmVeiculo);
        jTablePesquisarVeiculo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTablePesquisarVeiculo);

        jButtonOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/accept.png"))); // NOI18N
        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPesquisar)
                        .addGap(88, 88, 88))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonOk)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPesquisar)
                        .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonOk)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        campoPesquisa();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        setarDados();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jTextFieldPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            jButtonPesquisar.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyPressed

    private void jButtonPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonPesquisarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            campoPesquisa();
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
            java.util.logging.Logger.getLogger(VendasPesquisaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendasPesquisaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendasPesquisaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendasPesquisaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VendasPesquisaVeiculo dialog = new VendasPesquisaVeiculo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JLabel jLabelPesquisar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePesquisarVeiculo;
    private javax.swing.JTextField jTextFieldPesquisar;
    // End of variables declaration//GEN-END:variables
}
