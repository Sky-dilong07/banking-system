                                           🏦 Banking Management System
A full-stack Banking Management System built using Spring Boot, Spring Data JPA, MySQL, and a clean HTML, CSS, and JavaScript frontend.
It enables adding customers, creating accounts, and performing secure banking transactions with a modern interface.

🚀 Features

👤 Add and manage customers

🏦 Create accounts for existing customers

💰 Deposit, withdraw, and transfer money

📊 View account details and transaction history

🔗 RESTful APIs using Spring Boot

💻 Responsive frontend with HTML, CSS, JavaScript

🧰 Tech Stack
Layer	Technologies
Frontend	HTML, CSS, JavaScript
Backend	Java, Spring Boot, Spring Data JPA
Database	MySQL
Build Tool	Maven
Version Control	Git, GitHub

⚙️ Installation & Setup
1️⃣ Clone the Repository
git clone https://github.com/<your-username>/banking-system.git
cd banking-system

2️⃣ Configure the Database
Edit src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the Application
mvn spring-boot:run
Backend will start on: http://localhost:8081

4️⃣ Launch Frontend

Simply open the index.html file in your browser.
Frontend connects automatically to the backend APIs.

🧪 API Overview
| Operation         | Method   | Endpoint                        |
| ----------------- | -------- | ------------------------------- |
| Add Customer      | **POST** | `/api/customers`                |
| Get All Customers | **GET**  | `/api/customers`                |
| Create Account    | **POST** | `/api/accounts/create`          |
| Deposit           | **POST** | `/api/accounts/deposit`         |
| Withdraw          | **POST** | `/api/accounts/withdraw`        |
| Transfer          | **POST** | `/api/accounts/transfer`        |
| View Account      | **GET**  | `/api/accounts/{accountNumber}` |


👨‍💻 Author
Saurabh Mishra
MCA Student | Java & Spring Boot Developer
📧 saurabhmishra12mgs@gmail.com


## 🖼️ Frontend Preview
![Banking System Frontend](https://github.com/user-attachments/assets/a8d54b92-f23c-47cf-910e-ec5be3e939b7)

