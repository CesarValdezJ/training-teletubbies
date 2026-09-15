# Plantilla — API de catálogo de cursos

Recurso: `Course { id, title, instructor, capacity, enrolledCount }` ·
`Enrollment { id, courseId, studentName }`.

## Contrato — listar cursos

| Campo | Valor |
|---|---|
| Método y ruta | `GET /courses` |
| Path params | *(ninguno)* |
| Query params | `instructor` (string, opcional) — filtra por instructor.<br>`page` (int, opcional, default `1`).<br>`pageSize` (int, opcional, default `20`, máx `100`). |
| Request body | *(ninguno)* |
| Respuesta éxito | `200 OK` — `{ items: Course[], page, pageSize, totalItems, totalPages }` |
| Errores | `400 Bad Request` — `page` o `pageSize` inválidos (no numéricos, negativos, o fuera de rango). |

## Contrato — obtener curso por id

| Campo | Valor |
|---|---|
| Método y ruta | `GET /courses/{id}` |
| Path params | `id` (string/UUID, requerido) |
| Query params | *(ninguno)* |
| Request body | *(ninguno)* |
| Respuesta éxito | `200 OK` — objeto `Course` completo |
| Errores | `404 Not Found` — no existe un curso con ese `id`. |

## Contrato — crear curso

| Campo | Valor |
|---|---|
| Método y ruta | `POST /courses` |
| Path params | *(ninguno)* |
| Query params | *(ninguno)* |
| Request body | `{ title: string, instructor: string, capacity: number }` — `enrolledCount` no se recibe, el servidor lo inicializa en `0`. |
| Respuesta éxito | `201 Created` (header `Location: /courses/{id}`) — `Course` creado, con `id` y `enrolledCount: 0` |
| Errores | `400 Bad Request` — falta un campo requerido o `capacity` no es un entero positivo. |

## Contrato — inscribir estudiante en un curso

| Campo | Valor |
|---|---|
| Método y ruta | `POST /courses/{id}/enrollments` |
| Path params | `id` (string/UUID, requerido) — curso al que se inscribe el estudiante |
| Query params | *(ninguno)* |
| Request body | `{ studentName: string }` |
| Respuesta éxito | `201 Created` (header `Location: /courses/{id}/enrollments/{enrollmentId}`) — objeto `Enrollment` creado |
| Errores | `404 Not Found` — el curso `{id}` no existe.<br>`400 Bad Request` — `studentName` ausente o vacío.<br>`409 Conflict` — el curso ya está lleno (`enrolledCount == capacity`). |

## Contrato — eliminar curso

| Campo | Valor |
|---|---|
| Método y ruta | `DELETE /courses/{id}` |
| Path params | `id` (string/UUID, requerido) |
| Query params | *(ninguno)* |
| Request body | *(ninguno)* |
| Respuesta éxito | `204 No Content` — sin body de vuelta |
| Errores | `404 Not Found` — no existe un curso con ese `id`. |

## Diagrama de recorrido — inscribir estudiante en un curso

```
Cliente
  │  POST /courses/{id}/enrollments { studentName }
  ▼
Controller     → valida forma del body (¿vino studentName?)
  │               si falta → 400 Bad Request, corta aquí
  ▼
Service        → enrollStudent(courseId, studentName)
  │               1) pide el curso al Repository
  │               2) si no existe → 404 Not Found
  │               3) compara enrolledCount == capacity
  │                  ← AQUÍ vive la regla de "curso lleno", no en el Controller
  │               4) si está lleno → 409 Conflict, corta aquí
  ▼
Repository     → si hay cupo: INSERT enrollment + UPDATE course.enrolledCount += 1
  │
  ▼
Cliente        ← 201 Created (Enrollment) | 400 | 404 | 409
```

## Nota de diseño (3-5 líneas) — regla de "curso lleno"

Usé `409 Conflict`. El request está bien formado (`studentName` válido), así que
no es un error del cliente al armar la petición — es un choque entre la acción
pedida y el estado actual del recurso (`enrolledCount == capacity`), que es justo
lo que `409` representa. Descarté `422` porque ese código es para errores
semánticos del propio body, no para un conflicto con el estado del servidor; y
descarté `400` porque el mismo request, repetido después si alguien se da de
baja, sí podría tener éxito — eso es la firma de un `409`, no de un `400`.
