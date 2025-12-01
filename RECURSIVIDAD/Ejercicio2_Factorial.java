package recursividad;

public class Ejercicio2_Factorial {

    public static void main(String[] args) {
        System.out.println("Calculando factorial de 3: " + factorial(3));
        System.out.println("Calculando factorial de 5: " + factorial(5));
    }

    public static int factorial(int n) {
        // CASO BASE: Si n es 0 o 1, el factorial es 1.
        // Aquí la pila deja de crecer y empieza a "desapilarse".
        if (n <= 1) {
            return 1;
        }
        
        // CASO RECURSIVO: n * factorial de (n-1)
        // La función se queda en "pausa" esperando el resultado de la siguiente llamada.
        else {
            return n * factorial(n - 1);
        }
    }
 
    // La Pila (Call Stack)Justo en el instante en que estamos ejecutando factorial(1) (el Caso Base) 
    // dentro del cálculo global de factorial(3), la memoria se ve así:TOPE DE LA PILA (Lo que se está ejecutando ahora mismo)
    // factorial(1) con n = 1Estado: Ha entrado en el if (n <= 1). Está a punto de devolver 1 y desaparecer de la pila.
    // EN PAUSA (Esperando resultado)factorial(2) con n = 2Estado: Está pausado en la línea return 2 * .... Está esperando a que 
    // factorial(1) le devuelva el dato para poder multiplicar $2 \times 1$.EN PAUSA (Esperando resultado)factorial(3) 
    // con n = 3Estado: Está pausado en la línea return 3 * .... Está esperando a que factorial(2) termine su trabajo 
    // y le devuelva el resultado (que será 2) para hacer $3 \times 2$.BASE DE LA PILAmainEstado: Esperando a que factorial(3) 
    // le entregue el valor final (6) para imprimirlo en pantalla.
}