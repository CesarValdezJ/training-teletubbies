# Tarea — para la próxima sesión

Dos ejercicios, uno de cada mitad de la sesión.

Ambos son proyectos Maven con wrapper: `./mvnw test` (o `mvnw.cmd` en Windows), o los
botones de *Run* del IDE. Maven se ve a fondo en la sesión 08 — aquí solo lo usas para
correr las pruebas.

---

## Ejercicio 1 — Cuenta bancaria: diagnosticar y proteger

Carpeta: [`cuenta-bancaria/`](cuenta-bancaria)

`BankAccount` maneja un saldo con `deposit`, `withdraw` y `transferTo`. **Funciona a medias:
tiene tres defectos.** Corre `Main` para verlos:

```
Balance after over-withdrawing 150 from 100: -50.0     <- el retiro debió rechazarse
Balance after depositing -30: 70.0                     <- el depósito negativo debió rechazarse
Source after transferring 200 it does not have: -150.0 <- transferTo esquiva la regla de sobregiro
```

### Qué entregar

1. **Los tres defectos corregidos**, con validación explícita:
   - `deposit` y `withdraw` rechazan montos `<= 0` (`IllegalArgumentException` con mensaje claro).
   - `withdraw` lanza una **excepción propia que tú creas**, `InsufficientFundsException`,
     cuando el monto supera el saldo. El saldo **no cambia** cuando se rechaza la operación.
   - `transferTo` **reutiliza** `withdraw` y `deposit` (no toca `balance` directamente) para
     que la regla de sobregiro se aplique también en la transferencia. Valida que el destino
     no sea `null`.
2. **Al menos 4 pruebas** en `BankAccountTest`, patrón Arrange–Act–Assert:
   - camino feliz de `transferTo` (el dinero se mueve, los dos saldos quedan bien),
   - retiro mayor al saldo → `assertThrows(InsufficientFundsException.class, ...)` y saldo intacto,
   - depósito negativo → `assertThrows(IllegalArgumentException.class, ...)`,
   - `transferTo` con monto mayor al saldo → se rechaza igual que `withdraw`.

### Rúbrica

- `./mvnw test` en verde, ≥ 4 pruebas que fallarían con los defectos presentes.
- `InsufficientFundsException` es una clase propia con mensaje útil (incluye el monto y el saldo).
- Ningún `catch` vacío; el saldo nunca queda inconsistente tras una operación rechazada.
- `transferTo` no duplica la lógica de `withdraw`.

---

## Ejercicio 2 — Password policy: refactorizar sin romper

Carpeta: [`refactor-password/`](refactor-password)

`PasswordPolicy.check(password, username)` decide si una contraseña es aceptable y devuelve
uno de: `TOO_SHORT`, `NO_DIGIT`, `NO_UPPERCASE`, `CONTAINS_USERNAME`, `OK`.

**El código funciona** — `PasswordPolicyTest` tiene 7 pruebas de caracterización en verde que
fijan su comportamiento actual. Pero: `if` anidados de 5 niveles, números mágicos
(`8`), nombres de una letra (`p`, `u`, `r`, `f1`, `f2`, `l`), un `catch (Exception e) {}` vacío,
un método que hace de todo, lógica repetida (dos bucles casi iguales).

### Qué entregar

1. **`PasswordPolicy` refactorizado** aplicando las prácticas de la teoría, **sin cambiar el
   comportamiento**: las 7 pruebas de `PasswordPolicyTest` deben seguir pasando **sin
   tocarlas**. Se espera al menos:
   - `MIN_LENGTH` como constante con nombre; los verdictos como constantes (o un `enum`).
   - Manejo explícito del `null` (nada de apoyarse en un `catch` que se traga el NPE).
   - Guard clauses: validar y `return` temprano en vez de anidar.
   - Métodos privados pequeños con nombre (`isTooShort`, `hasDigit`, `hasUppercase`,
     `containsUsername`) — cada uno hace una sola cosa.
   - Nombres que revelan la intención en parámetros y variables.
2. Un comentario corto arriba de la clase (o un `NOTES.md`) con **3–5 líneas**: qué práctica
   aplicaste dónde ("extraje `hasDigit()` porque el bucle aparecía dos veces", etc.).

### Rúbrica

- `./mvnw test` en verde **sin haber modificado `PasswordPolicyTest`**.
- Sin números mágicos, sin nombres de una letra, sin `catch` vacío.
- `check()` cabe en la pantalla y se lee de corrido (guard clauses, sin anidar).
- La lógica duplicada quedó en un solo lugar.
- Las notas explican las decisiones.
