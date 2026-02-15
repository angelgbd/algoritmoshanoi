package org.itson.practica;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TorresHanoiGrafico {

    static long movimientos = 0;

    public static void main(String[] args) {
        // Reducimos a 20-22 para que la gráfica salga rápido en la demo
        int MAX_N = 20; 

        // Listas para guardar datos para la gráfica
        List<Double> ejeX_N = new ArrayList<>();
        List<Double> tiemposRecursivos = new ArrayList<>();
        List<Double> tiemposIterativos = new ArrayList<>();

        System.out.println("Ejecutando Benchmark...");

        for (int n = 1; n <= MAX_N; n++) {
            ejeX_N.add((double) n);

            // 1. Recursivo
            movimientos = 0;
            long inicioRec = System.nanoTime();
            resolverRecursivo(n, 'A', 'C', 'B', false);
            long finRec = System.nanoTime();
            tiemposRecursivos.add((finRec - inicioRec) / 1_000_000_000.0);

            // 2. Iterativo
            movimientos = 0;
            long inicioIter = System.nanoTime();
            resolverIterativo(n, 'A', 'C', 'B', false);
            long finIter = System.nanoTime();
            tiemposIterativos.add((finIter - inicioIter) / 1_000_000_000.0);
            
            System.out.println("N=" + n + " procesado.");
        }

        // --- GENERACIÓN DEL GRÁFICO ---
        graficarResultados(ejeX_N, tiemposRecursivos, tiemposIterativos);
    }

    public static void graficarResultados(List<Double> x, List<Double> yRec, List<Double> yIter) {
        // 1. Configurar el gráfico
        XYChart chart = new XYChartBuilder()
                .width(800).height(600)
                .title("Torres de Hanoi: Recursivo vs Iterativo")
                .xAxisTitle("Número de Discos (n)")
                .yAxisTitle("Tiempo (segundos)")
                .build();

        // 2. Estilo (Opcional: Escala logarítmica para ver mejor la curva exponencial)
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        // Descomenta la siguiente línea si quieres ver escala logarítmica:
        // chart.getStyler().setYAxisLogarithmic(true);

        // 3. Agregar las series de datos
        chart.addSeries("Recursivo", x, yRec).setMarker(SeriesMarkers.CIRCLE);
        chart.addSeries("Iterativo", x, yIter).setMarker(SeriesMarkers.DIAMOND);

        // 4. Mostrar ventana
        new SwingWrapper<>(chart).displayChart();
    }

    // --- Mismos algoritmos de antes ---

    public static void resolverRecursivo(int n, char origen, char destino, char auxiliar, boolean imprimir) {
        if (n == 1) {
            movimientos++; return;
        }
        resolverRecursivo(n - 1, origen, auxiliar, destino, imprimir);
        movimientos++;
        resolverRecursivo(n - 1, auxiliar, destino, origen, imprimir);
    }

    public static void resolverIterativo(int n, char s, char d, char a, boolean imprimir) {
        Stack<Integer> src = new Stack<>();
        Stack<Integer> dest = new Stack<>();
        Stack<Integer> aux = new Stack<>();
        for (int i = n; i >= 1; i--) src.push(i);

        if (n % 2 == 0) { char temp = d; d = a; a = temp; }
        long totalMovimientos = (long) Math.pow(2, n) - 1;

        for (long i = 1; i <= totalMovimientos; i++) {
            if (i % 3 == 1) moverDiscoLegal(src, dest);
            else if (i % 3 == 2) moverDiscoLegal(src, aux);
            else if (i % 3 == 0) moverDiscoLegal(aux, dest);
        }
    }

    private static void moverDiscoLegal(Stack<Integer> A, Stack<Integer> B) {
        movimientos++;
        if (A.isEmpty()) A.push(B.pop());
        else if (B.isEmpty()) B.push(A.pop());
        else if (A.peek() < B.peek()) B.push(A.pop());
        else A.push(B.pop());
    }
}