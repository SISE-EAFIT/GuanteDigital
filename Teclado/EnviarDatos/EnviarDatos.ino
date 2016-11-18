//Flex
const int indice  = A0;
const int corazon = A1;

//Botones
const int caracterPunto  = 12;
const int caracterComa   = 11;
const int caracterEspacio= 6;
const int caracterDelete = 8;
const int caracterEnter  = 7;

//Joystick
const int joyPin1  = A3; //arriba abajo
const int joyPin2  = A4; //izquierda derecha
 
int horizontalCont = 0;
int verticalCont = 0;

int letra = 0;

void setup() {
  Serial.begin(9600);
  
  pinMode(indice, INPUT);
  pinMode(corazon, INPUT);

  pinMode(caracterPunto, INPUT);
  pinMode(caracterComa, INPUT);
  pinMode(caracterEspacio, INPUT);
  pinMode(caracterDelete, INPUT);
  pinMode(caracterEnter, INPUT);
  
  pinMode(joyPin1, INPUT);
  pinMode(joyPin2, INPUT);
}


int treatValue(int data) {
  return (data/100)-4;
 }
 
void loop() {
  
  //Valores mapeados del flex
  int indiceRead = analogRead(indice); 
  int corazonRead = analogRead(corazon);

  int grupo1 = map(indiceRead, 590, 900, 0, 150);
  int grupo2 = map(corazonRead, 590, 900, 0, 150);

  grupo1 = constrain(grupo1, 0, 1);
  grupo2 = constrain(grupo2, 0, 1);


  //Valores leidos de los botones
  int punto = digitalRead(caracterPunto);
  int coma  =  digitalRead(caracterComa);
  int espacio = digitalRead(caracterEspacio);
  int borrar = digitalRead(caracterDelete);
  int enter = digitalRead(caracterEnter);
  
  
  //Valores mapeados del JoyStick
  int valorJoy1 = analogRead(joyPin1);
  int valorJoy2 = analogRead(joyPin2);
  
  int horizontalDes = treatValue(valorJoy1); //cambio de letra
  int verticalDes   = treatValue(valorJoy2); //cambio mayuscula o tilde


  //Logica del guante
  if(punto == 1){
    Serial.println(46);
  }
  if(coma == 1){
    Serial.println(44);
  }
  if(espacio == 1){
    Serial.println(8);
  }
  if(borrar == 1){
    Serial.println(32);
  }
  if(enter == 1){
    Serial.println(13);
  }

  delay(400);
}
