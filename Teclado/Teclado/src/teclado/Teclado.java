package teclado;

import java.io.InputStream;
import java.io.IOException;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEventListener;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.ERROR;

import java.util.Enumeration;


public class Teclado  implements  SerialPortEventListener{

    //Componenetes para la conexión con Arduino
    SerialPort serialPort;
    private InputStream Input = null;
    //Puerto al que se conecta el arduino
    private final String PORT_NAME = "/dev/cu.usbmodem1411";
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    
    //Clase que simula eventos
    Robot simulando;
    Interfaz desactivar;
    
    private void ArduinoConnection(){
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

    public String RecibirDatos() throws IOException, AWTException{
        //avalilble estima la cantidad de bytes que esta mandando el arduino
        int available = Input.available();
        
        //en necesario una variable byte para decirle al metodo "read" 
        //cuantos bytes va a leer
        byte lectura[] = new byte[available];
        
        //leer los datos enviados por el arduino
        Input.read(lectura, 0, available);
        
        String numero = new String(lectura);
        int letra = 0;
        try {
            simulando = new Robot();
            if(desactivar.desactivar){
                simulando.keyRelease(KeyEvent.VK_5);
            }
        }catch (AWTException e) {
            e.printStackTrace();
        }
        
        return numero;
    }
    
    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent){
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                //Llamando al método que recive los valores del arduino
                String datos = RecibirDatos();
                System.out.println(datos);
            } catch (AWTException | IOException e) {
                System.err.println(e.toString());
            }
        }
    }
    
    public static void main(String args[]) {
        Teclado a = new Teclado();
        a.ArduinoConnection();
    }

}
