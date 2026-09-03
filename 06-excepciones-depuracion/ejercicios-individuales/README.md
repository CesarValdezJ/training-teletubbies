# Ejercicio individual (25 min)

**Corregir un programa con tres defectos y entregar al menos tres pruebas unitarias.**

Proyecto Maven: [`order-processor-app/`](order-processor-app). `OrderProcessor` calcula el
total de un pedido, aplica un descuento por volumen y arma un resumen de texto. Tiene **tres
defectos plantados**. No se te dice cuáles: encuéntralos.

> Maven se ve a fondo en la sesión 08. Aquí solo lo usamos para traer JUnit y correr las
> pruebas: `./mvnw test` (o el botón *Run tests* del IDE).

## Estructura

```
order-processor-app/
├── pom.xml                         # JUnit 5 + surefire + exec-maven-plugin
├── mvnw / mvnw.cmd                 # Maven wrapper: no hace falta tener mvn instalado
└── src/
    ├── main/java/org/example/      # OrderLine, OrderProcessor (con los 3 defectos), Main
    └── test/java/org/example/      # OrderProcessorTest (arranca con 1 prueba + TODOs)
```

## Compilar y correr

```bash
cd order-processor-app

./mvnw compile exec:java     # corre Main  -> reproduce el primer defecto
./mvnw test                  # corre tus pruebas
```

(En Windows: `mvnw.cmd`. Con `mvn` instalado: `mvn` en vez de `./mvnw`.)

## Qué entregar

1. Los **tres defectos corregidos** con manejo de error **explícito** (excepción propia donde
   aplique — no `catch` vacío, no excepción ignorada).
2. **Al menos tres pruebas JUnit** en `OrderProcessorTest`, una por defecto, en patrón
   **Arrange–Act–Assert**. Al menos una debe usar `assertThrows`.
3. Un comentario corto (arriba de `OrderProcessor` o en un `.txt` aparte) respondiendo:
   - ¿Qué falla reprodujiste primero y cómo la aislaste?
   - ¿Qué contrato definiste para cada caso de error (texto no numérico, pedido vacío,
     recorrer las líneas del pedido)?

## Pista

Corre `Main` y lee el stack trace. El primer defecto es una excepción sin manejar. El segundo
**no lanza nada** pero el número sale mal — compáralo a mano. El tercero también lanza una
excepción; revisa con cuidado los índices del `for` dentro de `buildSummary()`.

## Rúbrica

- `./mvnw test` en verde.
- Los tres defectos corregidos.
- Ningún `catch` vacío ni excepción tragada; el manejo de error es explícito.
- ≥ 3 pruebas que **realmente fallarían** con el defecto presente (la prueba prueba el bug).
- Nombres de prueba que describen el caso; patrón AAA; al menos un `assertThrows`.

## Tarea (5 min, se revisa la próxima sesión)

Agrega **dos pruebas de caso límite** que hoy no estén cubiertas (pedido vacío, cantidad cero,
subtotal justo en el umbral del descuento) y **una excepción propia** con mensaje útil para
uno de esos casos.
