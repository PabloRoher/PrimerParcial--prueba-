package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Tablero {

    private static int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];;

    int fila = 0;
    int columnas = 0;

    public void leerEstadoActual() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/dominio/matriz.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (columnas == 0) {
                    columnas = linea.length();
                    estadoActual = new int[columnas][columnas];
                }
                for (int columna = 0; columna < columnas; columna++) {
                    char c = linea.charAt(columna);
                    estadoActual[fila][columna] = c == '1' ? 1 : 0;
                }
                fila++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int contarVecinos(int fila, int columna) {
        int vecinosVivos = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i == fila && j == columna) {
                    continue;
                }
                int filaValida = (i + DIMENSION) % DIMENSION;
                int columnaValida = (j + DIMENSION) % DIMENSION;
                if (estadoActual[filaValida][columnaValida] == 1) {
                    vecinosVivos++;
                }
            }
        }
        return vecinosVivos;
    }

    public void transitarAlEstadoSiguiente() {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {
                int vecinosVivos = contarVecinos(fila, columna);
                if (estadoActual[fila][columna] == 1 && (vecinosVivos == 2 || vecinosVivos == 3)) {
                    estadoSiguiente[fila][columna] = 1;
                } else if (estadoActual[fila][columna] == 0 && vecinosVivos == 3) {
                    estadoSiguiente[fila][columna] = 1;
                } else {
                    estadoSiguiente[fila][columna] = 0;
                }
            }
        }
        estadoActual = estadoSiguiente;
        estadoSiguiente = new int[DIMENSION][DIMENSION];
    }

    public void generarEstadoActualPorMontecarlo(double probabilidad) {
        Random r = new Random();
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {
                double random = r.nextDouble();
                if (random < probabilidad) {
                    estadoActual[fila][columna] = 1;
                } else {
                    estadoActual[fila][columna] = 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {
                if (estadoActual[fila][columna] == 1) {
                    sb.append("* ");
                } else {
                    sb.append(". ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}