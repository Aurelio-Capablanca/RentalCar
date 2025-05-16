/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Modelo.FuncionesContrato;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class PanelContrato extends javax.swing.JPanel {

    /**
     * Creates new form PanelContrato
     */
    Connection con2;
    Statement orden;
    ResultSet rs;
    FuncionesContrato mi = new FuncionesContrato();
    int dias = -1;
    int dias2 = 1;

    public PanelContrato() {
        initComponents();
        FuncionesContrato mi = new FuncionesContrato();
        cmbServicio.setModel(mi.getservicio());
        cmbPago.setModel(mi.getpago());
        cmbCliente.setModel(mi.getcliente());
        con2 = con.conectar();
        if (con2 != null) {
            this.verTabla();
            tableContratos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumnModel columnModel = tableContratos.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(100);
            columnModel.getColumn(2).setPreferredWidth(150);
            columnModel.getColumn(3).setPreferredWidth(150);
            columnModel.getColumn(4).setPreferredWidth(150);
            columnModel.getColumn(5).setPreferredWidth(280);
            columnModel.getColumn(6).setPreferredWidth(190);
            columnModel.getColumn(7).setPreferredWidth(150);
        }
        txtCodigo.setEnabled(false);
    }
    Conexion con = new Conexion();
    FuncionesContrato mct = new FuncionesContrato();

    Date date = new Date();
    DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy");
    String convertido = fechaHora.format(date);

    public void Limpiar() {
        txtCodigo.setText("");
        txtMonto.setText("");
        txtInteres.setText("");
        txtBusqueda.setText("");
        txtInicio.setCalendar(null);
        txtFin.setCalendar(null);
    }

    public void Arreglo() {
        tableContratos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tableContratos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(280);
        columnModel.getColumn(6).setPreferredWidth(190);
        columnModel.getColumn(7).setPreferredWidth(150);
    }

    public void verTabla() {

        String titulos[] = {"ID", "Interes", "Monto", "Inicio", "Fin", "Servicio", "Cliente", "Pago"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("SELECT idContrato, c.intereses,monto,fecha_inicio,fecha_fin, nombreServicio , nombre, tipoPago from Contrato c ,  Servicio sr , Cliente cl, TipoPago tp where c.idServicio=sr.idServicio and c.idCliente=cl.idCliente and c.idPago=tp.idPago");

            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                tm.addRow(Filas);
            }
            tableContratos.setModel(tm);
        } catch (Exception ex) {
            Logger.getLogger(PanelPreguntas.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void calcularDias1() {

        Calendar inicio = txtInicio.getCalendar();
        Calendar today = Calendar.getInstance();
        dias = 1;
        while (inicio.before(today) || inicio.equals(today)) {
            dias++;
            inicio.add(Calendar.DATE, 1);
        }
    }

    public void calcularDias2() {

        Calendar inicio = txtFin.getCalendar();
        Calendar today = Calendar.getInstance();
        dias2 = 1;
        while (inicio.before(today) || inicio.equals(today)) {
            dias2++;
            inicio.add(Calendar.DATE, 1);
        }
    }

    public void Ingresar() {

        if (txtMonto.getText().trim().isEmpty() || txtInteres.getText().trim().isEmpty()
                || txtInicio.getDate() == null || dias > 7 || txtFin.getDate() == null || dias2 > 7) {

            if (dias > 7) {
                txtInicio.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            if (dias2 > 7) {

                txtFin.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {
            int ids = mi.idIncremental();
            mi.setId(ids);
            mi.setInteres(Double.parseDouble(txtInteres.getText()));
            mi.setMonto(Double.parseDouble(txtMonto.getText()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String startDateString1 = dateFormat.format(txtInicio.getDate());

            if (dias <= 7) {
                mi.setInicio(startDateString1);

            } else {

                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año");
            }

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            String startDateString2 = dateFormat1.format(txtFin.getDate());

            if (dias2 <= 360 || dias2 == dias) {
                mi.setFin(startDateString2);

            } else {

                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año");
            }

            mi.setFregisto(convertido);

            int idp = mi.getIdServicio(String.valueOf(cmbServicio.getItemAt(cmbServicio.getSelectedIndex())));
            mi.setIdServicio(idp);
            int idc = mi.getIdCliente(String.valueOf(cmbCliente.getItemAt(cmbCliente.getSelectedIndex())));
            mi.setIdClinte(idc);
            int idu = mi.getIdPago(String.valueOf(cmbPago.getItemAt(cmbPago.getSelectedIndex())));
            mi.setIdPago(idu);

            if (mi.guardarContrato()) {
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                verTabla();
                Arreglo();
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar datos");
            }

        }
    }

    public void Actualizar() {

        if (txtMonto.getText().trim().isEmpty() || txtInteres.getText().trim().isEmpty()
                || txtInicio.getDate() == null || dias > 7 || txtFin.getDate() == null || dias2 > 7) {

            if (dias > 7) {
                txtInicio.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            if (dias2 > 7) {
                txtInicio.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {
            mi.setId(Integer.parseInt(txtCodigo.getText()));
            mi.setInteres(Double.parseDouble(txtInteres.getText()));
            mi.setMonto(Double.parseDouble(txtMonto.getText()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String startDateString1 = dateFormat.format(txtInicio.getDate());

            if (dias <= 7) {
                mi.setInicio(startDateString1);

            } else {

                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año");
            }

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            String startDateString2 = dateFormat1.format(txtFin.getDate());
            if (dias2 <= 360 || dias2 == dias) {
                mi.setFin(startDateString2);

            } else {

                JOptionPane.showMessageDialog(this, "El Contrato no debe exceder un año");
            }
            int idp = mi.getIdServicio(String.valueOf(cmbServicio.getItemAt(cmbServicio.getSelectedIndex())));
            mi.setIdServicio(idp);
            int idc = mi.getIdCliente(String.valueOf(cmbCliente.getItemAt(cmbCliente.getSelectedIndex())));
            mi.setIdClinte(idc);
            int idu = mi.getIdPago(String.valueOf(cmbPago.getItemAt(cmbPago.getSelectedIndex())));
            mi.setIdPago(idu);

            if (mi.modificarContrato()) {
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                verTabla();
                Arreglo();
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar datos");
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        cmbServicio = new javax.swing.JComboBox<>();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        cmbPago = new javax.swing.JComboBox<>();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        jSeparator20 = new javax.swing.JSeparator();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        txtFin = new com.toedter.calendar.JDateChooser();
        txtInicio = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableContratos = new javax.swing.JTable();
        txtInteres = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        txtMonto = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administración de Servicios");

        setBackground(new java.awt.Color(33, 33, 33));
        setPreferredSize(new java.awt.Dimension(900, 660));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Contratos");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("INTERES %:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 200, 10));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SERVICIO:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 90, 20));

        cmbServicio.setBackground(new java.awt.Color(33, 33, 33));
        cmbServicio.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbServicio.setForeground(new java.awt.Color(255, 255, 255));
        cmbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbServicio.setPreferredSize(new java.awt.Dimension(64, 25));
        cmbServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbServicioActionPerformed(evt);
            }
        });
        add(cmbServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 460, 35));
        add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 460, 10));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("FECHA DE INICIO:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));
        add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 200, 8));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("FECHA DE FIN:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));
        add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 200, 8));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("METODO DE PAGO:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        cmbPago.setBackground(new java.awt.Color(33, 33, 33));
        cmbPago.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbPago.setForeground(new java.awt.Color(255, 255, 255));
        cmbPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPago.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbPago.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 200, 35));
        add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 200, 10));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("CLIENTE:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

        cmbCliente.setBackground(new java.awt.Color(33, 33, 33));
        cmbCliente.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbCliente.setForeground(new java.awt.Color(255, 255, 255));
        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbCliente.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 200, 35));
        add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 200, 10));

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

        jSeparator21.setPreferredSize(new java.awt.Dimension(200, 0));
        add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 180, 10));

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

        btnBaja1.setBackground(new java.awt.Color(153, 153, 153));
        btnBaja1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnBaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/broom.png"))); // NOI18N
        btnBaja1.setText("Limpiar");
        btnBaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaja1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnBaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, 55));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 200, 300));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("MONTO $:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));
        add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 200, 10));

        txtFin.setBackground(new java.awt.Color(33, 33, 33));
        txtFin.setForeground(new java.awt.Color(255, 255, 255));
        txtFin.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        add(txtFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 200, 40));

        txtInicio.setBackground(new java.awt.Color(33, 33, 33));
        txtInicio.setForeground(new java.awt.Color(255, 255, 255));
        txtInicio.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        add(txtInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 200, 40));

        tableContratos = new javax.swing.JTable() {

            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tableContratos.setBackground(new java.awt.Color(204, 204, 204));
        tableContratos.setModel(new javax.swing.table.DefaultTableModel(
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
        tableContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableContratosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableContratos);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 920, 220));

        txtInteres.setBackground(new java.awt.Color(33, 33, 33));
        txtInteres.setBorder(null);
        txtInteres.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtInteres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtInteres.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        add(txtInteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 200, 40));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("ID:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, 30));

        txtCodigo.setBackground(new java.awt.Color(33, 33, 33));
        txtCodigo.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setBorder(null);
        txtCodigo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 200, 40));
        add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 200, 10));

        txtMonto.setBackground(new java.awt.Color(33, 33, 33));
        txtMonto.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtMonto.setForeground(new java.awt.Color(255, 255, 255));
        txtMonto.setBorder(null);
        txtMonto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });
        add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 200, 35));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:

        if (txtInicio.getDate() == null
                || txtFin.getDate() == null) {

            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {
            calcularDias1();
            calcularDias2();
        }
        Ingresar();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        verTabla();
        Arreglo();
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed
        Limpiar();

    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (txtMonto.getText().trim().isEmpty() || txtInteres.getText().trim().isEmpty() || txtInicio.getDate() == null
                || txtFin.getDate() == null) {

            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {
            calcularDias1();
            calcularDias2();
        }
        Actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tableContratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableContratosMouseClicked
        // TODO add your handling code here:
        try {
            int fila = tableContratos.getSelectedRow();

            txtCodigo.setText(String.valueOf(tableContratos.getValueAt(fila, 0).toString()));
            txtInteres.setText(String.valueOf(tableContratos.getValueAt(fila, 1).toString()));
            txtMonto.setText(String.valueOf(tableContratos.getValueAt(fila, 2).toString()));

            String nac = tableContratos.getValueAt(fila, 3).toString();
            SimpleDateFormat formatoNac = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaN = null;
            fechaN = formatoNac.parse(nac);
            txtInicio.setDate(fechaN);

            String ret = tableContratos.getValueAt(fila, 4).toString();
            SimpleDateFormat formatoRac = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaR = null;
            fechaR = formatoRac.parse(ret);
            txtFin.setDate(fechaR);

            cmbServicio.setSelectedItem(String.valueOf(tableContratos.getValueAt(fila, 5)));
            cmbCliente.setSelectedItem(String.valueOf(tableContratos.getValueAt(fila, 6)));
            cmbPago.setSelectedItem(String.valueOf(tableContratos.getValueAt(fila, 7)));

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }//GEN-LAST:event_tableContratosMouseClicked

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void cmbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbServicioActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.' || txtMonto.getText().contains("."))) {
            evt.consume();
        }
        if (txtMonto.getText().length() == 8) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed

        String titulos[] = {"ID", "Interes", "Monto", "Inicio", "Fin", "Servicio", "Cliente", "Pago"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("SELECT idContrato, c.intereses,monto,fecha_inicio,fecha_fin, nombreServicio , nombre, tipoPago from Contrato c ,  Servicio sr , Cliente cl, TipoPago tp where c.idServicio=sr.idServicio and c.idCliente=cl.idCliente and c.idPago=tp.idPago and nombre LIKE '%" + txtBusqueda.getText() + "%'");

            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                tm.addRow(Filas);
            }
            tableContratos.setModel(tm);
            Arreglo();
        } catch (Exception ex) {
            Logger.getLogger(PanelPreguntas.class.getName()).log(Level.SEVERE, null, ex);

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
        if (txtBusqueda.getText().length() == 48) {
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
    private javax.swing.JButton btnBaja1;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbPago;
    private javax.swing.JComboBox<String> cmbServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JTable tableContratos;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigo;
    private com.toedter.calendar.JDateChooser txtFin;
    private com.toedter.calendar.JDateChooser txtInicio;
    private javax.swing.JFormattedTextField txtInteres;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
