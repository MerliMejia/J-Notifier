package notificador.UI;

import com.bulenkov.darcula.DarculaLaf;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.table.DefaultTableModel;
/**
 *ESTA CLASE SE ENCARGA DE MOSTRAR LA UI PRINCIPAL DEL PROGRAMA DONDE SE PUEDE AGREGAR, MODIFICAR Y BORRAR NOTIFICACIONES
 * 
 * @author Merli Mejia Tavarez - merlimejia2@gmail.com
 * 
 */
public class mainForm extends javax.swing.JFrame {

    public mainForm() {
        initComponents();
        BasicLookAndFeel darcula = new DarculaLaf();//LOOK AND FEEL DARCULA
        try {
            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("PROBLEMA!");
        }
        
        for(Window window : getWindows()) {//ACTUALIZAR EL LOOK AND FEEL DE TODAS MIS VENTANAS
            SwingUtilities.updateComponentTreeUI(window);
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//OBTENER TAMAÑO DE PANTALLA
        setLocation((screenSize.width - getSize().width) / 2, (screenSize.height - getSize().height) / 2);//PONER EN EL MEDIO DE LA PANTALLA
        
        
        setVisible(true);
        tabulador.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               if(tabulador.getSelectedIndex() == 1)
               {
                   traerDatosTabla();
               }
            }
        });
        
        
        
    }
    /**
     * ESTE METODO 0
     */
    void traerDatosTabla()
    {
        String rutaJSON = System.getProperty("user.dir") + "\\DBB.json";//RUTA DEL .JSON
                
        JsonParser parse = new JsonParser();
        
        try {
            JsonObject objeto = (JsonObject) parse.parse(new FileReader(rutaJSON));
            JsonArray array = (JsonArray) objeto.get("datos");
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            for(int i = 0; i < array.size(); i++)
            {
                JsonObject item = (JsonObject) array.get(i);
                Object[] fila = new Object[]{item.get("RESTAURANTES").toString().replace("\"", ""), 
                    item.get("HABITACION").toString().replace("\"", ""), item.get("OBSERVACIONES").toString().replace("\"", ""), 
                    item.get("HORA").toString().replace("\"", ""), item.get("FECHA").toString().replace("\"", ""), 
                    item.get("C.ADULTOS").toString().replace("\"", ""), item.get("C.NIÑOS").toString().replace("\"", "")};
                
                modelo.addRow(fila);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //CODIGO GENERADO POR NETBEANS... SI, USO NETBEANS PARA HACER UI
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabulador = new javax.swing.JTabbedPane();
        panelAgregar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        locata = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        horaFecha = new com.github.lgooddatepicker.components.DateTimePicker();
        restaurantes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        observaciones = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        preHabitacion = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        cAdultos = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        cNinos = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        editar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J-Notifier");
        setBackground(new java.awt.Color(44, 44, 44));
        setName("formulario"); // NOI18N
        setResizable(false);

        tabulador.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel1.setText("RESTAURANTE");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel2.setText("HABITACION");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        locata.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setText("FECHA/HORA");
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notificador/resources/plus.png"))); // NOI18N
        agregar.setText("AGREGAR");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notificador/resources/notification-clear-all.png"))); // NOI18N
        limpiar.setText("LIMPIAR");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        horaFecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        restaurantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE RESTAURANTE", "LE GOURMET", "EL PESCADOR", "IL CAPPRICIO", "DON PABLO", "RODIZIO", "TAKARA" }));
        restaurantes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setText("OBSERVACIONES");
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        observaciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        preHabitacion.setText("*PRE");
        preHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preHabitacionMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("CANT. ADULTOS");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("CANT. NIÑOS");

        javax.swing.GroupLayout panelAgregarLayout = new javax.swing.GroupLayout(panelAgregar);
        panelAgregar.setLayout(panelAgregarLayout);
        panelAgregarLayout.setHorizontalGroup(
            panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAgregarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(limpiar)
                        .addGap(18, 18, 18)
                        .addComponent(agregar)
                        .addGap(58, 58, 58))
                    .addGroup(panelAgregarLayout.createSequentialGroup()
                        .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(locata, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(preHabitacion)
                                .addGroup(panelAgregarLayout.createSequentialGroup()
                                    .addComponent(observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(cAdultos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(cNinos, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(horaFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(restaurantes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(53, Short.MAX_VALUE))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelAgregarLayout.setVerticalGroup(
            panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(restaurantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(preHabitacion))
                .addGap(18, 18, 18)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cAdultos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(cNinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(horaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(limpiar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabulador.addTab("AGREGAR", panelAgregar);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RESTAURANTES", "HABITACION", "OBSERVACIONES", "HORA", "FECHA", "C.ADULTOS", "C.NIÑOS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notificador/resources/pencil.png"))); // NOI18N
        editar.setText("EDITAR");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notificador/resources/close.png"))); // NOI18N
        borrar.setText("BORRAR");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(borrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editar)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar)
                    .addComponent(borrar))
                .addGap(22, 22, 22))
        );

        tabulador.addTab("MODIFICAR", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabulador)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabulador)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<String> filaSeleccionada = new ArrayList<>();
    private int nFila = -1;
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

        filaSeleccionada.clear();
        if(editandoFila == false)
        {
            nFila = tabla.getSelectedRow();
        }
        
        tabla.changeSelection(nFila, tabla.getSelectedColumn(), false, false);
        
        for(int i = 0; i < tabla.getColumnCount(); i++)
        {
            //System.out.println(tabla.getValueAt(tabla.getSelectedRow(), i));
            filaSeleccionada.add((String) tabla.getValueAt(tabla.getSelectedRow(), i));
        }
        //System.out.println(filaSeleccionada);
    }//GEN-LAST:event_tablaMouseClicked

    private boolean editandoFila = false;
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        //System.out.println(System.getProperty("user.dir"));
        int fila = tabla.getSelectedRow();
        if(editandoFila == false)
        {
            if(fila == -1)
            {
                JOptionPane.showMessageDialog(rootPane, "POR FAVOR SELECCIONE UNA FILA", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }else
            {
                String rutaIcono = "notificador/resources/check.png";
                //System.out.println(rutaIcono + "\n" + fila);
                editar.setText("ACEPTAR");
                Icon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaIcono));
                editar.setIcon(icono);
                editandoFila = true;
                tabla.setSelectionBackground(Color.lightGray);
                tabla.setSelectionForeground(Color.black);
                
                JTextField texto = new JTextField();
                DateTimePicker fechaHora = new DateTimePicker();
                JSpinner spinner = new JSpinner();
                
                texto.setText((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()));
                
                if(tabla.getSelectedColumn() >= 3)
                {
                    if(tabla.getSelectedColumn() == 3)
                    {
                        fechaHora.timePicker.setText((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()));
                        fechaHora.datePicker.setText((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn() + 1));
                        
                        JOptionPane.showMessageDialog(rootPane, fechaHora, "ESCRIBIR VALOR", JOptionPane.INFORMATION_MESSAGE);
                        
                        tabla.setValueAt(fechaHora.timePicker.getText(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                        tabla.setValueAt(fechaHora.datePicker.getText(), tabla.getSelectedRow(), tabla.getSelectedColumn() + 1);
                    }else if(tabla.getSelectedColumn() == 4)
                    {
                        fechaHora.timePicker.setText((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn() - 1));
                        fechaHora.datePicker.setText((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()));
                        
                        JOptionPane.showMessageDialog(rootPane, fechaHora, "ESCRIBIR VALOR", JOptionPane.INFORMATION_MESSAGE);
                        
                        tabla.setValueAt(fechaHora.timePicker.getText(), tabla.getSelectedRow(), tabla.getSelectedColumn() - 1);
                        tabla.setValueAt(fechaHora.datePicker.getText(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                    }else
                    {   
                        spinner.setValue(Integer.parseInt((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())));
                        JOptionPane.showMessageDialog(rootPane, spinner, "ESCRIBIR VALOR", JOptionPane.INFORMATION_MESSAGE);
                        
                        tabla.setValueAt(spinner.getValue().toString(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                    }
                    
                    
                    
                }else
                {
                    JOptionPane.showMessageDialog(rootPane, texto, "ESCRIBIR VALOR", JOptionPane.INFORMATION_MESSAGE);
                    
                    tabla.setValueAt(texto.getText(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                }
                
                
                
            }

        }else
        {
            if(fila == -1)
            {
                JOptionPane.showMessageDialog(rootPane, "POR FAVOR SELECCIONE UNA FILA", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }else
            {
                String rutaIcono = "notificador/resources/pencil.png";
                //System.out.println(rutaIcono + "\n" + fila);
                editar.setText("EDITAR");
                Icon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaIcono));
                editar.setIcon(icono);
                editandoFila = false;
                tabla.setSelectionBackground(new Color(75,110,175));
                tabla.setSelectionForeground(Color.white);
                
                JsonObject objeto = new JsonObject();
                
                tabla.changeSelection(tabla.getSelectedRow(), 0, false, false);
                for(int i = 0; i < tabla.getColumnCount(); i++)
                { 
                    objeto.addProperty(tabla.getColumnName(tabla.getSelectedColumn() + i), 
                            tabla.getValueAt(tabla.getSelectedRow(), i).toString());
                    
                    //System.out.println(tabla.getColumnName(tabla.getSelectedColumn()) + "  " + tabla.getValueAt(tabla.getSelectedRow(), i).toString());
                }
                //System.out.println(objeto);
                String rutaJSON = System.getProperty("user.dir") + "\\DBB.json";//RUTA DEL .JSON
                
                JsonParser parse = new JsonParser();
                
                try {
                    Object obj = parse.parse(new FileReader(rutaJSON));//LEO EL JSON
                    JsonObject json = (JsonObject) obj;//JSON OBJECT
                    
                    JsonArray array = (JsonArray) json.get("datos");//ARRAY DE DATOS
                    
                    JsonElement filaObtenida = array.get(tabla.getSelectedRow());
                    JsonObject nuevo = (JsonObject) filaObtenida;
                    
                    objeto.add("KEY", nuevo.get("KEY"));

                    array.set(tabla.getSelectedRow(), objeto);
                    json.add("datos", array);
                    
                   //SOBRE ESCRIBO EL NUEVO .JSON
                    try {
                        FileWriter file = new FileWriter(rutaJSON);
                        file.write(json.toString());
                        file.flush();
                        
                        restaurantes.setSelectedIndex(0);
                        locata.setText("");
                        observaciones.setText("");
                        horaFecha.datePicker.setText("");
                        horaFecha.timePicker.setText("");
                        
                        preHabitacion.setSelected(false);
                    } catch (IOException ex) {
                        Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //System.out.println(json);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //System.out.println(array);
            }
            
        }
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        int fila = tabla.getSelectedRow();
         if(fila == -1)
            {
                JOptionPane.showMessageDialog(rootPane, "POR FAVOR SELECCIONE UNA FILA", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }else
            {
                int opcion = JOptionPane.showConfirmDialog(rootPane, "BORRAR ESTE RECORDATORIO?", 
                "CONFIRMACION", JOptionPane.YES_NO_OPTION);
                
                if(opcion == 0)
                {
                    String rutaJSON = System.getProperty("user.dir") + "\\DBB.json";//RUTA DEL .JSON
                
                    JsonParser parse = new JsonParser();

                    try {
                        Object obj = parse.parse(new FileReader(rutaJSON));//LEO EL JSON
                        JsonObject json = (JsonObject) obj;//JSON OBJECT

                        JsonArray array = (JsonArray) json.get("datos");//ARRAY DE DATOS
                        array.remove(tabla.getSelectedRow());
                        
                        json.add("datos", array);

                       //SOBRE ESCRIBO EL NUEVO .JSON
                        try {
                            FileWriter file = new FileWriter(rutaJSON);
                            file.write(json.toString());
                            file.flush();
                            traerDatosTabla();
                        } catch (IOException ex) {
                            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //System.out.println(json);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
            }
    }//GEN-LAST:event_borrarActionPerformed

    private void preHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preHabitacionMouseClicked
        if(preHabitacion.isSelected())
        {
            locata.setText("PRE-SELECCIONADA");
            locata.setEditable(false);
        }else
        {
            locata.setText("");
            locata.setEditable(true);
        }
    }//GEN-LAST:event_preHabitacionMouseClicked

    //ESTE METODO SE ENCARGA DE LIMPIAR LOS CAMPOS
    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        restaurantes.setSelectedIndex(0);
        locata.setText("");
        observaciones.setText("");
        horaFecha.datePicker.setText("");
        horaFecha.timePicker.setText("");
    }//GEN-LAST:event_limpiarActionPerformed

    /***
     * 
     * ESTE METODO SE ENCARGA DE CREAR LAS NOTIFICACIONES Y GUARDARLAS EN LA BASE DE DATOS.
     * TENER EN CUENTA QUE LA BASE DE DATOS NO ES MAS QUE UN .JSON, EL CUAL LEO, AGREGO LOS NUEVOS REGISTROS Y VUELVO A SOBREESCRIBIR
     */
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed

        //CONDICION PARA QUE NO SE GUARDEN DATOS EN BLANCO
        if(restaurantes.getSelectedIndex() != 0 && locata.getText().length() != 0 &&
            observaciones.getText().length() != 0 && horaFecha.datePicker.getText().length() != 0 &&
            horaFecha.timePicker.getText().length() != 0)
        {
            int opcion = JOptionPane.showConfirmDialog(rootPane, "AGREGAR ESTE RECORDATORIO?",
                "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            if(opcion == 0)
            {

                String rutaJSON = System.getProperty("user.dir") + "\\DBB.json";//RUTA DEL .JSON

                JsonParser parse = new JsonParser();

                try {
                    Object obj = parse.parse(new FileReader(rutaJSON));//LEO EL JSON
                    JsonObject json = (JsonObject) obj;//JSON OBJECT
                    JsonArray array = (JsonArray) json.get("datos");//ARRAY DE DATOS
                    int tDatos = json.get("total-datos").getAsInt();//TOTAL DE DATOS INGRESADOS

                    JsonObject cuerpoNuevoDato = new JsonObject();
                    String key = randomString(10);//LA KEY DE CADA REGISTRO ES ALEATORIA

                    /*CREO EL CUERPO DEL OBJETO QUE AGREGARE AL ARRAY*/
                    cuerpoNuevoDato.addProperty("RESTAURANTES", restaurantes.getSelectedItem().toString());
                    cuerpoNuevoDato.addProperty("HABITACION", locata.getText());
                    cuerpoNuevoDato.addProperty("OBSERVACIONES", observaciones.getText());
                    cuerpoNuevoDato.addProperty("FECHA", horaFecha.datePicker.getText());
                    cuerpoNuevoDato.addProperty("HORA", horaFecha.timePicker.getText());
                    cuerpoNuevoDato.addProperty("C.ADULTOS", cAdultos.getValue().toString());
                    cuerpoNuevoDato.addProperty("C.NIÑOS", cNinos.getValue().toString());
                    /******************************************************************************************/

                    cuerpoNuevoDato.addProperty("KEY", key);
                    array.add(cuerpoNuevoDato);

                    json.add("datos", array);
                    json.addProperty("total-datos", tDatos+ 1);
                    json.addProperty("ultimo-dato", key);

                    //SOBRE ESCRIBO EL NUEVO .JSON
                    try {
                        FileWriter file = new FileWriter(rutaJSON);
                        file.write(json.toString());
                        file.flush();

                        restaurantes.setSelectedIndex(0);
                        locata.setText("");
                        observaciones.setText("");
                        horaFecha.datePicker.setText("");
                        horaFecha.timePicker.setText("");

                        preHabitacion.setSelected(false);
                    } catch (IOException ex) {
                        Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //System.out.println(json);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else
            {
                //System.out.println("NO!");
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "FAVOR LLENAR TODOS LOS CAMPOS", "ERROR!", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_agregarActionPerformed

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    /**
     * 
     * @param len TAMAÑO DEL STRING QUE SE DEVOLVERA
     * @return RETORNA UNA CADENA TOTALMENTE RANDOM
     */
    String randomString( int len ){
       StringBuilder sb = new StringBuilder( len );
       for( int i = 0; i < len; i++ ) 
          sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
       return sb.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JSpinner cAdultos;
    private javax.swing.JSpinner cNinos;
    private javax.swing.JButton editar;
    private com.github.lgooddatepicker.components.DateTimePicker horaFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField locata;
    private javax.swing.JTextField observaciones;
    private javax.swing.JPanel panelAgregar;
    private javax.swing.JCheckBox preHabitacion;
    private javax.swing.JComboBox<String> restaurantes;
    private javax.swing.JTable tabla;
    private javax.swing.JTabbedPane tabulador;
    // End of variables declaration//GEN-END:variables
}
