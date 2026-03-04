-- ============================================
-- Script para poblar tecnologías en ms-technology
-- Base de datos: reactive_backend
-- Schema: ms_technology
-- ============================================

-- Conectarse a la base de datos reactive_backend
\c reactive_backend;

-- Establecer el schema
SET search_path TO ms_technology;

-- Limpiar datos existentes (opcional - comentar si no deseas eliminar datos)
-- TRUNCATE TABLE ms_technology.capacity_technology CASCADE;
-- TRUNCATE TABLE ms_technology.technologies RESTART IDENTITY CASCADE;

-- Insertar tecnologías
INSERT INTO ms_technology.technologies (name, description) VALUES
('Java', 'Lenguaje de programación orientado a objetos multiplataforma'),
('Spring Boot', 'Framework para desarrollo de aplicaciones Java empresariales'),
('Python', 'Lenguaje de programación interpretado de alto nivel'),
('Django', 'Framework web de alto nivel para Python'),
('JavaScript', 'Lenguaje de programación para desarrollo web'),
('React', 'Biblioteca de JavaScript para construir interfaces de usuario'),
('Angular', 'Framework de JavaScript para aplicaciones web'),
('Node.js', 'Entorno de ejecución de JavaScript del lado del servidor'),
('PostgreSQL', 'Sistema de gestión de bases de datos relacional'),
('MongoDB', 'Base de datos NoSQL orientada a documentos'),
('Docker', 'Plataforma de contenedores para desarrollo y despliegue'),
('Kubernetes', 'Sistema de orquestación de contenedores'),
('Git', 'Sistema de control de versiones distribuido'),
('AWS', 'Plataforma de servicios en la nube de Amazon'),
('TypeScript', 'Superset de JavaScript con tipado estático'),
('MySQL', 'Sistema de gestión de bases de datos relacional'),
('Redis', 'Base de datos en memoria para caché y mensajería'),
('GraphQL', 'Lenguaje de consulta para APIs'),
('REST API', 'Arquitectura para servicios web'),
('Microservices', 'Patrón de arquitectura de software')
ON CONFLICT (name) DO NOTHING;

-- Verificar las tecnologías insertadas
SELECT id, name, description FROM ms_technology.technologies ORDER BY id;

-- Mostrar el conteo total
SELECT COUNT(*) as total_technologies FROM ms_technology.technologies;
