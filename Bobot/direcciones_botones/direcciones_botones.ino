int pare = 13;
int derecha = 12;
int izquierda = 11;
int atras = 10;
int adelante = 9;

void setup() {
  
    Serial.begin(9600);

    pinMode(atras,INPUT);
    pinMode(adelante,INPUT);
    pinMode(izquierda,INPUT);
    pinMode(derecha,INPUT);
    pinMode(pare,INPUT);
 
}

void loop() {
  
    int b = digitalRead(atras);
    int f = digitalRead(adelante);
    int i = digitalRead(izquierda);
    int d = digitalRead(derecha);
    int p = digitalRead(pare);

  
    if(b == 1){
      Serial.println(1);
    }
    else if(f == 1){
      Serial.println(2);
    }    
    else if(i == 1){
      Serial.println(3);
    }
    else if(d == 1){
      Serial.println(4);
    }    
    else{
      Serial.println(0);
    }
    
    
    delay(300);

}
