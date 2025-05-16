package Vista;

import Controlador.ConstructorCliente;
import Modelo.FuncionesCliente;
import Controlador.Conexion;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author gdmer
 */
public class PanelClientes extends javax.swing.JPanel {

    ConstructorCliente mc = new ConstructorCliente();
    FuncionesCliente cc = new FuncionesCliente();
    DefaultComboBoxModel modelo;
    String f = "27/07/2020";
    Connection con2;
    Statement orden;
    ResultSet rs;
    Conexion con = new Conexion();

    public PanelClientes() {
        initComponents();
        txtId.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnBaja.setEnabled(false);
        con2 = con.conectar();
        if (con2 != null) {
            this.TablaClientes();
            acoplarTabla();
        }
        cc.getValues();
        cmbTipoCliente.setModel(cc.getValues());
        cc.getEstado();
        cmbEstado.setModel(cc.getEstado());
        cambio();
    }

    public void acoplarTabla() {

        listViajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = listViajes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(100);
    }

    //fechaRegistro
    Date date = new Date();
    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
    String convertido = fechaHora.format(date);
    // 

    public void Arreglo() {
        listViajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = listViajes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(165);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(100);
        columnModel.getColumn(8).setPreferredWidth(150);

    }

    public void TablaClientes() {
        String titulos[] = {"Id", "Nombres", "Apellidos", "Representante", "Telefono", "Direccion", "NIT", "Tipo Cliente", "Estado Cliente"};
        DefaultTableModel md = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("Select idCliente, nombre, apellido, representanteLegal,telefono,direccion,nit,tipoCliente, estadoCliente From Cliente cl , TipoCliente tc, EstadoCliente ec  Where cl.idTipoCliente=tc.idTipoCliente and cl.idEstadoCliente=ec.idEstadoCliente");
            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9)};
                md.addRow(Filas);
            }
            listViajes.setModel(md);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void Limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtRepresentate.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtNIT.setText("");
        cambio();
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnBaja.setEnabled(false);
    }

    public void cambio() {
        if (cmbTipoCliente.getSelectedIndex() == 0 || cmbTipoCliente.getSelectedIndex() == 2
                || cmbTipoCliente.getSelectedIndex() == 3 || cmbTipoCliente.getSelectedIndex() == 4) {
            txtApellido.setEnabled(false);
            txtRepresentate.setEnabled(true);
        } else if (cmbTipoCliente.getSelectedIndex() == 1) {
            txtApellido.setEnabled(true);
            txtRepresentate.setEnabled(false);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        listViajes = new javax.swing.JTable();
        jSeparator11 = new javax.swing.JSeparator();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txtApellido = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        txtRepresentate = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtDireccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cmbTipoCliente = new javax.swing.JComboBox<>();
        jSeparator22 = new javax.swing.JSeparator();
        txtTelefono = new javax.swing.JFormattedTextField();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtNombre2 = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jSeparator23 = new javax.swing.JSeparator();
        txtNIT = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(33, 33, 33));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        listViajes = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        listViajes.setBackground(new java.awt.Color(204, 204, 204));
        listViajes.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        listViajes.setModel(new javax.swing.table.DefaultTableModel(
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
        listViajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listViajesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listViajes);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 471, 910, 170));
        add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 200, 10));

        txtId.setBackground(new java.awt.Color(33, 33, 33));
        txtId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(null);
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 200, 35));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Clientes");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));
        add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 200, 10));

        txtApellido.setBackground(new java.awt.Color(33, 33, 33));
        txtApellido.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setBorder(null);
        txtApellido.setCaretColor(new java.awt.Color(255, 255, 255));
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 200, 35));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("APELLIDOS:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));
        add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 200, 10));

        txtRepresentate.setBackground(new java.awt.Color(33, 33, 33));
        txtRepresentate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtRepresentate.setForeground(new java.awt.Color(255, 255, 255));
        txtRepresentate.setBorder(null);
        txtRepresentate.setCaretColor(new java.awt.Color(255, 255, 255));
        txtRepresentate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRepresentateActionPerformed(evt);
            }
        });
        txtRepresentate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRepresentateKeyTyped(evt);
            }
        });
        add(txtRepresentate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 200, 35));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("REPRESENTANTE:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));
        add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 200, 10));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("TELEFONO:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));
        add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 200, 10));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("NIT:");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 430, 10));

        txtDireccion.setBackground(new java.awt.Color(33, 33, 33));
        txtDireccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setBorder(null);
        txtDireccion.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 430, 40));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DIRECCION:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("TIPO DE CLIENTE:");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        cmbTipoCliente.setBackground(new java.awt.Color(33, 33, 33));
        cmbTipoCliente.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbTipoCliente.setForeground(new java.awt.Color(255, 255, 255));
        cmbTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbTipoCliente.setPreferredSize(new java.awt.Dimension(64, 25));
        cmbTipoCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoClienteItemStateChanged(evt);
            }
        });
        cmbTipoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbTipoClienteMouseClicked(evt);
            }
        });
        add(cmbTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 200, 35));
        add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 200, 10));

        txtTelefono.setBackground(new java.awt.Color(33, 33, 33));
        txtTelefono.setBorder(null);
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefono.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 200, 40));

        txtBusqueda.setBackground(new java.awt.Color(33, 33, 33));
        txtBusqueda.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(null);
        txtBusqueda.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtBusqueda.setPreferredSize(new java.awt.Dimension(200, 0));
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 180, 30));

        jSeparator14.setPreferredSize(new java.awt.Dimension(200, 0));
        add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 180, 10));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/search.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, 40));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mantenimientos");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 130, -1));

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
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 55));

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
        jPanel1.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 180, 55));

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
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 180, 55));

        btnBaja.setBackground(new java.awt.Color(153, 153, 153));
        btnBaja.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/download.png"))); // NOI18N
        btnBaja.setText("Dar de Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, 55));

        btnBaja1.setBackground(new java.awt.Color(153, 153, 153));
        btnBaja1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnBaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/broom.png"))); // NOI18N
        btnBaja1.setText("Limpiar");
        btnBaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaja1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnBaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 180, 55));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 200, 360));

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
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 35));

        txtNombre2.setBackground(new java.awt.Color(33, 33, 33));
        txtNombre2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtNombre2.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre2.setBorder(null);
        txtNombre2.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombre2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre2ActionPerformed(evt);
            }
        });
        add(txtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 35));
        add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 200, 10));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("NOMBRES:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("ESTADO DE CLIENTE:");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, -1, -1));

        cmbEstado.setBackground(new java.awt.Color(33, 33, 33));
        cmbEstado.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbEstado.setForeground(new java.awt.Color(255, 255, 255));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbEstado.setPreferredSize(new java.awt.Dimension(64, 25));
        cmbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadoItemStateChanged(evt);
            }
        });
        cmbEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbEstadoMouseClicked(evt);
            }
        });
        add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 200, 35));
        add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 200, 10));

        txtNIT.setBackground(new java.awt.Color(33, 33, 33));
        txtNIT.setBorder(null);
        txtNIT.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtNIT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-######-###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNIT.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtNIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNITActionPerformed(evt);
            }
        });
        add(txtNIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 200, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void txtRepresentateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRepresentateActionPerformed

    }//GEN-LAST:event_txtRepresentateActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (txtNombre.getText().trim().equals("") || txtTelefono.getText().trim().equals("")
                || txtDireccion.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        } else if (cmbTipoCliente.getSelectedIndex() == 0 || cmbTipoCliente.getSelectedIndex() == 2 || cmbTipoCliente.getSelectedIndex() == 3 || cmbTipoCliente.getSelectedIndex() == 4) {
            int ids = cc.idIncremental();
            mc.setId(ids);
            mc.setNombre(txtNombre.getText());
            mc.setApellido("-");
            mc.setRepresentante(txtRepresentate.getText());
            mc.setTelefono(txtTelefono.getText());
            mc.setDireccion(txtDireccion.getText());
            mc.setNit(txtNIT.getText());
            mc.setFechas(convertido);
            int idTipo = cc.obtenerIdTipo(String.valueOf(cmbTipoCliente.getItemAt(cmbTipoCliente.getSelectedIndex())));
            mc.setTipo(idTipo);
            int idEstado = cc.obtenerIdEstado(String.valueOf(cmbEstado.getItemAt(cmbEstado.getSelectedIndex())));
            mc.setEstado(idEstado);

            if (cc.guardarCliente(mc)) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
            } else {
                JOptionPane.showMessageDialog(null, "Error al gurdar datos");
            }
            TablaClientes();
            acoplarTabla();
            Arreglo();
            Limpiar();

        } else if (cmbTipoCliente.getSelectedIndex() == 1) {
            int ids = cc.idIncremental();
            mc.setId(ids);
            mc.setNombre(txtNombre.getText());
            mc.setApellido(txtApellido.getText());
            mc.setRepresentante("-");
            mc.setTelefono(txtTelefono.getText());
            mc.setDireccion(txtDireccion.getText());
            mc.setNit(txtNIT.getText());
            mc.setFechas(convertido);
            int idTipo = cc.obtenerIdTipo(String.valueOf(cmbTipoCliente.getItemAt(cmbTipoCliente.getSelectedIndex())));
            mc.setTipo(idTipo);
            int idEstado = cc.obtenerIdEstado(String.valueOf(cmbEstado.getItemAt(cmbEstado.getSelectedIndex())));
            mc.setEstado(idEstado);
            if (cc.guardarCliente(mc)) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
            } else {
                JOptionPane.showMessageDialog(null, "Error al gurdar datos");
            }
            TablaClientes();
            acoplarTabla();
            Arreglo();
            Limpiar();

        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        TablaClientes();
        acoplarTabla();

    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        if (txtId.getText().trim().equals("") || txtNombre.getText().trim().equals("") || txtTelefono.getText().trim().equals("")
                || txtDireccion.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campos Vacios");
        } else {
            mc.setId(Integer.parseInt(txtId.getText()));
            mc.setNombre(txtNombre.getText());
            mc.setApellido(txtApellido.getText());
            mc.setRepresentante(txtRepresentate.getText());
            mc.setTelefono(txtTelefono.getText());
            mc.setDireccion(txtDireccion.getText());
            mc.setNit(txtNIT.getText());

            int idTipo = cc.obtenerIdTipo(String.valueOf(cmbTipoCliente.getItemAt(cmbTipoCliente.getSelectedIndex())));
            mc.setTipo(idTipo);
            int idEstado = cc.obtenerIdEstado(String.valueOf(cmbEstado.getItemAt(cmbEstado.getSelectedIndex())));
            mc.setEstado(idEstado);
            if (cc.actualizarCliente(mc)) {
                JOptionPane.showMessageDialog(null, "Datos actualizados");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar datos");
            }
            TablaClientes();
            acoplarTabla();
            Arreglo();
            Limpiar();

        }


    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre2ActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed

        if (txtId.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campos Vacios");
        } else {
            int baja = 2;
            mc.setEstado(baja);
            mc.setId(Integer.parseInt(txtId.getText()));
            if (cc.darBaja(mc)) {
                JOptionPane.showMessageDialog(null, "Datos Inhabilitados");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar datos");
            }
            TablaClientes();
            acoplarTabla();
            Arreglo();
            Limpiar();

        }


    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void listViajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listViajesMouseClicked

        int fila = listViajes.getSelectedRow();
        txtId.setText(String.valueOf(listViajes.getValueAt(fila, 0)));
        txtNombre.setText(String.valueOf(listViajes.getValueAt(fila, 1)));
        txtApellido.setText(String.valueOf(listViajes.getValueAt(fila, 2)));
        txtRepresentate.setText(String.valueOf(listViajes.getValueAt(fila, 3)));
        txtTelefono.setText(String.valueOf(listViajes.getValueAt(fila, 4)));
        txtDireccion.setText(String.valueOf(listViajes.getValueAt(fila, 5)));
        txtNIT.setText(String.valueOf(listViajes.getValueAt(fila, 6)));
        cmbTipoCliente.setSelectedItem(String.valueOf(listViajes.getValueAt(fila, 7)));
        cmbEstado.setSelectedItem(String.valueOf(listViajes.getValueAt(fila, 8)));

        btnActualizar.setEnabled(true);
        btnBaja.setEnabled(true);
        btnAgregar.setEnabled(false);

    }//GEN-LAST:event_listViajesMouseClicked

    private void cmbTipoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbTipoClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoClienteMouseClicked

    private void cmbTipoClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoClienteItemStateChanged

        if (cmbTipoCliente.getSelectedIndex() == 0 || cmbTipoCliente.getSelectedIndex() == 2
                || cmbTipoCliente.getSelectedIndex() == 3 || cmbTipoCliente.getSelectedIndex() == 4) {
            txtApellido.setEnabled(false);
            txtRepresentate.setEnabled(true);
        } else if (cmbTipoCliente.getSelectedIndex() == 1) {
            txtApellido.setEnabled(true);
            txtRepresentate.setEnabled(false);

        }


    }//GEN-LAST:event_cmbTipoClienteItemStateChanged

    private void cmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoItemStateChanged

    private void cmbEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoMouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Character e;
        e = evt.getKeyChar();
        if (!Character.isLetter(e) && e != KeyEvent.VK_SPACE) {
            evt.consume();
        }

        //Validamos el limite de caracteres
        if (txtNombre.getText().length() == 25) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        //Validamos que se puedan poner espacios
        Character e;
        e = evt.getKeyChar();
        if (!Character.isLetter(e) && e != KeyEvent.VK_SPACE) {
            evt.consume();
        }

        //Validamos el limite de caracteres
        if (txtApellido.getText().length() == 25) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        Character e;
        e = evt.getKeyChar();
        if (!Character.isLetter(e) && !Character.isDigit(e) && e != KeyEvent.VK_SPACE) {
            evt.consume();
        }

        //Validamos el limite de caracteres
        if (txtDireccion.getText().length() == 55) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtRepresentateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRepresentateKeyTyped
        //Validamos que se puedan poner espacios
        Character e;
        e = evt.getKeyChar();
        if (!Character.isLetter(e) && e != KeyEvent.VK_SPACE) {
            evt.consume();
        }

        //Validamos el limite de caracteres
        if (txtRepresentate.getText().length() == 25) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtRepresentateKeyTyped

    private void txtNITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNITActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNITActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed

        String titulos[] = {"Id", "Nombres", "Apellidos", "Representante", "Telefono", "Direccion", "NIT", "Tipo Cliente", "Estado Cliente"};
        DefaultTableModel md = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("Select idCliente, nombre, apellido, representanteLegal,telefono,direccion,nit,tipoCliente, estadoCliente From Cliente cl , TipoCliente tc, EstadoCliente ec  Where cl.idTipoCliente=tc.idTipoCliente and cl.idEstadoCliente=ec.idEstadoCliente and nombre LIKE '%" + txtBusqueda.getText() + "%'");
            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9)};
                md.addRow(Filas);
            }
            listViajes.setModel(md);
            acoplarTabla();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        
        //Validamos que no se puedan poner espacios
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car > ' ') && (car != (char) KeyEvent.VK_ENTER) && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        //Validamos el limite de caracteres
        if (txtBusqueda.getText().length() == 42) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }

        //Solo numeros
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBaja1;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbTipoCliente;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JTable listViajes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JFormattedTextField txtNIT;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtRepresentate;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
