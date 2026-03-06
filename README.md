# Spring Boot + Kafka Order Processing Demo by Keval Chheda

This project demonstrates a **simple event-driven architecture** using **Apache Kafka** and **Spring Boot**.

It simulates an **Order Processing System** where an order event is published to Kafka and consumed by multiple independent services.

---

# Architecture Overview

```
Client Request
      │
      ▼
OrderController (REST API)
      │
      ▼
OrderProducer
      │
      ▼
Kafka Topic : order-created
      │
      │
      ├── EmailConsumer
      ├── NotificationConsumer
      └── AnalyticsConsumer
```

A single event (`order-created`) triggers multiple services independently.

---

# Technologies Used

* Java
* Spring Boot
* Spring Kafka
* Apache Kafka
* Maven

---

# Project Structure

```
src/main/java/com/kafka/order
│
├── OrderApplication.java
│
├── config
│     ├── KafkaProducerConfig.java
│     └── KafkaConsumerConfig.java
│
├── controller
│     └── OrderController.java
│
├── producer
│     └── OrderProducer.java
│
├── consumer
│     ├── EmailConsumer.java
│     ├── NotificationConsumer.java
│     └── AnalyticsConsumer.java
│
└── model
      └── OrderEvent.java
```

---

# Step 1 – Download and Run Kafka

Download Kafka from:

https://kafka.apache.org/downloads

Extract Kafka.

Example location:

```
C:\kafka\kafka_2.13-3.6.1
```

---

# Step 2 – Start Zookeeper

Open terminal:

```
cd C:\kafka\kafka_2.13-3.6.1
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

---

# Step 3 – Start Kafka Broker

Open a new terminal:

```
cd C:\kafka\kafka_2.13-3.6.1
bin\windows\kafka-server-start.bat config\server.properties
```

---

# Step 4 – Create Kafka Topic

Run:

```
bin\windows\kafka-topics.bat --create --topic order-created --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

Verify topic:

```
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
```

---

# Step 5 – Start Spring Boot Application

Run the application:

```
OrderApplication.java
```

Spring Boot will start the Kafka producer and consumers.

---

# Step 6 – Send Order Request

Send request using **Postman**.

### Endpoint

```
POST http://localhost:8080/orders
```

### Request Body

```json
{
  "orderId": "ORD3001",
  "userId": "USER701",
  "amount": 4200
}
```

---

# Step 7 – Producer Publishes Event

Producer sends event to Kafka topic:

```
order-created
```

Log example:

```
PRODUCER → Order sent: ORD3001
```

---

# Step 8 – Consumers Process Event

Kafka delivers the event to multiple consumers.

Example logs:

```
EMAIL → Sending email for order ORD3001
NOTIFICATION → User notified USER701
ANALYTICS → Order ORD3001 amount 4200 | partition=0 | offset=1
```

---

# Understanding Kafka Concepts

## Topic

A topic is a category where messages are stored.

Example:

```
order-created
```

---

## Partitions

Topics are divided into partitions.

Example:

```
order-created
   ├── partition0
   ├── partition1
   └── partition2
```

Partitions enable **parallel processing and scalability**.

---

## Offset

Each message inside a partition has a unique offset.

Example:

```
partition0
   offset0 → ORD1001
   offset1 → ORD3001
```

Offsets represent the **position of the message in the log**.

---

## Consumer Groups

Consumers belong to groups.

Example groups in this project:

```
email-group
notification-group
analytics-group
```

Kafka delivers each event **once per consumer group**.

---

# Observing Messages in Kafka

Run a Kafka console consumer:

```
bin\windows\kafka-console-consumer.bat --topic order-created --bootstrap-server localhost:9092 --from-beginning
```

Example output:

```
{"orderId":"ORD3001","userId":"USER701","amount":4200}
```

This shows the **raw event stored in Kafka**.

---

# What This Project Demonstrates

* Event-driven architecture
* Kafka producers
* Kafka consumers
* Consumer groups
* Topic partitions
* Message offsets
* Asynchronous processing

---

# Future Enhancements

Possible improvements:

* Dead Letter Queue (DLQ)
* Retry mechanism
* Schema validation
* Kafka Streams
* Microservice separation
* Consumer scaling

---

# Learning Outcome

This project helps understand the core Kafka workflow:

```
Producer → Topic → Partition → Consumer Group → Business Logic
```

---

# License

This project is for educational purposes.
