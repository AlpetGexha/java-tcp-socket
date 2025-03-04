# java-tcp-socket

Config VM
Adapter 1:
NAT

Adapter 2: 
Host Only Adapter


to check the ip on linux
```bash
ip route

or

ifconfig
```

Install Java if needed
```bash
cd /usr/lib/jvm

sudo wget  https://download.java.net/java/GA/jdk23.0.2/6da2a6609d6e406f85c491fcb119101b/7/GPL/openjdk-23.0.2_linux-x64_bin.tar.gz

tar xvf openjdk-23.0.2_linux-x64_bin.tar.gz
```

Windows Check
```bash
netstat -ano | findstr :5050
```

Linux Check
```bash
nc -zv 192.168.56.101 5050
```
