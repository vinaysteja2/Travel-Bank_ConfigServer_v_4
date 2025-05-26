# 🏦 Travel Bank – Config Server with Spring Cloud (v4)

This repository introduces centralized configuration management for the Travel Bank microservices using **Spring Cloud Config Server** with **native file-based support**. It extends the previous versions of the project to achieve better separation of concerns and scalable architecture.

---

## 📌 Project Continuity

This repository continues from:

* `TRAVEL-BANK_Microservices_v.1.0`
* `TRAVEL-BANK_Docker_v.2.0`
* `TRAVEL-BANK_ConfigManagement_v.3.0`

The current version, **`v4.0`**, introduces a **Spring Cloud Config Server** as a centralized source of truth for all configuration files used by the microservices.

---

## 🔧 NEW: Centralized Config Server with Native Support

In this version, we’ve added a **Spring Cloud Config Server** with **native file-based support** to externalize and centralize configurations across all microservices.

### ✅ Configuration Highlights

* The **Config Server** is added for the first time, replacing the use of embedded `application.properties` in each service.
* The server is configured with the `native` profile (instead of Git) to fetch configurations from a local file system.
* All three core microservices — `accounts`, `cards`, and `loans` — fetch their configuration from this centralized Config Server.

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

## 🧩 Microservices Integration

Each microservice now fetches its configuration from the centralized config server. Example client configuration (`bootstrap.properties` or `application.yml`) for microservices:

```properties
spring.application.name=accounts
spring.config.import=optional:configserver:http://localhost:8071
```

Or in YAML format:

```yaml
spring:
  application:
    name: accounts
  config:
    import: optional:configserver:http://localhost:8071
```

---

## 💡 Benefits of Centralized Config Server

1. 🔁 **Live config refresh** support (with Spring Cloud Bus in future)
2. 🧩 **Decouples config from code**
3. 📁 **Supports multiple environments** (via profile-specific files)
4. 🔒 Potential for **integration with Git, Vault, or JDBC** in future versions
5. 🔍 **Easier auditing and tracking** of configuration changes

---

## 🚀 Future Scope for v5.x

* 🧭 Move from Native File to Git-based Config Repository
* 🛡️ Integrate with Spring Cloud Vault for secret management
* 🔁 Enable dynamic refresh with Spring Cloud Bus + RabbitMQ
* 🔐 Add Role-based Access for configuration access
* 🌐 Host config server on a centralized cloud environment (e.g., AWS EC2 or EKS)

---

## 📚 Related Repositories

* [`travelbank-microservices`](https://github.com/your-org/travelbank-microservices)
* [`travelbank-docker`](https://github.com/your-org/travelbank-docker)
* [`travelbank-config-management`](https://github.com/your-org/travelbank-config-management)

---

## 🙌 Contribution

Feel free to fork, raise issues, or submit pull requests. Feedback and improvements are always welcome!

---

## 📜 License

This project is licensed under the MIT License.
