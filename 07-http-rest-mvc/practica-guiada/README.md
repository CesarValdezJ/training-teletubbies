# Práctica guiada — Ver APIs reales y diseñar la de una biblioteca (25 min)

Antes de dibujar contratos en abstracto, los vemos funcionar. Se hace en vivo, en pantalla
compartida: primero se explora una API real ya documentada (Swagger Petstore), después se le
mandan peticiones reales a otra (Postman + `reqres.in`) y con esos datos observados —no
inventados— se completa el contrato de la API de biblioteca.

Plantilla y solución completa: [`plantilla-contrato.md`](plantilla-contrato.md) ·
[`SOLUCION.md`](SOLUCION.md) (no abrir antes de la sesión).

## Qué tener listo antes de la sesión

- Un navegador — para [Swagger Petstore](https://petstore.swagger.io/), no requiere cuenta
  ni instalación.
- [Postman](https://www.postman.com/downloads/) instalado (o la versión web en
  [web.postman.co](https://web.postman.co) con una cuenta gratuita).
- Importar la colección [`reqres-users-demo.postman_collection.json`](reqres-users-demo.postman_collection.json)
  de esta carpeta — trae las 4 peticiones ya armadas contra `reqres.in`, para no perder
  tiempo tecleando URLs en vivo.

## Flujo de la sesión

### 1. (8 min) Swagger Petstore — un contrato de API real, en vivo

Entrar a **[petstore.swagger.io](https://petstore.swagger.io/)** — es la demo oficial de
Swagger/OpenAPI, corriendo de verdad, sin login. Mostrar primero que su documentación tiene
**la misma forma que la plantilla de contrato** que se vio en teoría: método, ruta,
parámetros, cuerpo, respuestas por código.

Con el grupo, en el navegador:

1. `GET /pet/findByStatus` → `Try it out` → `status = available` → `Execute`. Ver el
   `200` y el arreglo JSON real que regresa — y la "Curl" que Swagger genera, que es
   exactamente la petición que se mandó.
2. `GET /pet/{petId}` con un id que sí existe (uno de los que salieron arriba) → `200` con
   el detalle de esa mascota.
3. `GET /pet/{petId}` con un id inventado (`999999999`) → `404`. Señalar: esto no es teoría,
   así se ve un error real en la fila "Errores" del contrato.
4. `POST /pet` con el body de ejemplo que Swagger ya trae precargado → `Execute` → ver el
   objeto creado de vuelta, con su `id`.

Cierre de este bloque: *"todo lo que acaban de ver — parámetros, body, respuesta, error — es
exactamente lo que van a diseñar ustedes para su propia API, antes de que exista código."*

### 2. (12 min) Postman contra `reqres.in` — llenar el contrato con datos observados

Abrir Postman con la colección importada. `reqres.in` es una API pública gratuita hecha para
practicar — el recurso que expone es `users`, que aquí se usa como *stand-in* de `books`
(misma forma de API, otro nombre de recurso).

Enviar, uno por uno, y leer en pantalla el status, el tiempo de respuesta y el body:

| Request de la colección | Qué se observa |
|---|---|
| `GET /api/users?page=2` | `200` — body con `data: [...]`, `page`, `total` |
| `GET /api/users/2` | `200` — un usuario |
| `GET /api/users/23` | `404` — no existe |
| `POST /api/users` (body `{ name, job }`) | `201 Created` — el objeto de vuelta con `id` e `createdAt` que **el cliente no mandó** |
| `DELETE /api/users/2` | `204 No Content` — sin body |

Con cada respuesta real todavía en pantalla, completar en `plantilla-contrato.md` el contrato
de la biblioteca — traduciendo el patrón observado en `users` a `books`:

- `GET /books` — igual que la lista de `users`: shape `{ items, page, size, total }` y algún
  query param de filtro.
- `POST /books` — igual que crear un `user`: el cliente manda `title`/`author`, el servidor
  regresa el objeto completo con `id` (y en nuestro caso, `available: true`) que el cliente
  **no** controla.
- `DELETE /books/{id}` — igual que borrar un `user`: `204` sin body si existe, `404` si no.

Para cada uno, comentar en voz alta qué capa haría cada cosa (Controller valida forma →
Service decide la regla → Repository guarda) — ya no hace falta dibujarlo en el pizarrón
paso a paso: se señala sobre la petición real que acaba de pasar.

### 3. (5 min) Cierre

Repasar la plantilla ya completa de principio a fin y conectar con lo que sigue: *"cuando en
la sesión 09 escriban el Controller/Service/Repository en Spring, van a estar armando
exactamente esto que acaban de ver pasar en Petstore y en Postman."* Anunciar el ejercicio
individual.

## Qué queda de la sesión

- El contrato completo de `GET /books`, `POST /books` y `DELETE /books/{id}` en
  `plantilla-contrato.md`, basado en patrones vistos funcionando de verdad.

Esto es exactamente lo que se pide, por cuenta propia y con un dominio distinto, en el
[ejercicio individual](../ejercicios-individuales/README.md).
