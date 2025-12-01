package recursividad;

/**
 * EJEMPLO 1: Cómo provocar un StackOverflowError
 * 
 * Este código está ROTO a propósito para que veas qué pasa
 * cuando una función recursiva NO tiene caso base.
 */
public class Ejemplo1_StackOverflow {

    // FUNCIÓN ROTA: No tiene caso base (freno de emergencia)
    public static void funcionInfinita(int n) {
        System.out.println("Llamada con n = " + n);
        funcionInfinita(n + 1);  // Se llama a sí misma SIEMPRE, nunca para
    }

    public static void main(String[] args) {
        System.out.println("Vamos a provocar un StackOverflowError...");
        System.out.println("Observa cómo n crece sin control:\n");
        
        funcionInfinita(1);  // ¡BOOM! Esto explotará
    }
}