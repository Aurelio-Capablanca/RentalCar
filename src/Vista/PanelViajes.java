/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Modelo.FuncionesViaje;
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
public class PanelViajes extends javax.swing.JPanel {

    /**
     * Creates new form PanelViajes
     */
    Connection con2;
    Statement orden;
    ResultSet rs;
    FuncionesViaje mv = new FuncionesViaje();
    int dias = -1;
    int dias2 = -1;

    public PanelViajes() {
        initComponents();
        FuncionesViaje mv = new FuncionesViaje();
        cmbTipoViaje.setModel(mv.gettipoviaje());
        cmbCliente.setModel(mv.getcliente());
        cmbDepartamento.setModel(mv.getdepartamento());
        cmbEmpleado.setModel(mv.getpersona());
        cmbUnidad.setModel(mv.getunidad());
        con2 = con.conectar();
        if (con2 != null) {
            this.verTabla();
            acoplarTabla();
        }
        txtCodigo.setEnabled(false);
    }
    Conexion con = new Conexion();
    FuncionesViaje mtp = new FuncionesViaje();

    Date date = new Date();
    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
    String convertido = fechaHora.format(date);

    public void acoplarTabla() {
        tableViajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tableViajes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(250);
        columnModel.getColumn(8).setPreferredWidth(150);
        columnModel.getColumn(9).setPreferredWidth(150);
    }

