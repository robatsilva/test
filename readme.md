## **Product Management Application**

This project is a **Product Management System** built with **Spring MVC** and **MySQL**. It includes both a JSON API (RESTful controllers) and an MVC-based interface using **Thymeleaf**. The project also has basic unit tests implemented.

If required, I can develop a **Single Page Application (SPA)** version of this project using **Angular**.

---

### **Technologies Used**
- **Java** (Spring Boot, Spring MVC)
- **MySQL** (Database)
- **Thymeleaf** (Server-side rendering for MVC)
- **Bootstrap 5** (Frontend styling)
- **JUnit 5** (Unit testing)
- **Maven** (Build tool)

---

### **Configuration: `application.properties`**

The `application.properties` file is ignored by Git. Use the following template to set up your database connection:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/productdb
spring.datasource.username=root
spring.datasource.password=your_password

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Port
server.port=8080
```

Replace:
- `productdb` with your database name.
- `root` and `your_password` with your MySQL username and password.

---

### **Controllers**
The project is structured to include two types of controllers:

1. **REST Controllers** (JSON API):
   - Provides endpoints for JSON-based operations (CRUD for Products).

2. **MVC Controllers**:
   - Handles the user interface for managing products using Thymeleaf templates.

---

### **Features**
1. **Product Management**:
   - Add, edit, delete, and list products.
   - Filter products by name and price.
   - Sort products by ID, name, and price.

2. **Database**:
   - The project uses MySQL for persistence.

3. **Unit Tests**:
   - Includes basic unit tests for key functionalities.
   - Proper testing would include full coverage of all edge cases and scenarios.

---

### **How to Run the Project**

#### **1. Prerequisites**
Make sure you have the following tools installed:
- **Java 17** or higher
- **MySQL** (running locally)
- **Maven**

#### **2. Setup Database**
Create a database in MySQL:
```sql
CREATE DATABASE productdb;
```

#### **3. Clone the Project**
Clone the repository to your local machine:
```bash
git clone <repository-url>
cd <project-folder>
```

#### **4. Update `application.properties`**
Configure your MySQL credentials as shown in the [Configuration](#configuration-applicationproperties) section.

#### **5. Run the Application**
Use Maven to build and run the project:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

---

### **Endpoints**

#### **REST API Endpoints**
| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| GET    | `/api/products`      | Get all products         |
| POST   | `/api/products`      | Create a new product     |
| PUT    | `/api/products/{id}` | Update product by ID     |
| DELETE | `/api/products/{id}` | Delete product by ID     |

#### **Web Interface (MVC)**
| Endpoint           | Description                   |
|--------------------|-------------------------------|
| `/products/page`   | Product management interface |
| `/products/edit/{id}` | Edit an existing product    |

---

### **Running Tests**
Run unit tests using Maven:
```bash
mvn test
```

---

### **Future Enhancements**
1. Full unit test coverage for all controllers and services.
2. Add validation for form inputs and API requests.
3. Develop a **Single Page Application (SPA)** version with Angular if required.
