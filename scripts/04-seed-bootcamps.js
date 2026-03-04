// ============================================
// Script para poblar bootcamps en ms_bootcamp
// Base de datos: MongoDB - reactive_backend
// Colección: bootcamps
// ============================================

// Conectarse a MongoDB
use reactive_backend;

// Limpiar colección existente (opcional - comentar si no deseas eliminar datos)
// db.bootcamps.deleteMany({});

// ============================================
// Insertar bootcamps de ejemplo
// ============================================

// Bootcamp 1: Full Stack Developer Bootcamp
db.bootcamps.insertOne({
    name: "Full Stack Developer Bootcamp",
    description: "Bootcamp intensivo para convertirse en desarrollador full stack con Java, Spring Boot, React y bases de datos",
    capacitiesCount: 3,
    capacitiesIds: [1, 2, 4]  // Backend Development, Frontend Development, Database Management
});

// Bootcamp 2: DevOps Engineer Bootcamp
db.bootcamps.insertOne({
    name: "DevOps Engineer Bootcamp",
    description: "Aprende las mejores prácticas de DevOps con Docker, Kubernetes, AWS y automatización",
    capacitiesCount: 2,
    capacitiesIds: [3, 5]  // DevOps, Cloud Computing
});

// Bootcamp 3: Backend Specialist Bootcamp
db.bootcamps.insertOne({
    name: "Backend Specialist Bootcamp",
    description: "Especialización en desarrollo backend con microservicios, APIs REST y arquitectura limpia",
    capacitiesCount: 2,
    capacitiesIds: [1, 4]  // Backend Development, Database Management
});

// Bootcamp 4: Frontend Developer Bootcamp
db.bootcamps.insertOne({
    name: "Frontend Developer Bootcamp",
    description: "Domina el desarrollo frontend moderno con React, Angular y TypeScript",
    capacitiesCount: 1,
    capacitiesIds: [2]  // Frontend Development
});

// Bootcamp 5: Cloud Native Developer Bootcamp
db.bootcamps.insertOne({
    name: "Cloud Native Developer Bootcamp",
    description: "Desarrollo de aplicaciones nativas en la nube con contenedores y orquestación",
    capacitiesCount: 3,
    capacitiesIds: [1, 3, 5]  // Backend Development, DevOps, Cloud Computing
});

// Bootcamp 6: Mobile Developer Bootcamp
db.bootcamps.insertOne({
    name: "Mobile Developer Bootcamp",
    description: "Desarrollo de aplicaciones móviles multiplataforma con React Native",
    capacitiesCount: 2,
    capacitiesIds: [2, 6]  // Frontend Development, Mobile Development
});

// Bootcamp 7: Complete Software Engineer Bootcamp
db.bootcamps.insertOne({
    name: "Complete Software Engineer Bootcamp",
    description: "Programa completo que cubre todas las áreas del desarrollo de software moderno",
    capacitiesCount: 4,
    capacitiesIds: [7, 3, 4, 5]  // Full Stack Development, DevOps, Database Management, Cloud Computing
});

// ============================================
// Verificar los bootcamps insertados
// ============================================

print("\n========================================");
print("Bootcamps insertados:");
print("========================================\n");

db.bootcamps.find().forEach(function(bootcamp) {
    print("ID: " + bootcamp._id);
    print("Nombre: " + bootcamp.name);
    print("Descripción: " + bootcamp.description);
    print("Cantidad de capacidades: " + bootcamp.capacitiesCount);
    print("IDs de capacidades: " + bootcamp.capacitiesIds);
    print("----------------------------------------\n");
});

// Mostrar el conteo total
print("Total de bootcamps: " + db.bootcamps.countDocuments());
