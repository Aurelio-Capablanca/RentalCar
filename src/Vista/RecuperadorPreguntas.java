
package Vista;
import Controlador.Conexion;
import Modelo.FuncionesRecuperador;
import Controlador.ConstructorRecuperacion;
import Modelo.CifrarClave;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author samue
 */
public class RecuperadorPreguntas extends javax.swing.JFrame {

    ConstructorRecuperacion cr = new ConstructorRecuperacion();
    FuncionesRecuperador fr= new FuncionesRecuperador();
    Connection con2;
    Statement orden;
    ResultSet rs;
    Conexion con = new Conexion();
    DefaultComboBoxModel modelo;
    CifrarClave obj3 = new CifrarClave();
    
    public RecuperadorPreguntas() {
        initComponents();
        setLocationRelativeTo(null);
        cmbPregunta1.setEnabled(false);
        cmbPregunta2.setEnabled(false);
        BtnGuardar.setEnabled(false);
        txtRespuesta1.setEnabled(false);
        txtRespuesta3.setEnabled(false);
        txtNuevaContraseña.setEnabled(false);
        
//        fr.getValues();
//        cmbPregunta1.setModel(fr.getValues());
    }
  
 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JLabel();
        btnRecuperadores = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbPregunta2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmbPregunta1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtRespuesta1 = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtRespuesta3 = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        BtnGuardar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtNuevaContraseña = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        btnIngresar1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtUsuario2 = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        btnVerificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Recuperador por Preguntas de seguridad");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/close.png"))); // NOI18N
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, 32));

        btnRecuperadores.setBackground(new java.awt.Color(204, 204, 204));
        btnRecuperadores.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        btnRecuperadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/recu.png"))); // NOI18N
        btnRecuperadores.setText("Volver a Recuperadores");
        btnRecuperadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperadoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecuperadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 190, 66));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Responde a las preguntas");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PREGUNTA 2:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        cmbPregunta2.setBackground(new java.awt.Color(51, 51, 51));
        cmbPregunta2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbPregunta2.setForeground(new java.awt.Color(255, 255, 255));
        cmbPregunta2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPregunta2.setPreferredSize(new java.awt.Dimension(64, 25));
        jPanel3.add(cmbPregunta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 305, 35));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PREGUNTA 1:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        cmbPregunta1.setBackground(new java.awt.Color(51, 51, 51));
        cmbPregunta1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbPregunta1.setForeground(new java.awt.Color(255, 255, 255));
        cmbPregunta1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPregunta1.setPreferredSize(new java.awt.Dimension(64, 25));
        jPanel3.add(cmbPregunta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 305, 35));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("RESPUESTA:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        txtRespuesta1.setBackground(new java.awt.Color(51, 51, 51));
        txtRespuesta1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtRespuesta1.setForeground(new java.awt.Color(255, 255, 255));
        txtRespuesta1.setBorder(null);
        txtRespuesta1.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtRespuesta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 305, 30));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 305, 10));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("RESPUESTA:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        txtRespuesta3.setBackground(new java.awt.Color(51, 51, 51));
        txtRespuesta3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtRespuesta3.setForeground(new java.awt.Color(255, 255, 255));
        txtRespuesta3.setBorder(null);
        txtRespuesta3.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtRespuesta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 305, 30));
        jPanel3.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 305, 10));

        BtnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        BtnGuardar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        BtnGuardar.setText("Ingresar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 110, 100));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 230, 810, 200));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("NUEVA CONTRASEÑA:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        txtNuevaContraseña.setBackground(new java.awt.Color(51, 51, 51));
        txtNuevaContraseña.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtNuevaContraseña.setForeground(new java.awt.Color(255, 255, 255));
        txtNuevaContraseña.setBorder(null);
        txtNuevaContraseña.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtNuevaContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 305, 35));
        jPanel2.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 305, 10));

        btnIngresar1.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnIngresar1.setText("Guardar");
        btnIngresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 110, 100));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 449, 810, 140));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("VERIFICA TU USUARIO:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        txtUsuario2.setBackground(new java.awt.Color(33, 33, 33));
        txtUsuario2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtUsuario2.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario2.setBorder(null);
        txtUsuario2.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtUsuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 305, 35));
        jPanel1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 305, 10));

        btnVerificar.setBackground(new java.awt.Color(204, 204, 204));
        btnVerificar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 130, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnRecuperadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperadoresActionPerformed
        RecuperadoresContraseña rc = new RecuperadoresContraseña();
        rc.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRecuperadoresActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
       
        if(txtUsuario2.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Ingrese su Usuario", JOptionPane.ERROR_MESSAGE);
        }
        else 
        {
           cr.setUsuario(txtUsuario2.getText());
        if(fr.CapturarPersona(cr))
        {
           JOptionPane.showMessageDialog(this, "Continue con sus preguntas de seguridad", "Datos correctos", JOptionPane.INFORMATION_MESSAGE);
            BtnGuardar.setEnabled(true);
            txtRespuesta1.setEnabled(true);
            txtRespuesta3.setEnabled(true);
            txtUsuario2.setEnabled(false);
            btnVerificar.setEnabled(false);
           
            fr.obtenerPreguntas(cr);
            cmbPregunta1.setModel( fr.obtenerPreguntas(cr));
            fr.obtenerPreguntas2(cr);
            cmbPregunta2.setModel(fr.obtenerPreguntas2(cr));  
        }
        else 
        {
         JOptionPane.showMessageDialog(null, "Error");
        }
        
        }
        
        
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
       
        if(txtRespuesta1.getText().trim().equals("") || txtRespuesta3.getText().trim().equals(""))
        {
          JOptionPane.showMessageDialog(this, "Datos Incompletos", "Ingrese sus respuestas de seguridad", JOptionPane.ERROR_MESSAGE);
        }
        else 
        {
        cr.setRes1(txtRespuesta1.getText());
        cr.setRes2(txtRespuesta3.getText());
        if(fr.compararRespuestas(cr))
        {
          JOptionPane.showMessageDialog(this, "Continue a ingresar su nueva contraseña", "Datos correctos", JOptionPane.INFORMATION_MESSAGE);
           txtNuevaContraseña.setEnabled(true);
           btnIngresar1.setEnabled(true);
           BtnGuardar.setEnabled(false);
           txtRespuesta1.setEnabled(false);
           txtRespuesta3.setEnabled(false);
          
        }
        else 
        {
         JOptionPane.showMessageDialog(null, "Error");
        }
        
        }
        
        
        
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void btnIngresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar1ActionPerformed

        if(txtNuevaContraseña.getText().trim().equals(""))
        {
         JOptionPane.showMessageDialog(this, "Datos Incompletos", "Ingrese su nueva contraseña", JOptionPane.ERROR_MESSAGE);
        }
        else 
        {
          String encriptar = "";
          cr.setUsuario(txtUsuario2.getText());
          
          try {
          encriptar = obj3.Encriptar(txtNuevaContraseña.getText());
         } catch (NoSuchAlgorithmException e) {
          Logger.getLogger(CifrarClave.class.getName()).log(Level.SEVERE, null, e);
         }
          
          cr.setClave(encriptar);
          if(fr.actualizarContraseña(cr))
          {
             JOptionPane.showMessageDialog(null,"Contraseña Recuperada");
             LoginHermes log = new LoginHermes();
             log.setVisible(true);
             dispose();
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Error al cambiar datos");
          }
        
        } 
    }//GEN-LAST:event_btnIngresar1ActionPerformed

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
            java.util.logging.Logger.getLogger(RecuperadorPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperadorPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperadorPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperadorPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperadorPreguntas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JButton btnIngresar1;
    private javax.swing.JButton btnRecuperadores;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox<String> cmbPregunta1;
    private javax.swing.JComboBox<String> cmbPregunta2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JTextField txtNuevaContraseña;
    private javax.swing.JTextField txtRespuesta1;
    private javax.swing.JTextField txtRespuesta3;
    private javax.swing.JTextField txtUsuario2;
    // End of variables declaration//GEN-END:variables
}
