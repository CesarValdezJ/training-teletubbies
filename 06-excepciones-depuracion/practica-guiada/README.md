# Práctica guiada — Calculadora de calificaciones

`GradeCalculator` calcula el promedio de un grupo de estudiantes y cuántos aprueban. Dos
proyectos Maven, para mostrar las dos versiones lado a lado (no se codifica en vivo):

- [`grade-calculator-app/`](grade-calculator-app) — **con los tres defectos plantados**.
- [`grade-calculator-app-solucion/`](grade-calculator-app-solucion) — defectos corregidos +
  las 4 pruebas. No abrir antes de la sesión.

> Maven se ve a fondo en la sesión 08. Por ahora basta con dos comandos (`./mvnw test`,
> `./mvnw compile exec:java`) o los botones de *Run* / *Run tests* del IDE.

## Estructura (ambos proyectos)

```
grade-calculator-app[-solucion]/
├── pom.xml                         # JUnit 5 + surefire + exec-maven-plugin
├── mvnw / mvnw.cmd                 # Maven wrapper: no hace falta tener mvn instalado
└── src/
    ├── main/java/org/example/      # Student, GradeCalculator, Main (+ NoGradesException en la solución)
    └── test/java/org/example/      # GradeCalculatorTest (1 prueba en la base, 4 en la solución)
```

## Compilar y correr

```bash
cd grade-calculator-app              # o grade-calculator-app-solucion

./mvnw compile exec:java     # base: revienta con el primer defecto · solución: corre limpio
./mvnw test                  # base: 1 prueba · solución: 4 pruebas en verde
```

(En Windows: `mvnw.cmd`. Con `mvn` instalado: `mvn` en vez de `./mvnw`.)

## Los tres defectos

| # | Dónde | Síntoma |
|---|---|---|
| 1 | `averageOf()` recorre `getGrades()` sin proteger contra `null` | `NullPointerException` con un estudiante recién inscrito y sin notas |
| 2 | `averageOf()` divide entre `getGrades().size()` | división entre cero → `NaN` / `Infinity` con lista vacía |
| 3 | `countPassing()` usa `average > PASSING_GRADE` | quien saca **exactamente** la nota de paso cuenta como reprobado (debe ser `>=`) |

El defecto 3 **no lanza ninguna excepción** — se descubre escribiendo una prueba del caso límite.

## Flujo de la sesión

1. **`./mvnw compile exec:java`.** Aparece el `NullPointerException`. Leer el stack trace
   juntos: ¿cuál es la primera línea de *nuestro* código? → `GradeCalculator.averageOf`.
2. **Breakpoint** en el `for` de `averageOf`, inspeccionar al estudiante `Mara`:
   `grades == null`. Decidir el **contrato**: un estudiante sin notas no tiene promedio.
   - Solución tomada: normalizar `null` a lista vacía en el constructor de `Student`, y que
     `averageOf` lance `NoGradesException` si no hay notas.
   - `averageOfClass` y `countPassing` **saltan** a los estudiantes sin notas en vez de fallar.
3. **Segunda corrida.** El defecto 2 (división entre cero) ya no ocurre gracias a la guard
   clause del defecto 1.
4. **Defecto 3.** No se ve corriendo el programa. Escribir la prueba
   `countPassing_countsStudentExactlyAtPassingGrade` con un estudiante cuyo promedio es
   exactamente `6.0`. **Rojo.** Cambiar `>` por `>=`. **Verde.**
5. **Cierre.** Abrir `grade-calculator-app-solucion` al lado y repasar las 4 pruebas finales
   de `GradeCalculatorTest` y el patrón AAA en cada una.
