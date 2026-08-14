# 🌱 AI Carbon Footprint Monitoring System

An AI-powered full-stack web application designed to help users calculate,
monitor, analyze, and manage their carbon footprint.

The system provides carbon emission tracking, dashboards, historical records,
reports, secure authentication, and an AI-powered advisor that provides
personalized suggestions to reduce carbon emissions.

---

## 📌 Overview

The AI Carbon Footprint Monitoring System is a full-stack application
consisting of a responsive frontend and a Spring Boot backend connected
to a MySQL database.

Users can create an account, securely log in, calculate their carbon
emissions based on their activities, view their carbon footprint through
charts and reports, maintain historical records, and receive AI-powered
recommendations.

---

## ✨ Features

### 👤 User Management

- User Signup
- User Login
- JWT-based Authentication
- Secure Password Encryption using BCrypt
- User Profile Management
- Update Profile
- Change Password
- Account Settings
- Delete Account

### 🌍 Carbon Footprint Monitoring

- Calculate Carbon Footprint
- Record Carbon Emission Data
- View Carbon Dashboard
- View Previous Entries
- Track Carbon Footprint History
- Generate Carbon Reports
- Visualize Carbon Data using Charts

### 🤖 AI Carbon Advisor

- AI-powered carbon footprint advisor
- Personalized recommendations
- Suggestions to reduce carbon emissions
- Integration with Groq API

### 🔐 Security

- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Protected REST APIs
- Environment Variables for Sensitive Information

---

## 🛠️ Technologies Used

### Frontend

- HTML5
- CSS3
- JavaScript
- Chart.js

### Backend

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- REST APIs
- Maven

### Database

- MySQL

### AI

- Groq API

### Tools & Platforms

- IntelliJ IDEA
- VS Code
- MySQL Workbench
- Git
- GitHub
- Render

---

## 🏗️ System Architecture

```text
                    ┌──────────────────────┐
                    │      User/Browser    │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Frontend        │
                    │ HTML / CSS / JS      │
                    │      Chart.js        │
                    └──────────┬───────────┘
                               │ REST API
                               ▼
                    ┌──────────────────────┐
                    │    Spring Boot       │
                    │      Backend         │
                    │                      │
                    │ Spring Security      │
                    │ JWT Authentication    │
                    │ REST Controllers      │
                    │ Services              │
                    └───────┬───────┬──────┘
                            │       │
                   ┌────────▼──┐ ┌──▼────────────┐
                   │   MySQL   │ │   Groq API    │
                   │ Database  │ │ AI Advisor    │
                   └───────────┘ └───────────────┘

📂 Project Structure
Frontend
frontend/
│
├── AIAdvisor.html
├── CarbonCalculate.html
├── CarbonReport.html
├── CarbonSignUp.html
├── PastEntries.html
├── Profile.html
├── Settings.html
├── index.html
├── Css/
├── Js/
└── Image/

Backend

carbon-backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/carbontracker/
│   │   │       ├── Config/
│   │   │       ├── Controller/
│   │   │       ├── DTO/
│   │   │       ├── Entity/
│   │   │       ├── Exception/
│   │   │       ├── Repository/
│   │   │       ├── Security/
│   │   │       └── Service/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
└── mvnw.cmd

🌐 Frontend

The frontend is built using HTML, CSS, and JavaScript.
It communicates with the Spring Boot backend through REST APIs.

Main frontend modules include:
Signup/Login
Carbon Calculation
Dashboard
Carbon History
Reports
AI Advisor
Profile
Settings

🚀 Deployment
The project is configured for deployment using:
GitHub
Render
Docker
The frontend and backend can be deployed as separate services.

🧪 Testing
The backend contains a Spring Boot test structure for application
testing.
Testing can be extended using:
JUnit
Mockito
Spring Boot Test

🎯 Project Goals
The main goals of this project are:
Help users understand their carbon footprint
Track carbon emissions over time
Provide meaningful visualizations
Encourage environmentally responsible decisions
Provide AI-powered recommendations
Demonstrate full-stack application development

Future Enhancements
Possible future improvements include:
More detailed emission categories
Advanced analytics
Improved AI recommendations
Carbon reduction goals
Notifications and reminders
More detailed monthly/yearly reports
Cloud deployment improvements
Automated testing

👩‍💻 Author
Poonam Ahirwar
B.Tech – Computer Science & Engineering
