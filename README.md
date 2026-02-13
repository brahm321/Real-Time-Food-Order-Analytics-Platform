
---

# 🚀 Real-Time Food Order Analytics Platform

A high-performance distributed system designed to validate live food orders, stream them through a message broker, and visualize real-time business analytics and system health.

### 🛠 Tech Stack

* **Backend:** Java 17+, Spring Boot 4.0.2
* **Messaging:** Confluent Cloud Kafka (Managed Service)
* **Database:** PostgreSQL
* **Observability:** Grafana, Loki, Promtail (Log-based Metrics)
* **Concurrency:** Java ExecutorService (Fixed Thread Pool)

---

### 🏗 Architecture Overview

The system is divided into two main microservices:

1. **Consumer Service:** Acts as the entry point for orders. It performs business validation (e.g., rejecting orders with a cooking time > 120 mins) and publishes validated orders to the Kafka topic `topic_0`.
2. **Chef Service:** Consumes messages from the Kafka topic and utilizes an `ExecutorService` to manage concurrent order preparation and delivery cycles in parallel.

Sample Schema:

{
  "id": 40,
  "customerName": "Allen",
  "items": [
    "Margherita Pizza",
    "Garlic Knots",
    "Coke"
  ],
  "totalAmount": 24.50,
  "cookingTime": 11,
  "status": "PREPARING",
  "deliveryAddress": "123 Maple St, Apt 4B, Springfield",
  "orderTime": "2026-02-10T22:05:00"
}

---

### 📊 Real-Time Monitoring (Grafana + Loki)

The system features full-stack observability, allowing you to track live logs and system performance metrics without accessing the server directly.

#### **Grafana Dashboard:**

🔗 [Live Monitoring Dashboard](https://bkumar4032.grafana.net/public-dashboards/adcd3da3d40145cdb16eb02fa82fb413)

#### **Dashboard Preview:**

> *Visualizing Kitchen Fulfillment Rate, Active Cooking Throughput, and Order Source Breakdown (WEB vs. ANDROID).*
<img width="1440" height="697" alt="Screenshot 2026-02-13 at 20 08 39" src="https://github.com/user-attachments/assets/c680671a-ef25-4fb0-8ff7-83c540a0fe4e" />

---

### ☁️ Confluent Kafka Integration

The platform leverages **Confluent Cloud Kafka** for reliable, cloud-native messaging. It uses secure connectivity (SASL_SSL) to ensure data is transferred safely between services.

#### **Kafka Topic Preview:**

> *Real-time message streaming on topic: `topic_0`.*
<img width="1125" height="722" alt="Screenshot 2026-02-13 at 20 55 43" src="https://github.com/user-attachments/assets/58455011-55df-4ad3-97c4-9088ec9e4d28" />
---

### 🚀 Getting Started

#### 1. Configuration

Update the `application.properties` in both services with your specific PostgreSQL and Confluent Kafka credentials.

#### 2. Run the Services

```bash
# To run the Consumer Service
cd Consumer
./mvnw spring-boot:run

# To run the Chef Service
cd Chef
./mvnw spring-boot:run

```

#### 3. Monitoring Setup

Run the Promtail binary with your custom configuration to start shipping logs to Grafana Loki:

```bash
./promtail -config.file=promtail-config.yaml

```

---

### 🔥 Key Features

* **Multi-threaded Execution:** Uses a fixed thread pool to handle multiple kitchen tasks simultaneously.
* **Business Validation:** Automatically discards invalid orders based on predefined time constraints.
* **Live Log Tailing:** Integrated LogQL queries to stream live application logs directly onto the Grafana dashboard.

---

**Author:** Brahmesh

**Portfolio:** [github.com/brahm321](https://www.google.com/search?q=https://github.com/brahm321)
