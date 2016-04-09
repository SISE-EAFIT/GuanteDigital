int letra1 = 22;
int letra2 = 23;
int letra3 = 24;
int letra4 = 25;
int letra5 = 26;
int letra6 = 27;
int letra7 = 28;
int letra8 = 29;

void setup() {
  
    Serial.begin(9600);

    pinMode(letra1,INPUT);
    pinMode(letra2,INPUT);
    pinMode(letra3,INPUT);
    pinMode(letra4,INPUT);
    pinMode(letra5,INPUT);
    pinMode(letra6,INPUT);
    pinMode(letra7,INPUT);
    pinMode(letra8,INPUT);
 
}

void loop() {
  
    int m = digitalRead(letra1);
    int a = digitalRead(letra2);
    int r = digitalRead(letra3);
    int i = digitalRead(letra4);
    int n = digitalRead(letra5);
    int s = digitalRead(letra6);
    int e = digitalRead(letra7);
    int space = digitalRead(letra8);
  
    if(m==1){
      Serial.println(109);
    }
    else if(a == 1){
      Serial.println(397);
    }
    else if(r == 1){
      Serial.println(114);
    }
    else if(i == 1){
      Serial.println(105);
    }
    else if(n == 1){
      Serial.println(110);
    }
    else if(s == 1){
      Serial.println(115);
    }
    else if(e == 1){
      Serial.println(101);
    }
    else if(space == 1){
      Serial.println(332);
    }
    
    delay(100);

}
