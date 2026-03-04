-- ============================================
-- Script para asociar tecnologías con capacidades
-- Base de datos: reactive_backend
-- Schema: ms_technology
-- Tabla: capacity_technology
-- ============================================

-- Conectarse a la base de datos reactive_backend
\c reactive_backend;

-- Establecer el schema
SET search_path TO ms_technology;

-- Limpiar relaciones existentes (opcional)
-- TRUNCATE TABLE ms_technology.capacity_technology RESTART IDENTITY CASCADE;

-- ============================================
-- Asociar tecnologías con capacidades
-- ============================================

-- CAPACITY 1: Backend Development (ID: 1)
-- Tecnologías: Java, Spring Boot, Python, Django, Node.js, PostgreSQL, MongoDB, REST API, Microservices
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'Java')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'Spring Boot')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'Python')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'Django')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'Node.js')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'PostgreSQL')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'MongoDB')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'REST API')),
(1, (SELECT id FROM ms_technology.technologies WHERE name = 'Microservices'));

-- CAPACITY 2: Frontend Development (ID: 2)
-- Tecnologías: JavaScript, React, Angular, TypeScript
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(2, (SELECT id FROM ms_technology.technologies WHERE name = 'JavaScript')),
(2, (SELECT id FROM ms_technology.technologies WHERE name = 'React')),
(2, (SELECT id FROM ms_technology.technologies WHERE name = 'Angular')),
(2, (SELECT id FROM ms_technology.technologies WHERE name = 'TypeScript'));

-- CAPACITY 3: DevOps (ID: 3)
-- Tecnologías: Docker, Kubernetes, Git, AWS
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(3, (SELECT id FROM ms_technology.technologies WHERE name = 'Docker')),
(3, (SELECT id FROM ms_technology.technologies WHERE name = 'Kubernetes')),
(3, (SELECT id FROM ms_technology.technologies WHERE name = 'Git')),
(3, (SELECT id FROM ms_technology.technologies WHERE name = 'AWS'));

-- CAPACITY 4: Database Management (ID: 4)
-- Tecnologías: PostgreSQL, MongoDB, MySQL, Redis
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(4, (SELECT id FROM ms_technology.technologies WHERE name = 'PostgreSQL')),
(4, (SELECT id FROM ms_technology.technologies WHERE name = 'MongoDB')),
(4, (SELECT id FROM ms_technology.technologies WHERE name = 'MySQL')),
(4, (SELECT id FROM ms_technology.technologies WHERE name = 'Redis'));

-- CAPACITY 5: Cloud Computing (ID: 5)
-- Tecnologías: AWS, Docker, Kubernetes
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(5, (SELECT id FROM ms_technology.technologies WHERE name = 'AWS')),
(5, (SELECT id FROM ms_technology.technologies WHERE name = 'Docker')),
(5, (SELECT id FROM ms_technology.technologies WHERE name = 'Kubernetes'));

-- CAPACITY 6: Mobile Development (ID: 6)
-- Tecnologías: JavaScript, React, TypeScript
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(6, (SELECT id FROM ms_technology.technologies WHERE name = 'JavaScript')),
(6, (SELECT id FROM ms_technology.technologies WHERE name = 'React')),
(6, (SELECT id FROM ms_technology.technologies WHERE name = 'TypeScript'));

-- CAPACITY 7: Full Stack Development (ID: 7)
-- Tecnologías: Java, Spring Boot, JavaScript, React, PostgreSQL, MongoDB, Docker, Git
INSERT INTO ms_technology.capacity_technology (capacity_id, technology_id) VALUES
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'Java')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'Spring Boot')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'JavaScript')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'React')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'PostgreSQL')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'MongoDB')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'Docker')),
(7, (SELECT id FROM ms_technology.technologies WHERE name = 'Git'));

-- ============================================
-- Verificar las relaciones creadas
-- ============================================

-- Ver todas las relaciones
SELECT 
    ct.id,
    ct.capacity_id,
    ct.technology_id,
    t.name as technology_name
FROM ms_technology.capacity_technology ct
JOIN ms_technology.technologies t ON ct.technology_id = t.id
ORDER BY ct.capacity_id, t.name;

-- Contar tecnologías por capacidad
SELECT 
    capacity_id,
    COUNT(*) as technology_count
FROM ms_technology.capacity_technology
GROUP BY capacity_id
ORDER BY capacity_id;

-- ============================================
-- Actualizar el contador de tecnologías en ms_capacity
-- ============================================

-- Cambiar al schema de ms_capacity
SET search_path TO ms_capacity;

-- Actualizar technology_count basado en las relaciones
UPDATE ms_capacity.capacities c
SET technology_count = (
    SELECT COUNT(*)
    FROM ms_technology.capacity_technology ct
    WHERE ct.capacity_id = c.id
);

-- Verificar la actualización
SELECT id, name, technology_count 
FROM ms_capacity.capacities 
ORDER BY id;
