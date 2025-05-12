
import java.util.Scanner;
import java.util.function.Function;


// Clase principal
public class MetodoTrapecio {
    public static void main(String[] args) {
        CapturaDatos datos = new CapturaDatos();
        datos.leerDatos();


        IntegradorTrapecio calculadora = new IntegradorTrapecio();
        double resultado = calculadora.calcular(datos);


        Resultados salida = new Resultados();
        salida.mostrar(resultado);
    }
}



// Clase encargada de capturar los datos
class CapturaDatos {
    private double limiteInferior, limiteSuperior;
    private int segmentos;
    private Function<Double, Double> funcion;


    public void leerDatos() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Método del Trapecio - Integración Numérica");


        System.out.println("Ingresa una función (x como variable):");
        String funcionTexto = entrada.nextLine();
        this.funcion = x -> evaluar(funcionTexto.replace("x", Double.toString(x)));


        System.out.print("Límite inferior a: ");
        limiteInferior = entrada.nextDouble();


        System.out.print("Límite superior b: ");
        limiteSuperior = entrada.nextDouble();


        System.out.print("Número de divisiones (n): ");
        segmentos = entrada.nextInt();
    }


    private double evaluar(String expresion) {
        try {
            return new Object() {
                int pos = -1, ch;
                void siguiente() { ch = (++pos < expresion.length()) ? expresion.charAt(pos) : -1; }
                boolean aceptar(int esperado) {
                    while (ch == ' ') siguiente();
                    if (ch == esperado) { siguiente(); return true; }
                    return false;
                }
                double analizar() {
                    siguiente();
                    double resultado = expresion();
                    if (pos < expresion.length()) throw new RuntimeException("Error: " + (char)ch);
                    return resultado;
                }
                double expresion() {
                    double x = termino();
                    while (true) {
                        if (aceptar('+')) x += termino();
                        else if (aceptar('-')) x -= termino();
                        else return x;
                    }
                }
                double termino() {
                    double x = factor();
                    while (true) {
                        if (aceptar('*')) x *= factor();
                        else if (aceptar('/')) x /= factor();
                        else if (aceptar('^')) x = Math.pow(x, factor());
                        else return x;
                    }
                }
                double factor() {
                    if (aceptar('+')) return factor();
                    if (aceptar('-')) return -factor();
                    double x;
                    int inicio = this.pos;
                    if (aceptar('(')) {
                        x = expresion();
                        aceptar(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') siguiente();
                        x = Double.parseDouble(expresion.substring(inicio, this.pos));
                    } else if (ch >= 'a' && ch <= 'z') {
                        while (ch >= 'a' && ch <= 'z') siguiente();
                        String func = expresion.substring(inicio, this.pos);
                        x = factor();
                        switch (func) {
                            case "sin": x = Math.sin(x); break;
                            case "cos": x = Math.cos(x); break;
                            case "esp": x = Math.exp(x); break;
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


    public double getA() { return limiteInferior; }
    public double getB() { return limiteSuperior; }
    public int getN() { return segmentos; }
    public Function<Double, Double> getFuncion() { return funcion; }
}


// Clase que realiza la integración
class IntegradorTrapecio {
    public double calcular(CapturaDatos datos) {
        double a = datos.getA();
        double b = datos.getB();
        int n = datos.getN();
        Function<Double, Double> f = datos.getFuncion();


        double paso = (b - a) / n;
        double total = (f.apply(a) + f.apply(b)) / 2.0;


        for (int i = 1; i < n; i++) {
            double x = a + i * paso;
            total += f.apply(x);
        }


        return paso * total;
    }
}


// Clase para mostrar resultados
class Resultados {
    public void mostrar(double valor) {
        System.out.println("Resultado aproximado: " + valor);
    }
}
