import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    //Clase "Menu" que despliega un mensaje de bienvenida y un menú en consola que permite mostrar dicha expresión en
    //forma de pila, analizar si dicha expresión es una expresión aritmética válida y evaluar dicha expresión
    //aritmética.

    //Atributos
    private boolean estaCorriendo;  //Bandera que indica si el menú está corriendo.
    private Pila pila;              //Pila que almacena los tokens de la expresión.

    //Métodos
    //Constructor
    public Menu() {
        estaCorriendo = true;
        pila = new Pila();
    }

    //Funcionales
    public void correrMenu() {
        String expresion = almacenarExpresion();
        while (estaCorriendo) {
            desplegarMenu();
            obtenerOpcion(pila.generarPila(expresion), expresion);
        }
    }

    public String almacenarExpresion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAnálisis de expresiones aritméticas");
        System.out.print("Ingrese una expresión aritmética: ");
        return scanner.nextLine();
    }

    public void desplegarMenu() {
        System.out.println("\nMenú");
        System.out.println("[1] Mostrar la expresión como una pila de tokens");
        System.out.println("[2] Analizar la validez de la expresión aritmética");
        System.out.println("[3] Salir");
        System.out.print("Seleccione una opción del menú: ");
    }

    public void obtenerOpcion(Pila pila, String expresion) {
        Scanner scanner = new Scanner(System.in);
        String seleccion = scanner.nextLine();
        boolean expresionValida = false;
        switch (seleccion) {
            case "1":
                System.out.println("\nPila almacenada\n");
                pila.imprimirPila(pila);
                break;
            case "2":
                if (pila.tieneOperadorFinal(pila)
                        || pila.tieneOperadorInicio(pila)
                        || pila.tieneDuplicidad(pila)
                       // || pila.tieneOperandoConsecutivo(pila)
                ) {
                    estaCorriendo = false;
                    break;
                }
                System.out.println("\nExpresión aritmética es válida.");
                System.out.print("El resultado de evaluar la expresión aritmética es: ");
                System.out.println(evaluar(postfijo(expresion)));
                break;
            case "3":
                System.out.println("\nCerrando el programa...");
                estaCorriendo = false;
                break;
            default:
                System.out.println("\nOpción no válida, por favor intente de nuevo.");
        }
    }

    //Función que recibe una variable tipo String que almacena un operador matemático y devuelve un número entero que
    //representa el orden en el cual los operadores son evaluados uno respecto del otro.
    public int precedencia(String cadena) {
        return switch (cadena) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    //Función que recibe una variable tipo String e indica si representa un número entero.
    public boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Función que recibe una variable tipo String que almacena una expresión aritmética en notación infijo y devuelve un
    //objeto tipo pila que almacena la expresión aritmética en notación postfijo.
    public Pila postfijo(String expresion) {
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
    public String evaluar(Pila pila) {
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
}
