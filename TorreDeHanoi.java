public class TorreDeHanoi {

    public static void resolverHanoi(int n, char origen, char auxiliar, char destino) {
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origen + " a " + destino);
            return;
        }

        // 1) Mover n-1 discos de origen a auxiliar, usando destino como apoyo
        resolverHanoi(n - 1, origen, destino, auxiliar);

        // 2) Mover el disco n de origen a destino
        System.out.println("Mover disco " + n + " de " + origen + " a " + destino);

        // 3) Mover n-1 discos de auxiliar a destino, usando origen como apoyo
        resolverHanoi(n - 1, auxiliar, origen, destino);
    }

    public static void main(String[] args) {
        int n = 3;
        resolverHanoi(n, 'A', 'B', 'C');
    }
}