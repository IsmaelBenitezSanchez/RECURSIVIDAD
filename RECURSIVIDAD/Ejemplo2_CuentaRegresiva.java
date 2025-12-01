package recursividad;

/**
 * EJEMPLO 2: Cuenta regresiva (Recursividad correcta)
 * 
 * Esta función SÍ tiene caso base, por lo tanto funciona bien.
 */
public class Ejemplo2_CuentaRegresiva {

    public static void cuentaRegresiva(int n) {
        // ═══════════════════════════════════════════════════
        // CASO BASE: El freno de emergencia
        // ═══════════════════════════════════════════════════
        if (n < 0) {
            System.out.println("¡Despegue! 🚀");
            return;  // PARO AQUÍ, no me llamo más
        }

        // ═══════════════════════════════════════════════════
        // CASO RECURSIVO: Hago mi trabajo y delego el resto
        // ═══════════════════════════════════════════════════
        System.out.println(n);           // Imprimo el número actual
        cuentaRegresiva(n - 1);          // Me llamo con un problema MÁS PEQUEÑO
    }

    public static void main(String[] args) {
        System.out.println("=== CUENTA REGRESIVA DEL 5 AL 0 ===\n");
        cuentaRegresiva(5);
    }
}