#include <Keyboard.h>

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
    Keyboard.write(46);
  }
  if(coma == 1){
    Serial.println(44);
    Keyboard.write(44);
  }
  if(espacio == 1){
    Serial.println(8);
    Keyboard.write(8);
  }
  if(borrar == 1){
    Serial.println(32);
    Keyboard.write(32);
  }
  if(enter == 1){
    Serial.println(13);
    Keyboard.write(13);
  }


  if(horizontalCont < -6 || horizontalCont > 7){
    horizontalCont = 0;
  }

  Serial.println("grupo1 Activado "+ String(grupo1));
  Serial.println("grupo2 Activado "+ String(grupo2));

  if(grupo2 == 0 && grupo1 == 1 && horizontalDes !=0 || verticalDes!=0){
    if(horizontalDes == 5){
      horizontalCont++;
    }else if(horizontalDes == -4){
      horizontalCont--;
    }
    
    if(horizontalCont == 1){
      letra  = 97; //a
      if(verticalDes == 5 ){
        letra = 65; //A
       }
       if(verticalDes == -4){
        letra = 160; //á
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 2){
      letra  = 98; //b
      if(verticalDes == 5 ){
        letra = 66; //B
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 3 ){
      letra  = 99; //c
      if(verticalDes == 5){
        letra = 67; //C
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 4){
      letra  = 100; //d
      if(verticalDes == 5){
        letra = 68; //D
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 5){
      letra  = 101; //e
      if(verticalDes == 5){
        letra = 69; //E
       }
       if(verticalDes == -4){
        letra = 130; //é
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 6){
      letra  = 102; //f
      if(verticalDes == 5){
        letra = 70; //F
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 7){
      letra  = 103; //g
      if(verticalDes == 5){
        letra = 71; //G 
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -6){
      letra  = 104; //h
      if(verticalDes == 5){
        letra = 72; //H
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -5){
      letra  = 105; //i
      if(verticalDes == 5){
        letra = 73; //I
       }
       if(verticalDes == -4){
        letra = 161; //í
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -4){
      letra  = 106; //j
      if(verticalDes == 5){
        letra = 74; //J
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -3){
      letra  = 107; //k
      if(verticalDes == 5){
        letra = 75; //K
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -2){
      letra  = 108; //l
      if(verticalDes == 5){
        letra = 76; //L
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -1){
      letra  = 109; //m
      if(verticalDes == 5){
        letra = 77; //M
       }
       Keyboard.write(letra);
    }
  }

  if(horizontalCont < -6 || horizontalCont > 7){
    horizontalCont = 0;
  }
  
  if(grupo1 == 0 && grupo2 == 1 && horizontalDes !=0 || verticalDes !=0){
    if(horizontalDes == 5){
      Serial.println("siguiente 2 ");
      horizontalCont++;
    }else if(horizontalDes == -4){
      Serial.println("atras 2 ");
      horizontalCont--;
    }
    if(horizontalCont == 1){
      letra  = 110; //n
      if(verticalDes == 5){
        letra = 78; //N
       }
       if(verticalDes == -4){
        letra = 164; //ñ
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 2){
      letra  = 111; //o
      if(verticalDes == 5){
        letra = 79; //O
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 3){
      letra  = 112; //p
      if(verticalDes == 5){
        letra = 80; //p
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 4){
      letra  = 113; //q
      if(verticalDes == 5){
        letra = 81; //Q
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 5){
      letra  = 114; //r
      if(verticalDes == 5){
        letra = 82; //R
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 6){
      letra  = 115; //s
      if(verticalDes == 5){
        letra = 83; //S
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == 7){
      letra  = 116; //t
      if(verticalDes == 5){
        letra = 84; //T
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -6){
      letra  = 117; //u
      if(verticalDes == 5){
        letra = 85; //U
       }
       if(verticalDes == -4){
        letra = 163; //ú
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -5){
      letra  = 118; //v
      if(verticalDes == 5){
        letra = 86; //V
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -4){
      letra  = 119; //w
      if(verticalDes == 5){
        letra = 87; //W
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -3){
      letra  = 120; //x
      if(verticalDes == 5){
        letra = 88; //X
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -2){
      letra  = 121; //y
      if(verticalDes == 5){
        letra = 89; //Y
       }
       Keyboard.write(letra);
    }
    if(horizontalCont == -1){
      letra  = 122; //z
      if(verticalDes == 5){
        letra = 90; //Z
       }
       Keyboard.write(letra);
    }
  }
   
  delay(400);
}
