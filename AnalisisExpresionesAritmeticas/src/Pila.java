import java.util.ArrayList;
import java.util.Arrays;

public class Pila {

    //Atributos
    private final ArrayList<String> pilaCadena;
    private byte top;

    //Métodos
    //Constructor
    public Pila() {
        pilaCadena = new ArrayList<>();
        top = -1;
    }

    //Funcionales
    public void push(String cadena) {
        pilaCadena.add(++top, cadena);
    }

    public String pop() {
        if (pilaCadena.isEmpty()) {
            return null;
        }
        return pilaCadena.remove(top--);
    }

    public String peek() {
        if (pilaCadena.isEmpty()) {
            return null;
        }
        return pilaCadena.get(top);
    }

    public boolean estaVacia() {
        return pilaCadena.isEmpty();
    }

    public Pila generarPila(String expresion) {
        Pila resultado = new Pila();
        String[] elementos = expresion.split(" ");
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(elementos));
        for (String elemento : lista) {
            resultado.push(elemento);
        }
        return resultado;
    }

    public void imprimirPila(Pila pila) {
        String costados = "| %5s |%n";
        String fondo = " ------- %n";
        while (!pila.estaVacia()) {
            System.out.format(costados, pila.peek());
            System.out.format(fondo);
            pila.pop();
        }
    }

    public boolean tieneOperadorFinal(Pila pila) {
        if (pila.estaVacia()) {
            System.out.println(
                    "Expresión aritmética no válida.\n" +
                            "La expresión ingresada está vacía.");
            return false;
        } else if (
                pila.peek().equals("+") ||
                        pila.peek().equals("-") ||
                        pila.peek().equals("*") ||
                        pila.peek().equals("/")
        ) {
            System.out.println(
                    "\nExpresión aritmética no válida.\n" +
                            "Existe un operador aritmético al final de la expresión ingresada.");
            return true;
        } else {
            return false;
        }
    }

    public boolean tieneOperadorInicio(Pila pila) {
        if (pila.estaVacia()) {
            System.out.println(
                    "\nExpresión aritmética no válida.\n" +
                            "La expresión ingresada está vacía.");
            return false;
        } else if (
                pilaCadena.getFirst().equals("+") ||
                        pilaCadena.getFirst().equals("-") ||
                        pilaCadena.getFirst().equals("*") ||
                        pilaCadena.getFirst().equals("/")
        ) {
            System.out.println(
                    "\nExpresión aritmética no válida.\n" +
                            "Existe un operador aritmético al inicio de la expresión ingresada.");
            return true;
        } else {
            return false;
        }
    }

    public boolean tieneDuplicidad(Pila pila) {
        if (pila.estaVacia()) {
            System.out.println(
                    "\nExpresión aritmética no válida.\n" +
                            "La expresión ingresada está vacía.");
            return false;
        }
        int contadorOperadores = 0;
        int contadorOperandos = 0;
        while (!pila.estaVacia()) {
            if (pila.peek().equals("+") ||
                    pila.peek().equals("-") ||
                    pila.peek().equals("*") ||
                    pila.peek().equals("/")
            ) {
                contadorOperadores += 1;
                contadorOperandos = 0;
                pila.pop();
            } else if (pila.peek().matches("\\d+")) {
                contadorOperandos += 1;
                contadorOperadores = 0;
                pila.pop();
            }
            if (contadorOperadores > 1) {
                System.out.println(
                        "\nExpresión aritmética no válida.\n" +
                                "Existen dos operadores aritméticos consecutivos en la expresión ingresada.");
                return true;
            }
            if (contadorOperandos > 1) {
                System.out.println(
                        "\nExpresión aritmética no válida.\n" +
                                "Existen dos operandos consecutivos en la expresión ingresada.");
                return true;
            }
        }
        return false;
    }
}