# Bootcamp-Reactive-Backend

Sistema de gestión de bootcamps basado en arquitectura de microservicios reactivos con Spring WebFlux. El sistema permite administrar tecnologías, capacidades y bootcamps de forma escalable y no bloqueante.

## Arquitectura del Sistema

El proyecto está compuesto por 3 microservicios independientes que se comunican entre sí mediante APIs REST reactivas:

```
┌─────────────────┐      ┌─────────────────┐      ┌─────────────────┐
│  ms-technology  │◄─────│   ms_capacity   │◄─────│   ms_bootcamp   │
│   (Port 8443)   │      │   (Port 8444)   │      │   (Port 8445)   │
└─────────────────┘      └─────────────────┘      └─────────────────┘
        │                        │                        │
        ▼                        ▼                        ▼
   PostgreSQL              PostgreSQL                 MongoDB
```

---

## 🔧 ms-technology

**Puerto:** 8443  
**Base de datos:** PostgreSQL (Schema: ms_technology)  
**Propósito:** Gestión de tecnologías y su relación con capacidades

### Responsabilidades
- CRUD de tecnologías (Java, Python, React, etc.)
- Asociación de tecnologías con capacidades
- Validación de reglas de negocio para tecnologías
- Consulta de tecnologías por capacidad

### Entidades principales
- `Technology`: Representa una tecnología con nombre y descripción
- `TechnologyCapacity`: Relación entre tecnologías y capacidades
- `TechnologyIdName`: DTO para consultas optimizadas

### Características técnicas
- Arquitectura hexagonal (Clean Architecture)
- Spring WebFlux para programación reactiva
- R2DBC para acceso reactivo a PostgreSQL
- Validaciones de negocio (cantidad de tecnologías, duplicados)

---

## 📚 ms_capacity

**Puerto:** 8444  
**Base de datos:** PostgreSQL (Schema: ms_capacity)  
**Propósito:** Gestión de capacidades técnicas y su relación con tecnologías

### Responsabilidades
- CRUD de capacidades (Backend, Frontend, DevOps, etc.)
- Consulta de capacidades con sus tecnologías asociadas
- Paginación y ordenamiento de capacidades
- Validación de asociaciones con tecnologías
- Comunicación con ms-technology para obtener información de tecnologías

### Entidades principales
- `Capacity`: Representa una capacidad con nombre, descripción y contador de tecnologías
- `CapacityWithTechnologies`: DTO que incluye la capacidad y sus tecnologías
- `TechnologyIdName`: Información básica de tecnologías asociadas

### Características técnicas
- Arquitectura hexagonal (Clean Architecture)
- Spring WebFlux para programación reactiva
- R2DBC para acceso reactivo a PostgreSQL
- WebClient para comunicación reactiva con ms-technology
- CORS configurado para frontend
- Endpoints de health y prometheus para monitoreo

---

## 🎓 ms_bootcamp

**Puerto:** 8445  
**Base de datos:** MongoDB  
**Propósito:** Gestión de bootcamps y su relación con capacidades

### Responsabilidades
- CRUD de bootcamps
- Asociación de bootcamps con múltiples capacidades
- Validación de reglas de negocio (cantidad de capacidades, duplicados)
- Comunicación con ms_capacity para validar capacidades
- Documentación de API con Swagger/OpenAPI

### Entidades principales
- `Bootcamp`: Representa un bootcamp con nombre, descripción y lista de capacidades
- Validaciones de negocio para capacidades asociadas

### Características técnicas
- Arquitectura hexagonal (Clean Architecture)
- Spring WebFlux para programación reactiva
- Spring Data MongoDB Reactive
- WebClient para comunicación reactiva con ms_capacity
- Swagger UI integrado (disponible en `/swagger-ui.html`)
- OpenAPI 3.0 documentation en `/v3/api-docs`

---

## 🏗️ Arquitectura Común

Todos los microservicios implementan **Clean Architecture** con la siguiente estructura:

### Domain Layer
- **Model**: Entidades de dominio, excepciones y reglas de negocio
- **UseCase**: Casos de uso que orquestan la lógica de aplicación

