# Backend demo project

I am using the following software for this project:
* Java 11.0.7
* Maven 3.6.3
* Springboot 2.7.5

To start the server run the following command below.  This runs on port **80**, if you have conflicts you can change the `server.port` property in *application.properties*

```
mvn spring-boot:run
```

H2 is used to hold data in memory.  If you want to view the data in the database, navigate to **http://localhost:8082** and use the following settings to connect:

```
Saved Settings: Generic H2 (Embedded)

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:test
User Name: sa
Password: (blank)
```

## Sample requests

#### List All Doctors

```
curl "http://localhost/doctor/list"
```

#### Insert DOCTOR

```
curl -X POST -H 'Content-Type: application/json' http://localhost/doctor -d '{"firstName":"JOHN","lastName":"ZOIDBERG","email":"john@notablehealth.com"}'
```

#### List Appointments by Date and DoctorId

```
curl "http://localhost/appointment/2022-11-03/2"
```

#### Create Appointment

```
curl -X POST -H 'Content-Type: application/json' http://localhost/appointment --data '{"doctorId":1,"firstName":"HOMER","lastName":"SIMPSON","apptDate":"2022-11-05","apptTime":"11:30:00","newPatient":true}'
```

#### Cancel Appointment


```
curl -X DELETE "http://localhost/appointment/8"
```