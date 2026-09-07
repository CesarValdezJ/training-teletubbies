# Plantilla — API de una biblioteca

Recurso: `Book { id, title, author, available }`. Se completa con lo observado en Swagger
Petstore y en las peticiones de Postman contra `reqres.in` — no de memoria.

## Diagrama de recorrido — `POST /books`

Basado en lo que se vio en `POST /api/users`: el servidor regresó un `id` y datos que el
cliente no mandó. Traducido a capas:

```
Cliente
  │  POST /books  { "title": "...", "author": "..." }
  ▼
Controller     →  ¿trae title y author? ______________________________
  │
  ▼
Service        →  regla de negocio: ___________________________________
  │
  ▼
Repository     →  ______________________________________________________
  │
  ▼  (la respuesta sube por las mismas capas, en sentido inverso)
Cliente        ←  código _______  body ________________________________
```

## Contrato — `GET /books`

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Contrato — `POST /books`

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |

## Contrato — `DELETE /books/{id}`

| Campo | Valor |
|---|---|
| Método y ruta | |
| Path params | |
| Query params | |
| Request body | |
| Respuesta éxito | |
| Errores | |
