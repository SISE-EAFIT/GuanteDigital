package teclado;

import java.io.InputStream;
import java.io.IOException;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEventListener;

import java.util.Enumeration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Robot;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import static java.awt.image.ImageObserver.ERROR;
import javax.swing.event.ChangeListener;


public class ensayis extends JFrame implements  Runnable, SerialPortEventListener{

    //Componenetes para la conexión con Arduino
    SerialPort serialPort;
    private InputStream Input = null;
    //Puerto al que se conecta el arduino
    private final String PORT_NAME = "/dev/cu.usbmodem1411";
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    Thread Hilo;
    int datos;
    
    Robot simulando;
    
    //Componentes de la interfaz
    JLabel labelIndice, labelCorazon,
           labelGrupo1, labelGrupo2,
           labelCaracter, labelGrupo3,
           labelDesactivar;
    JCheckBox boxDesactivar;
    JButton btnAyuda;
    JPanel panel1 = new JPanel();
    
    public ensayis(){
        initComponents();
        ArduinoConnection();
    }
    
    private void initComponents(){
        this.setTitle("  Teclado Dígital");
        this.setSize(200, 280);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(Color.WHITE);
        
        labelIndice = new JLabel("Índice");
        labelIndice.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelIndice.setBounds(20, 20, 50, 40);
        
        labelCorazon = new JLabel("Corazón");
        labelCorazon.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelCorazon.setBounds(130, 20, 70, 40);
        
        labelGrupo1 = new JLabel(String.valueOf(datos));
        labelGrupo1.setHorizontalAlignment(JLabel.CENTER);
        labelGrupo1.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelGrupo1.setBounds(20, 100, 25, 25);
        labelGrupo1.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        labelGrupo2 = new JLabel(String.valueOf(datos));
        labelGrupo2.setHorizontalAlignment(JLabel.CENTER);
        labelGrupo2.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelGrupo2.setBounds(140, 100, 25, 25);
        labelGrupo2.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        labelGrupo3 = new JLabel(String.valueOf(datos));
        labelGrupo3.setHorizontalAlignment(JLabel.CENTER);
        labelGrupo3.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelGrupo3.setBounds(20, 215, 25, 25);
        labelGrupo3.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        labelCaracter = new JLabel("<html>Caracteres<br>Especiales</html>");
        labelCaracter.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelCaracter.setBounds(60, 210, 200, 40);
        
        boxDesactivar = new JCheckBox("Desactivar");
        boxDesactivar.setFont(new Font("Monospaced", Font.PLAIN, 14));
        boxDesactivar.setBounds(20, 300, 200, 40);
        
        btnAyuda = new JButton("?");
        btnAyuda.setFont(new Font("Monospaced", Font.PLAIN, 14));
        btnAyuda.setBounds(160, 305, 25, 25);
        

        this.add(labelIndice);
        this.add(labelCorazon);
        this.add(labelGrupo1);
        this.add(labelGrupo2);
        this.add(labelGrupo3);
        this.add(labelCaracter);
        this.add(boxDesactivar);
        this.add(btnAyuda);
        
        this.setVisible(true);
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
        
        String numero = new String(lectura);
        int letra = 0;
        try {
            simulando = new Robot();
            //Simulando teclado
            if(numero.equals("44")){
                letra = KeyEvent.VK_COMMA;
            }
            simulando.keyPress(letra);
        }catch (AWTException e) {
            e.printStackTrace();
        }
        
        return numero;
    }
    
        
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ensayis().setVisible(true);
            }
        });
    }
    
    public synchronized void serialEvent(SerialPortEvent oEvent){
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                //Llamando al método que recive los valores del arduino
                String datos = RecibirDatos();
                System.out.println(datos);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
