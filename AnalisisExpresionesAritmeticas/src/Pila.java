import java.util.ArrayList;

public class Pila {

    //Atributos
    private final ArrayList<Character> pilaCaracter;
    private byte top;

    //Métodos
    //Constructor
    public Pila() {
        pilaCaracter = new ArrayList<>();
        top = -1;
    }

    //Funcionales
    public void push(char caracter) {
        pilaCaracter.add(++top, caracter);
    }

    public char pop() {
        if (pilaCaracter.isEmpty()) {
            return '\0';
        }
        return pilaCaracter.remove(top--);
    }

    public char peek() {
        if (pilaCaracter.isEmpty()) {
            return '\0';
        }
        return pilaCaracter.get(top);
    }
}
