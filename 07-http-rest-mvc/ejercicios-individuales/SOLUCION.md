# Solución de referencia — Sistema de tickets de soporte

> No abrir antes de terminar el ejercicio. Es una referencia, no la única respuesta correcta
> — lo que importa es que cada decisión esté justificada y el contrato sea explícito.

Recurso: `Ticket { id, title, description, status: OPEN|CLOSED, createdAt }`.

## Contrato — `GET /tickets`

| Campo | Valor |
|---|---|
| Método y ruta | `GET /tickets` |
| Path params | — |
| Query params | `status` (string, opcional: `OPEN` \| `CLOSED`) — filtra por estado · `page` (integer, opcional, default `0`) · `size` (integer, opcional, default `20`) |
| Request body | — |
| Respuesta éxito | `200 OK` — `{ "items": [ {id, title, description, status, createdAt}, ... ], "page": 0, "size": 20, "total": 42 }` |
| Errores | `400 Bad Request` — `status` con un valor que no es `OPEN` ni `CLOSED` |

## Contrato — `POST /tickets`

| Campo | Valor |
|---|---|
| Método y ruta | `POST /tickets` |
| Path params | — |
| Query params | — |
| Request body | `{ "title": string (requerido), "description": string (requerido) }` |
| Respuesta éxito | `201 Created` — `{ "id", "title", "description", "status": "OPEN", "createdAt" }` |
| Errores | `400 Bad Request` — falta `title` o `description`, o llegan vacíos |

`status` y `createdAt` **no** se aceptan en el body de entrada — los asigna el servidor
(`status = OPEN`, `createdAt = ahora`). Que el cliente pudiera mandarlos sería dejarle decidir
algo que es responsabilidad del servidor.

## Contrato — `PATCH /tickets/{id}`

| Campo | Valor |
|---|---|
| Método y ruta | `PATCH /tickets/{id}` |
| Path params | `id` (integer, requerido) — identificador del ticket |
| Query params | — |
| Request body | — (esta acción no necesita datos de entrada, solo cambia el estado) |
| Respuesta éxito | `200 OK` — el ticket actualizado: `{ "id", "title", "description", "status": "CLOSED", "createdAt" }` |
| Errores | `404 Not Found` — no existe un ticket con ese `id` |

**Decisión de diseño (ejemplo de nota):** cerrar un ticket que ya está `CLOSED` regresa
`200 OK` sin cambiar nada (es idempotente), en vez de un error — porque el objetivo del
cliente ("que quede cerrado") ya se cumple, y devolver un `409 Conflict` aquí sería castigar
al cliente por una operación que de todas formas no tiene efectos indeseados. Esta no es la
única postura válida: preferir `409 Conflict` explícito también es defendible; lo que importa
es documentar cuál se eligió y por qué.

## Diagrama de recorrido — `PATCH /tickets/{id}`

```
Cliente
  │  PATCH /tickets/17
  ▼
Controller     →  valida que {id} sea un entero; no hay body que validar.
  │
  ▼
Service        →  busca el ticket; si no existe, lanza TicketNotFoundException.
  │                 si existe, cambia status a CLOSED (si ya lo estaba, no hace nada).
  ▼
Repository     →  guarda el cambio y regresa el ticket actualizado.
  │
  ▼  (la respuesta sube por las mismas capas, en sentido inverso)
Cliente        ←  200 OK  { "id": 17, ..., "status": "CLOSED" }
                  — o 404 Not Found si el Service lanzó la excepción
```
