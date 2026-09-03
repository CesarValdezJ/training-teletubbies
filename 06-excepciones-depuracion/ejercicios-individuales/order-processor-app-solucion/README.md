# order-processor-app-solucion

Solución de referencia del [ejercicio individual](../README.md). **No compartir con los
practicantes antes de la sesión.**

```bash
cd order-processor-app-solucion
./mvnw test                  # 6 pruebas en verde
./mvnw compile exec:java     # corre Main
```

## Los tres defectos y su arreglo

| # | Método | Defecto original | Arreglo |
|---|---|---|---|
| 1 | `calculateSubtotal()` | `Integer.parseInt()` lanzaba `NumberFormatException` sin manejo con cantidad no numérica | Se envuelve en `InvalidQuantityException` (excepción propia) **conservando la causa** (`e`) |
| 2 | `calculateTotal()` | `subtotal + (subtotal * RATE)` — **sumaba** el descuento | `subtotal - (subtotal * VOLUME_DISCOUNT_RATE)` |
| 3 | `buildSummary()` | El `for` de la lista de productos iba `for (int i = 1; i <= lines.size(); i++)`: saltaba la primera línea y leía un índice más allá del final → `IndexOutOfBoundsException` (crashea hasta con una sola línea) | `for (OrderLine line : lines)` — sin índices, sin off-by-one |

## Pruebas

`OrderProcessorTest` tiene 6: una por defecto (`calculateSubtotal_throwsInvalidQuantityOnNonNumericText`,
`calculateTotal_appliesVolumeDiscountAboveThreshold`, `buildSummary_listsEveryLineIncludingFirstAndLast`) más
casos límite (sin descuento bajo el umbral, suma simple, pedido de una sola línea). Patrón AAA;
la del defecto 1 usa `assertThrows` y además verifica el mensaje y la causa.
