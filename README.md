# 🌍 Travel Bank – Centralized Config Server with Dynamic Refresh Support (v4.0)

Welcome to the **Travel Bank Configuration Server**, now upgraded to support **dynamic runtime refresh**, **event-driven configuration updates**, and **health probes** for better resilience and scalability across all microservices.

---

## 📌 Project Continuity

This project is an evolution of the following previous versions:

* 🔗 [`TRAVEL-BANK_Microservices_v.1.0`](https://github.com/vinaysteja2/TRAVEL-BANK_Micorservices_v_1.git)
* 🔗 [`TRAVEL-BANK_Docker_v.2.0`](https://github.com/vinaysteja2/TRAVEL-BANK_Docker_v_2.git)
* 🔗 [`TRAVEL-BANK_ConfigManagement_v.3.0`](https://github.com/vinaysteja2/Travel-Bank_Config_Management_v_3.git)

---

## ✨ What’s New in v4.0?

### ✅ Centralized Config Server with Native File Support

```yaml
spring:
  application:
    name: "configserver"
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          search-locations: "file:///D://GitHub_Public//configurationfiles"
server:
  port: 8071
```

Each microservice like `accounts`, `loans`, and `cards` now fetches configuration from this central server using:

```properties
spring.application.name=accounts
spring.config.import=optional:configserver:http://localhost:8071
```

---

## 🔄 Dynamic Configuration Refresh at Runtime

In typical Spring Boot apps, config changes require a restart. Spring Cloud Config allows **hot reload** with the `/refresh` or `/busrefresh` endpoints:

---

### 🔃 Approach 1: Refresh Using `/refresh` Endpoint

#### ✅ Steps

1. **Add actuator dependency** to each microservice (`accounts`, `loans`, `cards`):

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

2. **Enable refresh endpoint** in `application.yml`:

   ```yaml
   management:
     endpoints:
       web:
         exposure:
           include: refresh
   ```

3. **Trigger refresh** via POST:

   ```bash
   curl -X POST http://localhost:<port>/actuator/refresh
   ```

4. ✅ **No restart required** — configuration updates take effect immediately!

---

### 📱 Approach 2: Refresh Using Spring Cloud Bus + RabbitMQ

#### ✅ Steps

1. **Add dependencies** to all services:

   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-bus-amqp</artifactId>
   </dependency>
   ```

2. **Enable `busrefresh` endpoint** in `application.yml`:

   ```yaml
   management:
     endpoints:
       web:
         exposure:
           include: busrefresh
   ```

3. **Run RabbitMQ via Docker**:

   ```bash
   docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
   ```

4. **Trigger refresh across all services**:

   ```bash
   curl -X POST http://localhost:<port>/actuator/busrefresh
   ```

✅ Works across all instances and services connected to the Bus.

---

### 🔔 Approach 3: Fully Automated Refresh using Spring Cloud Config Monitor + GitHub Webhooks

#### ✅ Steps

1. **Add monitor dependency** to the config server:

   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-config-monitor</artifactId>
   </dependency>
   ```

2. **Enable `/monitor` endpoint** (already enabled with actuator).

3. **Configure GitHub webhook**:

   * Target URL: `http://<config-server>/monitor`
   * Content Type: `application/json`
   * Trigger on: `push`

✅ When a change is pushed to GitHub config repo, **refresh is automatically triggered across all services** via RabbitMQ!

---

## 📶 Probes: Liveness and Readiness in Spring Boot

Spring Boot Actuator supports health probes to monitor container/app status:

| Probe Type    | Path                         | Description                        |
| ------------- | ---------------------------- | ---------------------------------- |
| **Liveness**  | `/actuator/health/liveness`  | Is the app alive? Restart if fails |
| **Readiness** | `/actuator/health/readiness` | Can the app handle traffic?        |

Enable in `application.yml`:

```yaml
management:
  endpoint:
    health:
      probes:
        enabled: true
  endpoints:
    web:
      exposure:
        include: health
```

---

## 🔍 Architecture Summary

### ↺ Manual Refresh

```
GitHub Config Repo --> Push config
                     ↓
Config Server <---- /refresh
                     ↓
Microservice reloads updated config
```

### 🖡 Automated Refresh with Spring Cloud Bus + Monitor

```
GitHub Config Repo --> Push config
                     ↓ webhook
Config Server <---- /monitor
                     ↓ Bus Event
Microservices <---- /busrefresh (auto)
```

---

## 💡 Benefits

* 🔄 Real-time config refresh without restarts
* 🕸️ Event-driven updates with RabbitMQ
* 🛡️ Robust health checks via probes
* 📆 Git-based externalized config management (future)
* 🤖 Monitor-based automation with GitHub Webhooks

---

## 🚀 What’s Next in v5.x?

* ✅ Move to Git-based configuration source
* 🔒 Integrate Spring Cloud Vault for secrets
* 🌐 Deploy Config Server to AWS EC2 or EKS
* 🔐 Add role-based config access controls

---

## 🙌 Contribution

We welcome contributions, feedback, and suggestions!
Please feel free to fork, create issues, or open PRs.

---

## 📜 License

MIT License © [Vinay Steja](https://github.com/vinaysteja2)

---

> *Built with ❤️ by Vinay Teja and Spring Cloud enthusiasts.*
