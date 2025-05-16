/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Modelo.FuncionesServicio;
import Modelo.LlenarCombos;
import Modelo.FuncionesDetalleRecurso;
import Modelo.FuncionesUnidad;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author gdmer
 */
public class PanelUnidades extends javax.swing.JPanel {

    //Declaracion de atributos
    Connection con2;
    Statement orden;
    ResultSet rs;
    Conexion con = new Conexion();
    FuncionesUnidad obj = new FuncionesUnidad();
    int dias = -1;

    /**
     * Creates new form PanelUnidades
     */
    public PanelUnidades() {
        initComponents();
        FuncionesUnidad hp = new FuncionesUnidad();
        cmbMarca.setModel(hp.getMarca());
        cmbEstado.setModel(hp.getEstado());
        cmbTipo.setModel(hp.getTipo());
        con2 = con.conectar();
        if (con2 != null) {
            this.tablaUnidad();
            acoplarTabla();
        }
        txtId.setEnabled(false);
    }

    public void acoplarTabla() {

        TbUnidad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = TbUnidad.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(150);
        columnModel.getColumn(9).setPreferredWidth(150);
    }

//    public void llenar(){
//        mtoUnidad obj = new mtoUnidad();
//        obj.cargarUnidad(listUnidades);
//    }
    public void calcularDias() {

        Calendar inicio = txtFechaLanzamiento.getCalendar();
        Calendar today = Calendar.getInstance();
        dias = 1;
        while (inicio.before(today) || inicio.equals(today)) {
            dias++;
            inicio.add(Calendar.DATE, 1);
        }

    }

    public void tablaUnidad() {
        String titulos[] = {"ID", "Modelo", "Capacidad", "Codigo", "Placas", "Año Unidad", "Fecha Registro", "Marca", "Tipo", "Estado"};
        DefaultTableModel md = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("Select idUnidad , modelo , capacidad, codigo , placas, fechaLanzamiento, fechaRegistro, nombreMarca, tipounidad, estadounidad from Unidad un, Marca mr, TipoUnidad tu, EstadoUnidad es Where un.idMarca=mr.idMarca and un.idTipoUni=tu.idTipoUni and un.idEstadoUni=es.idEstadoUni");
            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
                md.addRow(Filas);
            }
            TbUnidad.setModel(md);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void limpiar() {
        txtId.setText("");
        txtModelo.setText("");
        txtCapacidad.setText("");
        txtCodigo.setText("");
        txtPlaca.setText("");
        txtBusqueda.setText("");
        txtFechaLanzamiento.setCalendar(null);
        btnActualizar.setEnabled(false);
        btnBaja.setEnabled(false);
        btnAgregar.setEnabled(true);
    }

