# Expense Tracker (Java + MySQL)

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue)
![Swing](https://img.shields.io/badge/GUI-Swing-yellowgreen)
![Maven](https://img.shields.io/badge/Build-Maven-red)

A desktop application to track personal expenses with categorized spending and monthly reports.


## Features

- ➕ Add new expenses with amount, category, date, and description
- ❌ Delete existing expenses
- 📊 Categorize spending (Food, Rent, Entertainment, etc.)
- 📅 Generate monthly expense reports
- 💾 Persistent storage using MySQL database
- 🖥️ User-friendly Swing GUI


## Tech Stack

- **Backend**: Java (JDK 17+)
- **Database**: MySQL (8.0+)
- **GUI**: Swing
- **Database Connectivity**: JDBC
- **Build Tool**: Maven


## Prerequisites

Before you begin, ensure you have installed:

- [JDK 17 or later](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL 8.0 or later](https://dev.mysql.com/downloads/mysql/)
- [Maven](https://maven.apache.org/download.cgi)
- (Optional) IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions


## Installation

### Prerequisites
1. **Java JDK 17+**  
   Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   
2. **MySQL Server 8.0+**  
   Download from [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)

3. **Maven 3.6+**  
   Download from [Apache Maven](https://maven.apache.org/download.cgi)


### Clone the Repository
```bash
git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker
```

### 1. Database Setup

1 . **Start MySQL server**

2 . **Execute these SQL commands:**
```sql
CREATE DATABASE expense_tracker;
USE expense_tracker;

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255)
);

-- Optional: Create dedicated database user
CREATE USER 'expense_user'@'localhost' IDENTIFIED BY 'SecurePass123!';
GRANT ALL PRIVILEGES ON expense_tracker.* TO 'expense_user'@'localhost';
FLUSH PRIVILEGES;
```
3 . **Update database credentials in src/main/java/dao/ExpenseDAO.java:**
```sql
private final String jdbcUrl = "jdbc:mysql://localhost:3306/expense_tracker";
private final String username = "expense_user"; // or "root"
private final String password = "SecurePass123!";
```


## 🚀Running the Application

**Build with Maven:**
```bash
mvn clean install
```

**Run the Application:**
```bash
mvn clean compile exec:java
```


## Alternative (Run from IDE):

1 . Import as Maven project in IntelliJ/Eclipse

2 . Run ``` Main.java ``` directly


## 📂 Project Structure
```
expense-tracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/          # Data models (Expense.java)
│   │   │   ├── dao/            # Database operations (ExpenseDAO.java)
│   │   │   ├── gui/            # Swing components (ExpenseTrackerGUI.java)
│   │   │   └── Main.java       # Entry point
│   │   └── resources/          # (Future use for config files)
├── screenshots/                 # Application screenshots
├── pom.xml                      # Maven configuration
└── README.md                    # This file
```


## 📸 Screenshots

**Main Interface:**

<img width="586" height="393" alt="image" src="https://github.com/user-attachments/assets/2c0aa94b-5636-4b1e-b917-404f4762753d" />




**Adding an Expense:**


<img width="586" height="393" alt="image" src="https://github.com/user-attachments/assets/12995511-e2a3-435e-9e5d-bd7fc6e528b3" />




**Expense List:**


<img width="579" height="386" alt="image" src="https://github.com/user-attachments/assets/acd27e3c-03ea-41f7-bab0-bf949b97f834" />



## 🤝 Contributing

**1 . Fork the repository.**

**2 . Create a new branch ``` (git checkout -b feature/your-feature) ```.**

**3 . Commit your changes ```(git commit -am 'Add some feature')```.**

**4 . Push to the branch ```(git push origin feature/your-feature)```.**

**5 . Open a Pull Request.**


## 📜 License
This project is licensed under the [MIT License](LICENSE) - see the link for details.

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)


## 📧 Contact
Developer Name - jaynikam2005@gmail.com

Project Link : https://github.com/jaynikam2005/ExpenseTracker
