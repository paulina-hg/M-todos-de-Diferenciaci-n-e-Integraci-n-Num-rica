public class Simpson13 {

    public static void main(String[] args) {
        // Definir parámetros fijos para la integración
        double a = 0;       // Límite inferior
        double b = 2;       // Límite superior
        int n = 4;          // Número de segmentos (debe ser par)
        
        // Calcular la integral usando la regla de Simpson 1/3
        double resultado = simpson13(a, b, n);
        
        // Imprimir información y resultado formateado
        System.out.println("Función: x*sin(x)");               // Función integrada (corregido de x^2)
        System.out.println("Límite Inferior: " + (int)a);      // Límite inferior
        System.out.println("Límite Superior: " + (int)b);      // Límite superior
        System.out.println("Número de Segmentos: " + n);       // Número de segmentos
        System.out.printf("Resultado de la integral por el método de Simpson 1/3: %.4g%n", resultado);
    }

    // Define la función a integrar, modificable según necesidades
    public static double funcion(double x) {
        return x * Math.sin(x); // Función f(x) = x * sin(x)
    }

    // Calcula la integral numérica usando la regla de Simpson 1/3
    public static double simpson13(double a, double b, int n) {
        // Validar que n sea par
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n debe ser un número par");
        }
        
        // Calcular el tamaño del paso
        double h = (b - a) / n;
        
        // Sumar los extremos: f(a) + f(b)
        double suma = funcion(a) + funcion(b);
        
        // Sumar los puntos intermedios con coeficientes 4 (impar) y 2 (par)
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma += (i % 2 == 0) ? 2 * funcion(x) : 4 * funcion(x);
        }
        
        // Aplicar la fórmula: (h/3) * [f(a) + f(b) + 4*suma_impar + 2*suma_par]
        return (h / 3) * suma;
    }
}
