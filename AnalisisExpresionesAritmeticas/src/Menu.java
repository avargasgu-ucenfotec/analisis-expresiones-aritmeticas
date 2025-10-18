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
            obtenerOpcion(pila.generarPila(expresion));
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
        System.out.println("[3] Evaluar la expresión aritmética");
        System.out.println("[4] Salir");
        System.out.print("Seleccione una opción del menú: ");
    }

    public void obtenerOpcion(Pila pila) {
        Scanner scanner = new Scanner(System.in);
        String seleccion = scanner.nextLine();
        boolean expresionValida = false;
        switch (seleccion) {
            case "1":
                System.out.println("\nPila almacenada\n");
                pila.imprimirPila(pila);
                break;
            case "2":
                if (pila.tieneOperadorFinal(pila) || pila.tieneOperadorInicio(pila)) {
                    estaCorriendo = false;
                    break;
                }
                System.out.println("\nExpresión aritmética es válida.");
                expresionValida = true;
                break;
            case "3":
                System.out.println("\nEvaluando...");
                if (expresionValida) {
                    System.out.println("\nEvaluando...");
                } else {
                    System.out.println("\nDebe analizar la expresión antes de evaluar,");
                }
                break;
            case "4":
                System.out.println("\nCerrando el programa...");
                estaCorriendo = false;
                break;
            default:
                System.out.println("\nOpción no válida, por favor intente de nuevo.");
        }
    }
}
