/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Modelo.llenarCombo;
import java.awt.event.KeyEvent;
import Modelo.FuncionesFactura;
import javax.swing.JOptionPane;
import Controlador.ConstructorFactura;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author samue
 */
public class PanelFactura extends javax.swing.JFrame {

    ConstructorFactura cf = new ConstructorFactura();
    FuncionesFactura ff = new FuncionesFactura();

    DefaultComboBoxModel modelo;
    String f = "27/07/2020";
    Connection con2;
    Statement orden;
    ResultSet rs;
    Conexion con = new Conexion();
    Double TotalC = 0.0;
    Double Viaje = 0.0;
    Double Calcular = 0.0;

    public PanelFactura() {

        initComponents();
        setLocationRelativeTo(null);
        llenarCombo hp = new llenarCombo();
        cmbTipo.setModel(hp.getModd());
        cmbContrato.setModel(hp.getValues4());
        txtId.setEnabled(false);

        con2 = con.conectar();
        if (con2 != null) {
            this.Factura();
            listViajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumnModel columnModel = listViajes.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(250);
            columnModel.getColumn(2).setPreferredWidth(250);
            columnModel.getColumn(3).setPreferredWidth(150);
            columnModel.getColumn(4).setPreferredWidth(250);
            columnModel.getColumn(5).setPreferredWidth(150);
            columnModel.getColumn(6).setPreferredWidth(150);

        }
        txtId.setVisible(false);
        txtCliente.setEditable(false);
        txtServicio.setEditable(false);
        txtTipoPago.setEditable(false);
        txtTotal.setEditable(false);
        cmbContrato.setEnabled(false);

        jrTarjeta.setEnabled(false);
        jrEfectivo.setEnabled(false);
        txtGiro.setEnabled(false);
        cmbTipo.setEnabled(false);
        txtTarjeta.setEnabled(false);
        txtEfectivo.setEnabled(false);
        btnFacturar.setEnabled(false);
        lblCambio.setVisible(false);
        lblcantidad.setVisible(false);
        lblcantidad.setText("0.00");

    }

    Date date = new Date();
    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
    String convertido = fechaHora.format(date);

    public void Limpiar() {
        txtId.setText("");
        txtCliente.setText("");
        txtServicio.setText("");
        txtTipoPago.setText("");
        txtTotal.setText("");
        lblCambio.setVisible(false);
        lblcantidad.setVisible(false);
        lblcantidad.setText("");
        txtGiro.setText("");
        txtTarjeta.setText("");
        txtEfectivo.setText("");
        jrTarjeta.setSelected(false);
        jrEfectivo.setSelected(false);
        jrTarjeta.setEnabled(false);
        jrEfectivo.setEnabled(false);
        txtGiro.setEnabled(false);
        cmbTipo.setEnabled(false);
        txtTarjeta.setEnabled(false);
        txtEfectivo.setEnabled(false);
        btnFacturar.setEnabled(false);
        lblCambio.setVisible(false);
        lblcantidad.setVisible(false);
        cmbTipo.setSelectedIndex(0);
        cmbContrato.setSelectedIndex(0);
    }

    public void Arreglo() {
        listViajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = listViajes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(250);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(250);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(150);

    }