### Infrastructure Layer
- **Entry Points**: Controladores REST reactivos (WebFlux)
- **Driven Adapters**: Implementaciones de persistencia (R2DBC/MongoDB) y clientes HTTP (WebClient)

### Application Layer
- Configuración de beans y dependencias
- Punto de entrada de la aplicación (MainApplication)

---

## 🚀 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring WebFlux** (Programación reactiva)
- **Project Reactor** (Mono/Flux)
- **R2DBC** (PostgreSQL reactivo)
- **Spring Data MongoDB Reactive**
- **Gradle** (Gestión de dependencias)
- **PostgreSQL** (ms-technology, ms_capacity)
- **MongoDB** (ms_bootcamp)

---

## 📋 Requisitos Previos

- Java 17 o superior
- PostgreSQL 12+
- MongoDB 4.4+
- Gradle 7.x o superior

---

## ⚙️ Configuración y Ejecución

### 1. Base de datos PostgreSQL
```sql
CREATE DATABASE reactive_backend;
CREATE SCHEMA ms_technology;
CREATE SCHEMA ms_capacity;
```

### 2. Base de datos MongoDB
```bash
# MongoDB se conectará automáticamente a localhost:27017
# La base de datos 'reactive_backend' se creará automáticamente
```

### 3. Orden de inicio de microservicios

Es importante iniciar los microservicios en el siguiente orden debido a las dependencias:

```bash
# 1. Iniciar ms-technology (no tiene dependencias)
cd ms-technology
./gradlew bootRun

# 2. Iniciar ms_capacity (depende de ms-technology)
cd ms_capacity
./gradlew bootRun

# 3. Iniciar ms_bootcamp (depende de ms_capacity)
cd ms_bootcamp
./gradlew bootRun
```

---

## 🔗 Endpoints Principales

### ms-technology (8443)
- `GET /api/technologies` - Listar tecnologías
- `POST /api/technologies` - Crear tecnología
- `GET /api/technologies/{id}` - Obtener tecnología
- `PUT /api/technologies/{id}` - Actualizar tecnología
- `DELETE /api/technologies/{id}` - Eliminar tecnología

### ms_capacity (8444)
- `GET /api/capacities` - Listar capacidades (con paginación)
- `POST /api/capacities` - Crear capacidad
- `GET /api/capacities/{id}` - Obtener capacidad
- `PUT /api/capacities/{id}` - Actualizar capacidad
- `DELETE /api/capacities/{id}` - Eliminar capacidad
- `GET /api/capacities/with-technologies` - Capacidades con tecnologías

### ms_bootcamp (8445)
- `GET /api/bootcamps` - Listar bootcamps
- `POST /api/bootcamps` - Crear bootcamp
- `GET /api/bootcamps/{id}` - Obtener bootcamp
- `PUT /api/bootcamps/{id}` - Actualizar bootcamp
- `DELETE /api/bootcamps/{id}` - Eliminar bootcamp
- `GET /swagger-ui.html` - Documentación Swagger

---

## 📊 Monitoreo

### Health Checks
- ms-technology: `http://localhost:8443/api/info/health`
- ms_capacity: `http://localhost:8444/actuator/health`
- ms_bootcamp: `http://localhost:8445/actuator/health`

### Métricas
- ms_capacity expone métricas de Prometheus en `/actuator/prometheus`

---

## 🧪 Testing

Cada microservicio incluye tests unitarios y de integración:

```bash
# Ejecutar tests de un microservicio
cd ms-technology
./gradlew test

cd ms_capacity
./gradlew test

cd ms_bootcamp
./gradlew test
```

---

## 📝 Notas Adicionales

- Todos los microservicios utilizan programación reactiva (no bloqueante)
- La comunicación entre microservicios es asíncrona mediante WebClient
- Se implementan validaciones de negocio en cada capa de dominio
- CORS está configurado para permitir peticiones desde `localhost:4200` y `localhost:8080`
- Los logs están configurados en modo DEBUG para desarrollo

---

## 🤝 Contribución

Para contribuir al proyecto, por favor sigue las convenciones de Clean Architecture y asegúrate de que todos los tests pasen antes de crear un pull request.