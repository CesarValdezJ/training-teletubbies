# Solución — Normalización de la biblioteca


## 1. Problemas encontrados en la tabla plana

- "Gabriel García Márquez" aparece escrito de dos formas (fila 1 vs. fila 2) y con la bio
  puntuada distinto → **anomalía de actualización**: corregir el nombre en una fila no lo
  corrige en las demás.
- El email de Ana Torres varía (`example.com` / `exemple.com`) → misma anomalía.
- No hay forma de registrar un autor o un socio nuevo sin que tengan ya un préstamo →
  **anomalía de inserción**.
- Borrar el único préstamo de un libro (si tuviera uno) borraría también sus datos de autor si
  no existiera una tabla `Author` separada → **anomalía de eliminación**.
- `return_date` vacío en filas 2, 4 y 6 **no** es un defecto — es un préstamo todavía activo;
  se modela como columna nullable, no como problema a normalizar.

## 2. Entidades identificadas

| Entidad | Por qué es una entidad propia |
|---|---|
| `Author` | `author_name` + `author_bio` se repiten juntos en varias filas — mismo autor, mismo dato. |
| `Book` | `book_title` + `book_isbn` se repiten juntos — mismo libro, mismo dato. |
| `Member` | `member_name` + `member_email` se repiten juntos — mismo socio, mismo dato. |
| `Loan` | El resto de columnas (`loan_date`, `due_date`, `return_date`) no describen ni al libro ni al socio — describen el **evento** del préstamo. Además resuelve el N:N conceptual entre `Book` y `Member`. |

## 3. Diagrama relacional (Mermaid `erDiagram`)

```
erDiagram
    Author {
        int id PK
        string name
        string bio
    }
    Book {
        int id PK
        string title
        string isbn
        int author_id FK
    }
    Member {
        int id PK
        string name
        string email UK
    }
    Loan {
        int id PK
        int book_id FK
        int member_id FK
        date loan_date
        date due_date
        date return_date
    }

    Author ||--o{ Book   : writes
    Book   ||--o{ Loan   : "is loaned in"
    Member ||--o{ Loan   : "borrows in"
```

## 4. Diccionario de datos

| Tabla | Columna | Tipo | PK/FK | Nulo | Descripción |
|---|---|---|---|---|---|
| Author | id | integer | PK | no | identificador surrogate |
| Author | name | varchar | | no | nombre del autor |
| Author | bio | varchar | | sí | biografía corta |
| Book | id | integer | PK | no | identificador surrogate |
| Book | title | varchar | | no | título del libro |
| Book | isbn | varchar | | no | identificador natural del libro (candidato a UNIQUE) |
| Book | author_id | integer | FK → Author.id | no | autor del libro |
| Member | id | integer | PK | no | identificador surrogate |
| Member | name | varchar | | no | nombre del socio |
| Member | email | varchar | UK | no | único por socio, se usa para contacto |
| Loan | id | integer | PK | no | identificador surrogate |
| Loan | book_id | integer | FK → Book.id | no | libro prestado |
| Loan | member_id | integer | FK → Member.id | no | socio que pide prestado |
| Loan | loan_date | date | | no | fecha en que se prestó |
| Loan | due_date | date | | no | fecha límite de devolución |
| Loan | return_date | date | | **sí** | fecha real de devolución — vacío mientras el préstamo está activo |

## 5. Índices justificados

| Columna | Consulta que lo justifica | Por qué |
|---|---|---|
| `Loan.member_id` | "¿Qué libros tiene prestados actualmente un socio?" | Es FK usada en `WHERE`/`JOIN` con alta selectividad (muchos socios distintos). |
| `Book.author_id` | "¿Qué libros ha escrito un autor?" | Es FK usada en `WHERE`/`JOIN`; alta selectividad si hay muchos autores. |

Nota para discusión adicional (opcional, si sobra tiempo): `Book.isbn` es un buen candidato a
`UNIQUE` — es la forma natural en que alguien buscaría "¿ya tenemos este libro?" antes de
registrarlo dos veces.
