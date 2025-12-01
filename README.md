# Recursividad en Java — Apuntes

Estos apuntes cubren los fundamentos de la recursividad en Java, organizados en tres fases progresivas: desde el concepto básico hasta el manejo del flujo de datos en las llamadas recursivas.

---

## Fase 1: Los Fundamentos Mentales

### ¿Qué es la recursividad?

Una función que **se llama a sí misma** para resolver un problema dividiéndolo en partes más pequeñas.

**Analogía:** Imagina que estás en una fila de personas y quieres saber cuántas hay delante de ti:
- Tú preguntas a la persona de adelante: "¿Cuántas personas hay delante de ti?"
- Ella pregunta lo mismo a la siguiente persona.
- Hasta que alguien al frente dice: "¡Ninguna, soy el primero!"
- Entonces las respuestas vuelven hacia atrás sumando 1 cada vez.

Eso es recursividad: **delegar el trabajo a una versión más pequeña del mismo problema**.

### Las dos partes obligatorias

Toda función recursiva **debe** tener:

```
┌─────────────────────────────────────────────────────────────┐
│  1. CASO BASE (El freno de emergencia)                      │
│     → "¿Ya terminé? Si sí, PARO aquí y devuelvo respuesta"  │
│                                                             │
│  2. CASO RECURSIVO (El paso hacia la meta)                  │
│     → "Si no terminé, me llamo a mí mismo con un problema   │
│        MÁS PEQUEÑO"                                         │
└─────────────────────────────────────────────────────────────┘
```

**Si olvidas el caso base** → La función nunca para → **StackOverflowError** (la memoria se desborda).

### StackOverflowError

Cada vez que llamas a una función, Java la coloca en una "pila" en memoria (como apilar platos). Si la función se llama a sí misma infinitamente sin llegar nunca al caso base, **la pila se desborda**.

Ejemplo de función que provoca el error:

```java
// ❌ FUNCIÓN ROTA: No tiene caso base
public static void funcionInfinita(int n) {
    System.out.println("Llamada con n = " + n);
    funcionInfinita(n + 1);  // Se llama a sí misma SIEMPRE, nunca para
}
```

### Ejemplo: Cuenta regresiva

```java
public static void cuentaRegresiva(int n) {
    // CASO BASE
    if (n < 0) {
        System.out.println("¡Despegue!");
        return;  // PARO AQUÍ, no me llamo más
    }

    // CASO RECURSIVO
    System.out.println(n);
    cuentaRegresiva(n - 1);  // Me llamo con un problema MÁS PEQUEÑO
}
```

Salida de `cuentaRegresiva(5)`:

```
5
4
3
2
1
0
¡Despegue!
```

---

## Fase 2: Entendiendo la Pila (The Stack)

### Principio LIFO

La pila de llamadas funciona con el principio **LIFO (Last In, First Out)**: el último que entra es el primero que sale.

Cuando una función llama a otra:
1. La función actual se "pausa" y queda en la pila.
2. La nueva función se coloca encima.
3. Cuando la nueva función termina, se quita de la pila.
4. La función pausada continúa desde donde se quedó.

### Variables locales independientes

Cada llamada recursiva tiene **su propia copia de las variables locales**. Si `factorial(3)` tiene `n = 3`, y llama a `factorial(2)`, esta última tiene su propio `n = 2`. No se mezclan.

### Ejemplo: Factorial

Definición matemática:
- `0! = 1`
- `n! = n × (n - 1)!` para `n > 0`

```java
public static int factorial(int n) {
    // CASO BASE
    if (n <= 1) {
        return 1;
    }
    
    // CASO RECURSIVO
    return n * factorial(n - 1);
}
```

### Visualización de la pila para `factorial(3)`

#### Durante la bajada (las llamadas se apilan)

```
1) main llama a factorial(3)

TOPE
┌───────────────────────┐
│ factorial(3)   n = 3  │
└───────────────────────┘

2) factorial(3) necesita factorial(2)

TOPE
┌───────────────────────┐
│ factorial(2)   n = 2  │
├───────────────────────┤
│ factorial(3)   n = 3  │  ← en pausa, esperando
└───────────────────────┘

3) factorial(2) necesita factorial(1)

TOPE
┌───────────────────────┐
│ factorial(1)   n = 1  │  ← CASO BASE
├───────────────────────┤
│ factorial(2)   n = 2  │  ← en pausa
├───────────────────────┤
│ factorial(3)   n = 3  │  ← en pausa
└───────────────────────┘
```

#### Durante la subida (los retornos desapilan)

