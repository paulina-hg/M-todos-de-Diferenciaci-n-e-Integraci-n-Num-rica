// Importa la clase Scanner para leer datos del usuario por consola
import java.util.Scanner;

// Importa las clases necesarias de la librería exp4j para evaluar expresiones matemáticas
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

// Clase principal del programa
public class DerivacionNumericaTresPuntos {

    // Método principal que ejecuta el programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mensaje de bienvenida
        System.out.println("DERIVACIÓN NUMÉRICA (MÉTODO DE 3 PUNTOS)");

        // Solicita al usuario que ingrese la función f(x)
        System.out.print("\nIngrese la función f(x): ");
        String funcionStr = scanner.nextLine();

        // Solicita el valor de x donde se evaluará la derivada
        System.out.print("Ingrese el valor de x: ");
        double x = scanner.nextDouble();

        // Solicita el valor de h (paso pequeño)
        System.out.print("Ingrese el valor de h: ");
        double h = scanner.nextDouble();
       
        // Crea un objeto de tipo Function con la función ingresada
        Function funcion = new Function(funcionStr);
       
        // Verifica si la función ingresada es válida
        if (!funcion.esValida()) {
            System.out.println("Error: La función ingresada no es válida.");
            System.out.println("Asegúrese de usar sintaxis correcta.");
            System.exit(1); // Termina el programa si la función no es válida
        }
       
        // Imprime los resultados
        System.out.println("\nRESULTADOS:");
        System.out.printf("f(%.4f) = %.3f%n", x, x, funcion.evaluar(x));
        System.out.printf("Derivada hacia adelante: %.3f%n", derivadaAdelante(funcion, x, h));
        System.out.printf("Derivada hacia atrás:    %.3f%n", derivadaAtras(funcion, x, h));
        System.out.printf("Derivada centrada:      %.3f%n", derivadaCentrada(funcion, x, h));

        // Cierra el objeto Scanner
        scanner.close();
    }

    // Clase interna Function para manejar funciones matemáticas como objetos
    static class Function {
        private String expresion; // Expresión matemática en formato string
        private ExpressionBuilder builder; // Constructor de expresiones de exp4j
       
        // Constructor: recibe la expresión y construye el objeto evaluable
        public Function(String expresion) {
            this.expresion = expresion;
            try {
                this.builder = new ExpressionBuilder(expresion)
                    .variables("x") // Declara que "x" es una variable
                    .build(); // Construye la expresión
            } catch (Exception e) {
                this.builder = null; // Si hay error, se marca como inválida
            }
        }

        // Verifica si la expresión fue construida correctamente
        public boolean esValida() {
            return builder != null;
        }

        // Evalúa la función en un punto x dado
        public double evaluar(double x) {
            if (!esValida()) return Double.NaN;
            return builder.setVariable("x", x).evaluate();
        }
    }

    // Método para calcular la derivada hacia adelante (f'(x) ≈ [-f(x+2h) + 4f(x+h) - 3f(x)] / 2h)
    public static double derivadaAdelante(Function f, double x, double h) {
        return (-f.evaluar(x + 2*h) + 4*f.evaluar(x + h) - 3*f.evaluar(x)) / (2*h);
    }

    // Método para calcular la derivada hacia atrás (f'(x) ≈ [3f(x) - 4f(x-h) + f(x-2h)] / 2h)
    public static double derivadaAtras(Function f, double x, double h) {
        return (3*f.evaluar(x) - 4*f.evaluar(x - h) + f.evaluar(x - 2*h)) / (2*h);
    }

    // Método para calcular la derivada centrada (f'(x) ≈ [f(x+h) - f(x-h)] / 2h)
    public static double derivadaCentrada(Function f, double x, double h) {
        return (f.evaluar(x + h) - f.evaluar(x - h)) / (2*h);
    }
}
