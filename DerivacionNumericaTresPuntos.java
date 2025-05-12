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
