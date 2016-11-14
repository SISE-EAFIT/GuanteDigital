//Joystick
const int joyPin1  = A3; //arriba abajo
const int joyPin2  = A4; //izquierda derecha

int horizontalCont = 0;
int verticalCont = 0;

void setup(){
  Serial.begin(9600);
  pinMode(joyPin1, INPUT);
  pinMode(joyPin2, INPUT);
}

int treatValue(int data) {
  return (data/100)-4;
 }

void loop(){

  //Valores mapeados del JoyStick
  int valorJoy1 = analogRead(joyPin1);
  int valorJoy2 = analogRead(joyPin2);
  
  int horizontalDes = treatValue(valorJoy1); //cambio de letra
  int verticalDes   = treatValue(valorJoy2); //cambio mayuscula o tilde

  if(horizontalCont < -6 || horizontalCont > 7){
    horizontalCont = 0;
  }
  
  if(horizontalDes == 5){
      horizontalCont++;
    }else if(horizontalDes == -4){
      horizontalCont--;
    }
    
  Serial.println(horizontalCont);
  delay(400);
}
