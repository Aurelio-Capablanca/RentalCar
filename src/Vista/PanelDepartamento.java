/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Controlador.ConstructorDepartamento;
import Modelo.FuncionesDepartamento;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author adalb
 */
public class PanelDepartamento extends javax.swing.JFrame {
     
    ConstructorDepartamento md = new ConstructorDepartamento();
    FuncionesDepartamento cd = new FuncionesDepartamento();
    Conexion con = new Conexion(); 
    Connection con2;
    Statement orden;
    ResultSet rs;
  
    public PanelDepartamento() {
        initComponents();
        setLocationRelativeTo(null);
        con2 = con.conectar();
        if (con2 != null) {
            this.TablaDepartamentos();
        listDepartamento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = listDepartamento.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
         columnModel.getColumn(1).setPreferredWidth(600);
        } 
        txtId.setEnabled(false);
    }

  public void TablaDepartamentos()
  {
    String titulos[] = {"Id", "Departamento"};  
    DefaultTableModel md = new DefaultTableModel(null,titulos);
    try
    { 
       orden = con2.createStatement();
       rs = orden.executeQuery("SELECT * FROM Departamento");
       while(rs.next())
       {
           Object Filas[] = {rs.getString(1), rs.getString(2)};
           md.addRow(Filas);
       }
       listDepartamento.setModel(md);
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
 
  }  
    
   public void Limpiar()
     {
       txtId.setText("");
       txtNombre.setText("");
       txtBusqueda.setText("");
     }


     
  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDepartamento = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtId = new javax.swing.JTextField();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Departamentos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        listDepartamento.setBackground(new java.awt.Color(204, 204, 204));
        listDepartamento.setModel(new javax.swing.table.DefaultTableModel(
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
        listDepartamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listDepartamentoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listDepartamento);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 670, 270));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DEPARTAMENTO:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 200, 10));

        txtNombre.setBackground(new java.awt.Color(33, 33, 33));
        txtNombre.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setBorder(null);
        txtNombre.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 200, 35));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/close.png"))); // NOI18N
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, 32));

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

        txtBusqueda.setBackground(new java.awt.Color(33, 33, 33));
        txtBusqueda.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(null);
        txtBusqueda.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtBusqueda.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 180, 30));

        jSeparator14.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanel1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, 180, 10));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/search.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, -1, 40));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mantenimientos");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 130, -1));

        btnAgregar.setBackground(new java.awt.Color(153, 153, 153));
        btnAgregar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/plus.png"))); // NOI18N
        btnAgregar.setText("Ingresar");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 55));

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
        jPanel3.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 180, 55));

        btnActualizar.setBackground(new java.awt.Color(153, 153, 153));
        btnActualizar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/loop.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 180, 55));

        btnBaja1.setBackground(new java.awt.Color(153, 153, 153));
        btnBaja1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnBaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/broom.png"))); // NOI18N
        btnBaja1.setText("Limpiar");
        btnBaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaja1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnBaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 180, 55));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 200, 310));

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

    private void listDepartamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDepartamentoMouseClicked
          int fila = listDepartamento.getSelectedRow();
        txtId.setText(String.valueOf(listDepartamento.getValueAt(fila,0)));
        txtNombre.setText(String.valueOf(listDepartamento.getValueAt(fila,1)));
        
     
    }//GEN-LAST:event_listDepartamentoMouseClicked

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

       int id = cd.idIncremental();
        md.setId(id);
        md.setDepartamento(txtNombre.getText());
        if(cd.guardarDepartamento(md))
        {
          JOptionPane.showMessageDialog(null,"Datos guardados");
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Error al gurdar datos");
        }
        TablaDepartamentos();
        Limpiar();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed

      md.setId(Integer.parseInt(txtBusqueda.getText()));
       if(cd.consultarDepartamento(md))
       {
         txtNombre.setText(md.getDepartamento());
         TablaDepartamentos();
       }
       else
       {
        JOptionPane.showMessageDialog(this, "Error al consultar datos");
       }   

    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

       md.setId(Integer.parseInt(txtId.getText()));
        md.setDepartamento(txtNombre.getText());
        if(cd.actualizarDepartamento(md))
        {
           JOptionPane.showMessageDialog(null,"Datos actualizados");
        }
        else
        {
           JOptionPane.showMessageDialog(null,"Error al actualizar datos"); 
        }
        TablaDepartamentos();
        Limpiar();
      

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed
        
        Limpiar();
        
    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Character e ;
      e=evt.getKeyChar();
      if(!Character.isLetter(e) && e!=KeyEvent.VK_SPACE )
      {
         evt.consume();
      }
        
        //Validamos el limite de caracteres
        if (txtNombre.getText().length() == 25) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

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
            java.util.logging.Logger.getLogger(PanelDepartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelDepartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelDepartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelDepartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelDepartamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBaja1;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JTable listDepartamento;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
