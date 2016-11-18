#include <Keyboard.h>

int letra1 = 2;
int letra2 = 3;
int letra3 = 4;
int letra4 = 5;
int letra5 = 6;
int letra6 = 7;
int letra7 = 8;
int letra8 = 9;

void setup() {
  
    Serial.begin(9600);
    Keyboard.begin();
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
    int t = digitalRead(letra7);
    int e = digitalRead(letra8);
  
    if(m==1){
      Keyboard.write(109);
    }
    if(a == 1){
      Keyboard.write(97);
    }
    if(r == 1){
      Keyboard.write(114);
    }
    if(i == 1){
      Keyboard.write(105);
    }
    if(n == 1){
      Keyboard.write(110);
    }
    if(s == 1){
      Keyboard.write(115);
    }
    if(t == 1){
      Keyboard.write(116);
    }
    if(e == 1){
      Keyboard.write(101);
    }
    
    delay(100);

}
