package teclado;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.io.InputStream;
import java.io.IOException;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEventListener;

import java.util.Enumeration;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import static java.awt.image.ImageObserver.ERROR;

public class Teclado implements Runnable, SerialPortEventListener{
    //Interfaz
    private JFrame mainFrame;
    private JLabel lblIndice;
    private JLabel lblCorazon;
    private JLabel letraIndice;
    private JLabel letraCorazon;
    private JLabel lblCaracter;
    private JLabel caracter;
    private JSlider sliderI;
    private JSlider sliderC;
    private JPanel controlIndice;
    private JPanel controlCorazon;
    private JCheckBox desactivar;
    
    //Componenetes para la conexión con Arduino
    SerialPort serialPort;
    private InputStream Input = null;
    private final String PORT_NAME = "/dev/cu.usbmodem1411";//Puerto al que se conecta el arduino
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    
    private String letra;
    private String teclaI;
    private String teclaC;
    private String valorCaracter;
    private Robot simulando;
    private int contadorI = 0;
    private int contadorC = 0;

    public Teclado(){
        ArduinoConnection();
        Interfaz();
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
        //available estima la cantidad de bytes que esta mandando el arduino
        int available = Input.available();
        //en necesario una variable byte para decirle al metodo read 
        //cuanto va a leer
        byte lectura[] = new byte[available];
        //leer los datos enviados por el arduino
        Input.read(lectura, 0, available);
        String datos = new String(lectura);
        
        return datos;
    }

