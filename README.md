# parking-lot-finder
This project is for finding available parking lot in Seoul.
It has two modules as ui and server.

## Prerequisite
There are two required steps to run this application. 

One is to set auth key for Seoul open api(http://openapi.seoul.go.kr:8088), and the other is to build server module using gradle wrapper.

#### Set the environment value 
```   
$ export SEOUL_PARKING_LOT_API_AUTH_KEY={AUTH_KEY}
```  

#### Build 
```  
$ ./gradlew backend-module:clean backend-module:build
```  

## Run with docker 
```  
$ docker-compose up --build
```  

## Stop
```
$ docker-compose down
```

## Web page URL
```
http://localhost:3000
```

## API document URL 
``` 
http://localhost:8080/swagger-ui.html  
http://localhost:8080/api-docs  
http://localhost:8080/api-docs.yaml
```

## API format
All parameters are optional.
```
http://localhost:8080/api/v1/parking-lots?address={address}&name={name}&tel={tel}&sort=basicParkingFee,ASC
```

#### Example
```
http://localhost:8080/api/v1/parking-lots
http://localhost:8080/api/v1/parking-lots?address=강남&sort=basicParkingFee,ASC
http://localhost:8080/api/v1/parking-lots?address=강남&name=주차장&sort=basicParkingFee,ASC
http://localhost:8080/api/v1/parking-lots?address=강남&name=주차장&tel=02&sort=basicParkingFee,ASC
```
