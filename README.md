# 🚗 TPI Backend - Sistema de Gestión de Pruebas de Manejo

Sistema de backend desarrollado como **Trabajo Práctico Integrador** de la materia **Backend de Aplicaciones** en la **Universidad Tecnológica Nacional - Facultad Regional Córdoba (UTN FRC)**.

## 🏗️ Arquitectura del Sistema

El proyecto implementa una **arquitectura de microservicios** con los siguientes componentes:

### 🔧 Microservicios Backend

| Servicio | Puerto | Descripción | Tecnologías |
|----------|--------|-------------|-------------|
| `tpi-admin-service` | 8081 | Gestión de empleados, interesados, pruebas y zonas peligrosas | Spring Boot 3.3.5, JPA, PostgreSQL |
| `tpi-vehiculos-service` | 8082 | Administración de vehículos y sus relaciones | Spring Boot 3.3.5, JPA, PostgreSQL |
| `tpi-reportes-service` | 8083 | Generación de reportes y estadísticas | Spring Boot 3.3.5, JPA, PostgreSQL |
| `tpi-pruebas-service` | 8085 | Gestión específica de pruebas de manejo | Spring Boot 3.3.5, JPA, PostgreSQL |
| `tpi-notificaciones-service` | 8084 | Sistema de notificaciones via Discord webhook | Spring Boot 3.3.5, WebClient |
| `tpi-gateway-service` | 8080 | API Gateway para enrutamiento centralizado | Spring Cloud Gateway |

### 🎨 Frontend

| Componente | Puerto | Descripción | Tecnologías |
|------------|-------|-------------|-------------|
| `tpi-frontend` | 3000 | Interfaz de usuario web | Next.js 15.4.3, React 19.1.0, Tailwind CSS 4 |

### 🗄️ Base de Datos

- **Motor**: PostgreSQL 15
- **Contenedor**: Docker
- **Inicialización**: Script automático en `/docker/db/init.sql`

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 21** - Lenguaje principal
- **Spring Boot 3.3.5** - Framework de desarrollo
- **Spring Cloud Gateway** - API Gateway
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos principal
- **H2 Database** - Base de datos para testing
- **OpenFeign** - Cliente HTTP declarativo
- **SpringDoc OpenAPI** - Documentación de APIs
- **Maven** - Gestión de dependencias

### Frontend
- **Next.js 15.4.3** - Framework React
- **React 19.1.0** - Biblioteca de UI
- **Tailwind CSS 4** - Framework CSS
- **ESLint** - Linting de código

### DevOps
- **Docker** - Contenedores
- **Docker Compose** - Orquestación de servicios
- **Git** - Control de versiones

## 📋 Requisitos Previos

- **Java 21** o superior
- **Maven 3.6+**
- **Docker** y **Docker Compose**
- **Node.js 18+** (para desarrollo frontend)
- **Git**

## 🛠️ Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tuusuario/TPI-Backend.git
cd TPI-Backend
```

### 2. Ejecución Automática (Recomendado)

El proyecto incluye scripts automatizados para facilitar el despliegue:

```bash
# En Linux/macOS
./build-all.sh
docker-compose up --build

# En Windows (PowerShell)
.\build-all.sh
docker-compose up --build
```

### 3. Ejecución Manual

Si prefieres ejecutar los servicios manualmente:

```bash
# Compilar todos los microservicios
mvn clean install -DskipTests

# Levantar la base de datos
docker-compose up postgres -d

# Ejecutar microservicios (en terminales separadas)
cd tpi-admin-service && mvn spring-boot:run
cd tpi-vehiculos-service && mvn spring-boot:run
cd tpi-reportes-service && mvn spring-boot:run
cd tpi-pruebas-service && mvn spring-boot:run
cd tpi-notificaciones-service && mvn spring-boot:run
cd tpi-gateway-service && mvn spring-boot:run