    public void ingresar() {

        try {

            if (txtModelo.getText().trim().equals("") || txtCapacidad.getText().trim().equals("")
                    || txtPlaca.getText().trim().equals("") || txtFechaLanzamiento.getDate() == null || dias > 2920) {
                if (dias > 2920) {
                    txtFechaLanzamiento.setCalendar(null);
                    JOptionPane.showMessageDialog(this, "La unidad no debe superar los 8 años de antiguedad", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
            } else {
                int ids = obj.idAumento();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDateString = dateFormat.format(txtFechaLanzamiento.getDate());

                if (dias <= 2920) {
                    obj.setFechaLanzamiento(startDateString);

                } else {

                    JOptionPane.showMessageDialog(this, "La unidad no debe superar los 8 años de antiguedad");
                }

                obj.setId(ids);
                obj.setModelo(txtModelo.getText());
                obj.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                obj.setCodigo(Integer.parseInt(txtCodigo.getText()));
                obj.setPlacas(txtPlaca.getText());
                obj.setFechaRegistro(convertido);
                int Marca = obj.getIdMarca(String.valueOf(cmbMarca.getItemAt(cmbMarca.getSelectedIndex())));
                int Tipo = obj.getIdTipo(String.valueOf(cmbTipo.getItemAt(cmbTipo.getSelectedIndex())));
                int Estado = obj.getIdEstado(String.valueOf(cmbEstado.getItemAt(cmbEstado.getSelectedIndex())));
                obj.setIdMarca(Marca);
                obj.setIdTipo(Tipo);
                obj.setIdEstado(Estado);

                if (obj.ingresarUnidad()) {
                    JOptionPane.showMessageDialog(this, "Datos Agregados");
                    tablaUnidad();
                    acoplarTabla();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Esta intentando ingresar datos ya registrados ", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
                }

            }

        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(this, "Error algo malo ha sucedido " + e);
        }

    }

    public void actualizar() {

        try {

            if (txtModelo.getText().trim().equals("") || txtCapacidad.getText().trim().equals("")
                    || txtPlaca.getText().trim().equals("") || txtFechaLanzamiento.getDate() == null || dias > 2920) {
                if (dias > 2920) {
                    txtFechaLanzamiento.setCalendar(null);
                    JOptionPane.showMessageDialog(this, "La unidad no debe superar los 8 años de antiguedad", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
            } else {
                

                obj.setId(Integer.parseInt(txtId.getText()));
                obj.setModelo(txtModelo.getText());
                obj.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                obj.setCodigo(Integer.parseInt(txtCodigo.getText()));
                obj.setPlacas(txtPlaca.getText());
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDateString = dateFormat.format(txtFechaLanzamiento.getDate());
                if (dias <= 2920) {
                    obj.setFechaLanzamiento(startDateString);

                } else {

                    JOptionPane.showMessageDialog(this, "La unidad no debe superar los 8 años de antiguedad");
                }
                
                int Marca = obj.getIdMarca(String.valueOf(cmbMarca.getItemAt(cmbMarca.getSelectedIndex())));
                int Tipo = obj.getIdTipo(String.valueOf(cmbTipo.getItemAt(cmbEstado.getSelectedIndex())));
                int Estado = obj.getIdEstado(String.valueOf(cmbEstado.getItemAt(cmbEstado.getSelectedIndex())));
                obj.setIdTipo(Marca);
                obj.setIdTipo(Tipo);
                obj.setIdEstado(Estado);

                if (obj.actualizarUnidad()) {
                    JOptionPane.showMessageDialog(this, "Datos Actualizados");
                    tablaUnidad();
                    acoplarTabla();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Esta intentando ingresar datos ya registrados ", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(this, "Error algo malo ha sucedido " + e);
        }

    }

    Date date = new Date();
    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
    String convertido = fechaHora.format(date);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        cmbMarca = new javax.swing.JComboBox<>();
        jSeparator18 = new javax.swing.JSeparator();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JFormattedTextField();
        jSeparator21 = new javax.swing.JSeparator();
        txtModelo = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        txtFechaLanzamiento = new com.toedter.calendar.JDateChooser();
        cmbTipo = new javax.swing.JComboBox<>();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbUnidad = new javax.swing.JTable();

        setBackground(new java.awt.Color(33, 33, 33));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Unidades");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mantenimientos");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

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

        btnMostrar.setBackground(new java.awt.Color(153, 153, 153));
        btnMostrar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosbotones/eye.png"))); // NOI18N
        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 180, 55));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 200, 360));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 60, 10));

        txtId.setBackground(new java.awt.Color(33, 33, 33));
        txtId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(null);
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 60, 35));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("MODELO:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));
        add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 160, 10));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("PLACA:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, -1));
        add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 180, 10));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("ESTADO DE UNIDAD:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        cmbEstado.setBackground(new java.awt.Color(33, 33, 33));
        cmbEstado.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbEstado.setForeground(new java.awt.Color(255, 255, 255));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbEstado.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 200, 35));
        add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 200, 10));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("TIPO DE UNIDAD:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, -1, -1));

        cmbMarca.setBackground(new java.awt.Color(33, 33, 33));
        cmbMarca.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbMarca.setForeground(new java.awt.Color(255, 255, 255));
        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMarca.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbMarca.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 160, 35));
        add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 200, 10));

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
        add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 200, 40));

        jSeparator16.setPreferredSize(new java.awt.Dimension(200, 0));
        add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 200, 10));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconografias/search.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, -1, 40));

        txtPlaca.setBackground(new java.awt.Color(33, 33, 33));
        txtPlaca.setBorder(null);
        txtPlaca.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("P ### ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPlaca.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 180, 40));
        add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 160, 10));

        txtModelo.setBackground(new java.awt.Color(33, 33, 33));
        txtModelo.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtModelo.setForeground(new java.awt.Color(255, 255, 255));
        txtModelo.setBorder(null);
        txtModelo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloKeyTyped(evt);
            }
        });
        add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 160, 35));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("MARCA:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("AÑO:");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, 30));
        add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 160, 10));
        add(txtFechaLanzamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 160, 30));

        cmbTipo.setBackground(new java.awt.Color(33, 33, 33));
        cmbTipo.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbTipo.setForeground(new java.awt.Color(255, 255, 255));
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbTipo.setPreferredSize(new java.awt.Dimension(64, 25));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 200, 35));
        add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 110, 10));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CAPACIDAD:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, -1, -1));

        txtCapacidad.setBackground(new java.awt.Color(33, 33, 33));
        txtCapacidad.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtCapacidad.setForeground(new java.awt.Color(255, 255, 255));
        txtCapacidad.setBorder(null);
        txtCapacidad.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCapacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapacidadKeyTyped(evt);
            }
        });
        add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 110, 35));

        txtCodigo.setBackground(new java.awt.Color(33, 33, 33));
        txtCodigo.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setBorder(null);
        txtCodigo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 35));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CODIGO:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, -1, -1));
        add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 110, 10));

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        TbUnidad = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        TbUnidad.setBackground(new java.awt.Color(204, 204, 204));
        TbUnidad.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        TbUnidad.setModel(new javax.swing.table.DefaultTableModel(
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
        TbUnidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbUnidadMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TbUnidad);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 501, 910, 140));
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {

            if (txtModelo.getText().trim().equals("") || txtCapacidad.getText().trim().equals("")
                    || txtPlaca.getText().trim().equals("") || txtFechaLanzamiento.getDate() == null) {

                JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);

            } else {
                calcularDias();
            }

            ingresar();

        } catch (Exception e) {
            System.err.println(e.toString());
        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        try {

            if (txtModelo.getText().trim().equals("") || txtCapacidad.getText().trim().equals("")
                    || txtPlaca.getText().trim().equals("") || txtFechaLanzamiento.getDate() == null) {

                JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);

            } else {
                calcularDias();
            }

            actualizar();

        } catch (Exception e) {
            System.err.println(e.toString());
        }


    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        if (txtId.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Existen campos vacíos. Por favor, rellenelos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        } else {
            int baja = 2;
            obj.setIdEstado(baja);
            obj.setId(Integer.parseInt(txtId.getText()));

            if (obj.eliminarUnidad()) {
                JOptionPane.showMessageDialog(null, "Datos inhabilitados");
                tablaUnidad();
                acoplarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }

        }

    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void TbUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbUnidadMouseClicked

        int fila = TbUnidad.getSelectedRow();
        txtId.setText(String.valueOf(TbUnidad.getValueAt(fila, 0)));
        txtModelo.setText(String.valueOf(TbUnidad.getValueAt(fila, 1)));
        txtCapacidad.setText(String.valueOf(TbUnidad.getValueAt(fila, 2)));
        txtCodigo.setText(String.valueOf(TbUnidad.getValueAt(fila, 3)));
        txtPlaca.setText(String.valueOf(TbUnidad.getValueAt(fila, 4)));

        String nac = TbUnidad.getValueAt(fila, 5).toString();
        SimpleDateFormat formatoNac = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaN = null;
        try {
            fechaN = formatoNac.parse(nac);
        } catch (ParseException ex) {
            Logger.getLogger(PanelUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtFechaLanzamiento.setDate(fechaN);

        cmbMarca.getModel().setSelectedItem(String.valueOf(TbUnidad.getModel().getValueAt(fila, 7)));
        cmbTipo.getModel().setSelectedItem(String.valueOf(TbUnidad.getModel().getValueAt(fila, 8)));
        cmbEstado.getModel().setSelectedItem(String.valueOf(TbUnidad.getModel().getValueAt(fila, 9)));
        btnActualizar.setEnabled(true);
        btnBaja.setEnabled(true);
        btnAgregar.setEnabled(false);

    }//GEN-LAST:event_TbUnidadMouseClicked

    private void txtModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyTyped
        //Validamos que no se puedan poner espacios
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_ENTER) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        //Validamos el limite de caracteres
        if (txtModelo.getText().length() == 16) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtModeloKeyTyped

    private void txtCapacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapacidadKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (int) KeyEvent.VK_ENTER) && (car != (int) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        //Validamos el limite de caracteres
        if (txtCapacidad.getText().length() == 3) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtCapacidadKeyTyped

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (int) KeyEvent.VK_ENTER) && (car != (int) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        //Validamos el limite de caracteres
        if (txtCodigo.getText().length() == 9) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }

    }//GEN-LAST:event_txtCodigoKeyTyped


    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        tablaUnidad();
        acoplarTabla();
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        String titulos[] = {"ID", "Modelo", "Capacidad", "Codigo", "Placas", "Año Unidad", "Fecha Registro", "Marca", "Tipo", "Estado"};
        DefaultTableModel md = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("Select idUnidad , modelo , capacidad, codigo , placas, fechaLanzamiento, fechaRegistro, nombreMarca, tipounidad, estadounidad from Unidad un, Marca mr, TipoUnidad tu, EstadoUnidad es Where un.idMarca=mr.idMarca and un.idTipoUni=tu.idTipoUni and un.idEstadoUni=es.idEstadoUni and modelo LIKE '%" + txtBusqueda.getText() + "%'");
            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
                md.addRow(Filas);
            }
            TbUnidad.setModel(md);
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
        if (txtBusqueda.getText().length() == 15) {
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
    private javax.swing.JTable TbUnidad;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBaja1;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCodigo;
    private com.toedter.calendar.JDateChooser txtFechaLanzamiento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JFormattedTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
