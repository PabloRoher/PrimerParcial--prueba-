package mates;

import java.util.Random;

public class Matematicas{
    /**
     * Genera una aproximación al número pi mediante el método de
     * Montecarlo. El parámetro ‘pasos‘ indica el número de puntos
     * generado.
     */
    public static double generarNumeroPi(long pasos){
        Random r = new Random();

        double x, y, cuadrado, aciertos = 0;
        for (int i = 0; i < pasos; i++){
            x = r.nextDouble();
            y = r.nextDouble();
            cuadrado = x*x + y*y;
            if (cuadrado <= 1){
                aciertos++;
            }

        }

        return 4 * (aciertos/pasos) ;

    }
}

