# TPI Backend 2025 - UTN FRC

Sistema de backend desarrollado como **Trabajo Práctico Integrador** de la materia **Backend de Aplicaciones** en la **Universidad Tecnológica Nacional - Facultad Regional Córdoba (UTN FRC)**.

## 🧩 Microservicios

El proyecto está organizado en 4 microservicios:

| Servicio              | Descripción                                                                 |
|-----------------------|------------------------------------------------------------------------------|
| `tpi-admin-service`   | Gestión de pruebas, interesados, empleados y zonas peligrosas               |
| `tpi-reportes-service`| Generación de reportes y estadísticas del sistema                           |
| `tpi-vehiculos-service`| Administración de vehículos y sus relaciones                               |
| `tpi-gateway-service` | API Gateway (Spring Cloud Gateway) para enrutar las peticiones entrantes   |

## 🐘 Base de Datos

- Motor: **PostgreSQL**
- Montado en **Docker**
- Dump inicial disponible en la carpeta `/db/dump.sql`

## 🚀 Ejecución del proyecto

### Requisitos

- Java 21 o superior
- Maven
- Docker + Docker Compose

### Levantar la base de datos

```bash
docker-compose up -d
```

Esto iniciará un contenedor PostgreSQL y cargará el esquema automáticamente desde el dump.

### Ejecutar los microservicios

Desde terminal o IntelliJ:

```bash
cd tpi-admin-service
mvn spring-boot:run
```

Repetir para cada microservicio. El Gateway se ejecuta en `http://localhost:8080`.

### Ejemplo de uso

```http
GET http://localhost:8080/admin/hello
```

Este request será redirigido por el gateway al microservicio correspondiente (`admin-service`).

## 📁 Estructura del proyecto

```
TPI-Backend/
│
├── db/                    # Dump SQL de la base PostgreSQL
├── docker-compose.yml     # Levanta el contenedor de PostgreSQL
├── tpi-admin-service/     # Microservicio de gestión general
├── tpi-reportes-service/  # Microservicio de reportes
├── tpi-vehiculos-service/ # Microservicio de vehículos
├── tpi-gateway-service/   # API Gateway
```

## 🛡 Seguridad

A futuro, se prevé integrar autenticación y autorización con **Keycloak**.

## 👥 Equipo

- Nicolás Garay
- Mariano Iturriza
- Marcos Belli
- Francisco López Mora

---

UTN - Facultad Regional Córdoba  
Ingeniería en Sistemas de Información  
Backend de Aplicaciones 2025
