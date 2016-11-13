#!/usr/bin/python
import serial
from time import sleep
from Xlib.ext  import xtest
from Xlib import  display, X, XK

from Xlib.ext.xtest import fake_input

print ("Conexion con arduino")
port = "/dev/cu.usbmodem1411"
ser = serial.Serial(port, 9600, timeout=0, bytesize=8, stopbits=1)

class Teclado:
    print ("entre a la clase")

    def __init__(self):
        self.d = display.Display()
        self.scr = self.d.screen()
        self.root = self.d.screen().root

    def escribir(self, letra):
        print ("Escribiendo...")
        keycode = self.d.keysym_to_keycode(letra)
        fake_input(self.d, X.KeyPress, keycode)
        fake_input(self.d, X.KeyRelease, keycode)
        display.flush()
        displayc.d.syc()

while True:

    data1 = ser.read(1)
    data2 = ser.read(1)
    data3 = ser.read(1)

    num1 = XK.string_to_keysym(data3)
    unid = num1-48
    num2 = XK.string_to_keysym(data2)
    dece = num2-48
    num3 = XK.string_to_keysym(data1)
    cent = num3-48

    if cent != 1:
        letra = (cent*0)+(dece*10)+unid
    else:
       letra = (cent*100)+(dece*10)+unid

    if letra > 0:
        print (letra)

    #Declarando la clase
    ejecutar = Teclado()
    ejecutar.escribir(letra)

    sleep(0.1)

ser.close()




