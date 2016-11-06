//Flex
const int indice  = A0;
const int corazon = A1;

//Joystick
const int joyPin1  = A4; //valores en el eje y

void setup() {
  Serial.begin(9600);
  
  pinMode(indice, INPUT);
  pinMode(corazon, INPUT);
  pinMode(joyPin1, INPUT);
  //pinMode(joyPin2, INPUT);
}


int treatValue(int data) {
  return (data/100)-4;
 }
 
void loop() {
  //Valores mapeados del flex
  int indiceRead = analogRead(indice); 
  int corazonRead = analogRead(corazon);
  
  int adelante = map(indiceRead, 590, 900, 0, 150);
  int atras = map(corazonRead, 590, 900, 0, 150);
  adelante = constrain(adelante, 0, 90);
  atras = constrain(atras, 0, 90);
  
  //Valores mapeados del JoyStick
  int valorJoy1 = analogRead(joyPin1);   
  int direccion = treatValue(valorJoy1);
  
  if(direccion == -4){
    Serial.println(300);
  }else if(direccion > 0){
    Serial.println(400);
  }else if(adelante > 10 && atras == 0){
     Serial.println(adelante+160);
  }else if(atras > 0 && adelante == 0){
    Serial.println(atras+260);
  }else{
    Serial.println(500);
  }
  
  delay(300);
}
