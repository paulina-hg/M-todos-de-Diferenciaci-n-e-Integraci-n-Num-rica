import java.util.Scanner;
 import net.objecthunter.exp4j.Expression;
 import net.objecthunter.exp4j.ExpressionBuilder;
 
 public class Derivacion5Puntos {
 
     public static void main(String[] args) {
         // Configuración del scanner para entrada de usuario
         Scanner scanner = new Scanner(System.in);
         System.out.println("DERIVACIÓN NUMÉRICA (MÉTODO DE 5 PUNTOS CENTRADO)");
 
         // Solicitar la función al usuario
         System.out.print("\nIngrese la función f(x): ");
         String funcionStr = scanner.nextLine();
 
         // Solicitar el punto donde evaluar la derivada
         System.out.print("Ingrese el valor de x: ");
         double x = scanner.nextDouble();
 
         // Solicitar el tamaño del paso (h)
         System.out.print("Ingrese el valor de h: ");
         double h = scanner.nextDouble();
 
         // Crear objeto Función con la expresión ingresada
         Funcion funcion = new Funcion(funcionStr);
 
         // Validar que la función sea correcta
         if (!funcion.esValida()) {
             System.out.println("Error: La función ingresada no es válida.");
             System.out.println("Asegúrese de usar sintaxis correcta.");
             System.exit(1);
         }
 
         // Calcular la derivada usando el método de 5 puntos
         double resultado = derivadaCincoPuntos(funcion, x, h);
 
         // Mostrar resultados
         System.out.println("\nRESULTADOS:");
         System.out.printf("f(%.4f) = %.4f%n", x, funcion.evaluar(x));
         System.out.printf("Derivada centrada (5 puntos): %.4f%n", resultado);
 
         scanner.close();
     }
 
     /*
      * Clase interna para manejar funciones matemáticas
      * Permite evaluar expresiones ingresadas por el usuario
      */
     static class Funcion {
         private String expresion;  // Expresión original como string
         private Expression expresionEvaluable;  // Objeto para evaluar la expresión
 
         // Constructor: intenta parsear la expresión
         public Funcion(String expresion) {
             this.expresion = expresion;
             try {
                 this.expresionEvaluable = new ExpressionBuilder(expresion)
                         .variables("x")  // Variable permitida
                         .build();
             } catch (Exception e) {
                 this.expresionEvaluable = null;  // Si hay error, queda nulo
             }
         }
 
         // Verifica si la expresión es válida
         public boolean esValida() {
             return expresionEvaluable != null;
         }
 
         // Evalúa la función en un punto x dado
         public double evaluar(double x) {
             if (!esValida()) return Double.NaN;  // NaN si no es válida
             return expresionEvaluable.setVariable("x", x).evaluate();
         }
     }
 
     /*
      * Método de 5 puntos centrado para calcular derivadas
      * Fórmula: f'(x) ≈ [-f(x+2h) + 8f(x+h) - 8f(x-h) + f(x-2h)] / (12h)
      *
      * @param f La función a derivar
      * @param x Punto donde evaluar la derivada
      * @param h Tamaño del paso
      * @return Valor aproximado de la derivada
      */
     public static double derivadaCincoPuntos(Funcion f, double x, double h) {
         // Evaluar la función en los 5 puntos requeridos
         double fx2h = f.evaluar(x + 2*h);  // f(x+2h)
         double fxh  = f.evaluar(x + h);    // f(x+h)
         double fxmh = f.evaluar(x - h);    // f(x-h)
         double fxm2h = f.evaluar(x - 2*h); // f(x-2h)
 
         // Aplicar la fórmula de 5 puntos centrada
         return redondear((-fx2h + 8*fxh - 8*fxmh + fxm2h) / (12*h), 4);
     }
 
     /*
      * Función auxiliar para redondear números
      *
      * @param valor Número a redondear
      * @param decimales Cantidad de decimales deseada
      * @return Valor redondeado
      */
     public static double redondear(double valor, int decimales) {
         double factor = Math.pow(10, decimales);
         return Math.round(valor * factor) / factor;
     }


 }
