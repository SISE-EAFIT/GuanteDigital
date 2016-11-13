import serial
from time import sleep
from Xlib.display import Display
from Xlib.ext  import xtest
from Xlib import X, XK

d = Display()
port = "/dev/cu.usbmodem1411"
ser = serial.Serial(port, 9600, timeout=0, bytesize=8, stopbits=1)

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
        print letra

    keycode = d.keysym_to_keycode(letra)

    xtest.fake_input(d, X.KeyPress, keycode)
    xtest.fake_input(d, X.KeyRelease, keycode)
    d.flush()

    sleep(0.1)

ser.close()
