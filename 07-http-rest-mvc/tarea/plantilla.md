# Plantilla — API de catálogo de cursos

Recurso: `Course { id, title, instructor, capacity, enrolledCount }` ·
`Enrollment { id, courseId, studentName }`.

## Contrato — listar cursos

| Campo | Valor |
|---|---|
| Método y ruta | GET /courses|
| Path params |Ninguno |
| Query params |page, size, title |
| Request body |No aplica |
| Respuesta éxito |200 OK — Lista de cursos |
| Errores | 400 Bad Request — Parámetros incorrectos|

## Contrato — obtener curso por id

| Campo | Valor |
|---|---|
| Método y ruta | GET /courses/{id} |
| Path params | {id} |
| Query params | |
| Request body | |
| Respuesta éxito | 200 OK — Detalles del curso |
| Errores | 404 Not Found — Curso no encontrado |

## Contrato — crear curso

| Campo | Valor |
|---|---|
| Método y ruta |POST /courses |
| Path params |Ninguno |
| Query params |Ninguno |
| Request body |{ "title": "Java básico", "instructor": "Juan", "capacity": 30 } |
| Respuesta éxito |201 Created — Curso creado |
| Errores |400 Bad Request — Datos incorrectos |


## Contrato — inscribir estudiante en un curso

| Campo | Valor |
|---|---|
| Método y ruta |POST /courses/{courseId}/enrollments |
| Path params |courseId — ID del curso |
| Query params |Ninguno |
| Request body |{ "studentName": "María" } |
| Respuesta éxito |201 Created — Estudiante inscrito |
| Errores |404 Not Found — Curso no encontrado. 409 Conflict — El curso está lleno. 400 Bad Request — Datos incorrectos |

## Contrato — eliminar curso
| Campo | Valor |
|---|---|
| Método y ruta |DELETE /courses/{id} |
| Path params |id — ID del curso |
| Query params |Ninguno |
| Request body |No aplica |
| Respuesta éxito |204 No Content — Curso eliminado |
| Errores |404 Not Found — Curso no encontrado |


## Diagrama de recorrido — inscribir estudiante en un curso


```
Cliente
  │ POST /courses/{courseId}/enrollments
  ▼
Controller     →
  │
  ▼
Service        →
  │
  ├── Sí ──► 409 Conflict
  ▼No
Repository     →
  │
  ▼
Service 
  │
  ▼
Controller 
  │
  ▼
Cliente  
  │
  └──► 201 Created
```

## Nota de diseño (3-5 líneas) — regla de "curso lleno"

_¿Qué código de error usaste para rechazar una inscripción cuando el curso ya está lleno, y
por qué ese y no otro?_

Elegí el código 409 Conflict cuando el curso ya está lleno.
Lo elegí porque la petición puede estar bien, pero el curso no tiene espacio disponible.
El problema depende del estado actual del curso y no de que los datos enviados estén mal.
Por eso considero que 409 es más adecuado que 400.
