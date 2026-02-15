
package org.itson.practica;
/**
 *
 * @author angel
 */
import java.util.Stack;

public class TorresHanoi {

    // Variable estática para contar movimientos (solo para verificación)
    static long movimientos = 0;

    public static void main(String[] args) {
        // Rango de prueba: de 1 a 25.
        // ADVERTENCIA: n=30 genera 1,073,741,823 movimientos. 
        // En Java, esto puede tardar unos minutos solo calculando. 
        // Si quieres probar hasta 30, cambia MAX_N a 30.
        int MAX_N = 25; 

        System.out.printf("%-5s | %-20s | %-20s | %-20s%n", "N", "Movimientos", "T. Recursivo (s)", "T. Iterativo (s)");
        System.out.println("---------------------------------------------------------------------------");

        for (int n = 1; n <= MAX_N; n++) {
            // 1. Ejecutar y medir Recursivo
            movimientos = 0;
            long inicioRec = System.nanoTime();
            // false = modo silencioso (no imprimir cada movimiento)
            resolverRecursivo(n, 'A', 'C', 'B', false); 
            long finRec = System.nanoTime();
            double tiempoRec = (finRec - inicioRec) / 1_000_000_000.0;

            // Guardamos el número de movimientos teóricos para verificar
            long movimientosTotales = movimientos; 

            // 2. Ejecutar y medir Iterativo
            movimientos = 0;
            long inicioIter = System.nanoTime();
            resolverIterativo(n, 'A', 'C', 'B', false);
            long finIter = System.nanoTime();
            double tiempoIter = (finIter - inicioIter) / 1_000_000_000.0;

            // Imprimir fila de la tabla
            System.out.printf("%-5d | %-20d | %-20.6f | %-20.6f%n", n, movimientosTotales, tiempoRec, tiempoIter);
        }
    }


    // ALGORITMO RECURSIVO
    public static void resolverRecursivo(int n, char origen, char destino, char auxiliar, boolean imprimir) {
        if (n == 1) {
            movimientos++;
            if (imprimir) System.out.println("Mover disco 1 de " + origen + " a " + destino);
            return;
        }
        resolverRecursivo(n - 1, origen, auxiliar, destino, imprimir);
        movimientos++;
        if (imprimir) System.out.println("Mover disco " + n + " de " + origen + " a " + destino);
        resolverRecursivo(n - 1, auxiliar, destino, origen, imprimir);
    }

   // ALGORITMO ITERATIVO
    public static void resolverIterativo(int n, char s, char d, char a, boolean imprimir) {
        // Crear las pilas que representan los postes
        Stack<Integer> src = new Stack<>();
        Stack<Integer> dest = new Stack<>();
        Stack<Integer> aux = new Stack<>();

        // Inicializar poste origen con n discos (n al fondo, 1 arriba)
        for (int i = n; i >= 1; i--) {
            src.push(i);
        }

        char tempS = s, tempD = d, tempA = a;

        // Si n es par, intercambiamos destino y auxiliar para seguir la regla matemática
        if (n % 2 == 0) {
            char temp = tempD;
            tempD = tempA;
            tempA = temp;
        }

        // Total de movimientos = 2^n - 1
        // Usamos Math.pow pero casteamos a long para precisión
        long totalMovimientos = (long) Math.pow(2, n) - 1;

        // Iteramos desde 1 hasta el total de movimientos
        for (long i = 1; i <= totalMovimientos; i++) {
            if (i % 3 == 1) {
                moverDiscoLegal(src, dest, tempS, tempD, imprimir);
            } else if (i % 3 == 2) {
                moverDiscoLegal(src, aux, tempS, tempA, imprimir);
            } else if (i % 3 == 0) {
                moverDiscoLegal(aux, dest, tempA, tempD, imprimir);
            }
        }
    }

    // Método auxiliar para el iterativo: Decide quién mueve a quién entre dos postes
    private static void moverDiscoLegal(Stack<Integer> torreA, Stack<Integer> torreB, char nombreA, char nombreB, boolean imprimir) {
        movimientos++;
        
        // Caso 1: Torre A vacía, Torre B tiene disco -> B mueve a A
        if (torreA.isEmpty()) {
            int disco = torreB.pop();
            torreA.push(disco);
            if (imprimir) System.out.println("Mover disco " + disco + " de " + nombreB + " a " + nombreA);
        }
        // Caso 2: Torre B vacía, Torre A tiene disco -> A mueve a B
        else if (torreB.isEmpty()) {
            int disco = torreA.pop();
            torreB.push(disco);
            if (imprimir) System.out.println("Mover disco " + disco + " de " + nombreA + " a " + nombreB);
        }
        // Caso 3: Ambas tienen discos, mover el más pequeño sobre el más grande
        else if (torreA.peek() < torreB.peek()) {
            int disco = torreA.pop();
            torreB.push(disco);
            if (imprimir) System.out.println("Mover disco " + disco + " de " + nombreA + " a " + nombreB);
        } else {
            int disco = torreB.pop();
            torreA.push(disco);
            if (imprimir) System.out.println("Mover disco " + disco + " de " + nombreB + " a " + nombreA);
        }
    }
}
