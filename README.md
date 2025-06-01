# Introduction

This is a simple SpringBoot application for employee and department management.

# Starting the application

Create local addresses for the backend

```
$sudo vim /etc/hosts

##                                                                                                                                                                                                                                                                                                                                                 
# Host Database
#                                                                                                                                                                                                                                                                                                                                                  
# localhost is used to configure the loopback interface
# when the system is booting.  Do not change this entry.
##                                                                                                                                                                                                                                                                                                                                                 
127.0.0.1       localhost                                                                                                                                                                                                                                                                                                                          
255.255.255.255 broadcasthost                                                                                                                                                                                                                                                                                                                      
::1             localhost                                                                                                                                                                                                                                                                                                                          
-> 127.0.0.1       ems-api.local
```

Create the EMS user in your local mysql database:
```
mysql -u root -p
CREATE USER 'ems'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'ems'@'localhost' WITH GRANT OPTION;
```

Run backend with SpringBoot and Java 17