# 07 · HTTP, REST, MVC y separación de capas

**Objetivo:** marco conceptual para pasar de programas de consola a una aplicación web
mantenible — cómo viaja una petición y cómo se diseña el contrato de un endpoint antes de
programarlo.

**Resultado esperado:** diagrama del recorrido de una petición + contrato de endpoint.

> Sesión **sin código**: se diseña, no se implementa. Programar estos mismos endpoints con
> Spring Boot llega en las sesiones 08 y 09.

## Formato — 15 · 25 · 15 · 5

| Tramo | Minutos | Contenido |
|---|---|---|
| Teoría | 15 | HTTP · REST/API · recorrido de una petición · MVC · separación de capas · contrato de endpoint — [`teoria/`](teoria/http-rest-mvc.html) (slides) |
| Práctica guiada | 25 | Explorar Swagger Petstore y Postman contra una API real, y con eso completar el contrato de una API de biblioteca — [`practica-guiada/`](practica-guiada/README.md) |
| Ejercicio individual | 15 | Diseñar el contrato de 3 endpoints de un sistema de tickets de soporte — [`ejercicios-individuales/`](ejercicios-individuales/README.md) |
| Tarea | 5 | Se explica el ejercicio para casa — [`tarea/`](tarea/README.md) |

## Teoría (15 min)

Sesión de conceptos nuevos — no hay repaso de código previo, entra directo al tema.

1. **HTTP** — protocolo cliente-servidor, sin estado; métodos (`GET`/`POST`/`PUT`/`PATCH`/
   `DELETE`) e idempotencia; códigos de estado por rango (2xx/3xx/4xx/5xx); anatomía de una
   petición (método, ruta, query params, headers, body) y de una respuesta (status, headers,
   body).
2. **REST y API** — una API es el contrato que expone un servidor; REST es un estilo sobre
   HTTP: recursos como sustantivos en la URI, el método dice la acción, sin estado,
   representaciones (JSON en la práctica).
3. **El recorrido de una petición** — Cliente → Controller → Service → Repository/Model, y de
   regreso en sentido inverso. Cada capa solo conoce a la de al lado.
4. **MVC y separación de capas** — qué es cada letra del patrón y cómo se adapta a una API sin
   pantallas; por qué separar en capas (una responsabilidad por capa, se puede probar Service
   sin servidor ni base de datos real, cambiar de almacenamiento no debería tocar la lógica).
5. **Contrato de endpoint** — la plantilla que se usa toda la sesión: método y ruta, path
   params, query params, request body, respuesta de éxito, errores esperados.

## Plantilla de contrato de endpoint

La misma tabla se usa en la práctica guiada, el ejercicio individual y la tarea:

| Campo | Descripción |
|---|---|
| **Método y ruta** | Verbo HTTP + path, con placeholders entre llaves: `GET /books/{id}` |
| **Path params** | Nombre, tipo, si es requerido, qué identifica |
| **Query params** | Nombre, tipo, opcional/requerido, valor por defecto — filtros, paginación, orden |
| **Request body** | Forma del JSON de entrada (campos y tipos), o "—" si no aplica |
| **Respuesta éxito** | Código HTTP + forma del JSON de salida |
| **Errores** | Cada código de error posible + la condición que lo dispara |

Ejemplo completo en la [teoría, diapositiva 11](teoria/http-rest-mvc.html) (`GET /books/{id}`).

## Herramientas de la práctica guiada

No se abre un IDE ni se escribe código, pero sí se manda tráfico HTTP real:

- **[Swagger Petstore](https://petstore.swagger.io/)** — API de demo pública, sin instalar
  nada. Sirve para ver un contrato de API documentado y en funcionamiento.
- **[Postman](https://www.postman.com/downloads/)** contra **[reqres.in](https://reqres.in/)**
  — API pública gratuita para practicar. Colección lista para importar en
  [`practica-guiada/reqres-users-demo.postman_collection.json`](practica-guiada/reqres-users-demo.postman_collection.json).

Maven, Spring Boot y la implementación real de estos endpoints empiezan en la sesión 08.
