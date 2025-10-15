import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    //Función que recibe una variable tipo String que almacena un operador matemático y devuelve un número entero que
    //representa el orden en el cual los operadores son evaluados uno respecto del otro.
    public static int precedencia(String cadena) {
        return switch (cadena) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    //Función que recibe una variable tipo String e indica si representa un valor numérico.
    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Función que recibe una variable tipo String que almacena una expresión aritmética en notación infijo y devuelve un
    //objeto tipo pila que almacena la expresión aritmética en notación postfijo.
    public static Pila postfijo(String expresion) {
        Pila auxiliar= new Pila();
        Pila resultado = new Pila();
        String[] elementos = expresion.split(" ");
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(elementos));
        for (String elemento : lista) {
            if (esNumero(elemento)) {
                auxiliar.push(elemento);
            } else {
                while (!resultado.estaVacia() && precedencia(elemento) <= precedencia(resultado.peek())) {
                    auxiliar.push(resultado.pop());
                }
                resultado.push(elemento);
            }
        }
        while (!auxiliar.estaVacia()) {
            resultado.push(auxiliar.pop());
        }
        return resultado;
    }

    //Función que recibe un objeto tipo pila que almacena una expresión aritmética en notación postfijo y devuelve una
    //cadena con el resultado de evaluar la expresión aritmética.
    public static String evaluar(Pila pila) {
        Pila resultado = new Pila();
        while (!pila.estaVacia()) {
            if (esNumero(pila.peek())) {
                resultado.push(pila.pop());
            } else {
                int operando2 = Integer.parseInt(resultado.pop());
                int operando1 = Integer.parseInt(resultado.pop());
                switch (pila.pop()) {
                    case "+":
                        int suma = operando1 + operando2;
                        resultado.push(Integer.toString(suma));
                        break;
                    case "-":
                        int resta = operando1 - operando2;
                        resultado.push(Integer.toString(resta));
                        break;
                    case "*":
                        int multiplicacion = operando1 * operando2;
                        resultado.push(Integer.toString(multiplicacion));
                        break;
                    case "/":
                        int division = operando1 / operando2;
                        resultado.push(Integer.toString(division));
                        break;
                }
            }
        }
        return resultado.peek();
    }

    public static void main(String[] args) {
        String miExpresion = "40 / 2 + 1 * 2 + 15";
        Pila miPila = new Pila();

        miPila = postfijo(miExpresion);

        System.out.println(evaluar(miPila));
    }
}
