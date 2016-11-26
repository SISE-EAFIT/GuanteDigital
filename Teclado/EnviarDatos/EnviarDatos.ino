//Flex
const int indice  = A0;
const int corazon = A1;

//Botones
const int seleccionar = 12;
const int caracterPunto   = 11;
const int caracterEspacio = 6;
const int caracterDelete  = 8;
const int caracterEnter   = 7;

//Joystick
const int joyPin1  = A3; //arriba abajo
const int joyPin2  = A4; //izquierda derecha
 
int horizontalCont = 0;
int verticalCont = 0;

void setup() {
  Serial.begin(9600);
  
  pinMode(indice, INPUT);
  pinMode(corazon, INPUT);

  pinMode(seleccionar, INPUT);
  pinMode(caracterPunto, INPUT);
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
  int select = digitalRead(seleccionar);
  int punto = digitalRead(caracterPunto);
  int espacio = digitalRead(caracterEspacio);
  int borrar = digitalRead(caracterDelete);
  int enter = digitalRead(caracterEnter);
  
  //Valores mapeados del JoyStick
  int valorJoy1 = analogRead(joyPin1);
  int valorJoy2 = analogRead(joyPin2);
  
  int horizontalDes = treatValue(valorJoy1); //cambio de letra
  int verticalDes   = treatValue(valorJoy2); //cambio mayuscula o tilde


  //Logica del guante
  if(select == 1){
    Serial.println(1);
  }
  if(punto == 1){
    Serial.println(46);
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

  if(verticalCont < -10 || verticalCont > 10){
    verticalCont = 0;
  }
  if(grupo2 == 0 && grupo1 == 0 && horizontalDes !=0 || verticalDes!=0){
    if(verticalDes == 5){
      verticalCont++;
    }else if(verticalDes == -4){
      verticalCont--;
    }
    //NUMEROS
    if(verticalCont == -1){
      Serial.println(48); //0
    }
    if(verticalCont == -2){
      Serial.println(49); //1
    }
    if(verticalCont == -3){
      Serial.println(50); //2
    }
    if(verticalCont == -4){
      Serial.println(51); //3
    }
    if(verticalCont == -5){
      Serial.println(52); //4
    }
    if(verticalCont == -6){
      Serial.println(53); //5
    }
    if(verticalCont == -7){
      Serial.println(54); //6
    }
    if(verticalCont == -8){
      Serial.println(55); //7
    }
    if(verticalCont == -9){
      Serial.println(56); //8
    }
    if(verticalCont == -10){
      Serial.println(57); //9 
    }
    //CARACTERES
    if(verticalCont == 1){
      Serial.println(44); //,
    }
    if(verticalCont == 2){
      Serial.println(40); //(
    }
    if(verticalCont == 3){
      Serial.println(41); //)
    }
    if(verticalCont == 4){
      Serial.println(58); //:
    }
    if(verticalCont == 5){
      Serial.println(47); //"/"
    }
    if(verticalCont == 6){
      Serial.println(63); //?
    }
    if(verticalCont == 7){
      Serial.println(43); //+
    }
    if(verticalCont == 8){
      Serial.println(45); //-
    }
    if(verticalCont == 9){
      Serial.println(64); //@
    }
    if(verticalCont == 10){
      Serial.println(61); //= 
    }    
  }

  //de a-A a m-M
  if(horizontalCont < -6 || horizontalCont > 7){
    horizontalCont = 0;
  }
  if(grupo2 == 0 && grupo1 == 1 && horizontalDes !=0 || verticalDes!=0){
    if(horizontalDes == 5){
      horizontalCont++;
    }else if(horizontalDes == -4){
      horizontalCont--;
    }
    if(horizontalCont == 1){
      Serial.println(97); //a
      if(verticalDes == 5 ){
        Serial.println(65);//A
       }
    }
    if(horizontalCont == 2){
      Serial.println(98); //b
      if(verticalDes == 5 ){
        Serial.println(66); //B
       }
    }
    if(horizontalCont == 3 ){
      Serial.println(99); //c
      if(verticalDes == 5){
        Serial.println(67); //C
       }
    }
    if(horizontalCont == 4){
      Serial.println(100); //d
      if(verticalDes == 5){
        Serial.println(68); //D
       }
    }
    if(horizontalCont == 5){
      Serial.println(101); //e
      if(verticalDes == 5){
        Serial.println(69); //E
       }
    }
    if(horizontalCont == 6){
      Serial.println(102); //f
      if(verticalDes == 5){
        Serial.println(70); //F
       }
    }
    if(horizontalCont == 7){
      Serial.println(103); //g
      if(verticalDes == 5){
        Serial.println(71); //G
       }
    }
    if(horizontalCont == -6){
      Serial.println(104); //h
      if(verticalDes == 5){
        Serial.println(72); //H
       }
    }
    if(horizontalCont == -5){
      Serial.println(105); //i
      if(verticalDes == 5){
        Serial.println(73); //I
       }
    }
    if(horizontalCont == -4){
      Serial.println(106); //j
      if(verticalDes == 5){
        Serial.println(74); //J
       }
    }
    if(horizontalCont == -3){
      Serial.println(107); //k
      if(verticalDes == 5){
        Serial.println(75); //K
       }
    }
    if(horizontalCont == -2){
      Serial.println(108); //l
      if(verticalDes == 5){
        Serial.println(76); //L
       }
    }
    if(horizontalCont == -1){
      Serial.println(109); //M
      if(verticalDes == 5){
        Serial.println(77); //m
       }
    }
  }

  //de n-N a z-Z
  if(horizontalCont < -6 || horizontalCont > 7){
    horizontalCont = 0;
  }
  if(grupo1 == 0 && grupo2 == 1 && horizontalDes !=0 || verticalDes !=0){
    if(horizontalDes == 5){
      horizontalCont++;
    }else if(horizontalDes == -4){
      horizontalCont--;
    }
    if(horizontalCont == 1){
      Serial.println(110); //n
      if(verticalDes == 5){
        Serial.println(78); //N
       }
    }
    if(horizontalCont == 2){
      Serial.println(111); //o
      if(verticalDes == 5){
        Serial.println(79); //O
       }
    }
    if(horizontalCont == 3){
      Serial.println(112); //p
      if(verticalDes == 5){
        Serial.println(80); //P
       }
    }
    if(horizontalCont == 4){
      Serial.println(113); //q
      if(verticalDes == 5){
        Serial.println(81); //Q
       }
    }
    if(horizontalCont == 5){
      Serial.println(114); //r
      if(verticalDes == 5){
        Serial.println(82); //R
       }
    }
    if(horizontalCont == 6){
      Serial.println(115); //s
      if(verticalDes == 5){
        Serial.println(83); //S
       }
    }
    if(horizontalCont == 7){
      Serial.println(116); //t
      if(verticalDes == 5){
        Serial.println(84); //T
       }
    }
    if(horizontalCont == -6){
      Serial.println(117); //u
      if(verticalDes == 5){
        Serial.println(85); //u
       }
    }
    if(horizontalCont == -5){
      Serial.println(118); //v
      if(verticalDes == 5){
        Serial.println(86); //V
       }
    }
    if(horizontalCont == -4){
      Serial.println(119); //w
      if(verticalDes == 5){
        Serial.println(87); //W
       }
    }
    if(horizontalCont == -3){
      Serial.println(120); //x
      if(verticalDes == 5){
        Serial.println(88); //X
       }
    }
    if(horizontalCont == -2){
      Serial.println(121); //y
      if(verticalDes == 5){
        Serial.println(89); //Y
       }
    }
    if(horizontalCont == -1){
      Serial.println(122); //z
      if(verticalDes == 5){
        Serial.println(90); //Z
      }
    }
  }
   
  delay(400);
}
