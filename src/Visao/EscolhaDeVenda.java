package Visao;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Funcionario;

public class EscolhaDeVenda extends javax.swing.JFrame {

    //Construtor
    public EscolhaDeVenda() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIcon();
        desabilitaUsuario();
    }
    
    private void setIcon(){
        //Seta icone no sistema
        URL url = this.getClass().getResource("../imagens/ParkingLot.png"); 
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url); 
        this.setIconImage(imagemTitulo);
    }
    
    public void obtemUsuarioVendaAvista() throws SQLException{
        //Obtem usuario e id para setar no Frame de cadastro de veiculo
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(Long.valueOf(jLabelIdFuncionario.getText()));
        funcionario.setUsuario(jLabelUsuario.getText());
        
        VendasAVista vendasAvista = new VendasAVista();
        vendasAvista.exportaEscolha(funcionario);
        vendasAvista.setVisible(true);
        dispose();
    }
    
    public void obtemUsuarioVendaFinanciada() throws SQLException{
        //Obtem usuario e id para setar no Frame de cadastro de veiculo
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(Long.valueOf(jLabelIdFuncionario.getText()));
        funcionario.setUsuario(jLabelUsuario.getText());
        
        VendasFinanciadas vendasFinanciadas = new VendasFinanciadas();
        vendasFinanciadas.exportaEscolha(funcionario);
        vendasFinanciadas.setVisible(true);
        dispose();
    }
    
    public void exportaTelaPrincipal(Funcionario funcionario){
        //Da tela principal importa para este metodo onde e setado os valores nos campos
        Long id = funcionario.getId_funcionario();
        String usuario = funcionario.getUsuario();
        
        jLabelIdFuncionario.setText(String.valueOf(id));
        jLabelUsuario.setText(usuario);
        jLabelIdFuncionario.setVisible(false);
    }
    
    public void desabilitaUsuario(){
        jLabelIdFuncionario.setVisible(false);
        jLabelNomeUsuario.setVisible(false);
        jLabelUsuario.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonAvista = new javax.swing.JButton();
        jButtonFinanciamento = new javax.swing.JButton();
        jLabelIdFuncionario = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Opção de venda");

        jButtonAvista.setText("À Vista");
        jButtonAvista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAvistaActionPerformed(evt);
            }
        });

        jButtonFinanciamento.setText("Financiamento");
        jButtonFinanciamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinanciamentoActionPerformed(evt);
            }
        });

        jLabelNomeUsuario.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelNomeUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonAvista)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonFinanciamento)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAvista)
                    .addComponent(jButtonFinanciamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelNomeUsuario, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFinanciamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinanciamentoActionPerformed
        try {
            obtemUsuarioVendaFinanciada();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir vendas! = " + ex);
        }
    }//GEN-LAST:event_jButtonFinanciamentoActionPerformed

    private void jButtonAvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAvistaActionPerformed
        try {
            obtemUsuarioVendaAvista();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao abrir vendas! = " + ex);
        }          
    }//GEN-LAST:event_jButtonAvistaActionPerformed

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
            java.util.logging.Logger.getLogger(EscolhaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EscolhaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EscolhaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EscolhaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EscolhaDeVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAvista;
    private javax.swing.JButton jButtonFinanciamento;
    private javax.swing.JLabel jLabelIdFuncionario;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelUsuario;
    // End of variables declaration//GEN-END:variables
}
