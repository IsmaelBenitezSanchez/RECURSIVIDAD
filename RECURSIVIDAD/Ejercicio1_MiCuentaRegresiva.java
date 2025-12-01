package recursividad;

public class Ejercicio1_MiCuentaRegresiva {

    public static void main(String[] args) {
        // Iniciamos la cuenta regresiva desde 5
        cuentaRegresiva(5);
    }

    public static void cuentaRegresiva(int n) {
        // -------------------------------------------------
        // CASO BASE: El punto donde la recursión se detiene
        // -------------------------------------------------
        if (n == 0) {
            System.out.println(n);
            // Al no llamar más a la función, aquí termina el proceso.
        } 
        
        // -------------------------------------------------
        // CASO RECURSIVO: La función se llama a sí misma
        // -------------------------------------------------
        else {
            System.out.println(n);
            
            // Llamada recursiva disminuyendo el número (acercándose al caso base)
            cuentaRegresiva(n - 1); 
        }
    }
    
}