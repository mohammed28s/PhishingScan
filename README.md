# 🛡️ PhishingScan: Phishing URL Detection API

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/mohammed28s/PhishingScan/blob/main/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/mohammed28s/PhishingScan)](https://github.com/mohammed28s/PhishingScan/stargazers)
[![GitHub issues](https://img.shields.io/github/issues/mohammed28s/PhishingScan)](https://github.com/mohammed28s/PhishingScan/issues)

**PhishingScan** is a secure, open-source REST API built with Java Spring Boot to detect phishing URLs using a Hugging Face AI model ([`mrm8488/bert-tiny-finetuned-urlphishing`](https://huggingface.co/mrm8488/bert-tiny-finetuned-urlphishing)). It features JWT-based authentication, SQLite for lightweight persistence, and role-based access control with Spring Security. The API is designed for easy integration, robust security, and extensibility.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [AI Model](#ai-model)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Future Improvements](#future-improvements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
- [Acknowledgements](#acknowledgements)

## Features

- 🔐 **Secure Authentication**: User registration and login with JWT-based authentication.
- 🧪 **Phishing Detection**: Classifies URLs as `phishing` or `benign` using a Hugging Face BERT model.
- 🔒 **Role-Based Authorization**: Protects sensitive endpoints with Spring Security.
- 💾 **Lightweight Persistence**: Stores user data in SQLite, with plans for scan history tracking.
- 🧑‍💻 **Developer-Friendly**: Built for easy testing with Postman or cURL.

## Tech Stack

| Layer             | Technology                          |
|-------------------|-------------------------------------|
| Backend Framework | Spring Boot + Maven                 |
| Authentication    | Spring Security + JWT               |
| Database          | SQLite + Spring Data JPA            |
| AI Integration    | Hugging Face Inference API (BERT)   |
| API Client        | RestTemplate                        |

## AI Model

The project uses the [**`mrm8488/bert-tiny-finetuned-urlphishing`**](https://huggingface.co/mrm8488/bert-tiny-finetuned-urlphishing) model from Hugging Face. This BERT-based model is fine-tuned to classify URLs as `phishing` or `benign` with high accuracy.

## Installation

### Prerequisites

- **Java**: 17 or higher
- **Maven**: 3.8+
- **IntelliJ IDEA**: Recommended for development
- **Hugging Face API Token**: Obtain a free token from [Hugging Face](https://huggingface.co/)
- **Git**: For cloning the repository
- **Postman**: Optional, for testing API endpoints
- **DB Browser for SQLite**: Optional, for inspecting the SQLite database

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/mohammed28s/PhishingScan.git
   cd PhishingScan


Build the project using Maven:
mvn clean install


Configure the Hugging Face API token:

Create a .env file in the project root or set the environment variable:export HUGGINGFACE_API_TOKEN=your-huggingface-token


Alternatively, update application.yml:huggingface:
  api-token: your-huggingface-token





Usage
Running the Application

Start the application:
mvn spring-boot:run

Or, build and run the JAR file:
mvn package
java -jar target/PhishingScan-1.0.0.jar


Access the API at http://localhost:8080.


Testing with Postman or cURL

Register a User:

Endpoint: POST /api/auth/register
Body:{
  "email": "user@example.com",
  "password": "123456"
}


Example with cURL:curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"email":"user@example.com","password":"123456"}'




Login:

Endpoint: POST /api/auth/login
Body:{
  "email": "user@example.com",
  "password": "123456"
}


Response:{
  "token": "eyJhbGciOi..."
}


Example with cURL:curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"email":"user@example.com","password":"123456"}'




Scan a URL:

Endpoint: POST /api/phishing/check
Header: Authorization: Bearer <your_token>
Body (form-urlencoded): url=https://suspicious-url.com
Response:{
  "url": "https://suspicious-url.com",
  "classification": "phishing"
}


Example with cURL:curl -X POST http://localhost:8080/api/phishing/check -H "Authorization: Bearer <your_token>" -H "Content-Type: application/x-www-form-urlencoded" -d "url=https://suspicious-url.com"





API Endpoints



Method
Endpoint
Description
Authentication Required



POST
/api/auth/register
Register a new user
None


POST
/api/auth/login
Login and receive a JWT token
None


POST
/api/phishing/check
Analyze a URL for phishing
JWT (Bearer Token)


Database
The project uses SQLite for lightweight persistence, managed via Spring Data JPA. The database includes:

User Table: Stores user credentials (email, hashed password).
ScanHistory Table: Planned for future implementation to track per-user scan results.

You can inspect the database using DB Browser for SQLite.
Configuration
The application is configured via application.yml. Key settings include:

Database: SQLite (stored locally as phishing_scan.db)
Hugging Face API: Token and endpoint for the BERT model
JWT: Secret key and token expiration
Server: Default port is 8080

Example application.yml:
spring:
  datasource:
    url: jdbc:sqlite:phishing_scan.db
    driver-class-name: org.sqlite.JDBC
  jpa:
    hibernate:
      ddl-auto: update
server:
  port: 8080
huggingface:
  api-token: ${HUGGINGFACE_API_TOKEN}
  url: https://api-inference.huggingface.co/models/mrm8488/bert-tiny-finetuned-urlphishing
jwt:
  secret: your-jwt-secret
  expiration: 86400000

To use a different database (e.g., MySQL), update the spring.datasource properties.
Future Improvements

Scan History: Track and store scan results per user in the ScanHistory table.
Admin Dashboard: Build a web interface for scan analytics and user management.
Docker Support: Containerize the application for easier deployment.
Swagger Documentation: Add Springdoc OpenAPI for interactive API documentation.

Contributing
Contributions are welcome! To contribute:

Fork the repository.
Create a feature branch:git checkout -b feature/your-feature


Commit your changes:git commit -m 'Add your feature'


Push to the branch:git push origin feature/your-feature


Open a pull request.

Please include tests and adhere to the project's coding standards.
License
This project is licensed under the MIT License. See the LICENSE file for details.
Contact
For questions or issues, please:

Open an issue on GitHub.
Contact the maintainer at mohammed.23s@outlook.com.

Acknowledgements

Hugging Face for providing the AI model and inference API.
Spring Boot for the robust framework.
All contributors and users for their support and feedback.

Let's make the web safer together! 🛡️```
