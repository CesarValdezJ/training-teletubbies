# Ejemplos — POO II: Composición e Interfaces

Cinco ejemplos progresivos. Los primeros cuatro comparten la misma estructura: una **interfaz**
(el contrato), dos o más **implementaciones** (el comportamiento concreto), y una clase que
**compone** una referencia a la interfaz en vez de heredar de una implementación concreta. El
quinto (`05-capacidades`) muestra la otra cara del mismo mecanismo: interfaces como
**capacidades** que una clase suma directamente, sin pasar por delegación.

## Cómo compilar y correr

Cada carpeta se compila y ejecuta igual (los `.class` se generan en `out/<carpeta>`, fuera del código fuente):

```bash
cd 01-figuras
javac -d ../out/01-figuras *.java
java -cp ../out/01-figuras Main
```

(Cambiar `01-figuras` por `02-vehiculos`, `03-personajes`, `04-notificaciones` o
`05-capacidades` según el ejemplo.)

## El criterio de justificación

Para cada ejemplo respondemos siempre las mismas tres preguntas:

1. **¿Por qué composición y no herencia?**
2. **¿Por qué interfaz y no una clase concreta directa?**
3. **¿Qué se rompe si se hace distinto?**

---

## 01 — Jardín y Forma

`Jardin` tiene una `Forma` (`Circulo` o `Rectangulo`).

1. Un `Jardin` no *es una* `Forma`, la *tiene*. Heredar de `Circulo` no tendría sentido semántico
   (¿un jardín "es un" círculo?), y además ataría al jardín a una sola forma para siempre.
2. `Forma` es interfaz porque lo único que le importa a `Jardin` es que le respondan
   `calcularArea()` — no le importa si es un círculo, un rectángulo, o mañana un triángulo.
3. Sin interfaz, `Jardin` tendría que tener un `if (tipo == "circulo") ... else if (...)` — cada
   figura nueva obligaría a modificar `Jardin`.

## 02 — Vehículo y Motor

`Vehiculo` tiene un `Motor` (`MotorGasolina` o `MotorElectrico`).

1. `Vehiculo` *tiene* un `Motor`, no *es* un motor. Con herencia, cambiar de motor en un mismo
   vehículo sería imposible (la clase de un objeto no cambia en tiempo de ejecución).
2. `Motor` es interfaz porque gasolina y eléctrico *generan potencia* de forma completamente
   distinta; `Vehiculo` no necesita saber cómo, solo que puede pedírselo.
3. Sin esto, tendrías una clase `Vehiculo` distinta por cada tipo de motor
   (`VehiculoGasolina`, `VehiculoElectrico`), duplicando toda la lógica de `acelerar()` que no
   depende del motor.

*Nota de diseño:* `Motor.generarPotencia()` no se llama `acelerar()` a propósito — acelerar es
una acción del vehículo, no del motor. Nombrar mal el método de la interfaz (que haga algo que en
realidad le corresponde a quien la usa) es el error más común al empezar con composición.

## 03 — Personaje y Arma

`Personaje` tiene un `Arma` (`Espada` o `Arco`), y puede **cambiarla en tiempo de ejecución**.

1. `Personaje` *tiene* un `Arma`, y esa es la clave del ejemplo: el arma cambia en tiempo de
   ejecución (`cambiarArma`). Eso es imposible con herencia — un objeto no puede cambiar de clase
   a mitad de partida.
2. `Arma` es interfaz porque espada y arco infligen daño de forma distinta, pero el personaje
   trata a cualquier arma igual.
3. Sin composición, tendrías que crear un `Personaje` nuevo cada vez que cambia de arma,
   perdiendo todo su estado (vida, nombre, inventario).

*Nota de diseño:* mismo cuidado que en el ejemplo 02 — `Arma.infligirDano()`, no `atacar()`.
Quien ataca es el personaje; el arma solo inflige el daño.

## 04 — Usuario y Notificador

`Usuario` tiene una **lista** de `Notificador` (`EmailNotificador`, `SmsNotificador`).

1. `Usuario` *tiene* varios `Notificador` a la vez — esto ya no es posible con
   herencia (no se puede heredar de dos canales simultáneamente de forma limpia).
2. `Notificador` es interfaz porque email y SMS envían de forma distinta, pero `Usuario` los
   trata de forma uniforme dentro de un `for`.
3. Sin esto, `notificar()` tendría un bloque de código por cada canal, y agregar push
   notifications obligaría a tocar `Usuario` otra vez.

---

## 05 — Pato y capacidades (Volador / Nadador)

*Este ejemplo es distinto a los cuatro anteriores*: no hay un campo que delega en un componente.
En vez de eso, una clase implementa varias interfaces directamente para sumar **capacidades**
independientes. Es el ejemplo clásico de *Head First Design Patterns* (el pato que vuela y nada).

- `Pato implements Volador, Nadador` — vuela y nada.
- `PatoDeGoma implements Nadador` — nada, pero no vuela.
- `Avion implements Volador` — vuela, pero no tiene ninguna relación con `Pato`.

1. **¿Por qué interfaces y no herencia?** Volar y nadar son capacidades independientes entre sí,
   y ni siquiera pertenecen a la misma familia de objetos: un `Avion` no tiene nada que ver con un
   `Pato`. Java además no permite heredar de dos clases a la vez, así que con herencia esto sería
   imposible de modelar; con interfaces, cada clase suma solo las capacidades que le corresponden.
2. **¿Por qué interfaz y no una clase concreta?** Porque el código que usa estos objetos
   (`List<Volador>`, `List<Nadador>` en `Main`) no quiere saber si es un pato, un avión o un pato
   de goma — solo le importa si *puede volar* o *puede nadar*.
3. **¿Qué se rompe sin esto?** Sin interfaces, tendrías que duplicar el método `volar()` en cada
   clase que vuela (sin ninguna relación entre ellas), o forzar una jerarquía de herencia
   artificial solo para compartir código (¿`Avion` hereda de `Ave`? No tiene sentido).

**Ejemplo real de industria:** esto es exactamente lo que hace el propio JDK de Java.
`Comparable`, `Runnable`, `Serializable`, `AutoCloseable` son interfaces que cualquier clase puede
implementar para declarar "yo sé hacer esto", sin que eso implique ningún parentesco entre las
clases que las implementan. Un `String` y un `Thread` no tienen nada que ver entre sí, pero ambos
son `Comparable` (o candidatos a serlo) porque cada uno sabe compararse a su manera. Es el mismo
patrón que `Pato`/`Avion`, a escala de librería estándar.

---

## El patrón común

En los ejemplos 01-04: **la clase compositora no sabe cómo trabaja el componente, solo conoce su
contrato (interfaz), y puede reemplazarlo sin modificarse a sí misma.**

En el ejemplo 05: **una clase puede declarar varias capacidades independientes implementando
varias interfaces, sin que eso implique ningún parentesco de herencia entre las clases que las
comparten.**

En ambos casos la interfaz cumple el mismo rol: es un contrato que permite tratar objetos de
forma uniforme sin acoplarse a su implementación concreta. Es *"favor composition over
inheritance"* + *"program to an interface, not an implementation"*, visto desde dos ángulos
distintos.
