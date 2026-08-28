# Práctica guiada — Parte B: Producto e Inventario

`Producto` e `Inventario` (`con-interfaces/`), extendidos con una `PoliticaCalculo`
intercambiable. Aplica el mismo patrón de `ejemplos/` (interfaz + implementaciones + composición)
sobre un caso con más peso.

(`base/` es solo la versión de arranque que se muestra en vivo durante la sesión, antes de llegar
a esta.)

## Compilar y correr

```bash
cd con-interfaces
javac -d out *.java
java -cp out Main
```

## La solución

Se agrega `PoliticaCalculo` (interfaz) con dos implementaciones (`CalculoSimple`,
`CalculoConDescuento`). **`Inventario` compone una `PoliticaCalculo`** que decide cómo se calcula
`calcularValorTotalInventario()`, y se puede reemplazar en caliente con `setPoliticaCalculo()`
sin tocar `Inventario`.

### Justificación (mismas 3 preguntas que en `ejemplos/`)

1. **¿Por qué composición y no herencia?** `Inventario` no *es una* `PoliticaCalculo`, la *usa*
   para calcular su valor total. Con herencia no podrías cambiar la política a mitad de ejecución
   (como sí se hace en `Main` con `setPoliticaCalculo`).
2. **¿Por qué interfaz y no una clase concreta?** A `Inventario` no le importa cómo se calcula el
   total, solo que le devuelvan un `double` a partir de la lista de productos.
3. **¿Qué se rompe sin esto?** `calcularValorTotalInventario()` tendría un `if (tipo ==
   "descuento") ... else ...` adentro, y cada regla de cálculo nueva obligaría a modificar
   `Inventario`.

Nota: `buscarProductos()` y `listarProductos()` se quedan como métodos concretos de `Inventario`
— no todo tiene que resolverse con una interfaz. La composición se usa donde hay una variación
real de comportamiento (cómo se calcula el valor), no en cada método.