# Ejecutar frontend
cd tpi-frontend && npm install && npm run dev
```

### 4. Resetear el Entorno

Para reiniciar completamente el sistema:

```bash
docker-compose down -v
./build-all.sh
docker-compose up --build
```

## 🌐 Acceso a los Servicios

Una vez ejecutado, los servicios estarán disponibles en:

| Servicio | URL | Descripción |
|----------|-----|-------------|
| **API Gateway** | http://localhost:8080 | Punto de entrada principal |
| **Frontend** | http://localhost:3000 | Interfaz de usuario |
| **Admin Service** | http://localhost:8081 | Microservicio de administración |
| **Vehículos Service** | http://localhost:8082 | Microservicio de vehículos |
| **Reportes Service** | http://localhost:8083 | Microservicio de reportes |
| **Pruebas Service** | http://localhost:8085 | Microservicio de pruebas |
| **Notificaciones Service** | http://localhost:8084 | Microservicio de notificaciones |
| **PostgreSQL** | localhost:5432 | Base de datos |

## 📚 Endpoints Principales

### API Gateway (puerto 8080)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/admin/hello` | Test de disponibilidad |
| GET | `/admin/empleados` | Listado de empleados |
| POST | `/admin/empleados` | Alta de empleado |
| GET | `/admin/interesados` | Listado de interesados |
| POST | `/admin/interesados` | Alta de interesado |
| GET | `/admin/pruebas` | Listado de pruebas |
| POST | `/admin/pruebas` | Alta de prueba |
| GET | `/vehiculos/hello` | Test de disponibilidad |
| GET | `/vehiculos/vehiculos` | Listado de vehículos |
| POST | `/vehiculos/vehiculos` | Alta de vehículo |
| GET | `/reportes/hello` | Test de disponibilidad |
| GET | `/pruebas/hello` | Test de disponibilidad |
| GET | `/notificaciones/hello` | Test de disponibilidad |

### Documentación de APIs

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 📁 Estructura del Proyecto

```
TPI-Backend/
├── 📁 docker/
│   └── 📁 db/
│       └── init.sql                 # Script de inicialización de BD
├── 📁 tpi-admin-service/           # Microservicio de administración
├── 📁 tpi-vehiculos-service/       # Microservicio de vehículos
├── 📁 tpi-reportes-service/        # Microservicio de reportes
├── 📁 tpi-pruebas-service/         # Microservicio de pruebas
├── 📁 tpi-notificaciones-service/  # Microservicio de notificaciones
├── 📁 tpi-gateway-service/         # API Gateway
├── 📁 tpi-frontend/                # Aplicación frontend
├── 📄 docker-compose.yml           # Configuración de contenedores
├── 📄 build-all.sh                 # Script de construcción automática
└── 📄 README.md                    # Este archivo
```

## 🧪 Testing

Cada microservicio incluye tests unitarios y de integración:

```bash
# Ejecutar tests de un microservicio específico
cd tpi-admin-service && mvn test

# Ejecutar todos los tests
mvn test
```

## 🔧 Configuración

### Variables de Entorno

Los servicios utilizan las siguientes configuraciones por defecto:

- **Base de datos**: `localhost:5432`
- **Usuario BD**: `postgres`
- **Contraseña BD**: `postgres`
- **Nombre BD**: `tpi`

### Configuración de Discord (Notificaciones)

Para configurar las notificaciones de Discord, edita el archivo de configuración del servicio de notificaciones con tu webhook URL.

## 🚀 Despliegue en Producción

Para desplegar en producción:

1. Configurar variables de entorno apropiadas
2. Usar una base de datos PostgreSQL externa
3. Configurar un reverse proxy (nginx/apache)
4. Implementar SSL/TLS
5. Configurar monitoreo y logging

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto es parte del trabajo académico de la UTN FRC.

## 👥 Equipo de Desarrollo

- **Nicolás Garay**
- **Mariano Iturriza**
- **Marcos Belli**
- **Francisco López Mora**

---

**Universidad Tecnológica Nacional - Facultad Regional Córdoba**  
**Ingeniería en Sistemas de Información**  
**Backend de Aplicaciones 2025**
