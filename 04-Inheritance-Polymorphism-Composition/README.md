# 04 · Herencia, polimorfismo y composición: cuándo usar cada uno

Tres ejemplos progresivos, todos con código real ejecutable, que responden la pregunta del
tema: ¿cuándo conviene heredar y cuándo conviene componer?

## Cómo compilar y correr

Cada carpeta se compila y ejecuta igual:

```bash
cd ejemplos/01-employees-real-inheritance
javac -d out *.java
java -cp out Main
```

(Cambiar `01-employees-real-inheritance` por `02-stack-misused-inheritance` o
`03-stack-composition-done-right` según el ejemplo.)

---

## 01 — Empleados: herencia bien usada

`Gerente` y `Vendedor` heredan de `Empleado` (clase abstracta) y sobreescriben `calcularPago()`.

Aquí la relación **es-un** es real: un `Gerente` *es un* `Empleado`, comparte su identidad
(nombre, salario base) y solo cambia cómo calcula su pago. `Main` guarda ambos en una misma
`List<Empleado>` y llama `calcularPago()` sin saber la subclase concreta — **polimorfismo**
sobre una jerarquía de herencia legítima.

**Cuándo usar herencia:** cuando la subclase es genuinamente un caso particular de la superclase,
y ese "ser" se mantiene fijo durante toda la vida del objeto (un `Gerente` nunca deja de ser
`Empleado`).

## 02 — Stack mal hecho: herencia como atajo (antipatrón)

`StackMala extends ArrayList<Integer>` para "heredar gratis" el almacenamiento, y le agrega
`push()`/`pop()`.

El problema: heredar de `ArrayList` no solo trae el almacenamiento, trae **todos** sus métodos
públicos (`add(index, valor)`, `remove(index)`, `sort()`, etc.), y ninguno respeta la regla de
un stack (solo se entra y sale por un extremo). `Main` lo demuestra llamando `add(0, 99)` —
insertando por debajo, sin pasar por `push()` — y "rompiendo" el stack sin que el compilador se
queje. Este es el ejemplo clásico de *Effective Java* (Bloch) sobre por qué `Stack extends
Vector` fue un error de diseño en el propio JDK.

**Por qué está mal:** un `StackMala` no *es un* `ArrayList` en el sentido correcto — es una
estructura con reglas más estrictas que un `ArrayList`, y la herencia no puede *quitar*
comportamiento, solo agregarlo.

## 03 — Stack bien hecho: composición como arreglo

`Stack` tiene un `List<Integer> datos` **privado**, en vez de heredar de `ArrayList`. Solo expone
`push()`, `pop()` y `estaVacia()`.

Ahora es imposible romper el invariante desde afuera: `stack.add(0, 99)` ni siquiera compila,
porque `add()` no existe en la API pública de `Stack`. La clase decide exactamente qué operaciones
permite, delegando el almacenamiento sin exponerlo.

**Cuándo usar composición:** cuando lo que quieres es *reutilizar una implementación* (no
declarar un tipo), o cuando la clase necesita reglas/invariantes que la clase reutilizada no
respeta por sí sola.

---

## El criterio para decidir

1. **¿La relación es "es-un" y se mantiene toda la vida del objeto?** → herencia (ejemplo 01).
2. **¿Solo quieres reutilizar código/datos, sin querer heredar TODA la API pública de la otra
   clase?** → composición (ejemplo 03), aunque el "atajo" con herencia parezca más corto
   (ejemplo 02).
3. **¿El polimorfismo que necesitas es "mismo método, comportamiento distinto según el tipo
   real"?** Eso se obtiene con herencia + `@Override` (ejemplo 01) *o* con una interfaz +
   composición (ver [`03-POO-II`](../03-POO-II)) — la interfaz es preferible cuando no hay una
   relación "es-un" real entre las implementaciones.

En resumen: *favor composition over inheritance*, pero no como regla absoluta — cuando la
relación "es-un" es genuina y estable, la herencia sigue siendo la herramienta correcta.