# 🥗 NutriMetrics

![Learning](https://skillicons.dev/icons?i=java,spring,mysql,postman)

- RESTful API developed in Java (Spring Boot) designed for calculating and managing patient nutritional metrics and energy expenditure.
- The application receives patient physical data, calculates Body Mass Index (BMI), BMI classification, Basal Metabolic Rate (BMR), and Total Daily Energy Expenditure (TDEE), persisting all historical records in a MySQL relational database.

---

## ⚙️ Features

- RESTful API Endpoints for patient management and nutritional calculations.
- Automated BMI Calculation and precise clinical categorization (Underweight, Normal Weight, Overweight, Obesity).
- Basal Metabolic Rate (BMR) and Total Daily Energy Expenditure (TDEE) estimation based on body parameters and physical activity levels.
- Spring Data JPA Integration: Automatic entity-to-database mapping (`PatientModel`) using Hibernate DDL Auto.
- Flexible configuration using Lombok to reduce boilerplate code (`@Builder`, `@Getter`, `@Setter`).

---

## 🏗️ Project Architecture

The project follows a standard Spring Boot layered architecture:
```
src/main/java/com/pedroguths/nutrimetrics
├── controller
│   └── PatientController.java      # REST Controller handling HTTP endpoints
├── dto
│   └── PatientRequest.java         # Request payload mapping
│   └── PatientResponse.java        # Response payload mapping
├── model
│   └── PatientModel.java           # JPA Entity mapping to the SQL database table
├── repository
│   └── PatientRepository.java      # Spring Data JPA Repository for database operations
└── service
    └── PatientService.java         # Core nutritional calculation logic and persistence
```

---

## 🛠️ Technologies

### Backend & Database
- Java 21
- Spring Boot (Web, REST API)
- Spring Data JPA / Hibernate
- MySQL (Managed via MySQL Workbench)
- Maven (Dependency & Build Management)

---

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK 21)
- MySQL Server & MySQL Workbench
- Maven (or use the included ./mvnw wrapper)

### 1. Clone the repository

```
git clone https://github.com/guthspedro10/NutriMetrics.git
```

### 2. Database Setup
Configure your MySQL database connection in src/main/resources/application.yaml:

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/nutrimetrics
    username: seu_nome_de_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: update
```

### 3. Run the Backend API

```
cd NutriMetrics
```
```
./mvnw spring-boot:run
```

### 4. Open Postman/Insomnia and try

```
{
  "name": "Lucas Silva",
  "height": 1.78,
  "weight": 72.5,
  "age": 25,
  "gender": "Male",
  "activityLevel": "Moderately Active"
}
```
```
{
  "name": "Maria Silveira",
  "height": 1.60,
  "weight": 60.00,
  "age": 27,
  "gender": "Female",
  "activityLevel": "Super Active"
}
```

---

## 📚 Possible Future Improvements

- Integrate Harris-Benedict or Mifflin-St Jeor specific formulas dynamically.
- Add patient profile updates (PUT) and deletion (DELETE) endpoints.
- Add input validation (`@Valid`, `@NotNull`, `@Positive`) for health parameters.
- Build a responsive frontend UI (HTML/CSS/JS or React) to consume the API.
- Add unit and integration tests using JUnit 5 and Mockito.

---

## 📌 Project Status

✅ Completed – Educational HealthTech backend project focused on Spring Boot, Data Persistence, and Health Metrics Processing.
