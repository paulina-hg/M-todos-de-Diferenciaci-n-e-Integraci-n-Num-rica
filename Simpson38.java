public class Simpson38 {

    public static void main(String[] args) {
        // Definir parámetros fijos para la integración
        double a = 0;       // Límite inferior
        double b = 2;       // Límite superior
        int n = 6;          // Número de segmentos (debe ser múltiplo de 3)
        
        // Calcular la integral usando la regla de Simpson 3/8
        double resultado = simpson38(a, b, n);
        
        // Imprimir información y resultado formateado
        System.out.println("Función: x*sin(x)");               // Función integrada
        System.out.println("Límite Inferior: " + (int)a);      // Límite inferior
        System.out.println("Límite Superior: " + (int)b);      // Límite superior
        System.out.println("Número de Segmentos: " + n);       // Número de segmentos
        System.out.printf("Resultado de la integral por el método de Simpson 3/8: %.4g%n", resultado);
    }

    // Define la función a integrar, modificable según necesidades
    public static double funcion(double x) {
        return x * Math.sin(x); // Función f(x) = x * sin(x)
    }

    // Calcula la integral numérica usando la regla de Simpson 3/8
    public static double simpson38(double a, double b, int n) {
        // Validar que n sea múltiplo de 3
        if (n % 3 != 0) {
            throw new IllegalArgumentException("n debe ser múltiplo de 3");
        }
        
        // Calcular el tamaño del paso
        double h = (b - a) / n;
        
        // Sumar los extremos: f(a) + f(b)
        double suma = funcion(a) + funcion(b);
        
        // Sumar los puntos intermedios con coeficientes 3 (no múltiplo de 3) y 2 (múltiplo de 3)
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma += (i % 3 == 0) ? 2 * funcion(x) : 3 * funcion(x);
        }
        
        // Aplicar la fórmula: (3h/8) * [f(a) + f(b) + 3*suma_no_múltiplo_3 + 2*suma_múltiplo_3]
        return (3 * h / 8) * suma;
    }
} 
