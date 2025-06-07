# prueba-campuslands

API REST para la gestión de una tienda de abarrotes, desarrollada con **Spring Boot** y **MySQL**. Esta API permite administrar productos disponibles y registrar ventas, actualizando el stock automáticamente.

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

- **GET** - Obtener todos los productos.
- **GET** `/{id}` - Obtener un producto por su ID.
- **POST** - Crear un nuevo producto.
- **PUT** `/{id}` - Actualizar un producto existente.
- **DELETE** `/{id}` - Eliminar un producto por sU ID.
- **GET** `/filter?category=categoryName` - Obtener productos por categoría.

### 💰 Venta (`/api/sales`)

- **POST** - Registrar una nueva venta (verifica stock antes).
- **GET** - Listar todas las ventas.
- **GET** `/customer/{customerIdentifier}` - Obtener una venta por identificador del cliente.

> 💡 Cada venta descuenta el stock del producto correspondiente.

## Para crear un producto
```bash
Ejemplo del JSON

{
    "sku": "CEL-73883",
    "name": "telefono xp",
    "unitPrice": 3600000.00,
    "stock": 10,
    "category": "Movil"
}
```

## Para crear una venta
```bash
Ejemplo del JSON

{
    "customerIdentifier": "12345",
    "productId": 1,
    "quantityPurchased": 2
}
```

## ⚙️ Configuración

### 1. Clonar el repositorio

```bash
https://github.com/Jearvaldor95/jesus_valeta_java_campus.git
cd jesus_valeta_java_campus
````

### 2. Crear archivo .env
Crea un archivo .env en la raíz del proyecto con el siguiente contenido:

```bash
DATASOURCE_DEV=jdbc:mysql://localhost:3306/grocery_db
USERNAME_DEV=root
PASSWORD_DEV=tu_contraseña
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