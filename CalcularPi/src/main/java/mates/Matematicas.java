package mates;

import java.util.Random;
import java.util.Scanner;

public class Matematicas{
    /**
     * Genera una aproximación al número pi mediante el método de
     * Montecarlo. El parámetro ‘pasos‘ indica el número de puntos
     * generado.
     */
    public static double generarNumeroPi(long pasos){
        Scanner teclado1 = new Scanner(System.in);
        int puntosTotales = teclado1.nextInt();
        Random random = new Random();
        int aciertos = 0;
        int areaCuadrado = 4;
        for (int i = 1; i < puntosTotales ; i++) {
            int x = random.nextInt(-1,1);
            int y = random.nextInt(-1,1);
            if (x <= 1 && y <= 1){
                aciertos += 1;
            }
        }
        return areaCuadrado * (aciertos/puntosTotales) ;
    }
}

