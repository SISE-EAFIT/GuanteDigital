#!/usr/bin/env python
import serial
import socket
from time import sleep
from Xlib.display import Display
from Xlib.ext  import xtest
from Xlib import X, XK

d = Display()

#port = "/dev/ttyACM0"
port = "/dev/cu.usbmodem1411"

ser = serial.Serial(port, 9600, timeout=0, bytesize=8, stopbits=1)

s = socket.socket()

s.connect(("192.168.0.12", 9000))

while True:

    num = ser.read(3)
    sleep(0.1)

    s.send(num)
    print num

s.close
ser.close()
