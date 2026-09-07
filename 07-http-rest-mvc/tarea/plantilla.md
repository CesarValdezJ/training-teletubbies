# Plantilla — API de catálogo de cursos

Recurso: `Course { id, title, instructor, capacity, enrolledCount }` ·
`Enrollment { id, courseId, studentName }`.

## Contrato — listar cursos

| Campo | Valor                |
|---|----------------------|
| Método y ruta | GET /courses         |
| Path params | —                    |
| Query params | instructor,page,size |
| Request body | —                    |
| Respuesta éxito | 200 OK               |
| Errores | 400 Bad Request      |

## Contrato — obtener curso por id

| Campo | Valor  |
|---|--------|
| Método y ruta | GET /courses/{id} |
| Path params | id     |
| Query params |  —      |
| Request body |  —      |
| Respuesta éxito | 200 OK |
| Errores | 404 Not Found  |

## Contrato — crear curso

| Campo | Valor       |
|---|-------------|
| Método y ruta | POST /courses |
| Path params |   —          |
| Query params |   —          |
| Request body | title, instructor, capacity |
| Respuesta éxito | 201    Created |
| Errores | 400 Bad Request     |

## Contrato — inscribir estudiante en un curso

| Campo | Valor                   |
|---|-------------------------|
| Método y ruta | POST /courses/{id}/enrollments |
| Path params | id                      |
| Query params |    —                     |
| Request body | studentName             |
| Respuesta éxito | 201 Created             |
| Errores | 400 Bad Request, 404 Not Found, 409 Conflict |

## Contrato — eliminar curso

| Campo | Valor        |
|---|--------------|
| Método y ruta | DELETE /courses/{id} |
| Path params | id           |
| Query params |   —           |
| Request body |   —         |
| Respuesta éxito | 204 No Content |
| Errores | 404 Not Found |
 
## Diagrama de recorrido — inscribir estudiante en un curso

```
Cliente   POST /courses/{id}/enrollments
  │
  ▼
Controller     → Recibe id + studentName
  │
  ▼
Service        → Regla de negocio
  │
  ▼
Repository     → Buscar el curso por id
  │
  ▼
Cliente        ← Dar respuesta según el resultado:
                 - 201 Created si se inscribe
                 - 404 Not Found si el curso no existe
                 - 409 Conflict si el curso está lleno
```

## Nota de diseño (3-5 líneas) — regla de "curso lleno"

_Se utiliza `409 Conflict` porque indica un conflicto con el estado actual del recurso, en este caso, la capacidad del curso. No se utiliza `400 Bad Request` porque la solicitud es válida, pero no puede realizarse debido al estado del curso. Tampoco se utiliza `404 Not Found` porque el curso sí existe.
