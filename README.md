# TransactionAPI

This is a Challenge for Mendel Company

## Functionalities

The functionalities of this application are:

- Save Transactions with the next fields: type (`String`), amount (`Double`), id(`Long`) and parentId (`Optional<Long>` )
    where the parentId is other transaction.
- Get Transaction by type: this functionality returns a List of Longs, that are Transaction ids.
- Get Sum of a transaction and each child: This functionality returns a `Double`, summarizing the amount of the first transaction plus the amount of the transaction that has this transaction as a parent.

## Endpoints

To see the available endpoints, this project implements Swagger UI, 
please go to the following link to see the endpoints (remember first to start the application)

```
http://localhost:8080/swagger-ui/index.html
```

## To Run this Application:
Execute on the root of this project:
```
docker build -t transactions-api .
docker run --name transactionAPI -dp 127.0.0.1:8080:8080 transactions-api
```