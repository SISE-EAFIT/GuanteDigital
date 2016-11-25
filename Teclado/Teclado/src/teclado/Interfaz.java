package teclado;

import javax.swing.*;

import java.io.InputStream;
import java.io.IOException;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEventListener;

import java.util.Enumeration;

import javax.swing.*;
import java.awt.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import static java.awt.image.ImageObserver.ERROR;

/**
 *
 * @author Michell Pineda
 */

public class Interfaz extends JFrame implements Runnable, SerialPortEventListener {
    
    
    //Componenetes para la conexión con Arduino
    SerialPort serialPort;
    private InputStream Input = null;
    //Puerto al que se conecta el arduino
    private final String PORT_NAME = "/dev/cu.usbmodem1411";
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    String letra;
    String caracter;
    Robot simulando;
    
    int cont1;
    int cont2;


    public Interfaz() {
        ArduinoConnection();
        initComponents();
    }

     public void ArduinoConnection(){
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if (PORT_NAME.equals(currPortId.getName())) {
                portId = currPortId;
                break;
            }
        }
        if (portId == null){
            System.exit(ERROR);
            return;
        }
        try{
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                                        SerialPort.STOPBITS_1,
                                        SerialPort.PARITY_NONE);
            //Variable que recibe los datos
            Input = serialPort.getInputStream();
            //Evento que reconoce el Serial.println
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.exit(ERROR);
        }
    }

    private String RecibirDatos() throws IOException {
        //avalilble estima la cantidad de bytes que esta mandando el arduino
        int available = Input.available();
        //en necesario una variable byte para decirle al metodo read 
        //cuanto va a leer
        byte lectura[] = new byte[available];
        //leer los datos enviados por el arduino
        Input.read(lectura, 0, available);
        String datos = new String(lectura);
        
        return datos;
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Teclado");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));

        jSlider1.setBackground(new java.awt.Color(255, 255, 255));
        jSlider1.setMaximum(7);
        jSlider1.setMinimum(-6);
        jSlider1.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider1.setValue(cont1);
        jSlider1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jSlider1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jSlider1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider1KeyPressed(evt);
            }
        });

        jSlider2.setBackground(new java.awt.Color(255, 255, 255));
        jSlider2.setMaximum(7);
        jSlider2.setMinimum(-6);
        jSlider2.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider2.setValue(cont2);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Índice");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Corazón");

        jLabel3.setText(letra);
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText(letra);
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText(caracter);
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Caracter");

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Descativar");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(35, 35, 35)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)))
                    .addComponent(jCheckBox1))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jSlider1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSlider1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider1AncestorAdded

    private void jSlider1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider1KeyPressed


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables

    public synchronized void serialEvent(SerialPortEvent oEvent){
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                //Simulando teclado
                simulando = new Robot();
                
                //Llamando al método que recibe los valores del arduino
                letra = RecibirDatos();
                
                /*CARACTERES*/
                if(letra.contains("8")){
                    caracter = "{ }";
                    System.out.println("space");
                    simulando.keyPress(KeyEvent.VK_SPACE);
                }
                if(letra.contains("13")){
                    caracter = "<-'";
                    System.out.println("enter");
                    simulando.keyPress(KeyEvent.VK_ENTER);
                }
                if(letra.contains("32")){
                    caracter = "<-";
                    System.out.println("delete");
                    simulando.keyPress(KeyEvent.VK_BACK_SPACE);
                }
                if(letra.contains("40")){
                    caracter = "(";
                    System.out.println("(");
                    simulando.keyPress(KeyEvent.VK_LEFT_PARENTHESIS);
                }
                if(letra.contains("41")){
                    caracter = ")";
                    System.out.println(")");
                    simulando.keyPress(KeyEvent.VK_RIGHT_PARENTHESIS);
                }
                if(letra.contains("43")){
                    caracter = "+";
                    System.out.println("+");
                    simulando.keyPress(KeyEvent.VK_PLUS);
                }                
                if(letra.contains("44")){
                    caracter = ",";
                    System.out.println(",");
                    simulando.keyPress(KeyEvent.VK_COMMA);
                }
                if(letra.contains("45")){
                    caracter = "-";
                    System.out.println("-");
                    simulando.keyPress(KeyEvent.VK_MINUS);
                }
                if(letra.contains("46")){
                    caracter = ".";
                    System.out.println(".");
                    simulando.keyPress(KeyEvent.VK_PERIOD);
                }
                if(letra.contains("47")){
                    caracter = "/";
                    System.out.println("/");
                    simulando.keyPress(KeyEvent.VK_SLASH);
                }
                if(letra.contains("58")){
                    caracter = ":";
                    System.out.println(":");
                    simulando.keyPress(KeyEvent.VK_COMMA);
                }
                if(letra.contains("61")){
                    caracter = "=";
                    System.out.println("=");
                    simulando.keyPress(KeyEvent.VK_EQUALS);
                }
                if(letra.contains("63")){
                    caracter = "?";
                    System.out.println("?");
                    simulando.keyPress(KeyEvent.VK_QUOTE);
                }
                if(letra.contains("64")){
                    caracter = "@";
                    System.out.println("@");
                    simulando.keyPress(KeyEvent.VK_AT);   
                }
                /*NUMEROS*/
                if(letra.contains("48")){
                    caracter = "0";
                    System.out.println("0");
                    simulando.keyPress(KeyEvent.VK_0);
                }
                if(letra.contains("49")){
                    caracter = "1";
                    System.out.println("1");
                    simulando.keyPress(KeyEvent.VK_1);
                }
                if(letra.contains("50")){
                    caracter = "2";
                    System.out.println("2");
                    simulando.keyPress(KeyEvent.VK_2);
                }
                if(letra.contains("51")){
                    caracter = "3";
                    System.out.println("3");
                    simulando.keyPress(KeyEvent.VK_3);
                }
                if(letra.contains("52")){
                    caracter = "4";
                    System.out.println("4");
                    simulando.keyPress(KeyEvent.VK_4);
                }
                if(letra.contains("53")){
                    caracter = "5";
                    System.out.println("5");
                    simulando.keyPress(KeyEvent.VK_5);
                }                
                if(letra.contains("54")){
                    caracter = "6";
                    System.out.println("6");
                    simulando.keyPress(KeyEvent.VK_6);
                }
                if(letra.contains("55")){
                    caracter = "7";
                    System.out.println("7");
                    simulando.keyPress(KeyEvent.VK_7);
                }
                if(letra.contains("56")){
                    caracter = "8";
                    System.out.println("8");
                    simulando.keyPress(KeyEvent.VK_8);
                }
                if(letra.contains("57")){
                    caracter = "9";
                    System.out.println("9");
                    simulando.keyPress(KeyEvent.VK_9);
                }
                
                
                /*TECLAS FLEX INDICE*/
                if(letra.contains("97")){
                    cont1 = 1;
                    System.out.println("a");
                    simulando.keyPress(KeyEvent.VK_A);
                }
                if(letra.contains("98")){
                    cont1 = 2;
                    System.out.println("b");
                    simulando.keyPress(KeyEvent.VK_L);
                }
                if(letra.contains("99")){
                    cont1 = 3;
                    System.out.println("c");
                    simulando.keyPress(KeyEvent.VK_C);
                }
                if(letra.contains("100")){
                    cont1 = 4;
                    System.out.println("d");
                    simulando.keyPress(KeyEvent.VK_D);
                }
                if(letra.contains("101")){
                    cont1 = 5;
                    System.out.println("e");
                    simulando.keyPress(KeyEvent.VK_E);
                }
                if(letra.contains("102")){
                    cont1 = 6;
                    System.out.println("f");
                    simulando.keyPress(KeyEvent.VK_F);
                }
                if(letra.contains("103")){
                    cont1 = 7;
                    System.out.println("g");
                    simulando.keyPress(KeyEvent.VK_G);
                }
                if(letra.contains("104")){
                    cont2 = -6;
                    System.out.println("h");
                    simulando.keyPress(KeyEvent.VK_H);
                }
                if(letra.contains("105")){
                    cont2 = -5;
                    System.out.println("i");
                    simulando.keyPress(KeyEvent.VK_I);
                }
                if(letra.contains("106")){
                    cont2 = -4;
                    System.out.println("j");
                    simulando.keyPress(KeyEvent.VK_J);
                }
                if(letra.contains("107")){
                    cont2 = -3;
                    System.out.println("k");
                    simulando.keyPress(KeyEvent.VK_K);
                }
                if(letra.contains("108")){
                    cont2 = -2;
                    System.out.println("l");
                    simulando.keyPress(KeyEvent.VK_L);
                }
                if(letra.contains("109")){
                    cont2 = -1;
                    System.out.println("m");
                    simulando.keyPress(KeyEvent.VK_M);
                }
                
                /*TECLAS FLEX CORAZÓN*/
                if(letra.contains("110")){
                    cont1 = 1;
                    System.out.println("n");
                    simulando.keyPress(KeyEvent.VK_N);
                }
                if(letra.contains("111")){
                    cont1 = 2;
                    System.out.println("o");
                    simulando.keyPress(KeyEvent.VK_O);
                }
                if(letra.contains("112")){
                    cont1 = 3;
                    System.out.println("p");
                    simulando.keyPress(KeyEvent.VK_P);
                }
                if(letra.contains("113")){
                    cont1 = 4;
                    System.out.println("q");
                    simulando.keyPress(KeyEvent.VK_Q);
                }
                if(letra.contains("114")){
                    cont1 = 5;
                    System.out.println("r");
                    simulando.keyPress(KeyEvent.VK_R);
                }
                if(letra.contains("115")){
                    cont1 = 6;
                    System.out.println("s");
                    simulando.keyPress(KeyEvent.VK_S);
                }
                if(letra.contains("116")){
                    cont1 = 7;
                    System.out.println("t");
                    simulando.keyPress(KeyEvent.VK_T);
                }
                if(letra.contains("117")){
                    cont2 = -6;
                    System.out.println("u");
                    simulando.keyPress(KeyEvent.VK_U);
                }
                if(letra.contains("118")){
                    cont2 = -5;
                    System.out.println("v");
                    simulando.keyPress(KeyEvent.VK_V);
                }
                if(letra.contains("119")){
                    cont2 = -4;
                    System.out.println("w");
                    simulando.keyPress(KeyEvent.VK_W);
                }
                if(letra.contains("120")){
                    cont2 = -3;
                    System.out.println("x");
                    simulando.keyPress(KeyEvent.VK_X);
                }
                if(letra.contains("121")){
                    cont2 = -2;
                    System.out.println("y");
                    simulando.keyPress(KeyEvent.VK_Y);
                }
                if(letra.contains("122")){
                    cont2 = -1;
                    System.out.println("z");
                    simulando.keyPress(KeyEvent.VK_Z);
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            jSlider1.setValue(cont1);
            jSlider2.setValue(cont2);
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
