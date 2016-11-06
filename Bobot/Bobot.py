import RPi.GPIO as GPIO
import time

izqF = 22
izqB = 23
derF = 24
derB = 25
pwm1 = 17
pwm2 = 18

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(izqF, GPIO.OUT)
GPIO.setup(izqB, GPIO.OUT)
GPIO.setup(derF, GPIO.OUT)
GPIO.setup(derB, GPIO.OUT)
GPIO.setup(pwm1, GPIO.OUT)
GPIO.setup(pwm2, GPIO.OUT)

a = GPIO.PWM(pwm1, 250)
b = GPIO.PWM(pwm2, 250)

print "gpio ok"

GPIO.output(izqF, True)
GPIO.output(izqB, False)
GPIO.output(derF, True)
GPIO.output(derB, False)
#GPIO.output(pwm1, True)
#GPIO.output(pwm2, True)
a.start(95)
b.start(95)
print "go"

time.sleep(3)
GPIO.output(izqF, False)
GPIO.output(izqB, False)
GPIO.output(derF, False)
GPIO.output(derB, False)
#GPIO.output(pwm1, False)
#GPIO.output(pwm2, False)
a.stop()
b.stop()
print "stop"

GPIO.cleanup()
