import java.util.HashMap;
import java.util.Map;

public class CuadraturaGaussiana {

    // Función que queremos integrar: f(x) = e^(-x^2)
    public static double funcion(double x) {
        return Math.exp(-x * x);
    }
    // Método que realiza la cuadratura gaussiana
    public static double cuadraturaGaussiana(double a, double b, int n) {
        // Coeficientes y nodos de Gauss predefinidos
        Map<Integer, double[]> coeficientes = new HashMap<>();
        coeficientes.put(2, new double[]{1.0, 1.0});
        coeficientes.put(3, new double[]{0.5555555556, 0.8888888889, 0.5555555556});
        coeficientes.put(4, new double[]{0.3478548451, 0.6521451549, 0.6521451549, 0.3478548451});
        coeficientes.put(5, new double[]{0.2369268851, 0.4786286705, 0.5688888889, 0.4786286705, 0.2369268851});
        coeficientes.put(6, new double[]{0.1713244924, 0.3607615730, 0.4679139346, 0.4679139346, 0.3607615730, 0.1713244924});

        Map<Integer, double[]> nodos = new HashMap<>();
        nodos.put(2, new double[]{-0.5773502692, 0.5773502692});
        nodos.put(3, new double[]{0.0, -0.7745966692, 0.7745966692});
        nodos.put(4, new double[]{-0.3399810436, 0.3399810436, -0.8611363116, 0.8611363116});
        nodos.put(5, new double[]{0.0, -0.5384693101, 0.5384693101, -0.9061798459, 0.9061798459});
        nodos.put(6, new double[]{0.6612093865, -0.6612093865, -0.2386191861, 0.2386191861, -0.9324695142, 0.9324695142});

        double[] coef = coeficientes.get(n);
        double[] nodo = nodos.get(n);

        double suma = 0.0;

        for (int i = 0; i < n; i++) {
            double xi = ((b - a) * nodo[i] + a + b) / 2.0;
            suma += coef[i] * funcion(xi);
        }

        return (b - a) / 2.0 * suma;
    }

    public static void main(String[] args) {
        // Parámetros de integración
        double limiteInferior = 0;
        double limiteSuperior = 1;
        int numeroPuntosGauss = 4;

        // Cálculo de la integral
        double resultado = cuadraturaGaussiana(limiteInferior, limiteSuperior, numeroPuntosGauss);

        // Mostrar resultado
        System.out.println("El resultado de la integral es: " + resultado);
    }
}
