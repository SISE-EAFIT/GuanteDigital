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

import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;

import static java.awt.image.ImageObserver.ERROR;


public class ensayis extends JFrame implements  Runnable, SerialPortEventListener{

    //Componenetes para la conexión con Arduino
    SerialPort serialPort;
    private InputStream Input = null;
    //Puerto al que se conecta el arduino
    private final String PORT_NAME = "/dev/cu.usbmodem1411";
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    String tecla;
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
        ArduinoConnection();
        initComponents();
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
        
        labelGrupo1 = new JLabel(tecla);
        labelGrupo1.setHorizontalAlignment(JLabel.CENTER);
        labelGrupo1.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelGrupo1.setBounds(20, 100, 25, 25);
        labelGrupo1.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        labelGrupo2 = new JLabel(tecla);
        labelGrupo2.setHorizontalAlignment(JLabel.CENTER);
        labelGrupo2.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelGrupo2.setBounds(140, 100, 25, 25);
        labelGrupo2.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        labelGrupo3 = new JLabel(tecla);
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
        String datos = new String(lectura);
        
        return datos;
    }
    
   /* public String Mapeando(String letra){
        tecla = this.tecla;
        switch(tecla) {
            case "46" :
                tecla = "."; 
                break;
            case "44" :
                tecla = ",";
            case "8" :
                tecla = "space"; 
                break;
            case "32" :
                tecla = "delete";
            case "13" :
                tecla = "enter";
         default :
            System.out.println("Tecla Inválida");
        }
        return tecla;
    }
    */
        
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
                //Simulando teclado
                simulando = new Robot();
                
                //Llamando al método que recibe los valores del arduino
                String letra = RecibirDatos();
                
                /*CARACTERES*/
                if(letra.contains("46")){
                    System.out.println(".");
                    simulando.keyRelease(KeyEvent.VK_PERIOD);
                }
                if(letra.contains("44")){
                    System.out.println(",");
                    simulando.keyRelease(KeyEvent.VK_COMMA);
                }
                if(letra.contains("8")){
                    System.out.println("space");
                    simulando.keyRelease(KeyEvent.VK_SPACE);
                }
                if(letra.contains("32")){
                    System.out.println("delete");
                    simulando.keyRelease(KeyEvent.VK_BACK_SPACE);
                }
                if(letra.contains("13")){
                    System.out.println("enter");
                    simulando.keyRelease(KeyEvent.VK_ENTER);
                }
                
                /*TECLAS FLEX CORAZÓN*/
                if(letra.contains("21")){
                    System.out.println("z");
                    simulando.keyRelease(KeyEvent.VK_Z);
                }
                if(letra.contains("20")){
                    System.out.println("y");
                    simulando.keyRelease(KeyEvent.VK_Y);
                }
                if(letra.contains("19")){
                    System.out.println("x");
                    simulando.keyRelease(KeyEvent.VK_X);
                }
                if(letra.contains("18")){
                    System.out.println("w");
                    simulando.keyRelease(KeyEvent.VK_W);
                }
                if(letra.contains("17")){
                    System.out.println("v");
                    simulando.keyRelease(KeyEvent.VK_V);
                }
                if(letra.contains("16")){
                    System.out.println("u");
                    simulando.keyRelease(KeyEvent.VK_U);
                }
                if(letra.contains("15")){
                    System.out.println("t");
                    simulando.keyRelease(KeyEvent.VK_T);
                }
                if(letra.contains("14")){
                    System.out.println("s");
                    simulando.keyRelease(KeyEvent.VK_S);
                }
                if(letra.contains("13")){
                    System.out.println("r");
                    simulando.keyRelease(KeyEvent.VK_R);
                }
                if(letra.contains("12")){
                    System.out.println("q");
                    simulando.keyRelease(KeyEvent.VK_Q);
                }
                if(letra.contains("11")){
                    System.out.println("p");
                    simulando.keyRelease(KeyEvent.VK_P);
                }
                if(letra.contains("10")){
                    System.out.println("o");
                    simulando.keyRelease(KeyEvent.VK_O);
                }
                if(letra.contains("9")){
                    System.out.println("n");
                    simulando.keyRelease(KeyEvent.VK_N);
                }
                
                /*TECLAS FLEX INDICE*/
                if(letra.contains("-6")){
                    System.out.println("m");
                    simulando.keyRelease(KeyEvent.VK_M);
                }
                if(letra.contains("-5")){
                    System.out.println("l");
                    simulando.keyRelease(KeyEvent.VK_L);
                }
                if(letra.contains("-4")){
                    System.out.println("k");
                    simulando.keyRelease(KeyEvent.VK_K);
                }
                if(letra.contains("-3")){
                    System.out.println("j");
                    simulando.keyRelease(KeyEvent.VK_J);
                }
                if(letra.contains("-2")){
                    System.out.println("i");
                    simulando.keyRelease(KeyEvent.VK_I);
                }
                if(letra.contains("-1")){
                    System.out.println("h");
                    simulando.keyRelease(KeyEvent.VK_H);
                }
                if(letra.contains("1")){
                    System.out.println("g");
                    simulando.keyRelease(KeyEvent.VK_G);
                }
                if(letra.contains("2")){
                    System.out.println("f");
                    simulando.keyRelease(KeyEvent.VK_F);
                }
                if(letra.contains("3")){
                    System.out.println("e");
                    simulando.keyRelease(KeyEvent.VK_E);
                }
                if(letra.contains("4")){
                    System.out.println("d");
                    simulando.keyRelease(KeyEvent.VK_D);
                }
                if(letra.contains("5")){
                    System.out.println("c");
                    simulando.keyRelease(KeyEvent.VK_C);
                }
                if(letra.contains("6")){
                    System.out.println("b");
                    simulando.keyRelease(KeyEvent.VK_B);
                }
                if(letra.contains("7")){
                    System.out.println("a");
                    simulando.keyRelease(KeyEvent.VK_A);
                }
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
