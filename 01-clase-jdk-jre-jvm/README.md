# 01 · JDK, JRE, JVM

Basic Java compilation and execution examples, using a bakery theme.

## Files

- `Pastel.java` — class with state (`sabor`, `rebanadas`, `horneado`) and a `main` that creates
  two cakes and serves slices.
- `Menu.java` — interactive console program (`Scanner`) that simulates the bakery menu.

## How to compile and run

From this folder:

```bash
javac Pastel.java
java Pastel
```

```bash
javac Menu.java
java Menu
```

`javac` (JDK) compiles the `.java` file into bytecode (`.class`); `java` runs it on the JVM,
using the runtime provided by the JRE included in the JDK.

Generated `.class` files are not pushed to the repo (see `.gitignore`), only the `.java` source
code.