    public void Factura() {
        String titulos[] = {"Id", "Fecha Inicio", "Fecha Fin", "Fecha Registro", "Nombre Servicio", "Cliente", "Tipo Pago"};
        DefaultTableModel md = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("Select idContrato, fecha_inicio, fecha_fin, cr.fechaRegistro, nombreServicio, nombre, tipoPago from Contrato cr, Servicio sr, Cliente cl, TipoPago tp Where cr.idServicio=sr.idServicio  And cr.idCliente=cl.idCliente  and cr.idPago=tp.idPago and cr.monto>0.0 ");
            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)
                };
                md.addRow(Filas);
            }
            listViajes.setModel(md);
        } catch (Exception ex) {
            System.out.println(ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JLabel();
        lblcantidad = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cmbContrato = new javax.swing.JComboBox<>();
        jSeparator22 = new javax.swing.JSeparator();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        btnFacturar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        listViajes = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        txtServicio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        txtTarjeta = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jrTarjeta = new javax.swing.JRadioButton();
        jrEfectivo = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        txtGiro = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        txtTipoPago = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Facturas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/close.png"))); // NOI18N
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, 30, 32));

        lblcantidad.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lblcantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblcantidad.setText("-");
        jPanel1.add(lblcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, 100, 30));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("CONTRATO:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 230, -1, -1));

        cmbContrato.setBackground(new java.awt.Color(33, 33, 33));
        cmbContrato.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbContrato.setForeground(new java.awt.Color(255, 255, 255));
        cmbContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbContrato.setPreferredSize(new java.awt.Dimension(64, 25));
        jPanel1.add(cmbContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 260, 200, 35));
        jPanel1.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 300, 200, 10));

        cmbTipo.setBackground(new java.awt.Color(33, 33, 33));
        cmbTipo.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbTipo.setForeground(new java.awt.Color(255, 255, 255));
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbTipo.setPreferredSize(new java.awt.Dimension(64, 25));
        jPanel1.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 200, 35));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("TIPO DE FACTURA");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, -1, -1));
        jPanel1.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 200, 10));

        btnFacturar.setBackground(new java.awt.Color(153, 153, 153));
        btnFacturar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/seo-report.png"))); // NOI18N
        btnFacturar.setText("Facturar");
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        jPanel1.add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 560, 180, 55));

        btnMostrar.setBackground(new java.awt.Color(153, 153, 153));
        btnMostrar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/eye.png"))); // NOI18N
        btnMostrar.setText("Busqueda de Datos de Cliente");
        btnMostrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 270, 55));

        txtId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(null);
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 30, 35));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("NUMERO DE TARJERTA:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, -1, -1));
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, 210, 10));

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 1200, 160));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CLIENTE:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        txtCliente.setBackground(new java.awt.Color(33, 33, 33));
        txtCliente.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtCliente.setBorder(null);
        txtCliente.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 330, 35));
        jPanel1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 330, 10));

        txtServicio.setBackground(new java.awt.Color(33, 33, 33));
        txtServicio.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtServicio.setForeground(new java.awt.Color(255, 255, 255));
        txtServicio.setBorder(null);
        txtServicio.setCaretColor(new java.awt.Color(255, 255, 255));
        txtServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicioActionPerformed(evt);
            }
        });
        txtServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtServicioKeyTyped(evt);
            }
        });
        jPanel1.add(txtServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 400, 35));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SERVICIO:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, -1, -1));
        jPanel1.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 400, 10));

        txtTarjeta.setBackground(new java.awt.Color(33, 33, 33));
        txtTarjeta.setBorder(null);
        txtTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtTarjeta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-######-###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTarjeta.setText("1111-1111-1111-1111-");
        txtTarjeta.setToolTipText("");
        txtTarjeta.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarjetaActionPerformed(evt);
            }
        });
        jPanel1.add(txtTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 210, 40));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, -1, -1));

        txtEfectivo.setBackground(new java.awt.Color(33, 33, 33));
        txtEfectivo.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtEfectivo.setForeground(new java.awt.Color(255, 255, 255));
        txtEfectivo.setBorder(null);
        txtEfectivo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfectivoActionPerformed(evt);
            }
        });
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyTyped(evt);
            }
        });
        jPanel1.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 200, 40));
        jPanel1.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 540, 200, 10));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("TOTAL: ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, -1, -1));

        txtTotal.setBackground(new java.awt.Color(33, 33, 33));
        txtTotal.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setBorder(null);
        txtTotal.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 200, 35));
        jPanel1.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 200, 10));

        jrTarjeta.setBackground(new java.awt.Color(33, 33, 33));
        jrTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        jrTarjeta.setText("PAGO CON TARJETA");
        jrTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jrTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrTarjetaActionPerformed(evt);
            }
        });
        jPanel1.add(jrTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, -1, -1));

        jrEfectivo.setBackground(new java.awt.Color(33, 33, 33));
        jrEfectivo.setForeground(new java.awt.Color(255, 255, 255));
        jrEfectivo.setText("PAGO CON EFECTIVO");
        jrEfectivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jrEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrEfectivoActionPerformed(evt);
            }
        });
        jPanel1.add(jrEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, -1, -1));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("GIRO:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 460, -1, -1));

        txtGiro.setBackground(new java.awt.Color(33, 33, 33));
        txtGiro.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtGiro.setForeground(new java.awt.Color(255, 255, 255));
        txtGiro.setBorder(null);
        txtGiro.setCaretColor(new java.awt.Color(255, 255, 255));
        txtGiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiroActionPerformed(evt);
            }
        });
        txtGiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiroKeyTyped(evt);
            }
        });
        jPanel1.add(txtGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 490, 200, 35));
        jPanel1.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 530, 200, 10));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("TIPO PAGO:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, -1, -1));

        txtTipoPago.setBackground(new java.awt.Color(33, 33, 33));
        txtTipoPago.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtTipoPago.setForeground(new java.awt.Color(255, 255, 255));
        txtTipoPago.setBorder(null);
        txtTipoPago.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoPagoActionPerformed(evt);
            }
        });
        txtTipoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoPagoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, 220, 35));
        jPanel1.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 220, 10));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("$");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 10, -1));

        lblCambio.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lblCambio.setForeground(new java.awt.Color(255, 255, 255));
        lblCambio.setText("CAMBIO:");
        jPanel1.add(lblCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, -1, -1));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("PAGO EN EFECTIVO:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 460, -1, -1));

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("$");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 10, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1281, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGiroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiroKeyTyped
        Character e;
        e = evt.getKeyChar();
        if (!Character.isLetter(e) && e != KeyEvent.VK_SPACE) {
            evt.consume();
        }

        //Validamos el limite de caracteres
        if (txtGiro.getText().length() == 120) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtGiroKeyTyped

    private void txtGiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiroActionPerformed

    public void Campos() {
        txtGiro.setEditable(false);
        cmbTipo.setEnabled(false);
        txtTarjeta.setEditable(false);
        txtEfectivo.setEditable(false);
        btnFacturar.setEnabled(false);
    }


    private void jrEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrEfectivoActionPerformed
        if (jrEfectivo.isSelected()) {
            txtTarjeta.setEnabled(false);
            jrTarjeta.setEnabled(false);

            cmbTipo.setEnabled(true);
            txtTarjeta.setEditable(false);
            txtGiro.setEditable(true);
            txtEfectivo.setEditable(true);
            btnFacturar.setEnabled(true);
        } else {
            txtTarjeta.setEnabled(true);
            txtGiro.setEditable(false);
            jrTarjeta.setEnabled(true);
            btnFacturar.setEnabled(false);
        }

    }//GEN-LAST:event_jrEfectivoActionPerformed

    private void jrTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrTarjetaActionPerformed

        if (jrTarjeta.isSelected()) {
            jrEfectivo.setEnabled(false);
            txtEfectivo.setEnabled(false);

            cmbTipo.setEnabled(true);
            txtTarjeta.setEditable(true);
            txtGiro.setEditable(true);
            txtEfectivo.setEditable(false);
            btnFacturar.setEnabled(true);
        } else {
            jrEfectivo.setEnabled(true);
            txtEfectivo.setEnabled(true);
            txtGiro.setEditable(false);
            btnFacturar.setEnabled(false);
        }
    }//GEN-LAST:event_jrTarjetaActionPerformed

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyTyped

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped

        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.' || txtEfectivo.getText().contains("."))) {
            evt.consume();
        }
        if (txtEfectivo.getText().length() == 8) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }

    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void txtEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoActionPerformed

    private void txtTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaActionPerformed

    private void txtServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioKeyTyped

    private void txtServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioActionPerformed

    private void txtClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyTyped

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void listViajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listViajesMouseClicked

        int fila = listViajes.getSelectedRow();
        //txtId.setText(String.valueOf(listViajes.getValueAt(fila,0)));
        cmbContrato.setSelectedItem(String.valueOf(listViajes.getValueAt(fila, 0)));
        txtCliente.setText(String.valueOf(listViajes.getValueAt(fila, 5)));
        txtServicio.setText(String.valueOf(listViajes.getValueAt(fila, 4)));
        txtTipoPago.setText(String.valueOf(listViajes.getValueAt(fila, 6)));

    }//GEN-LAST:event_listViajesMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    public void ActualizarContrato() {
        cf.setMonto(0.00);
        int xD2 = ff.getIdContrato(String.valueOf(cmbContrato.getItemAt(cmbContrato.getSelectedIndex())));
        cf.setIdContrato(xD2);

        if (ff.actualizarContrato(cf)) {
            JOptionPane.showMessageDialog(null, "Proceso exitoso, Contrato Finalizado");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void ActualizarServicio() {
        cf.setServicio(txtServicio.getText());
        cf.setEstadoServicio(4);

        if (ff.actualizarServicio(cf)) {
            JOptionPane.showMessageDialog(null, "Proceso exitoso, Servicio Cerrado");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public void BuscarTotalViajes() {

        if (txtCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado un cliente, para proceder es necesario obtener los datos del cliente", "Mensaje", JOptionPane.WARNING_MESSAGE);
        } else {
            cf.setCliente(txtCliente.getText());
            if (ff.consultarCliente(cf)) {
                txtTotal.setText(Double.toString(cf.getMonto()));
                txtEfectivo.setEnabled(true);
                txtTarjeta.setEnabled(true);
                jrTarjeta.setEnabled(true);
                jrEfectivo.setEnabled(true);
                txtGiro.setEnabled(true);
                cmbTipo.setEnabled(true);
                btnFacturar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error al consultar datos");
            }
        }

    }

    public void BuscarContratoTotal() {

        if (txtCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado un cliente", "Mensaje", JOptionPane.WARNING_MESSAGE);
        } else {
            cf.setCliente(txtCliente.getText());
            if (ff.BuscarContrato(cf)) {
                TotalC = cf.getTotalContrato();

                Calcular = TotalC + Viaje;
                txtTotal.setText(String.valueOf(Calcular));
            } else {
                JOptionPane.showMessageDialog(this, "Error al consultar datos");
            }
        }
    }


    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        Campos();
        BuscarTotalViajes();

    }//GEN-LAST:event_btnMostrarActionPerformed

    public void Tarjeta() {
        if (!txtTarjeta.getText().trim().isEmpty() || !txtGiro.getText().trim().isEmpty()) {
            int ids = ff.idIncremental();
            cf.setIdFactura(ids);
            cf.setMonto(Double.parseDouble(txtTotal.getText()));
            cf.setFechaRegistro(convertido);
            cf.setNumeroTarjeta(txtTarjeta.getText());
            cf.setCantidadEfectivo(0.00);
            cf.setCambio(lblcantidad.getText());
            cf.setGiro(txtGiro.getText());
            int xD = ff.getIdTipoFactura(String.valueOf(cmbTipo.getItemAt(cmbTipo.getSelectedIndex())));
            int xD2 = ff.getIdContrato(String.valueOf(cmbContrato.getItemAt(cmbContrato.getSelectedIndex())));

            cf.setIdTipoFactura(xD);
            cf.setIdContrato(xD2);

            if (ff.guardarFactura(cf)) {
                JOptionPane.showMessageDialog(this, "Datos Agregados");
                ActualizarContrato();
                ActualizarServicio();
                ReporteFactura();
                Limpiar();
                Factura();
                Arreglo();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar datos");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void Efectivo() {

        String cadena = txtEfectivo.getText();
        double Efectivo = Double.parseDouble(cadena);

        String cantidad = txtTotal.getText();
        double Total = Double.parseDouble(cantidad);

        double cambio = 0.00;
        
         DecimalFormat nuevo = new DecimalFormat("0.00"); 

        if (!txtEfectivo.getText().trim().isEmpty() || !txtGiro.getText().trim().isEmpty() && Efectivo >= Total) {
            int ids = ff.idIncremental();
            cf.setIdFactura(ids);
            cf.setMonto(Double.parseDouble(txtTotal.getText()));
            cf.setFechaRegistro(convertido);
            cf.setNumeroTarjeta("-");
            if (Efectivo >= Total) {
                cf.setCantidadEfectivo(Double.parseDouble(txtEfectivo.getText()));
                if (Efectivo > Total) {
                    cambio = Efectivo - Total;
                    lblCambio.setVisible(true);
                    lblcantidad.setVisible(true);
                    lblcantidad.setText(String.valueOf(nuevo.format(cambio)));
                }

            } else {
                JOptionPane.showMessageDialog(this, "No se puede ingresar cantidades menores al total a pagar", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }

            cf.setCambio(lblcantidad.getText());
            cf.setGiro(txtGiro.getText());

            int xD = ff.getIdTipoFactura(String.valueOf(cmbTipo.getItemAt(cmbTipo.getSelectedIndex())));
            int xD2 = ff.getIdContrato(String.valueOf(cmbContrato.getItemAt(cmbContrato.getSelectedIndex())));

            cf.setIdTipoFactura(xD);
            cf.setIdContrato(xD2);

            if (Efectivo >= Total) {
                if (ff.guardarFactura(cf)) {
                    JOptionPane.showMessageDialog(this, "Datos Agregados");
                    ActualizarContrato();
                    ActualizarServicio();
                    if (Efectivo >= Total) {
                        ReporteFactura();
                    }
                    Limpiar();
                    Factura();
                    Arreglo();

                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar datos");
                }

            }

        } else {
            JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void ReporteFactura() {
        try {
            //crear conexion
            Conexion con = new Conexion();
            //ruta del archivo
            String archivo = getClass().getResource("/Reportes/Factura.jrxml").getPath();
            archivo = URLDecoder.decode(archivo, "UTF-8");
            JasperReport report = JasperCompileManager.compileReport(archivo);

            //Enviar parametros
            Map parametros = new HashMap();
            String params1 = (txtCliente.getText());
            parametros.put("Cliente", params1);

            //Se crea el objeto para impirmir
            JasperPrint print = JasperFillManager.fillReport(report, parametros, con.conectar());
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Comprobante de Factura");
            visor.setVisible(true);

        } catch (JRException e) {
            System.out.println("AQUI1");
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PanelFactura.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed

        if (jrTarjeta.isSelected()) {
            if (txtTarjeta.getText().trim().isEmpty() || txtGiro.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
            } else {
                Tarjeta();
            }

        } else if (jrEfectivo.isSelected()) {

            if (txtEfectivo.getText().trim().isEmpty() || txtGiro.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
            } else {
                Efectivo();
            }

        }


    }//GEN-LAST:event_btnFacturarActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void txtTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoPagoActionPerformed

    private void txtTipoPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoPagoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoPagoKeyTyped

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
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cmbContrato;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JRadioButton jrEfectivo;
    private javax.swing.JRadioButton jrTarjeta;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblcantidad;
    private javax.swing.JTable listViajes;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtGiro;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JFormattedTextField txtTarjeta;
    private javax.swing.JTextField txtTipoPago;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
