# 06 · Excepciones, depuración, código limpio y pruebas JUnit

**Objetivo:** práctica de diagnóstico y manejo explícito de errores antes de avanzar a
frameworks.

**Resultado esperado:** falla reproducida, diagnosticada y cubierta con pruebas.

> A partir de esta sesión **todo el código y sus comentarios van en inglés**. Los `README` y
> el material de teoría siguen en español.

## Formato — 20 · 40 · 25 · 5

| Tramo | Minutos | Contenido |
|---|---|---|
| Teoría | 20 | Excepciones · depuración · código limpio · JUnit 5 — [`teoria/`](teoria/excepciones-depuracion.html) (slides) |
| Práctica guiada | 40 | Reproducir, diagnosticar y cubrir con pruebas un programa con 3 defectos — [`practica-guiada/`](practica-guiada/README.md) |
| Ejercicio individual | 25 | Corregir otro programa con 3 defectos y entregar ≥ 3 pruebas — [`ejercicios-individuales/`](ejercicios-individuales/README.md) |
| Tarea | 5 | Dos pruebas de caso límite + una excepción propia con mensaje útil |

## Teoría (20 min)

Cuatro bloques de ~5 minutos. Es una sesión de **conectar y formalizar** cosas que ya
aparecieron sueltas en clases anteriores (leer stack traces, depurar a ojo), no de introducir
todo desde cero.

1. **Excepciones** — qué es una excepción (interrumpe el flujo, se lanza y se atrapa);
   checked vs unchecked; `try/catch/finally` vs try-with-resources; envolver vs relanzar;
   no ignorarla en silencio (`catch` vacío); no usar excepciones como control de flujo;
   cuándo crear una excepción propia.
2. **Depuración** — qué es y cuándo se hace (encontrar la causa, no tapar el síntoma);
   método: *reproducir → aislar → hipótesis → verificar*; leer un stack trace (causa raíz
   vs ruido, `Caused by`); breakpoints, step over/into, watch; por qué el debugger le gana
   a `System.out.println`.
3. **Código limpio** — nombres que revelan la intención; funciones pequeñas que hacen una
   sola cosa; no repetirse (DRY); código autoexplicativo (comentar el porqué); pocos
   parámetros; manejo de errores explícito (sin `null`, códigos ni números mágicos).
4. **JUnit 5** — `@Test`, patrón **AAA** (Arrange–Act–Assert), `assertEquals` / `assertTrue`,
   `assertThrows` para verificar excepciones, nombres de prueba que describen el caso, una
   idea por prueba.

## Maven

Los dos ejercicios son **proyectos Maven** (`grade-calculator-app`, `order-processor-app`).
Es la primera vez que aparece Maven en el temario: **se ve a fondo en la sesión 08**. Por
ahora solo hay que saber:

- `pom.xml` declara las dependencias — aquí, **JUnit 5** con `scope test`.
- `./mvnw test` compila y corre las pruebas. `./mvnw compile exec:java` corre la app.
- `mvnw` / `mvnw.cmd` es el *Maven wrapper*: funciona aunque no tengas `mvn` instalado.
- El IDE (IntelliJ) importa el `pom.xml` solo y resuelve JUnit sin configurar nada.

Estructura estándar de cada proyecto:

```
<proyecto>-app/
├── pom.xml
├── mvnw / mvnw.cmd
└── src/
    ├── main/java/org/example/   # código de la aplicación
    └── test/java/org/example/   # clases de prueba (JUnit)
```
