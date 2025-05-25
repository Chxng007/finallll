package proyectokenner;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TiroParabolico {

    private double velocidadInicial;
    private double anguloGrados;
    private final double gravedad = 9.81;
    private final DecimalFormat df = new DecimalFormat("#.##");

    // Método para asignar valores de entrada
    public void asignarValores() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la velocidad inicial (m/s):");
        this.velocidadInicial = scanner.nextDouble();

        System.out.println("Ingrese el angulo de lanzamiento (grados):");
        this.anguloGrados = scanner.nextDouble();
    }

    // Método para convertir grados a radianes
    private double convertirARadianes(double grados) {
        return grados * Math.PI / 180.0;
    }

    // Método para calcular el tiempo total de vuelo
    public double calcularTiempoTotalVuelo() {
        double anguloRadianes = convertirARadianes(anguloGrados);
        return (2 * velocidadInicial * Math.sin(anguloRadianes)) / gravedad;
    }

    // Método para calcular el tiempo de subida
    public double calcularTiempoSubida() {
        return calcularTiempoTotalVuelo() / 2;
    }

    // Método para calcular la altura máxima alcanzada
    public double calcularAlturaMaxima() {
        double anguloRadianes = convertirARadianes(anguloGrados);
        return (Math.pow(velocidadInicial * Math.sin(anguloRadianes), 2)) / (2 * gravedad);
    }

    // Método para calcular la distancia máxima recorrida
    public double calcularDistanciaMaxima() {
        double anguloRadianes = convertirARadianes(anguloGrados);
        return (Math.pow(velocidadInicial, 2) * Math.sin(2 * anguloRadianes)) / gravedad;
    }

    // Método para guardar los resultados en un archivo de texto
    public void guardarResultados(String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write("Resultados del Tiro Parabolico\n");
            writer.write("==============================\n\n");
            writer.write("Datos de entrada:\n");
            writer.write("- Velocidad inicial: " + df.format(velocidadInicial) + " m/s\n");
            writer.write("- Angulo de lanzamiento: " + df.format(anguloGrados) + " grados\n\n");

            writer.write("Resultados calculados:\n");
            writer.write("- Tiempo total de vuelo: " + df.format(calcularTiempoTotalVuelo()) + " segundos\n");
            writer.write("- Tiempo de subida: " + df.format(calcularTiempoSubida()) + " segundos\n");
            writer.write("- Altura maxima alcanzada: " + df.format(calcularAlturaMaxima()) + " metros\n");
            writer.write("- Distancia maxima recorrida: " + df.format(calcularDistanciaMaxima()) + " metros\n");

            System.out.println("Resultados guardados exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar los resultados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TiroParabolico calculadora = new TiroParabolico();

        // Asignar valores
        calculadora.asignarValores();

        // Mostrar resultados en consola
        System.out.println("\nResultados del calculo:");
        System.out.println("Tiempo total de vuelo: " + calculadora.df.format(calculadora.calcularTiempoTotalVuelo()) + " s");
        System.out.println("Tiempo de subida: " + calculadora.df.format(calculadora.calcularTiempoSubida()) + " s");
        System.out.println("Altura maxima: " + calculadora.df.format(calculadora.calcularAlturaMaxima()) + " m");
        System.out.println("Distancia maxima: " + calculadora.df.format(calculadora.calcularDistanciaMaxima()) + " m");

        // Guardar resultados en archivo (cambiar la ruta según su sistema)
        calculadora.guardarResultados("C:\\resultados_tiro_parabolico.txt");
    }
}