    public void verTabla() {

        String titulos[] = {"ID", "direccion", "Precio", "Hora de Salida", "Hora de Retorno", "Personal", "Unidad", "Cliente", "Departamento", "Tipo de Viaje"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("SELECT idViaje, vj.direccion,precio,horaSalida,horaRetorno, nombres , modelo, nombre, departamento, tipoViaje from Viaje vj ,  Persona pr , Unidad un, Cliente cl , Departamento dp , TipoViaje tv where vj.idPersona=pr.idPersona and vj.idUnidad=un.idUnidad and vj.idCliente=cl.idCliente and vj.idDepartamento=dp.idDepartamento and vj.idTipoViaje=tv.idTipoViaje");

            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
                tm.addRow(Filas);
            }
            tableViajes.setModel(tm);
        } catch (Exception ex) {
            Logger.getLogger(PanelPreguntas.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void calcularDias1() {

        Calendar inicio = dateInicio.getCalendar();
        Calendar today = Calendar.getInstance();
        dias = 1;
        while (inicio.before(today) || inicio.equals(today)) {
            dias++;
            inicio.add(Calendar.DATE, 1);
        }
    }

    public void calcularDias2() {

        Calendar inicio = dateRetorno.getCalendar();
        Calendar today = Calendar.getInstance();
        dias2 = 1;
        while (inicio.before(today) || inicio.equals(today)) {
            dias2++;
            inicio.add(Calendar.DATE, 1);
        }
    }

    public void Ingresar() {
        if (txtDireccion.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()
                || dateInicio.getDate() == null || dias > 7 || dias == -1 || dateRetorno.getDate() == null || dias2 > 7 || dias2 == -1) {
            if (dias > 7) {
                dateInicio.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El viaje tiene que tener una fecha acorde", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            if (dias2 > 7) {
                dateRetorno.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El viaje tiene que tener una fecha acorde", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {
            int ids = mv.idIncremental();
            mv.setId(ids);
            mv.setDireccion(txtDireccion.getText());
            mv.setPrecio(Double.parseDouble(txtPrecio.getText()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString1 = dateFormat.format(dateInicio.getDate());

            if (dias <= 7) {
                mv.setfSalida(startDateString1);

            } else {
                System.out.println("" + dias);
                JOptionPane.showMessageDialog(this, "Debes ser mayor a 18 años");
            }

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString2 = dateFormat1.format(dateRetorno.getDate());

            if (dias2 < 7 || dias2 == dias) {
                mv.setfRetorno(startDateString2);

            } else {
                System.out.println("" + dias);
                JOptionPane.showMessageDialog(this, "Debes ser mayor a 18 años");
            }

            mv.setfRegistro(convertido);

            int idp = mv.getIdPersona(String.valueOf(cmbEmpleado.getItemAt(cmbEmpleado.getSelectedIndex())));
            mv.setIdPersona(idp);
            int idc = mv.getIdCliente(String.valueOf(cmbCliente.getItemAt(cmbCliente.getSelectedIndex())));
            mv.setIdCliente(idc);
            int idu = mv.getIdUnidad(String.valueOf(cmbUnidad.getItemAt(cmbUnidad.getSelectedIndex())));
            mv.setIdUnidad(idu);
            int idd = mv.getIdDepartamento(String.valueOf(cmbDepartamento.getItemAt(cmbDepartamento.getSelectedIndex())));
            mv.setIdDepartamento(idd);
            int idt = mv.getIdTipoViaje(String.valueOf(cmbTipoViaje.getItemAt(cmbTipoViaje.getSelectedIndex())));
            mv.setIdTipoViaje(idt);

            if (mv.guardarViaje()) {
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                verTabla();
                acoplarTabla();
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar datos");
            }

        }

    }

    public void Actualizar() {
        if (txtDireccion.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()
                || dateInicio.getDate() == null || dias > 7 || dias == -1 || dateRetorno.getDate() == null || dias2 > 7 || dias2 == -1) {

            if (dias > 7) {
                dateInicio.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El viaje tiene que tener una fecha acorde", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            if (dias2 > 7) {
                dateRetorno.setCalendar(null);
                JOptionPane.showMessageDialog(this, "El viaje tiene que tener una fecha acorde", "Campo no acorde a lo solicitado", JOptionPane.WARNING_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {

            mv.setId(Integer.parseInt(txtCodigo.getText()));
            mv.setDireccion(txtDireccion.getText());
            mv.setPrecio(Double.parseDouble(txtPrecio.getText()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString1 = dateFormat.format(dateInicio.getDate());

            if (dias <= 7) {
                mv.setfSalida(startDateString1);

            } else {
                System.out.println("" + dias);
                JOptionPane.showMessageDialog(this, "Debes ser mayor a 18 años");
            }

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString2 = dateFormat1.format(dateRetorno.getDate());

            if (dias2 < 7 || dias2 == dias) {
                mv.setfRetorno(startDateString2);

            } else {
                System.out.println("" + dias);
                JOptionPane.showMessageDialog(this, "Debes ser mayor a 18 años");
            }

            int idp = mv.getIdPersona(String.valueOf(cmbEmpleado.getItemAt(cmbEmpleado.getSelectedIndex())));
            mv.setIdPersona(idp);
            int idc = mv.getIdCliente(String.valueOf(cmbCliente.getItemAt(cmbCliente.getSelectedIndex())));
            mv.setIdCliente(idc);
            int idu = mv.getIdUnidad(String.valueOf(cmbUnidad.getItemAt(cmbUnidad.getSelectedIndex())));
            mv.setIdUnidad(idu);
            int idd = mv.getIdDepartamento(String.valueOf(cmbDepartamento.getItemAt(cmbDepartamento.getSelectedIndex())));
            mv.setIdDepartamento(idd);
            int idt = mv.getIdTipoViaje(String.valueOf(cmbTipoViaje.getItemAt(cmbTipoViaje.getSelectedIndex())));
            mv.setIdTipoViaje(idt);

            if (mv.modificarViaje()) {
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                verTabla();
                acoplarTabla();
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar datos");
            }

        }

    }

    public void Limpiar() {
        txtCodigo.setText("");
        txtDireccion.setText(" ");
        txtPrecio.setText(" ");
        dateInicio.setCalendar(null);
        dateRetorno.setCalendar(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txtNombre1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        txtNombre2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableServicios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtDireccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        cmbEmpleado = new javax.swing.JComboBox<>();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        cmbUnidad = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableViajes = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        cmbTipoViaje = new javax.swing.JComboBox<>();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox<>();
        jSeparator22 = new javax.swing.JSeparator();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator23 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();
        dateInicio = new com.toedter.calendar.JDateChooser();
        dateRetorno = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        txtPrecio = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        txtCodigo = new javax.swing.JTextField();

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("FECHA DE INICIO:");

        txtNombre1.setBackground(new java.awt.Color(23, 37, 42));
        txtNombre1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtNombre1.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre1.setBorder(null);
        txtNombre1.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("FECHA DE INICIO:");

        txtNombre2.setBackground(new java.awt.Color(23, 37, 42));
        txtNombre2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtNombre2.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre2.setBorder(null);
        txtNombre2.setCaretColor(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tableServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableServicios);

        setBackground(new java.awt.Color(33, 33, 33));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Viajes");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DIRECCION:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));
        add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 200, 10));

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
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 200, 40));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("PRECIO $:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 200, 10));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("FECHA DE INICIO:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, -1, -1));
        add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 200, 8));
        add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 200, 8));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("EMPLEADO:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        cmbEmpleado.setBackground(new java.awt.Color(33, 33, 33));
        cmbEmpleado.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        cmbEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEmpleado.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 200, 35));
        add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 200, 10));
        add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 200, 10));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("UNIDAD:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, -1));

        cmbUnidad.setBackground(new java.awt.Color(33, 33, 33));
        cmbUnidad.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbUnidad.setForeground(new java.awt.Color(255, 255, 255));
        cmbUnidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbUnidad.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 200, 35));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("CLIENTE:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, -1, -1));

        cmbCliente.setBackground(new java.awt.Color(33, 33, 33));
        cmbCliente.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbCliente.setForeground(new java.awt.Color(255, 255, 255));
        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCliente.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 200, 35));

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        tableViajes = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tableViajes.setBackground(new java.awt.Color(204, 204, 204));
        tableViajes.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        tableViajes.setModel(new javax.swing.table.DefaultTableModel(
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
        tableViajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViajesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableViajes);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 910, 170));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("TIPO DE VIAJE:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        cmbTipoViaje.setBackground(new java.awt.Color(33, 33, 33));
        cmbTipoViaje.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbTipoViaje.setForeground(new java.awt.Color(255, 255, 255));
        cmbTipoViaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipoViaje.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbTipoViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 200, 35));
        add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 200, 10));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("DEPARTAMENTO:");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, -1));

        cmbDepartamento.setBackground(new java.awt.Color(33, 33, 33));
        cmbDepartamento.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cmbDepartamento.setForeground(new java.awt.Color(255, 255, 255));
        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDepartamento.setPreferredSize(new java.awt.Dimension(64, 25));
        add(cmbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 200, 35));
        add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 200, 10));

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

        jSeparator23.setPreferredSize(new java.awt.Dimension(200, 0));
        add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 180, 10));

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

        dateInicio.setBackground(new java.awt.Color(33, 33, 33));
        dateInicio.setForeground(new java.awt.Color(255, 255, 255));
        dateInicio.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        add(dateInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 200, 40));

        dateRetorno.setBackground(new java.awt.Color(33, 33, 33));
        dateRetorno.setForeground(new java.awt.Color(255, 255, 255));
        dateRetorno.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        add(dateRetorno, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 200, 40));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("FECHA DE RETORNO:");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, -1, -1));
        add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 200, 10));

        txtPrecio.setBackground(new java.awt.Color(33, 33, 33));
        txtPrecio.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecio.setBorder(null);
        txtPrecio.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 200, 35));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("ID:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));
        add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 200, 10));

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
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 200, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (txtDireccion.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()
                || dateInicio.getDate() == null || dateRetorno.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {

            calcularDias1();
            calcularDias2();
        }
        Ingresar();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        verTabla();
        acoplarTabla();
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (txtDireccion.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()
                || dateInicio.getDate() == null || dateRetorno.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todo lo solicitado");
        } else {

            calcularDias1();
            calcularDias2();
        }
        Actualizar();

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void tableViajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViajesMouseClicked

        try {
            int fila = tableViajes.getSelectedRow();

            txtCodigo.setText(String.valueOf(tableViajes.getValueAt(fila, 0).toString()));
            txtDireccion.setText(String.valueOf(tableViajes.getValueAt(fila, 1).toString()));
            txtPrecio.setText(String.valueOf(tableViajes.getValueAt(fila, 2).toString()));

            String nac = tableViajes.getValueAt(fila, 3).toString();
            SimpleDateFormat formatoNac = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaN = null;
            fechaN = formatoNac.parse(nac);
            dateInicio.setDate(fechaN);

            String ret = tableViajes.getValueAt(fila, 4).toString();
            SimpleDateFormat formatoRac = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaR = null;
            fechaR = formatoRac.parse(ret);
            dateRetorno.setDate(fechaR);

            cmbEmpleado.setSelectedItem(String.valueOf(tableViajes.getValueAt(fila, 5)));
            cmbUnidad.setSelectedItem(String.valueOf(tableViajes.getValueAt(fila, 6)));
            cmbCliente.setSelectedItem(String.valueOf(tableViajes.getValueAt(fila, 7)));
            cmbDepartamento.setSelectedItem(String.valueOf(tableViajes.getValueAt(fila, 8)));
            cmbTipoViaje.setSelectedItem(String.valueOf(tableViajes.getValueAt(fila, 9)));

        } catch (Exception e) {
            System.err.println(e.toString());
        }


    }//GEN-LAST:event_tableViajesMouseClicked

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.' || txtPrecio.getText().contains("."))) {
            evt.consume();
        }
        if (txtPrecio.getText().length() == 7) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }

    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        Character e;
        e = evt.getKeyChar();
        if (!Character.isLetter(e) && e != KeyEvent.VK_SPACE) {
            evt.consume();
        }

        //Validamos el limite de caracteres
        if (txtDireccion.getText().length() == 55) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Limite de caracteres");
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed

        String titulos[] = {"ID", "direccion", "Precio", "Hora de Salida", "Hora de Retorno", "Personal", "Unidad", "Cliente", "Departamento", "Tipo de Viaje"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        try {
            orden = con2.createStatement();
            rs = orden.executeQuery("SELECT idViaje, vj.direccion,precio,horaSalida,horaRetorno, nombres , modelo, nombre, departamento, tipoViaje from Viaje vj ,  Persona pr , Unidad un, Cliente cl , Departamento dp , TipoViaje tv where vj.idPersona=pr.idPersona and vj.idUnidad=un.idUnidad and vj.idCliente=cl.idCliente and vj.idDepartamento=dp.idDepartamento and vj.idTipoViaje=tv.idTipoViaje and departamento LIKE '%" + txtBusqueda.getText() + "%'");

            while (rs.next()) {
                Object Filas[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
                tm.addRow(Filas);
            }
            tableViajes.setModel(tm);
            acoplarTabla();
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
        if (txtBusqueda.getText().length() == 20) {
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
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbEmpleado;
    private javax.swing.JComboBox<String> cmbTipoViaje;
    private javax.swing.JComboBox<String> cmbUnidad;
    private com.toedter.calendar.JDateChooser dateInicio;
    private com.toedter.calendar.JDateChooser dateRetorno;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JTable tableServicios;
    private javax.swing.JTable tableViajes;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
