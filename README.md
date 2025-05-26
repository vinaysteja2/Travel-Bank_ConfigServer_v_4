
# 🏦 Travel Bank – Config Management (v3)

This repository is dedicated to **configuration management** for the `Travel Bank` microservices application. It is designed to support scalable, cloud-native architecture using **Spring Boot configuration best practices**.

---

## 📌 Project Continuity

This repository continues from:
- `TRAVEL-BANK_Microservices_v.1.0`
- `TRAVEL-BANK_Docker_v.2.0`

The current version, **`v3.2`**, focuses on **centralized configuration management**, making the services more maintainable and scalable.

---

## 🔧 NEW: Centralized Config Server with Native Support

In this version, we’ve added a **Spring Cloud Config Server** with **native file-based support** to externalize and centralize configurations across all microservices.

### ✅ Configuration Highlights

- The **Config Server** is added for the first time, replacing the use of embedded `application.properties` in each service.
- The server is configured with the `native` profile (instead of Git) to fetch configurations from a local file system.
- All three core microservices — `accounts`, `cards`, and `loans` — fetch their configuration from this centralized Config Server.

### 🛠️ Config Server Configuration

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

---

## 🧰 Configuration Management in Spring Boot

Spring Boot supports multiple ways to read application properties. Here’s a breakdown of the most commonly used approaches:

### 1️⃣ Using `@Value`
```java
@Value("${property.name}")
private String propertyValue;
```

### 2️⃣ Using `Environment`
```java
@Autowired
private Environment environment;

public void getProperty() {
    String propertyValue = environment.getProperty("property.name");
}
```

### 3️⃣ Using `@ConfigurationProperties` ✅ *Recommended*
```java
@ConfigurationProperties(prefix = "prefix")
public class MyConfig {
    private String property;
    // getters and setters
}
```

This avoids hardcoding keys and improves maintainability.

---

## 🌐 Profiles in Spring Boot

Spring provides a powerful tool called **profiles** to manage configuration for multiple environments like `dev`, `qa`, and `prod`. Profiles allow selective loading of properties and bean creation based on the environment.

### ✅ Creating Profile-Specific Files

- `application.properties` → Default profile
- `application_qa.properties` → QA environment
- `application_prod.properties` → Production environment

### ✅ Activating a Profile

```properties
spring.profiles.active=prod
```

### ✅ Externalizing Configuration

Spring Boot supports various methods to externalize configuration after the JAR is built:

#### 1. Command-Line Arguments
```bash
java -jar accounts-service.jar --build.version="1.1"
```

#### 2. JVM System Properties
```bash
java -Dbuild.version="1.2" -jar accounts-service.jar
```

#### 3. Environment Variables
Windows:
```cmd
env:BUILD_VERSION="1.3"; java -jar accounts-service.jar
```
Linux/macOS:
```bash
BUILD_VERSION="1.3" java -jar accounts-service.jar
```

Spring handles relaxed binding (e.g., `BUILD_VERSION` becomes `build.version`).

### ❗ Drawbacks of Spring Boot Only Externalized Config

1. Risk of manual errors due to CLI/JVM arguments
2. Difficult to track/audit config changes
3. Lack of granular access control with environment variables
4. Challenges in managing config in distributed setups
5. No built-in encryption for secrets
6. Config reload without restart requires additional tools (e.g., Spring Cloud Config)

---

## 🐳 Docker Support (From v2.0)

Each microservice in the Travel Bank ecosystem is fully containerized using Docker.

### 🔧 Docker Compose
```bash
docker compose up -d
docker compose down
```

### 🔧 Build Docker Images

| Method         | Command                                         |
|----------------|--------------------------------------------------|
| Google Jib     | mvn compile jib:dockerBuild                    |
| Buildpacks     | mvn spring-boot:build-image                    |
| Dockerfile     | docker build . -t <image-name>:<tag>           |

### ▶️ Run Docker Containers
```bash
docker run -p 8080:8080 vinaysteja0231/accounts:v2.0
docker run -d -p 8080:8080 vinaysteja0231/accounts:v2.0
```

---

## 🧩 Travel Bank - Microservices Overview

A microservices-based banking platform built with Spring Boot.

### ✅ Core Services

| Service   | Features |
|-----------|----------|
| **Accounts** | Customer registration, account creation, validations, exception handling |
| **Cards** | Card issuance, CRUD for cards |
| **Loans** | Loan application, tracking, CRUD operations |

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Swagger (OpenAPI)
- Docker + Buildpacks + Jib
- Maven
- Postman

---

## 🔍 API Testing & Docs

- 📘 **Swagger UI**: http://localhost:8080/swagger-ui/index.html  
- 💾 **H2 Console**: http://localhost:8080/h2-console

---

## 🧪 Postman Collection

A ready-to-use Postman collection is available here:

📂 **File**: `TRAVEL-BANK.postman_collection.json`  
🔹 Test all microservices from one place

---

## 📈 Future Enhancements

- 📡 Integrate Kafka for event-driven architecture
- 🧭 API Gateway + Eureka Discovery
- 📊 Logging & Monitoring with ELK Stack
- 🐳 Docker Compose for full service orchestration

---

## 📚 Related Repositories

- `travelbank-microservices`
- `travelbank-docker`

---

## 🙌 Contribution

Feel free to fork, raise issues, or submit pull requests. Feedback and improvements are always welcome!

---

## 📜 License

This project is licensed under the MIT License.
