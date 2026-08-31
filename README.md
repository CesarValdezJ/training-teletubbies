# Teletubbies — Formación de practicantes iWA · Generación 2026

Repositorio de trabajo del programa de formación de practicantes iWA.
Aquí se irán agregando
los ejemplos, prácticas guiadas y ejercicios de cada sesión conforme se imparten.


## Estructura del repo

Cada bloque temático vive en su propia carpeta numerada, en el orden en que se imparte:

```
NN-Nombre-Del-Bloque/
├── teoria/                    # material de referencia de la sesión de teoría
├── practica-guiada/           # el ejercicio que se resuelve en vivo, guiado
├── ejemplos/                  # ejemplos progresivos que ilustran el concepto
└── ejercicios-individuales/   # ejercicios para resolver por cuenta propia (miércoles)
```

No todas las carpetas aparecen en todos los bloques — depende de lo que la sesión requiera.
Cada una trae su propio `README.md` con el detalle de esa sesión (qué se cubre, cómo compilar
y correr, y la justificación de diseño esperada).

## Temario

Solo las sesiones remotas de **lunes, martes y jueves** entran a este repo — 3 por semana,
numeradas en el orden en que se imparten. La sesión inicial y las sesiones de los viernes
(presenciales en oficina, formato "IA + problemas") son aparte y no generan contenido aquí.
Se marca con carpeta el bloque que ya tiene material subido.

### Semana 1 · Java base

| # | Tema |
|---|---|
| 01 | JDK, JRE, JVM, compilación, tipos y control de flujo ([`01-clase-jdk-jre-jvm`](01-clase-jdk-jre-jvm)) |
| 02 | POO I: clases, objetos, encapsulación y constructores |
| 03 | POO II: composición, interfaces y diseño de objetos ([`03-POO-II`](03-POO-II)) |

### Semana 2 · Java base

| # | Tema |
|---|---|
| 04 | Herencia, polimorfismo y composición: cuándo usar cada uno |
| 05 | Colecciones, genéricos, equals y hashCode |
| 06 | Excepciones, depuración, código limpio y pruebas JUnit |

### Semana 3 · Spring

| # | Tema |
|---|---|
| 07 | HTTP, REST, MVC y separación de capas |
| 08 | Maven, estructura Spring Boot e inyección de dependencias |
| 09 | API REST en memoria: controladores, servicios y validación |

### Semana 4 · SQL y datos

| # | Tema |
|---|---|
| 10 | Diseño relacional: entidades, llaves, normalización e índices |
| 11 | SQL: DDL, consultas, joins, agregaciones y transacciones |
| 12 | Laboratorio PostgreSQL: migraciones, restricciones y análisis de consultas |

### Semana 5 · JPA

| # | Tema |
|---|---|
| 13 | ORM, ciclo de vida de entidades y costos de abstracción |
| 14 | Entidades, repositorios, consultas derivadas, DTOs y transacciones |
| 15 | Relaciones, cascadas, carga diferida y problema N+1 |

### Semana 6 · Backend sólido

| # | Tema |
|---|---|
| 16 | Contratos REST, errores, seguridad básica y OpenAPI |
| 17 | Validación, manejo global de excepciones y pruebas Spring |
| 18 | Paginación, filtros, consultas y cierre del backend |

### Semana 7 · Frontend

| # | Tema |
|---|---|
| 19 | Contrato frontend-backend, estados y manejo de errores |
| 20 | TypeScript, componentes, servicios y comunicación |
| 21 | Angular: rutas, formularios, REST y depuración extremo a extremo |

### Semana 8 · QA y DevOps

| # | Tema |
|---|---|
| 22 | Estrategia de pruebas, pirámide, riesgos y criterios de aceptación |
| 23 | Casos de prueba, defectos y evidencia para PR |
| 24 | Docker, secretos y CI/CD con GitHub Actions |

### Semana 9 · IA y MVP

| # | Tema |
|---|---|
| 25 | Patrones de IA, privacidad, seguridad, agentes/RAG y límites |
| 26 | Alcance del MVP, riesgos y plan de cierre |
| 27 | Refactorización, revisión de PR y deuda técnica |

### Semana 10 · Cierre

| # | Tema |
|---|---|
| 28 | Demo, storytelling técnico y comunicación operativa |
| 29 | Seguridad, desempeño y checklist de salida |
| 30 | Revisión final de arquitectura, código y uso de IA |
