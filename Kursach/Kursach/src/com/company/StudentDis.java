package com.company;

public class StudentDis {

    // Гамма-функция Эйлера

    protected double gamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 +
                76.18009173 / (x + 0.0) - 86.50532033 / (x + 1.0) +
                24.01409822 / (x + 2.0) - 1.231739516 / (x + 3.0) +
                0.00120858003 / (x + 4.0) - 0.00000536382 / (x + 5.0);
        return Math.exp(tmp + Math.log(ser * Math.sqrt(2 * Math.PI)));
    }

    // Отношение гамма-функций
    protected double gammaRatio(double x, double y) {
        double m = Math.abs(Math.max(x, y));
        if (m <= 100.0)
            return gamma(x) / gamma(y);
        return Math.pow(2, x - y) *
                gammaRatio(x * 0.5, y * 0.5) *
                gammaRatio(x * 0.5 + 0.5, y * 0.5 + 0.5);
    }

    // Гипергеометрическая функция
    protected double hyperGeom(double a, double b, double c, double z, int deep) {
        double S = 1.0, M, d;
        for (int i = 1; i <= deep; i++) {
            M = Math.pow(z, (double) i);
            for (int j = 0; j < i; j++) {
                d = (double) j;
                M *= (a + d) * (b + d) / ((1.0 + d) * (c + d));
            }
            S += M;
        }
        return S;
    }

    // hypergeometric function with default deep value= 20
    protected double hyperGeom(double a, double b, double c, double z) {
        return hyperGeom(a, b, c, z, 20);
    }

    // Функция распределения Стьюдента:
    public double testDist(double x, int n) {
        return 0.5 + x * gammaRatio(0.5 * ((double) n + 1.0), 0.5 * (double) n) *
                hyperGeom(0.5, 0.5 * ((double) n + 1.0), 1.5, -x * x / (double) n) / Math.sqrt(Math.PI * (double) n);
    }

    public double dist(double alpha, int v) {
        double c0 = 2.515517, c1 = 0.802863, c2 = 0.010328, d1 = 1.432788, d2 = 0.189269, d3 = 0.001208;

        double r = Math.sqrt(-2 * Math.log(alpha));
        double u = r - (c0 + c1 * r + c2 * r * r) / (1 + d1 * r + d2 * r * r + d3 * Math.pow(r, 3));
        return u * (1 + (1 + Math.pow(u, 2)) / (4 * v) + (3 + 16 * Math.pow(u, 2) + 5 * Math.pow(u, 4)) / (96 * Math.pow(v, 2)));
    }

}
