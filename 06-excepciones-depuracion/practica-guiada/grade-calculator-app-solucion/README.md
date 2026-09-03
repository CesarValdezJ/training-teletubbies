# grade-calculator-app-solucion

Versión de [`grade-calculator-app`](../grade-calculator-app) con los **tres defectos
corregidos** y las **cuatro pruebas** finales. Se muestra al lado de la versión con defectos
para comparar; no se codifica en vivo.

```bash
cd grade-calculator-app-solucion
./mvnw test                  # 4 pruebas en verde
./mvnw compile exec:java     # Class average: 7.0 / Students passing: 2 / Expected: 'Mara' has no grades...
```

## Los tres defectos y su arreglo

| # | Método | Defecto original | Arreglo |
|---|---|---|---|
| 1 | `averageOf()` | recorría `getGrades()` sin proteger contra `null` → `NullPointerException` | `Student` normaliza `null` a lista vacía en el constructor; `averageOf()` lanza `NoGradesException` si el estudiante no tiene notas (guard clause) |
| 2 | `averageOf()` | dividía entre `getGrades().size()` → división entre cero (`NaN`) con lista vacía | la misma guard clause del defecto 1 cubre este caso |
| 3 | `countPassing()` | `averageOf(student) > PASSING_GRADE` → quien saca exactamente la nota de paso contaba como reprobado | `>= PASSING_GRADE` |

`averageOfClass()` y `countPassing()` **saltan** a los estudiantes sin notas en vez de
fallar; `averageOfClass()` solo lanza `NoGradesException` si nadie del grupo tiene notas.

## Pruebas

`GradeCalculatorTest` tiene 4, en patrón Arrange–Act–Assert:
`averageOf_returnsMeanOfGrades`, `averageOf_throwsWhenStudentHasNoGrades` (`assertThrows`),
`countPassing_countsStudentExactlyAtPassingGrade` (el caso límite del defecto 3) y
`averageOfClass_ignoresStudentsWithNoGrades`.
