# 🧠 Smart Calculator

A responsive and user-friendly smart calculator built using **Java Spring Boot**, designed for real-time expression evaluation with a modern frontend. Deployed live using **Render Web Services**.

---

## 🌟 Features

- Evaluate complex arithmetic expressions
- Clean and modern user interface
- Realtime result rendering
- Keyboard and button input support
- Full Spring Boot MVC structure
- Deployed and running on Render

Live App: [https://smart-calculator-0268.onrender.com](https://smart-calculator-0268.onrender.com)

---

## 🛠️ Tech Stack

| Layer          | Technology         |
|----------------|--------------------|
| Backend        | Java 21 (Spring Boot) |
| Frontend       | HTML, CSS, Bootstrap |
| Build Tool     | Maven              |
| Template Engine| Thymeleaf          |
| Deployment     | Render (Docker)    |

---

## ⚙️ How It Works

### 🧩 Architecture

- `@Controller`: Handles routing and processing
- `Model`: Transfers data between backend and frontend
- `Thymeleaf`: Renders dynamic values in HTML (`index.html`)
- `static/`: Contains CSS/JS
- `templates/`: Contains Thymeleaf template (`index.html`)

---

## 🐳 Docker & Deployment

### 🧑‍💻 Local Development
Ensure Java 21 and Maven are installed.

```bash
# Clone the repo
git clone https://github.com/RohitKumarRath/smart-calculator.git
cd smart-calculator

# Build and run
mvn clean install
mvn spring-boot:run
