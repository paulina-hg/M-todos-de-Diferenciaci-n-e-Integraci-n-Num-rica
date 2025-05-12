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
