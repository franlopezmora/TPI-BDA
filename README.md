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

### Ejecución rápida del proyecto

#### 1. Clonar el proyecto

```bash
git clone https://github.com/tuusuario/TPI-Backend.git
cd TPI-Backend
```

#### 2. Ejecutar todo automáticamente

Este proyecto cuenta con un script que construye todos los microservicios y levanta la base de datos y los contenedores:

```bash
./build-all.sh
docker-compose up --build
```

Este comando:

✅ Compila todos los microservicios (`admin`, `vehiculos`, `reportes`, `gateway`)  
✅ Construye las imágenes Docker  
✅ Levanta el contenedor de PostgreSQL  
✅ Inicia todos los servicios juntos

> 💡 Si usás Windows, asegurate de tener Git Bash o WSL para ejecutar el `.sh`. Si no, podés correr los comandos manualmente desde el archivo.


#### 3. Resetear el entorno desde cero (base limpia)

Si querés forzar que se ejecute de nuevo el init.sql (por ejemplo, para reiniciar la base), usá:

```bash
docker-compose down -v
./build-all.sh
docker-compose up --build
```

Esto borra los volúmenes (incluida la base de datos) y vuelve a levantar todo desde cero.

### Acceso a los servicios

Una vez levantado el sistema, podés probar los endpoints desde el **API Gateway** (localhost:8080):

| Método | Endpoint                | Descripción                    |
|--------|-------------------------|--------------------------------|
| GET    | `/admin/hello`          | Test de disponibilidad         |
| GET    | `/vehiculos/hello`      | Test de disponibilidad         |
| GE     | `/reportes/hello`       | Test de disponibilidad         |
| GET    | `/admin/empleados`      | Listado de empleados           |
| POST   | `/admin/empleados`      | Alta de empleado               |
| GET    | `/admin/interesados`    | Listado de interesados         |
| POST   | `/admin/interesados`    | Alta de interesado             |
| GET    | `/admin/pruebas`        | Listado de pruebas             |
| POST   | `/admin/pruebas`        | Alta de prueba                 |
| GET    | `/admin/notificaciones` | Listado de notificaciones (OK) |
| POST   | `/admin/notificaciones` | Alta de notificación           |



## 📁 Estructura del proyecto

```
TPI-Backend/
│
├── db/                    # Dump SQL de la base PostgreSQL
├── docker-compose.yml     # Levanta el contenedor de PostgreSQL
├── build-all.sh           # Script para compilar y levantar todo automáticamente
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
