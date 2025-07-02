# Spring Boot API Demo

🚀 **Modern REST API with Spring Boot, JPA, and H2 Database**

## 📋 **Project Overview**

This is a comprehensive Spring Boot application demonstrating modern REST API development with:

- **Product Management System** with full CRUD operations
- **User Management** with authentication and registration
- **File Upload** functionality with image processing
- **H2 In-Memory Database** for development
- **JPA/Hibernate** for data persistence
- **Maven** for dependency management
- **Docker** support for containerization

## 🛠️ **Technologies Used**

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database**
- **Thymeleaf** (for templating)
- **Maven**
- **Docker**

## 🚀 **Quick Start**

### **Prerequisites**
- Java 21 or higher
- Maven 3.8+
- Git

### **1. Clone the repository**
```bash
git clone https://github.com/Tiencut/spring-boot-api-demo.git
cd spring-boot-api-demo
```

### **2. Run the application**
```bash
# Using Maven
mvn spring-boot:run

# Or using Maven wrapper
./mvnw spring-boot:run
```

### **3. Access the application**
- **Application**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (empty)

## 📡 **API Endpoints**

### **Product Management**
```http
GET    /api/v1/Products           # Get all products
GET    /api/v1/Products/{id}      # Get product by ID
POST   /api/v1/Products           # Create new product
PUT    /api/v1/Products/{id}      # Update product
DELETE /api/v1/Products/{id}      # Delete product
```

### **User Management**
```http
POST   /api/login                 # User login
POST   /api/register              # User registration
GET    /api/users/{id}            # Get user by ID
```

### **File Upload**
```http
POST   /api/v1/FileUpload         # Upload file
```

## 🏗️ **Project Structure**

```
src/
├── main/
│   ├── java/com/tutorial/apidemo/apidemo/
│   │   ├── controller/         # REST Controllers
│   │   ├── model/             # Entity classes
│   │   ├── repositories/      # Data repositories
│   │   ├── services/          # Business logic
│   │   └── security/          # Security utilities
│   └── resources/
│       ├── application.properties
│       └── templates/         # Thymeleaf templates
└── test/                      # Unit tests
```

## 🐳 **Docker Support**

### **Build and run with Docker**
```bash
# Build image
docker build -t spring-boot-api-demo .

# Run container
docker run -p 8080:8080 spring-boot-api-demo
```

### **Using Docker Compose**
```bash
docker-compose up
```

## 🧪 **Testing**

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ProductControllerTest
```

## 📝 **Configuration**

### **Database Configuration**
The application uses H2 in-memory database by default. Configuration in `application.properties`:

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
```

## 🚀 **Development Setup**

### **Using GitHub Codespaces**
1. Click "Code" → "Codespaces" → "Create codespace on main"
2. Wait for environment setup
3. Run `mvn spring-boot:run`
4. Access forwarded port 8080

### **Local Development**
1. Clone repository
2. Import in IDE (VS Code, IntelliJ IDEA)
3. Install Java 21 and Maven
4. Run application

## 🔧 **Features**

### ✅ **Implemented**
- RESTful API design
- CRUD operations for Products
- User authentication system
- File upload with validation
- H2 database integration
- Docker containerization
- Comprehensive testing
- Input validation
- Exception handling

### 🚧 **In Progress**
- JWT authentication
- Email verification
- Advanced security features
- API documentation (Swagger)

## 🤝 **Contributing**

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📄 **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 **Contact**

- **Author**: Your Name
- **Email**: your.email@example.com
- **GitHub**: [@yourusername](https://github.com/yourusername)

## 🙏 **Acknowledgments**

- Spring Boot team for the amazing framework
- Spring community for excellent documentation
- H2 Database for lightweight development database
