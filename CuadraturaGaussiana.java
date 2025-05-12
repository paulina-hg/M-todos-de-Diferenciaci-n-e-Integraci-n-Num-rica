import java.util.HashMap;
import java.util.Map;

public class CuadraturaGaussiana {

    // Función que queremos integrar: f(x) = e^(-x^2)
    public static double funcion(double x) {
        return Math.exp(-x * x);
    }