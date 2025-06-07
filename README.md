# prueba-campuslands

API RESTful para la gestión de una tienda de abarrotes, desarrollada con **Spring Boot** y **MySQL**. Esta API permite administrar productos disponibles y registrar ventas, actualizando el stock automáticamente.

## 🚀 Tecnologías

- Java 17+
- Spring Boot 3.5.0
- Spring Data JPA
- MySQL
- Lombok
- MapStruct (Para mapeo DTO)
- Maven

## 📦 Funcionalidades

### 🛒 Producto (`/api/products`)

- **GET** `/` - Obtener todos los productos.
- **GET** `/{id}` - Obtener un producto por su ID.
- **POST** `/` - Crear un nuevo producto.
- **PUT** `/{id}` - Actualizar un producto existente.
- **DELETE** `/{id}` - Eliminar un producto.
- **GET** `/filter?category=categoryName` - Obtener productos por categoría.

### 💰 Venta (`/api/sales`)

- **POST** `/` - Registrar una nueva venta (verifica stock antes).
- **GET** `/` - Listar todas las ventas.
- **GET** `/customer/{customerIdentifier}` - Obtener una venta por identificador del cliente.

> 💡 Cada venta descuenta el stock del producto correspondiente.

## ⚙️ Configuración

### 1. Clonar el repositorio

```bash
git https://github.com/Jearvaldor95/prueba-campuslands.git
cd prueba-campuslands
````

### 2. Crear archivo .env
Crea un archivo .env en la raíz del proyecto con el siguiente contenido:

```bash
DB_URL=jdbc:mysql://localhost:3306/grocery_db
DB_USERNAME=root
DB_PASSWORD=tu_contraseña
```
Asegúrate de tener una base de datos MySQL creada llamada grocery_db.

## ▶️ Ejecutar el Proyecto
Usa Maven para correr la aplicación:
```bash
mvn spring-boot:run
```

## 🧪 Pruebas
Puedes ejecutar las pruebas con:
```bash
mvn test
```