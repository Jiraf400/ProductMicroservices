# ProductMicroservices
SpringBoot Microservices project

Builded services:

Product Service - simple service to view products. Uses mongo as database;

Order Service - can order products

Inventory Service - can chech if product, that will be ordered by order service, is in stock or not

Notification Service - Can send notifications, after orders is placed.


Order, Inventory and Notification Services interact with each other.  


STACK: 

Kafka, 
KeyCloak,
Resilience4j,
Mongodb, Mysql


