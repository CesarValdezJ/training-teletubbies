# Productos e Impuestos

Ejemplo que modela `Product` (productos) y `Taxes` (impuestos), y simula cómo un
front-end consumiría esos datos.

## Clases

- **Product**: `id` (1-99), `name`, `price` y `stock`.
- **Taxes**: `id` (101-999), `name`, `value` y `productId` (id del producto al que pertenece).
- **FrontEndSimulator**: simula las respuestas que vendrían de un backend/API.
  Tiene dos métodos, `getProducts()` y `getTaxes()`, que arman listas de forma
  manual (con valores hardcodeados) para poder probar el resto del código sin
  necesidad de una base de datos.
  - Devuelve 12 productos y 20 impuestos. Varios productos tienen más de un
    impuesto asociado (por ejemplo VAT + Eco Tax), para poder practicar
    filtrados y agrupaciones por `productId`.
- **SalesCalculator**: clase que recibe la lista de productos y de impuestos
  para calcular el precio final de venta de cada producto.
- **Main**: punto de entrada. Tiene una variable `boolean training` al inicio
  que decide qué bloque de código se ejecuta.

## Modo `training = false`

Corre una serie de ejemplos de **Streams de Java 8 usando solo `List`** (sin
`Map`), pensados para repasar las operaciones más comunes:

1. `map()` — transforma la lista de productos en una lista de solo nombres.
2. `filter()` — se queda con los productos cuyo precio es mayor a 50.
3. `sorted()` — ordena los productos por precio, de menor a mayor.
4. `mapToDouble()` + `average()` — calcula el precio promedio de los productos.
5. `mapToDouble()` + `sum()` — calcula el valor total del inventario (precio x stock).
6. `distinct()` — obtiene los nombres de impuestos sin repetir.
7. `filter()` + `count()` — cuenta cuántos impuestos tienen un valor mayor a 10.

Cada ejemplo imprime en consola qué operación se está usando y el resultado.

## Modo `training = true`

Usa `SalesCalculator` para calcular el precio final de venta de cada producto
a partir de los impuestos que le corresponden, y muestra el resultado en
consola. La lógica de ese cálculo queda encapsulada dentro de la clase.

## Ejecutar

```bash
javac *.java
java Main
```

También hay un `Makefile` para ejecutar cada modo sin tener que editar
manualmente la variable `training` en `Main.java`:

```bash
make training-false   # corre los ejemplos de Streams con Listas
make training-true    # corre el cálculo con SalesCalculator
```

Cada target compila, ejecuta y limpia los `.class` generados, dejando
`Main.java` con su valor original (`training = false`) al terminar.
