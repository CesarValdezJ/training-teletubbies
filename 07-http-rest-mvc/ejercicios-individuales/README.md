# Ejercicio individual (15 min)

**Diseñar el contrato de 3 endpoints. Sin escribir código.**

Dominio: un sistema de **tickets de soporte**. Un `Ticket` tiene `id`, `title`,
`description`, `status` (`OPEN`, `CLOSED`) y `createdAt`.

Copia [`plantilla.md`](plantilla.md) y complétala con los 3 endpoints. Guía de referencia
(no abrir antes de terminar): [`SOLUCION.md`](SOLUCION.md).

## Qué diseñar

Para cada una de estas 3 acciones, **tú decides el método HTTP y la ruta** — no se dan aquí a
propósito — y completas el resto del contrato: path params, query params, request body,
respuesta de éxito (código + forma del JSON) y **todos** los errores esperados con su código.

1. **Listar tickets.** Decide qué query params tiene sentido (filtrar por `status`, paginar,
   etc.) y qué trae la respuesta.
2. **Crear un ticket nuevo.** Decide qué campos son requeridos en el body, con qué `status`
   nace un ticket, y qué código de éxito corresponde (no es `200`).
3. **Cerrar un ticket** (cambiar su `status` a `CLOSED`). Decide qué pasa si el ticket no
   existe, y qué pasa si ya estaba cerrado (¿es un error o no hace nada?).

## Qué entregar

1. Los 3 contratos completos en tu copia de `plantilla.md`.
2. Un diagrama de recorrido (como el de la práctica guiada) para **uno** de los tres
   endpoints — el que tenga más lógica de negocio te conviene más para esto.
3. Una nota corta (2-3 líneas) explicando una decisión de diseño que tuviste que tomar y por
   qué (ej. "decidí que cerrar un ticket ya cerrado regresa 200 sin cambiar nada, en vez de
   error, porque...").

## Rúbrica

- Los 3 endpoints usan el método HTTP y la ruta correctos para lo que hacen (no todo es
  `GET` o `POST`; `{id}` va como path param, no como query param).
- Cada contrato lista **al menos un error** además del camino feliz.
- El código de éxito de crear un ticket es `201`, no `200`.
- El diagrama de recorrido tiene las 4 capas (Cliente, Controller, Service,
  Repository/Model) y dice qué hace cada una para ese endpoint específico.
- La nota de diseño explica el **porqué**, no solo repite el contrato.

## Tarea

El ejercicio de casa está en [`../tarea/`](../tarea/README.md): diseñar una API pequeña
completa (4-5 endpoints) para un dominio nuevo.
