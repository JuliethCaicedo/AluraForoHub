# 🚀 Foro Hub - Challenge Alura (Back End)

Este proyecto es una **API REST** robusta desarrollada en **Java** con **Spring Boot**. El objetivo es gestionar un foro donde los usuarios pueden interactuar con tópicos de discusión de manera segura y organizada.

> **✅ Proyecto Finalizado:** Cumple con todos los requisitos del desafío de Oracle Next Education (ONE).

## 🛠️ Tecnologías y Frameworks
* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**: Para la persistencia de datos.
* **Spring Security**: Para el control de acceso.
* **Auth0 JWT**: Implementación de tokens para autenticación segura.
* **MySQL**: Base de datos relacional.
* **Flyway**: Control de versiones de la base de datos (Migrations).
* **Maven**: Gestión de dependencias.

## 📌 Funcionalidades (CRUD RESTful)
La API permite gestionar el ciclo de vida completo de los tópicos:
1. **Crear un nuevo tópico**: Registra preguntas con validaciones de negocio.
2. **Mostrar todos los tópicos**: Listado optimizado desde MySQL.
3. **Mostrar un tópico específico**: Consulta detallada por ID.
4. **Actualizar un tópico**: Edición de datos existentes.
5. **Eliminar un tópico**: Remoción de registros del sistema.

## 🛡️ Seguridad y Buenas Prácticas
* **Autenticación JWT**: Solo usuarios autenticados pueden realizar cambios.
* **Validaciones**: Se implementaron reglas para evitar datos nulos o duplicados.
* **Modelo REST**: Uso correcto de métodos HTTP (GET, POST, PUT, DELETE) y códigos de estado.

## ⚙️ Pruebas de la API
Todas las rutas y el servicio de autenticación fueron validados satisfactoriamente utilizando **Insomnia**.

---
**Desarrollado por Julieth Caicedo**
*Alura Foro Hub - Grupo 9*
