#!/usr/bin/env python

import RPi.GPIO as GPIO
import time
import socket


derB = 14 #atras derecha
derF = 15 #adelante derecha
izqB = 23 #atras izquiera
izqF = 24 #adelante izquierda
pwm1 = 25 #derecha
pwm2 = 8  #izquierda

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(izqF, GPIO.OUT)
GPIO.setup(izqB, GPIO.OUT)
GPIO.setup(derF, GPIO.OUT)
GPIO.setup(derB, GPIO.OUT)
GPIO.setup(pwm1, GPIO.OUT)
GPIO.setup(pwm2, GPIO.OUT)
 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
 
s.bind(("", 9000))
 
s.listen(1)
 
sc, addr = s.accept()
 
while True:

    recibido = sc.recv(1)
 
    print str(addr[0]) + " dice: ", recibido

    if recibido == "1":#adelante
        GPIO.output(izqF, True)
	GPIO.output(izqB, False)
        GPIO.output(derF, True)
	GPIO.output(derB, False)
	GPIO.output(pwm1, True)
	GPIO.output(pwm2, True)
	
    if recibido == '2': #atras
	GPIO.output(izqF, False)
	GPIO.output(izqB, True)
        GPIO.output(derF, False)
	GPIO.output(derB, True)
	GPIO.output(pwm1, True)
	GPIO.output(pwm2, True)
    if recibido == "3": #izquierda
	GPIO.output(izqF, True)
	GPIO.output(izqB, False)
        GPIO.output(derF, False)
	GPIO.output(derB, True)
	GPIO.output(pwm1, True)
	GPIO.output(pwm2, True)
    if recibido == "4": #derecha
	GPIO.output(izqF, False)
	GPIO.output(izqB, True)
        GPIO.output(derF, True)
	GPIO.output(derB, False)
	GPIO.output(pwm1, True)
	GPIO.output(pwm2, True)
    if recibido == "0": #apagar
        GPIO.output(izqF, False)
        GPIO.output(izqB, False)
        GPIO.output(derF, False)
        GPIO.output(derB, False)
        GPIO.output(pwm1, False)
        GPIO.output(pwm2, False)
 
    #sc.send(recibido)
print "Adios."
 
sc.close()
s.close()
