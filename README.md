# Event-Driven Video Compliance Check System

This project is a Proof of Concept (POC) that demonstrates an Event-Driven Video Compliance Check System. It utilizes Spring Boot and Apache Kafka to implement a robust event-driven architecture for asynchronously scanning videos for restricted or copyrighted content. By leveraging Kafka’s distributed messaging capabilities, the system ensures real-time processing and efficient communication between services, enabling quick detection of compliance violations in video libraries. This POC showcases the potential of integrating Spring Boot and Kafka for scalable content monitoring solutions.

In this example, the Video Analyzer microservice analyzes the uploaded video for any unusual content, providing an added layer of security and content moderation.

## Kafka Message deilvery types

At-most-once delivery: The producer will send messages at most one time, and will not try sending them again if it receives an error or timeout message from the broker. This means that information will be lost if the consumer crashes before it has finished processing all the current messages on the topic.

At-least-once delivery: The producer will send messages at least one time. If the producer receives an error or timeout message from the broker, then it will attempt to resend the message. This means that messages should never be lost, although the producer may duplicate work (which may also result in duplicate outputs).

Exactly once delivery: The message will be delivered exactly one time. Failures and retries may occur, but the consumer is guaranteed to only receive a given message once. Kafka addresses various challenges through the implementation of an Idempotent producer and Transactions, ensuring message reliability and enabling atomic processing.

## API Reference

#### For Video Persistence

```http
  POST localhost:8090/api/video/save?videoCategory=gaming :- Saves video of given video category
```