- `factorial(1)` devuelve `1` → se desapila.
- `factorial(2)` calcula `2 × 1 = 2` → devuelve `2` → se desapila.
- `factorial(3)` calcula `3 × 2 = 6` → devuelve `6` → se desapila.

**Resultado final:** `6`

### Concepto clave

Cada llamada queda **en pausa** esperando el resultado de la siguiente. La pila "recuerda" dónde se quedó cada una y qué valores tenían sus variables locales. Cuando el caso base devuelve un valor, ese valor "sube" por la pila, permitiendo que cada llamada pausada termine su trabajo.

---

## Fase 3: Flujo de Datos (Ida y Vuelta)

### Trabajo en la bajada vs. trabajo en la subida

La diferencia está en **dónde** colocas el código que hace el trabajo respecto a la llamada recursiva.

#### Trabajo en la BAJADA (antes de la llamada recursiva)

```java
static void cuentaAbajo(int n) {
    if (n < 0) return;
    System.out.println(n);   // Trabajo ANTES de la llamada
    cuentaAbajo(n - 1);
}
```

Salida de `cuentaAbajo(3)`: `3, 2, 1, 0`

El trabajo ocurre **mientras la pila crece**.

#### Trabajo en la SUBIDA (después de la llamada recursiva)

```java
static void cuentaArriba(int n) {
    if (n < 0) return;
    cuentaArriba(n - 1);     // Llamada recursiva primero
    System.out.println(n);   // Trabajo DESPUÉS de la llamada
}
```

Salida de `cuentaArriba(3)`: `0, 1, 2, 3`

El trabajo ocurre **mientras la pila se vacía**.

### Recursividad `void` vs. con retorno

- **`void`**: El resultado se manifiesta por efectos secundarios (imprimir, modificar estructuras).
- **Con retorno (`int`, `String`, etc.)**: El resultado sube por los `return`, combinándose en cada nivel.

### Ejemplo: Sumar los dígitos de un número

```java
public static int sumarDigitos(int n) {
    // CASO BASE: un solo dígito
    if (n < 10) {
        return n;
    }
    
    // CASO RECURSIVO
    int ultimoDigito = n % 10;       // Ej: 123 % 10 = 3
    int restoDelNumero = n / 10;     // Ej: 123 / 10 = 12
    return ultimoDigito + sumarDigitos(restoDelNumero);
}
```

#### Visualización para `sumarDigitos(123)`

**Bajada:**

```
sumarDigitos(123)  →  3 + sumarDigitos(12)
sumarDigitos(12)   →  2 + sumarDigitos(1)
sumarDigitos(1)    →  1  (caso base)
```

**Subida:**

```
sumarDigitos(1)   devuelve 1
sumarDigitos(12)  devuelve 2 + 1 = 3
sumarDigitos(123) devuelve 3 + 3 = 6
```

**Resultado:** `6` (que es `1 + 2 + 3`)

### Ejemplo: Invertir un String

```java
public static String invertir(String s) {
    // CASO BASE: cadena vacía o de un solo carácter
    if (s.length() <= 1) {
        return s;
    }
    
    // CASO RECURSIVO
    char primerCaracter = s.charAt(0);
    String resto = s.substring(1);
    return invertir(resto) + primerCaracter;
}
```

**Idea:** "La inversión de un texto es la inversión de todo menos el primer carácter, y al final le pego ese primer carácter."

#### Visualización para `invertir("sol")`

**Bajada:**

```
invertir("sol")  →  invertir("ol") + 's'
invertir("ol")   →  invertir("l") + 'o'
invertir("l")    →  invertir("") + 'l'
invertir("")     →  ""  (caso base)
```

**Pila justo en el caso base:**

```
TOPE
┌─────────────────────────────┐
│ invertir("")                │  ← caso base, devuelve ""
├─────────────────────────────┤
│ invertir("l")               │  ← en pausa
├─────────────────────────────┤
│ invertir("ol")              │  ← en pausa
├─────────────────────────────┤
│ invertir("sol")             │  ← en pausa
└─────────────────────────────┘
```

**Subida:**

```
invertir("")    devuelve ""
invertir("l")   devuelve "" + 'l' = "l"
invertir("ol")  devuelve "l" + 'o' = "lo"
invertir("sol") devuelve "lo" + 's' = "los"
```

---

## Resumen: La Pila es el Rey

La recursividad **no es magia, es memoria**. Todo se reduce a entender cómo funciona la pila de llamadas:

1. **Cada llamada** se apila con sus propias variables locales.
2. **El caso base** detiene el crecimiento de la pila.
3. **Los retornos** desapilan y permiten que cada llamada pausada complete su trabajo.
4. **El trabajo puede hacerse** en la bajada (antes de la llamada) o en la subida (después).

