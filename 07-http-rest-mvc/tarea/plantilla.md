# Plantilla — API de catálogo de cursos

Recurso: `Course { id, title, instructor, capacity, enrolledCount }` ·
`Enrollment { id, courseId, studentName }`.

## Contrato — listar cursos

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Contrato — obtener curso por id

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Contrato — crear curso

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Contrato — inscribir estudiante en un curso

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Contrato — eliminar curso

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Diagrama de recorrido — inscribir estudiante en un curso

```
Cliente
  │
  ▼
Controller     →
  │
  ▼
Service        →
  │
  ▼
Repository     →
  │
  ▼
Cliente        ←
```

## Nota de diseño (3-5 líneas) — regla de "curso lleno"

_¿Qué código de error usaste para rechazar una inscripción cuando el curso ya está lleno, y
por qué ese y no otro?_
