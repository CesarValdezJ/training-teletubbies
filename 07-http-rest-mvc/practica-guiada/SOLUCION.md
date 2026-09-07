# Solución — API de una biblioteca

> No abrir antes de la sesión. Referencia para quien guía la práctica.

Recurso: `Book { id, title, author, available }`. Estas decisiones son las que se esperan
salir de lo observado en Swagger Petstore y en las peticiones de Postman contra `reqres.in`
(ver [`README.md`](README.md)) — sirve como respuesta esperada, no como guion a leer en voz
alta.

## Diagrama de recorrido — `POST /books`

```
Cliente
  │  POST /books  { "title": "Rayuela", "author": "Julio Cortázar" }
  ▼
Controller     →  valida que el body traiga "title" y "author" no vacíos.
  │                 si falta uno → corta aquí con 400 Bad Request, no llega al Service.
  ▼
Service        →  regla de negocio: todo libro nuevo entra con available = true,
  │                 sin importar lo que mande el cliente en el body.
  ▼
Repository     →  genera un id nuevo, guarda el libro, lo regresa completo.
  │
  ▼  (la respuesta sube por las mismas capas, en sentido inverso)
Cliente        ←  201 Created  { "id": 501, "title": "Rayuela",
                                  "author": "Julio Cortázar", "available": true }
```

Puntos a resaltar con el grupo:
- La validación de forma (¿trae los campos?) vive en el Controller — es sobre el **shape**
  del HTTP, no sobre la regla de negocio.
- La regla "nace disponible" vive en el Service — es una decisión del **dominio**, no de HTTP.
- El Controller nunca toca la base de datos directamente ni el Service arma la respuesta HTTP.

## Contrato — `GET /books`

| Campo | Valor |
|---|---|
| Método y ruta | `GET /books` |
| Path params | — |
| Query params | `author` (string, opcional) — filtra por autor · `available` (boolean, opcional) — filtra por disponibilidad · `page` (integer, opcional, default `0`) · `size` (integer, opcional, default `20`) |
| Request body | — |
| Respuesta éxito | `200 OK` — `{ "items": [ {id, title, author, available}, ... ], "page": 0, "size": 20, "total": 137 }` |
| Errores | `400 Bad Request` — `page` o `size` con valor negativo |

## Contrato — `POST /books`

| Campo | Valor |
|---|---|
| Método y ruta | `POST /books` |
| Path params | — |
| Query params | — |
| Request body | `{ "title": string (requerido), "author": string (requerido) }` |
| Respuesta éxito | `201 Created` — el libro creado: `{ "id", "title", "author", "available": true }` |
| Errores | `400 Bad Request` — falta `title` o `author`, o llegan vacíos |

## Contrato — `DELETE /books/{id}`

| Campo | Valor |
|---|---|
| Método y ruta | `DELETE /books/{id}` |
| Path params | `id` (integer, requerido) — identificador del libro |
| Query params | — |
| Request body | — |
| Respuesta éxito | `204 No Content` — sin body |
| Errores | `404 Not Found` — no existe un libro con ese `id` · `409 Conflict` — el libro está prestado (`available = false`) y no se puede eliminar |

El último error (`409`) es un buen disparador de discusión: ¿debería la API permitir borrar un
libro que alguien tiene prestado? No hay una única respuesta correcta — lo importante es que
el contrato **decida** explícitamente y lo documente, en vez de dejarlo implícito.
