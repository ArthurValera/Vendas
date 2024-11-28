# Sales Management System

This project is a system for managing sales, inventory control, customer, supplier, and employee registrations, as well as product entries. It is structured following a layered approach, including **control**, **models**, and **repositories**, ensuring organization and ease of maintenance.

---

## 📋 Features

### 🛒 **Sales Management**
- Record sales, including customer, sold products, quantity, and total value.
- Automatically reduce inventory when registering sales.
- Associate sales with customers and responsible employees.

### 📦 **Inventory Management**
- Manage registered products.
- Register product entries with supplier, quantity, and cost.
- Automatically update stock based on product entries and sales.

### 👤 **Person Registration**
- **Customers**: Register customers with name, address, and contact information.
- **Suppliers**: Register suppliers with relevant data for merchandise supply.
- **Employees**: Register employees involved in the sales process.

### 📊 **Management Reports**
- Report products with low stock levels.
- Sales report with total values and served customers.
- Detailed view of product entries and sales.

### ✔️ **Validation and Consistency**
- Ensure all required fields are completed.
- Maintain consistency in entity relationships (e.g., sales and inventory, entries and products).

---

## 🗂️ Project Structure

The code organization follows a modular structure:

### **Package `control`**
Responsible for managing system logic and intermediating communication between the model and repository layers:
- `CidadeControle`
- `ClienteControle`
- `EntradaControle`
- `EstadoControle`
- `FornecedorControle`
- `FuncionarioControle`
- `ProdutoControle`
- `VendaControle`
- `MainControle`

### **Package `models`**
Defines system entities and their attributes:
- `Cidade`
- `Cliente`
- `EntradaProd`
- `Estado`
- `Fornecedor`
- `Funcionario`
- `Item`
- `ItemEntrada`
- `ItemVenda`
- `Pessoa`
- `Produto`
- `Venda`

### **Package `repositories`**
Implements data persistence for entities:
- `CidadeRep`
- `ClienteRep`
- `EntradaRep`
- `EstadoRep`
- `FornecedorRep`
- `FuncionarioRep`
- `ItemEntradaRep`
- `ItemVendaRep`
- `ProdutoRep`
- `VendaRep`

### **Additional Components**
- `Database Configuration`
- `VendasApplication`

---

## 🚀 Technologies Used

- **Java**: Main language for backend development.
- **Spring Framework**: Framework used for dependency injection, route mapping, and database integration.
- **JPA/Hibernate**: For data management and persistence.
- **PostgreSQL**: Database used to store information.
- **Thymeleaf**: Template engine for rendering HTML pages.
- **Maven**: Dependency management and project build.

---

## 📂 How to Run the Project

### Prerequisites
- Java 11 or higher.
- PostgreSQL installed and configured.
- IDE with Java project support (e.g., IntelliJ IDEA, Eclipse, VS Code).
- Maven configured on your machine.

### Steps

1. **Clone this repository**  
   Run the command:
   ```bash
   git clone https://github.com/ArthurValera/vendas.git
   
2. **Navigate to the project directory**
  Use the command:
   ```bash
   cd vendas

4. **Configure the database in the `application.properties file`**  
  Open the configuration file and adjust the credentials to connect to your PostgreSQL database:
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_vendas
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

6. **Install project dependencies**  
   Ensure Maven is configured on your machine, then install dependencies with:
      ```bash
      mvn install

8. **Run the project**  
   Start the application with: `mvn spring-boot:run`.
   Or click the play button in your IDE

9. **Access the system**  
   After initialization, the system will be available in your browser at: `http://localhost:8080`.

## 📧 Contact

If you have questions or suggestions, feel free to reach out:

*   **Author**: Arthur Valera de Castro Guerra
*   **Email**: arthurvalera7@gmail.com
*   **LinkedIn**: [Arthur Valera](https://www.linkedin.com/in/arthur-valera-64352a210/)

---
