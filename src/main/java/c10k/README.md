# How to add net interface in Virtual Box
- Open Networks setting in VB
- Add `Bridge connection` mode virtual interface
- `Promiscuous mode` chose `Allow All`
- login to Linux and set 
```sh
ifconfig enp0s8 up
#ifconfig enp0s8 192.168.3.179 netmask 255.255.255.0
ifconfig enp0s8 10.209.21.112 netmask 255.255.255.0
```
if your sub net is `192.168.X.X` use first 
# How to run
Make sure you change the server ip address in `C10kclient.java`
and also the two local `ip address`
