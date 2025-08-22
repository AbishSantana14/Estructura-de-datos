import java.util.Scanner;

// Nodo para lista enlazada
class Nodo {
    String dato;
    Nodo siguiente;

    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Pila para historial de comandos
class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    // Verificar si la pila está vacía
    public boolean estaVacia() {
        return tope == null;
    }

    // Agregar elemento a la pila
    public void push(String comando) {
        Nodo nuevo = new Nodo(comando);
        nuevo.siguiente = tope;
        tope = nuevo;
        System.out.println("Comando '" + comando + "' ejecutado");
        ejecutar(comando);
    }

    // Remover elemento de la pila
    public String pop() {
        if (estaVacia()) {
            System.out.println("No hay comandos para deshacer");
            return null;
        }
        String comando = tope.dato;
        tope = tope.siguiente;
        System.out.println("Deshaciendo: " + comando);
        return comando;
    }

    // Ver el tope de la pila
    public String peek() {
        if (estaVacia()) {
            System.out.println("No hay comandos recientes");
            return null;
        }
        return tope.dato;
    }

    // Ejecutar simulación de comandos
    private void ejecutar(String comando) {
        switch (comando) {
            case "ls":
                System.out.println("Documentos  Imágenes  Música");
                break;
            case "cd documentos":
                System.out.println("Directorio cambiado a Documentos");
                 System.out.println("C:\\Users\\SANTANA");
                break;
            case "cd imagenes":
                System.out.println("Directorio cambiado a Imágenes");
                System.out.println("C:\\Users\\SANTANA\\Pictures");
                break;
            case "cd musica":
                System.out.println("Directorio cambiado a Música");
                System.out.println ("C:\\Users\\SANTANA\\Music\\Rolitas chidas");
                break;
            case "pwd":
                System.out.println("/home/usuario");
                break;
            case "help":
                System.out.println("Comandos: ls, cd, pwd, help");
                break;
            default:
                System.out.println("Comando no reconocido");
        }
    }

    // Mostrar historial de comandos
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Historial vacío");
            return;
        }
        System.out.println("--- Historial ---");
        Nodo actual = tope;
        while (actual != null) {
            System.out.println("- " + actual.dato);
            actual = actual.siguiente;
        }
    }
}

// Cola para procesos
class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    // Verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Agregar proceso a la cola
    public void encolar(String proceso) {
        Nodo nuevo = new Nodo(proceso);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        System.out.println("Proceso '" + proceso + "' en cola");
    }

    // Ejecutar próximo proceso
    public String desencolar() {
        if (estaVacia()) {
            System.out.println("No hay procesos");
            return null;
        }
        String proceso = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        System.out.println("Ejecutando: " + proceso);
        return proceso;
    }

    // Ver próximo proceso
    public String verProximo() {
        if (estaVacia()) {
            System.out.println("No hay procesos");
            return null;
        }
        return frente.dato;
    }

    // Mostrar procesos en cola
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Cola vacía");
            return;
        }
        System.out.println("--- Procesos ---");
        Nodo actual = frente;
        while (actual != null) {
            System.out.println("- " + actual.dato);
            actual = actual.siguiente;
        }
    }
}

// Clase principal
public class Actividad2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila pila = new Pila();
        Cola cola = new Cola();
        
        int opcion;
        
        System.out.println("Sistema Operativo ");
        
        do {
            // Menú principal
            System.out.println("\n1. Ejecutar comando");
            System.out.println("2. Deshacer comando");
            System.out.println("3. Ver último comando");
            System.out.println("4. Ver historial");
            System.out.println("5. Agregar proceso");
            System.out.println("6. Ejecutar proceso");
            System.out.println("7. Ver próximo proceso");
            System.out.println("8. Ver cola de procesos");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    // Ejecutar comando
                    System.out.println("Comandos: ls, cd documentos, cd imagenes, cd musica, pwd, help");
                    System.out.print("Comando: ");
                    String cmd = sc.nextLine();
                    pila.push(cmd);
                    break;
                    
                case 2:
                    // Deshacer comando
                    pila.pop();
                    break;
                    
                case 3:
                    // Ver último comando
                    String ultimo = pila.peek();
                    if (ultimo != null) {
                        System.out.println("Último: " + ultimo);
                    }
                    break;
                    
                case 4:
                    // Ver historial
                    pila.mostrar();
                    break;
                    
                case 5:
                    // Agregar proceso
                    System.out.print("Nombre del proceso: ");
                    String proc = sc.nextLine();
                    cola.encolar(proc);
                    break;
                    
                case 6:
                    // Ejecutar proceso
                    cola.desencolar();
                    break;
                    
                case 7:
                    // Ver próximo proceso
                    String prox = cola.verProximo();
                    if (prox != null) {
                        System.out.println("Próximo: " + prox);
                    }
                    break;
                    
                case 8:
                    // Ver cola de procesos
                    cola.mostrar();
                    break;
                    
                case 9:
                    // Salir
                    System.out.println("Adiós");
                    break;
                    
                default:
                    System.out.println("Opción inválida");
            }
            
        } while (opcion != 9);
        
        sc.close();
    }
}