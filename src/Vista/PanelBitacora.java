/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Controlador.ConstructorBitacora;
import Modelo.FuncionesBitacora;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adalb
 */
public class PanelBitacora extends javax.swing.JFrame {

    Connection con2;
    Statement orden;
    ResultSet rs;
   Conexion con = new Conexion();
   ConstructorBitacora cb = new  ConstructorBitacora();
   FuncionesBitacora fb = new FuncionesBitacora();
   
    public PanelBitacora() {
        initComponents();
         setLocationRelativeTo(null);
        con2 = con.conectar();
        if (con2 != null) {
            this.Bitacora();
        }
    }

   public void Bitacora()
  {
    String titulos[] = {"Id", "Persona", "Fecha", "Acción"};  
    DefaultTableModel md = new DefaultTableModel(null,titulos);
    try
    { 
       orden = con2.createStatement();
       rs = orden.executeQuery("select * from Bitacora");
       while(rs.next())
       {
           Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3), 
               rs.getString(4)};
           md.addRow(Filas);
       }
       listbitacora.setModel(md);
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
  }
   
   public void Limpiar()
  {
    txtId.setText("");
    txtPersona.setText("");
    txtAccion.setText("");
    txtFecha.setText("");
    txtBusqueda.setText("");
    
  }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listbitacora = new javax.swing.JTable();
        btnCerrar = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtPersona = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtId = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnMostrar = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtAccion = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bitacoras ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 160, 60));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        listbitacora.setBackground(new java.awt.Color(204, 204, 204));
        listbitacora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        listbitacora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listbitacoraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listbitacora);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 910, 310));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/close.png"))); // NOI18N
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, 32));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PERSONA:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, -1, -1));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 200, 10));

        txtPersona.setBackground(new java.awt.Color(33, 33, 33));
        txtPersona.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtPersona.setForeground(new java.awt.Color(255, 255, 255));
        txtPersona.setBorder(null);
        txtPersona.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersonaActionPerformed(evt);
            }
        });
        jPanel1.add(txtPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 200, 35));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CODIGO:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));
        jPanel1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 200, 10));

        txtId.setBackground(new java.awt.Color(33, 33, 33));
        txtId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(null);
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 200, 35));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mantenimientos");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 130, -1));

        btnMostrar.setBackground(new java.awt.Color(153, 153, 153));
        btnMostrar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/eye.png"))); // NOI18N
        btnMostrar.setText("Mostrar");
        btnMostrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 55));

        btnBaja1.setBackground(new java.awt.Color(153, 153, 153));
        btnBaja1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnBaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/broom.png"))); // NOI18N
        btnBaja1.setText("Limpiar");
        btnBaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaja1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnBaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, 55));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 200, 210));

        txtBusqueda.setBackground(new java.awt.Color(33, 33, 33));
        txtBusqueda.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(null);
        txtBusqueda.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtBusqueda.setPreferredSize(new java.awt.Dimension(200, 0));
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 190, 30));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/search.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, 40));
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 200, 10));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("FECHA:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        txtFecha.setBackground(new java.awt.Color(33, 33, 33));
        txtFecha.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setBorder(null);
        txtFecha.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 200, 35));
        jPanel1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 200, 10));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ACCIÓN:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txtAccion.setBackground(new java.awt.Color(33, 33, 33));
        txtAccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtAccion.setForeground(new java.awt.Color(255, 255, 255));
        txtAccion.setBorder(null);
        txtAccion.setCaretColor(new java.awt.Color(255, 255, 255));
        txtAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccionActionPerformed(evt);
            }
        });
        jPanel1.add(txtAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 35));
        jPanel1.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 200, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listbitacoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listbitacoraMouseClicked

    }//GEN-LAST:event_listbitacoraMouseClicked

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void txtPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonaActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed

        if(txtBusqueda.getText().trim().equals(""))
        {
           JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
          cb.setId(Integer.parseInt(txtBusqueda.getText()));
        if(fb.ConsultarBitacora(cb))
        {
          txtId.setText(String.valueOf(cb.getId()));
          txtPersona.setText(cb.getPersona());
          txtAccion.setText(cb.getAccion());
          txtFecha.setText(cb.getFecha());
          
          Bitacora();
          

        }
        else
        {
          JOptionPane.showMessageDialog(this, "Error al consultar datos");
        }
         
        }
        
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed

        Limpiar();

    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccionActionPerformed

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
            java.util.logging.Logger.getLogger(PanelBitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelBitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelBitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelBitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelBitacora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaja1;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JTable listbitacora;
    private javax.swing.JTextField txtAccion;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPersona;
    // End of variables declaration//GEN-END:variables
}
