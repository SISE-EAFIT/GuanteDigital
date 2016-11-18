  String palabra = "sise";
  PImage img;
  int rayas = palabra.length();
  int seMueve = 40;
  int oportunidades = 8;
  int clave;
  int x0 = 30;
  int x1 = 50;
  int x2 = 30;
  int i;
  int j;
  int k;
  
void setup(){
  size(480,240);
  background(255);
  img = loadImage("ahorcado.png");

}

void draw(){
  image(img,300,8);
  
  while (i < rayas){
    strokeWeight(6);
    line(x0,180,x1,180);
    x0 = x0 + seMueve;
    x1 = x1 + seMueve;
    i++;
  }
  

 if(keyPressed){
  if(clave!=rayas){
   if(key == palabra.charAt(clave)){
        fill(0);
        textSize(32);
        text(palabra.charAt(clave),x2,170);
        clave++;
        x2 = x2 + seMueve;
      }
        fill(255);
        stroke(0,0,0,0);
        rect(40,24,60,60);
        fill(0);
        textSize(32);
        text(k,40,56);  
        k++;
    }
  }
  
}
  


  