    private void Interfaz(){
        mainFrame = new JFrame("Teclado");
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setSize(170,440);
        mainFrame.getContentPane().setBackground(Color.WHITE);
      
        GridBagConstraints gbc = new GridBagConstraints();
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });
        
        lblIndice = new JLabel("Indice"); 
        lblIndice.setFont(new Font("Monospaced", Font.PLAIN, 14));
      
        lblCorazon = new JLabel("Corazón");
        lblCorazon.setFont(new Font("Monospaced", Font.PLAIN, 14));
  
        controlIndice = new JPanel();
        controlIndice.setLayout(new FlowLayout());
        controlIndice.setBackground(Color.WHITE);
      
        controlCorazon = new JPanel();
        controlCorazon.setLayout(new FlowLayout());
        controlCorazon.setBackground(Color.WHITE);
      
        letraIndice = new JLabel(""); 
        letraIndice.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        letraCorazon = new JLabel("");
        letraCorazon.setFont(new Font("Monospaced", Font.PLAIN, 14));

        sliderI= new JSlider(JSlider.VERTICAL);
        sliderI.setBackground(Color.WHITE);
        sliderI.setMaximum(7);
        sliderI.setMinimum(-6);
        
        sliderC= new JSlider(JSlider.VERTICAL);
        sliderC.setBackground(Color.WHITE);
        sliderC.setMaximum(7);
        sliderC.setMinimum(-6);
      
        lblCaracter = new JLabel("<html>Caracteres<br>Especiales</html>:"); 
        lblCaracter.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        caracter = new JLabel(""); 
        caracter.setFont(new Font("Monospaced", Font.PLAIN, 14));
      
        desactivar = new JCheckBox("Desactivar");
        desactivar.setFont(new Font("Monospaced", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        mainFrame.add(lblIndice, gbc);
      
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.5; 
        mainFrame.add(lblCorazon, gbc);
      
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        mainFrame.add(controlIndice,gbc);
      
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        mainFrame.add(controlCorazon, gbc);
      
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        mainFrame.add(letraIndice, gbc);
      
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        mainFrame.add(letraCorazon, gbc);
      
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        mainFrame.add(lblCaracter, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        mainFrame.add(caracter, gbc);
        mainFrame.setVisible(true);  
    }

    private void moveJslider(int valorI, int valorC){
        System.out.println("llegada: "+valorI +" "+valorC);
        letraIndice.setText("tecla: "+teclaI);
        letraCorazon.setText("tecla: "+teclaC);
        caracter.setText(valorCaracter);
        sliderI.setValue(valorI);
        sliderC.setValue(valorC);
        controlIndice.add(sliderI); 
        controlCorazon.add(sliderC); 
        mainFrame.setVisible(true);     
    } 
    
    public synchronized void serialEvent(SerialPortEvent oEvent){
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                //Simulando teclado
                simulando = new Robot();
                
                //Llamando al método que recibe los valores del arduino
                letra = RecibirDatos();
                /*CARACTERES*/
                if(letra.contains("8")){
                    valorCaracter = "{ }";
                    simulando.keyPress(KeyEvent.VK_SPACE);
                }
                if(letra.contains("13")){
                    valorCaracter = "<-'";
                    simulando.keyPress(KeyEvent.VK_ENTER);
                }
                if(letra.contains("32")){
                    valorCaracter = "<-";
                    simulando.keyPress(KeyEvent.VK_BACK_SPACE);
                }
                if(letra.contains("40")){
                    valorCaracter = "(";
                    simulando.keyPress(KeyEvent.VK_LEFT_PARENTHESIS);
                }
                if(letra.contains("41")){
                    valorCaracter = ")";
                    simulando.keyPress(KeyEvent.VK_RIGHT_PARENTHESIS);
                }
                if(letra.contains("43")){
                    valorCaracter = "+";
                    simulando.keyPress(KeyEvent.VK_PLUS);
                }                
                if(letra.contains("44")){
                    valorCaracter = ",";
                    simulando.keyPress(KeyEvent.VK_COMMA);
                }
                if(letra.contains("45")){
                    valorCaracter = "-";
                    simulando.keyPress(KeyEvent.VK_MINUS);
                }
                if(letra.contains("46")){
                    valorCaracter = ".";
                    simulando.keyPress(KeyEvent.VK_PERIOD);
                }
                if(letra.contains("47")){
                    valorCaracter = "/";
                    simulando.keyPress(KeyEvent.VK_SLASH);
                }
                if(letra.contains("58")){
                    valorCaracter = ":";
                    simulando.keyPress(KeyEvent.VK_COMMA);
                }
                if(letra.contains("61")){
                    valorCaracter = "=";
                    simulando.keyPress(KeyEvent.VK_EQUALS);
                }
                if(letra.contains("63")){
                    valorCaracter = "?";
                    simulando.keyPress(KeyEvent.VK_QUOTE);
                }
                if(letra.contains("64")){
                    valorCaracter = "@";
                    simulando.keyPress(KeyEvent.VK_AT);   
                }
                /*NUMEROS*/
                if(letra.contains("48")){
                    valorCaracter = "0";
                    simulando.keyPress(KeyEvent.VK_0);
                }
                if(letra.contains("49")){
                    valorCaracter = "1";
                    simulando.keyPress(KeyEvent.VK_1);
                }
                if(letra.contains("50")){
                    valorCaracter = "2";
                    simulando.keyPress(KeyEvent.VK_2);
                }
                if(letra.contains("51")){
                    valorCaracter = "3";
                    simulando.keyPress(KeyEvent.VK_3);
                }
                if(letra.contains("52")){
                    valorCaracter = "4";
                    simulando.keyPress(KeyEvent.VK_4);
                }
                if(letra.contains("53")){
                    valorCaracter = "5";
                    simulando.keyPress(KeyEvent.VK_5);
                }                
                if(letra.contains("54")){
                    valorCaracter = "6";
                    simulando.keyPress(KeyEvent.VK_6);
                }
                if(letra.contains("55")){
                    valorCaracter = "7";
                    simulando.keyPress(KeyEvent.VK_7);
                }
                if(letra.contains("56")){
                    valorCaracter = "8";
                    simulando.keyPress(KeyEvent.VK_8);
                }
                if(letra.contains("57")){
                    valorCaracter = "9";
                    simulando.keyPress(KeyEvent.VK_9);
                }
                
                
                /*TECLAS FLEX INDICE*/
                if(letra.contains("97")){
                    contadorI = 1;
                    teclaI = "a";
                    simulando.keyPress(KeyEvent.VK_A);
                }
                if(letra.contains("98")){
                    contadorI = 2;
                    teclaI = "b";
                    simulando.keyPress(KeyEvent.VK_L);
                }
                if(letra.contains("99")){
                    contadorI = 3;
                    teclaI = "c";
                    simulando.keyPress(KeyEvent.VK_C);
                }
                if(letra.contains("100")){
                    contadorI = 4;
                    teclaI = "d";
                    simulando.keyPress(KeyEvent.VK_D);
                }
                if(letra.contains("101")){
                    contadorI = 5;
                    teclaI = "e";
                    simulando.keyPress(KeyEvent.VK_E);
                }
                if(letra.contains("102")){
                    contadorI = 6;
                    teclaI = "f";
                    simulando.keyPress(KeyEvent.VK_F);
                }
                if(letra.contains("103")){
                    contadorI = 7;
                    teclaI = "g";
                    simulando.keyPress(KeyEvent.VK_G);
                }
                if(letra.contains("104")){
                    contadorC = -6;
                    teclaI = "h";
                    simulando.keyPress(KeyEvent.VK_H);
                }
                if(letra.contains("105")){
                    contadorC = -5;
                    teclaI = "i";
                    simulando.keyPress(KeyEvent.VK_I);
                }
                if(letra.contains("106")){
                    contadorC = -4;
                    teclaI = "j";
                    simulando.keyPress(KeyEvent.VK_J);
                }
                if(letra.contains("107")){
                    contadorC = -3;
                    teclaI = "k";
                    simulando.keyPress(KeyEvent.VK_K);
                }
                if(letra.contains("108")){
                    contadorC = -2;
                    teclaI = "l";
                    simulando.keyPress(KeyEvent.VK_L);
                }
                if(letra.contains("109")){
                    contadorC = -1;
                    teclaI = "m";
                    simulando.keyPress(KeyEvent.VK_M);
                }
                
                /*TECLAS FLEX CORAZÓN*/
                if(letra.contains("110")){
                    contadorC = 1;
                    teclaC = "n";
                    simulando.keyPress(KeyEvent.VK_N);
                }
                if(letra.contains("111")){
                    contadorC = 2;
                    teclaC = "o";
                    simulando.keyPress(KeyEvent.VK_O);
                }
                if(letra.contains("112")){
                    contadorC = 3;
                    teclaC = "p";
                    simulando.keyPress(KeyEvent.VK_P);
                }
                if(letra.contains("113")){
                    contadorC = 4;
                    teclaC = "q";
                    simulando.keyPress(KeyEvent.VK_Q);
                }
                if(letra.contains("114")){
                    contadorC = 5;
                    teclaC = "r";
                    simulando.keyPress(KeyEvent.VK_R);
                }
                if(letra.contains("115")){
                    contadorC = 6;
                    teclaC = "s";
                    simulando.keyPress(KeyEvent.VK_S);
                }
                if(letra.contains("116")){
                    contadorC = 7;
                    teclaC = "t";
                    simulando.keyPress(KeyEvent.VK_T);
                }
                if(letra.contains("117")){
                    contadorC = -6;
                    teclaC = "u";
                    simulando.keyPress(KeyEvent.VK_U);
                }
                if(letra.contains("118")){
                    contadorC = -5;
                    teclaC = "v";
                    simulando.keyPress(KeyEvent.VK_V);
                }
                if(letra.contains("119")){
                    contadorC = -4;
                    teclaC = "w";
                    simulando.keyPress(KeyEvent.VK_W);
                }
                if(letra.contains("120")){
                    contadorC = -3;
                    teclaC = "x";
                    simulando.keyPress(KeyEvent.VK_X);
                }
                if(letra.contains("121")){
                    contadorC = -2;
                    teclaC = "y";
                    simulando.keyPress(KeyEvent.VK_Y);
                }
                if(letra.contains("122")){
                    contadorC = -1;
                    teclaC = "z";
                    simulando.keyPress(KeyEvent.VK_Z);
                }
                moveJslider(contadorI, contadorC);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
    
    public static void main(String[] args){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Teclado  teclado = new Teclado();
            }
        });
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}