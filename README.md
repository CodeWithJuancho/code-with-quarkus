# Vass Challenge

Welcome to the implementation of the code challenge, this project has been implemented using an in:mem:H2 database only for developing purposes, the initial loading of the database is done within the code to have more control over the data that is been used.

As an addition some other entities and relationships have been implemented for having a more "realistic" implementation of the app:

 + In the [@PriceResource](/src/main/java/com/vass/api/PriceResource.java) you can find the rest controller implemented.
 + In the [@PriceResourceTest](/src/test/java/com/vass/PriceResourceTest.java) you can find the required tests.

## Curl requests for using the app

### Return all the prices of the database 
#### Example:
curl -X GET "http://localhost:8080/api/prices"

### Return all the price filtered by the brand, product and date selected (that comes from the user side in secondsEpoch!)
#### Example:
curl -X GET "http://localhost:8080/api/prices/filter?brandId=1&productId=1&dateEpoch=1592341200000"

## Additional Quarkus Info

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.