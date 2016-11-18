#include <iostream>
#include "Include.h"

int main(int argc, char *argv[])
{
    int a=0, b=0,c=0;
    cout<<"Ingrese un numero: "; cin>>a;
    cout<<"Ingrese otro numero: "; cin>>b;
    while(a<=b)
    {
        if(a%2!=0)
        c=c+1; a=a+1;
    }
    cout<<c<<endl;
    cin.ignore(); 
    return 0;
}
