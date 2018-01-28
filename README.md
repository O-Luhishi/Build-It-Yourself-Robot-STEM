# Build It Yourself STEM Robot

Written by Oluhishi [http://Zonosnetworks.com]

## Overview

This is the code for a "Science Technology Engineering Mathemetics (STEM) Build-It-Yourself Robot", which tries to encourage kids of all ages to get into STEM. This page has the source code for the Interface used as well as the program files to actually move the robot. The interface code will be able to work on any computer which has Java installed, however the python files which move the stepper motors will need to be downloaded on to the Raspberry Pi. This code works alongside the specific 3D printed chassis to build the robot.

### Requirements:
          - Python 3.X
          - Raspberry Pi & SSH Login
          - 2 Stepper Motors
          - Jumper Wires
          - 3D Printed Robot Chassis
          
## Running & Compiling

Open up the program in an IDE and change the values of the SSH Login to that of your Raspberry Pi's address. Then change the directory of the files 


To compile and run this program you will need Java installed.

``` $ cd Build-It-Yourself-Robot-STEM/SCE Challenge/src/ ```

``` $ javac Main.java ``` Name of the File

``` $ java Main ``` Name of the Class

## Raspberry Pi

Make sure SSH is enabled on the Raspberry Pi. Have the file path in your java Interface file set according to where you have downloaded the python files on your Pi. 

### Stepper Motor A

- Input 1: GPIO 11
- Input 2: GPIO 15
- Input 3: GPIO 16
- Input 4: GPIO 18

### Stepper Motor B

- Input 1: GPIO 32
- Input 2: GPIO 17
- Input 3: GPIO 22
- Input 4: GPIO 20

## Screenshots

![alt text](https://user-images.githubusercontent.com/18488979/31190909-70a16a76-a934-11e7-9c94-9b2a032688f8.png)
![alt text](https://user-images.githubusercontent.com/18488979/31190910-70a230c8-a934-11e7-8678-b086b2ab5fc6.png)
![alt text](https://user-images.githubusercontent.com/18488979/31190911-70a3710e-a934-11e7-95ba-419a7655e5a2.png)
![alt text](https://user-images.githubusercontent.com/18488979/31190912-70a76ff2-a934-11e7-8808-cc020f31b6c5.png)
![alt text](https://user-images.githubusercontent.com/18488979/31190914-70a8094e-a934-11e7-85c0-877027dc927f.png)
![alt text](https://user-images.githubusercontent.com/18488979/31190913-70a7fa44-a934-11e7-938c-1aac776304de.png)
