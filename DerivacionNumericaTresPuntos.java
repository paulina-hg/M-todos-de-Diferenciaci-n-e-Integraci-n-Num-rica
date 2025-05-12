import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class DerivacionNumericaTresPuntos {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DERIVACIÓN NUMÉRICA (MÉTODO DE 3 PUNTOS)");
        System.out.print("\nIngrese la función f(x): ");
        String funcionStr = scanner.nextLine();
        System.out.print("Ingrese el valor de x: ");
        double x = scanner.nextDouble();
        System.out.print("Ingrese el valor de h: ");
        double h = scanner.nextDouble();
       
        Function funcion = new Function(funcionStr);
       
        if (!funcion.esValida()) {
            System.out.println("Error: La función ingresada no es válida.");
            System.out.println("Asegúrese de usar sintaxis correcta.");
            System.exit(1);
        }
       
        System.out.println("\nRESULTADOS:");
        System.out.printf("f(%.4f) = %.3f%n", x, x, funcion.evaluar(x));
        System.out.printf("Derivada hacia adelante: %.3f%n", derivadaAdelante(funcion, x, h));
        System.out.printf("Derivada hacia atrás:    %.3f%n", derivadaAtras(funcion, x, h));
        System.out.printf("Derivada centrada:      %.3f%n", derivadaCentrada(funcion, x, h));
        scanner.close();
    }
static class Function {
        private String expresion;
        private ExpressionBuilder builder;
       
        public Function(String expresion) {
            this.expresion = expresion;
            try {
                this.builder = new ExpressionBuilder(expresion)
                    .variables("x")
                    .build();
            } catch (Exception e) {
                this.builder = null;
            }
        }
        public boolean esValida() {
            return builder != null;
        }
        public double evaluar(double x) {
            if (!esValida()) return Double.NaN;
            return builder.setVariable("x", x).evaluate();
        }
    }
    public static double derivadaAdelante(Function f, double x, double h) {
        return (-f.evaluar(x + 2*h) + 4*f.evaluar(x + h) - 3*f.evaluar(x)) / (2*h);
    }
    public static double derivadaAtras(Function f, double x, double h) {
        return (3*f.evaluar(x) - 4*f.evaluar(x - h) + f.evaluar(x - 2*h)) / (2*h);
    }
    public static double derivadaCentrada(Function f, double x, double h) {
        return (f.evaluar(x + h) - f.evaluar(x - h)) / (2*h);
    }
}
