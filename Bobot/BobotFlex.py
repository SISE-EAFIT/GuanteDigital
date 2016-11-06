#!/usr/bin/env python

import RPi.GPIO as GPIO
import time
import socket

pin1 = 22 #
pin2 = 23 #
pin3 = 24 #
pin4 = 25 #
pwm1 = 17 #velocidad 
pwm2 = 18 #velocidad

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(pin1, GPIO.OUT)
GPIO.setup(pin2, GPIO.OUT)
GPIO.setup(pin3, GPIO.OUT)
GPIO.setup(pin4, GPIO.OUT)
GPIO.setup(pwm1, GPIO.OUT)
GPIO.setup(pwm2, GPIO.OUT)

a = GPIO.PWM(pwm1, 250)
b = GPIO.PWM(pwm2, 250)
print "gpio ok"

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(("", 9000))
s.listen(1)
sc, addr = s.accept()
	 
while True:
    recibido = sc.recv(3)
    #print str(addr[0]) + "dice: ", recibido
    
    

    if recibido >= "100": #adelante
    	if recibido < "200": #adelante
		GPIO.output(pin1, True)
		GPIO.output(pin2, False)
		GPIO.output(pin3, True)
		GPIO.output(pin4, False)
    		v = (int(recibido)-100)
		a.start(90)
		b.start(90)
    		print v

    if recibido >= "200": #atras
    	if recibido < "300": #atras
		GPIO.output(pin1, False)
		GPIO.output(pin2, True)
		GPIO.output(pin3, False)
		GPIO.output(pin4, True)
	    	v = (int(recibido)-200)
		a.start(90)
		b.start(90)
		print v 

    if recibido == "300": #izquierda
	GPIO.output(pin1, True)
	GPIO.output(pin2, False)
       	GPIO.output(pin3, False)
	GPIO.output(pin4, True)
	a.start(100)
	b.start(100)
	print "left"

    if recibido == "400": #derecha
	GPIO.output(pin1, False)
	GPIO.output(pin2, True)
        GPIO.output(pin3, True)
	GPIO.output(pin4, False)
	a.start(100)
	b.start(100)
	print "right"
    
    if recibido == "500": #apagar
       	GPIO.output(pin1, False)
       	GPIO.output(pin2, False)
        GPIO.output(pin3, False)
        GPIO.output(pin4, False)
	a.stop()
	b.stop()
	print "stop"

sc.close()
s.close()
