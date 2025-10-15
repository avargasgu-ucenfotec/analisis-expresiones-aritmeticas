import java.util.ArrayList;

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
}
