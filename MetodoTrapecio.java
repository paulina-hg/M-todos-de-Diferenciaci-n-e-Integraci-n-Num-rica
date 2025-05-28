import java.util.Scanner;
import java.util.function.Function;

// Clase principal que coordina el flujo del programa
public class MetodoTrapecio {
    public static void main(String[] args) {
        // Captura los datos del usuario
        CapturaDatos datos = new CapturaDatos();
        datos.leerDatos();

        // Realiza el cálculo de la integral usando el método del trapecio
        IntegradorTrapecio calculadora = new IntegradorTrapecio();
        double resultado = calculadora.calcular(datos);

        // Muestra el resultado al usuario
        Resultados salida = new Resultados();
        salida.mostrar(resultado);
    }
}

// Clase encargada de capturar los datos de entrada del usuario
class CapturaDatos {
    private double limiteInferior, limiteSuperior;
    private int segmentos;
    private Function<Double, Double> funcion;

    // Método para leer la función, límites de integración y cantidad de segmentos
    public void leerDatos() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Método del Trapecio - Integración Numérica");

        // Solicita al usuario la función en formato texto (x como variable)
        System.out.println("Ingresa una función (x como variable):");
        String funcionTexto = entrada.nextLine();
        // Reemplaza 'x' por el valor numérico e invoca al evaluador
        this.funcion = x -> evaluar(funcionTexto.replace("x", Double.toString(x)));

        // Límite inferior del intervalo de integración
        System.out.print("Límite inferior a: ");
        limiteInferior = entrada.nextDouble();

        // Límite superior del intervalo
        System.out.print("Límite superior b: ");
        limiteSuperior = entrada.nextDouble();

        // Número de segmentos (divisiones del intervalo)
        System.out.print("Número de divisiones (n): ");
        segmentos = entrada.nextInt();
    }

    // Método privado que evalúa la expresión matemática ingresada
    private double evaluar(String expresion) {
        try {
            // Parser manual para evaluar expresiones matemáticas básicas
            return new Object() {
                int pos = -1, ch;

                void siguiente() { ch = (++pos < expresion.length()) ? expresion.charAt(pos) : -1; }

                boolean aceptar(int esperado) {
                    while (ch == ' ') siguiente();
                    if (ch == esperado) { siguiente(); return true; }
                    return false;
                }

                // Comienza el análisis de la expresión completa
                double analizar() {
                    siguiente();
                    double resultado = expresion();
                    if (pos < expresion.length()) throw new RuntimeException("Error: " + (char)ch);
                    return resultado;
                }

                // Expresión: suma y resta
                double expresion() {
                    double x = termino();
                    while (true) {
                        if (aceptar('+')) x += termino();
                        else if (aceptar('-')) x -= termino();
                        else return x;
                    }
                }

                // Términos: multiplicación, división y potencia
                double termino() {
                    double x = factor();
                    while (true) {
                        if (aceptar('*')) x *= factor();
                        else if (aceptar('/')) x /= factor();
                        else if (aceptar('^')) x = Math.pow(x, factor());
                        else return x;
                    }
                }

                // Factores: números, paréntesis, funciones y signos
                double factor() {
                    if (aceptar('+')) return factor(); // Signo +
                    if (aceptar('-')) return -factor(); // Signo -
                    double x;
                    int inicio = this.pos;

                    // Paréntesis
                    if (aceptar('(')) {
                        x = expresion();
                        aceptar(')');
                    }
                    // Números
                    else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') siguiente();
                        x = Double.parseDouble(expresion.substring(inicio, this.pos));
                    }
                    // Funciones matemáticas (sin, cos, log, etc.)
                    else if (ch >= 'a' && ch <= 'z') {
                        while (ch >= 'a' && ch <= 'z') siguiente();
                        String func = expresion.substring(inicio, this.pos);
                        x = factor();
                        switch (func) {
                            case "sin": x = Math.sin(x); break;
                            case "cos": x = Math.cos(x); break;
                            case "esp": x = Math.exp(x); break; // 'esp' debe ser 'exp'
                            case "log": x = Math.log(x); break;
                            case "sqrt": x = Math.sqrt(x); break;
                            default: throw new RuntimeException("Función no reconocida: " + func);
                        }
                    } else {
                        throw new RuntimeException("Carácter no esperado: " + (char)ch);
                    }
                    return x;
                }
            }.analizar();
        } catch (Exception e) {
            throw new RuntimeException("Error al evaluar expresión: " + e.getMessage());
        }
    }

    // Métodos para acceder a los datos capturados
    public double getA() { return limiteInferior; }
    public double getB() { return limiteSuperior; }
    public int getN() { return segmentos; }
    public Function<Double, Double> getFuncion() { return funcion; }
}

// Clase que aplica el método del trapecio para calcular la integral
class IntegradorTrapecio {
    public double calcular(CapturaDatos datos) {
        double a = datos.getA();
        double b = datos.getB();
        int n = datos.getN();
        Function<Double, Double> f = datos.getFuncion();

        // Calcula el tamaño del paso h
        double paso = (b - a) / n;

        // Aplica la fórmula del trapecio compuesta
        double total = (f.apply(a) + f.apply(b)) / 2.0;
        for (int i = 1; i < n; i++) {
            double x = a + i * paso;
            total += f.apply(x); // suma los valores intermedios
        }

        return paso * total; // resultado final
    }
}

// Clase que muestra el resultado de la integración
class Resultados {
    public void mostrar(double valor) {
        System.out.println("Resultado aproximado: " + valor);
    }
}
