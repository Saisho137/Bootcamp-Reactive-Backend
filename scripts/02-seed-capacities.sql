-- ============================================
-- Script para poblar capacidades en ms_capacity
-- Base de datos: reactive_backend
-- Schema: ms_capacity
-- ============================================

-- Conectarse a la base de datos reactive_backend
\c reactive_backend;

-- Establecer el schema
SET search_path TO ms_capacity;

-- Limpiar datos existentes (opcional - comentar si no deseas eliminar datos)
-- TRUNCATE TABLE ms_capacity.capacities RESTART IDENTITY CASCADE;

-- Insertar capacidades
-- NOTA: technology_count se actualizará después de asociar las tecnologías

INSERT INTO ms_capacity.capacities (name, description, technology_count) VALUES
('Backend Development', 'Desarrollo de aplicaciones del lado del servidor', 0),
('Frontend Development', 'Desarrollo de interfaces de usuario y experiencia', 0),
('DevOps', 'Prácticas de desarrollo y operaciones integradas', 0),
('Database Management', 'Gestión y administración de bases de datos', 0),
('Cloud Computing', 'Computación y servicios en la nube', 0),
('Mobile Development', 'Desarrollo de aplicaciones móviles', 0),
('Full Stack Development', 'Desarrollo completo frontend y backend', 0)
ON CONFLICT (name) DO NOTHING;

-- Verificar las capacidades insertadas
SELECT id, name, description, technology_count FROM ms_capacity.capacities ORDER BY id;

-- Mostrar el conteo total
SELECT COUNT(*) as total_capacities FROM ms_capacity.capacities;

-- ============================================
-- NOTA IMPORTANTE:
-- ============================================
-- Las relaciones entre capacidades y tecnologías se gestionan en ms-technology
-- a través de la tabla ms_technology.capacity_technology
-- 
-- Para asociar tecnologías a capacidades, ejecuta el siguiente script:
-- 03-seed-capacity-technology-relations.sql
