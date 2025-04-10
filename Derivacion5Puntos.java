import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Derivacion5Puntos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DERIVACIÓN NUMÉRICA (MÉTODO DE 5 PUNTOS CENTRADO)");

        System.out.print("\nIngrese la función f(x): ");
        String funcionStr = scanner.nextLine();

        System.out.print("Ingrese el valor de x: ");
        double x = scanner.nextDouble();

        System.out.print("Ingrese el valor de h: ");
        double h = scanner.nextDouble();

        Funcion funcion = new Funcion(funcionStr);

        if (!funcion.esValida()) {
            System.out.println("Error: La función ingresada no es válida.");
            System.out.println("Asegúrese de usar sintaxis correcta.");
            System.exit(1);
        }

        double resultado = derivadaCincoPuntos(funcion, x, h);

        System.out.println("\nRESULTADOS:");
        System.out.printf("f(%.4f) = %.4f%n", x, funcion.evaluar(x));
        System.out.printf("Derivada centrada (5 puntos): %.4f%n", resultado);

        scanner.close();
    }

    // Clase interna para manejar funciones
    static class Funcion {
        private String expresion;
        private Expression expresionEvaluable;

        public Funcion(String expresion) {
            this.expresion = expresion;
            try {
                this.expresionEvaluable = new ExpressionBuilder(expresion)
                        .variables("x")
                        .build();
            } catch (Exception e) {
                this.expresionEvaluable = null;
            }
        }

        public boolean esValida() {
            return expresionEvaluable != null;
        }

        public double evaluar(double x) {
            if (!esValida()) return Double.NaN;
            return expresionEvaluable.setVariable("x", x).evaluate();
        }
    }

    // Método de 5 puntos centrado
    public static double derivadaCincoPuntos(Funcion f, double x, double h) {
        double fx2h = f.evaluar(x + 2*h);
        double fxh  = f.evaluar(x + h);
        double fxmh = f.evaluar(x - h);
        double fxm2h = f.evaluar(x - 2*h);

        return redondear((-fx2h + 8*fxh - 8*fxmh + fxm2h) / (12*h), 4);
    }

    // Función para redondear a N cifras decimales
    public static double redondear(double valor, int decimales) {
        double factor = Math.pow(10, decimales);
        return Math.round(valor * factor) / factor;
    }
}
