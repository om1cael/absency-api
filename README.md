# 🌟 Absency API

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-green?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge)
![JWT](https://img.shields.io/badge/JWT-Authentication-orange?style=for-the-badge)
![Security](https://img.shields.io/badge/Security-Enabled-brightgreen?style=for-the-badge)

📌 **Absency** is a REST API built with Spring Boot, designed to help users track their school absences efficiently. It includes secure authentication with JWT and endpoints for user registration, login, and absence management.

## 🚀 Features
✅ Secure JWT-based authentication  
✅ User registration and login  
✅ Absence tracking and management  
✅ Increase and decrease absence count  
✅ Retrieve absence details  
✅ Built-in security with Spring Security & BCrypt  

## 🛠️ Tech Stack
- **Backend**: Spring Boot, Spring Security
- **Database**: PostgreSQL with Hibernate & JPA
- **Security**: JWT Authentication & BCrypt Password Hashing
- **Build Tool**: Gradle

## 📥 Installation
### Prerequisites
Before running the project, ensure you have:
- ✅ [Java 21](https://adoptium.net/)
- ✅ [Gradle](https://gradle.org/install/), or use the version embedded in the project.
- ✅ PostgreSQL database installed

### Clone the Repository
```bash
git clone https://github.com/om1cael/absency-api.git
cd absency-api
```

### Configure Database
Modify `application.properties` to match your database settings:
```properties
spring.application.name=absency-api

spring.datasource.url=jdbc:postgresql://localhost:5432/absency
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create-drop

jwt.secret=${SECRET}

server.address=0.0.0.0
```

- If you want to keep the data, change `spring.jpa.hibernate.ddl-auto=create-drop` to `spring.jpa.hibernate.ddl-auto=update` 

### Build and Run
```bash
gradle build
gradle bootRun
```

## 📌 API Endpoints

### 🔐 Authentication
#### 📝 Register a New User
```http
POST /auth/register
```
📤 **Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123",
  "schoolDays": 200,
  "maximumAbsenceInPercentage": 20
}
```
📥 **Response:**
```json
{
  "token": "your-jwt-token"
}
```

#### 🔑 Login
```http
POST /auth/login
```
📤 **Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```
📥 **Response:**
```json
{
  "token": "your-jwt-token"
}
```

### 📊 Absence Management
#### 📋 Get Absence Details
```http
GET /absences
Authorization: Bearer your-jwt-token
```
📥 **Response:**
```json
{
  "schoolDays": 200,
  "maximumAbsenceInPercentage": 20,
  "absentDays": 10
}
```

#### ➕ Increase Absences
```http
PUT /absences/increase
Authorization: Bearer your-jwt-token
```
📥 **Response:**
```json
{
  "success": true,
  "message": "11"
}
```

#### ➖ Decrease Absences
```http
PUT /absences/decrease
Authorization: Bearer your-jwt-token
```
📥 **Response:**
```json
{
  "success": true,
  "message": "10"
}
```

## 🔒 Security
✅ JWT authentication protects all endpoints except `/auth/register` and `/auth/login`.  
✅ Passwords are securely hashed using **BCrypt**.  
✅ CORS is configured to allow cross-origin requests.  

## 📜 License
This project is open-source and available under the [MIT License](LICENSE).

📣 **Contributions & Issues:** Feel free to fork, contribute, and open issues!
