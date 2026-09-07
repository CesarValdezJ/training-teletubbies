# Tarea — para la próxima sesión

Un ejercicio: diseñar el contrato completo de una API pequeña. Se explica al final de la
sesión (5 min) y se revisa al inicio de la sesión 08. Entrégalo en tu fork / rama, como un
archivo markdown.

Sigue siendo diseño: **no se escribe código**. Copia [`plantilla.md`](plantilla.md) como
punto de partida

---

## Ejercicio — API de catálogo de cursos

Dominio: una plataforma que ofrece cursos y permite inscribirse a ellos.

- `Course { id, title, instructor, capacity, enrolledCount }`
- `Enrollment { id, courseId, studentName }`

### Qué diseñar

Contrato completo (los 6 campos de la plantilla, **incluyendo método y ruta** — no se dan
aquí, son parte de lo que tienes que decidir) para **cada una** de estas 5 acciones:

1. Listar los cursos disponibles, con al menos un filtro y paginación.
2. Obtener el detalle de un curso puntual.
3. Crear un curso nuevo.
4. Inscribir a un estudiante en un curso existente. **Piensa en la regla de negocio**: ¿qué
   pasa si el curso ya está lleno (`enrolledCount == capacity`)?
5. Eliminar un curso.

Más:

6. **Diagrama de recorrido** de la acción de inscribir a un estudiante — es la que tiene más
   lógica de negocio (una regla que puede rechazar la operación), así que es la mejor
   candidata para mostrar las 4 capas con detalle.

### Qué entregar

Un archivo markdown (tu copia de `plantilla.md`) con:

1. Los 5 contratos completos, cada uno con su método y ruta ya definidos por ti.
2. El diagrama de recorrido de la acción de inscribir a un estudiante.
3. Una nota corta (3-5 líneas) explicando cómo decidiste resolver la regla de "curso lleno":
   ¿qué código de error usaste y por qué ese y no otro?

### Rúbrica

- El método HTTP y la ruta de cada endpoint son consistentes con las convenciones REST vistas
  en teoría (recursos como sustantivos, el método dice la acción, `{id}` como path param —no
  como query param ni parte del nombre del recurso).
- Los 5 endpoints usan el método y el código de éxito correctos (crear → `201`, eliminar sin
  body de vuelta → `204`, etc.).
- Inscribir a un estudiante tiene un error explícito para "curso lleno" — no se ignora la
  regla de negocio ni se deja para "ya lo valida el frontend".
- Cada contrato lista sus path params y query params por separado, sin mezclarlos.
- El diagrama de recorrido muestra dónde vive la regla de "curso lleno" (pista: no es en el
  Controller).
- La nota de diseño justifica la elección de código de error, no solo la repite.
