# 🏨 Airbnb Clone Backend

A scalable backend system for an Airbnb-like platform built using Spring Boot.
This project provides REST APIs for user authentication, hotel management, booking system, inventory handling, and payment integration.

---

## 🚀 Features

* 🔐 **JWT Authentication & Authorization**
* 👤 **User Management (Signup, Login, Profile)**
* 🏨 **Hotel & Room Management**
* 📅 **Booking System**
* 📦 **Inventory Management**
* 💳 **Payment Integration (Stripe)**
* 🔔 **Webhook Handling for Payment Events**
* 📊 **Dynamic Pricing Strategies**

  * Occupancy-based pricing
  * Surge pricing
  * Holiday pricing
* ⚠️ **Global Exception Handling**
* 📬 **Standardized API Responses**

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** MySQL (JPA / Hibernate)
* **Payments:** Stripe API
* **Build Tool:** Maven

---

## 📂 Project Structure

```
src/main/java/com/project/airBnbApp
│
├── controller      # REST API endpoints
├── service         # Business logic
├── repository      # Database layer (JPA)
├── entity          # Database models
├── dto             # Data Transfer Objects
├── security        # JWT & security configuration
├── config          # App configuration (Stripe, Mapper, etc.)
├── exception       # Custom exceptions
├── advices         # Global handlers (response & exception)
├── strategy        # Pricing strategy implementations
└── util            # Utility classes
```

---

## 🔐 Environment Variables

Sensitive data is managed using environment variables:

```
STRIPE_SECRET_KEY=your_stripe_secret_key
STRIPE_WEBHOOK_SECRET=your_webhook_secret
JWT_SECRET=your_jwt_secret
```

---

## ▶️ Run Locally

### 1. Clone the repository

```
git clone https://github.com/mdaquil92/airbnb-clone-backend.git
cd airbnb-clone-backend
```

### 2. Configure environment variables

Set your environment variables in your system.

### 3. Run the application

```
mvn spring-boot:run
```

---

## 📌 API Modules

* 🔑 Auth APIs
* 👤 User APIs
* 🏨 Hotel APIs
* 🛏 Room APIs
* 📅 Booking APIs
* 💳 Payment APIs
* 📦 Inventory APIs

---

## 🔍 Future Improvements

* Dockerize the application
* Add unit & integration tests
* Implement caching (Redis)
* Deploy on cloud (AWS / Render)

---

## 👨‍💻 Author

**Md Aquil**
GitHub: https://github.com/mdaquil92

---

## ⭐ Show your support

If you like this project, give it a ⭐ on GitHub!
