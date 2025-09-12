class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;
    
    // Constructor
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}
class ArbolBinario {
    Nodo raiz;
    
    // Constructor
    public ArbolBinario() {
        this.raiz = null;
    }
    
    // Método para insertar un nodo
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }
    
    private Nodo insertarRecursivo(Nodo actual, int valor) {
        // Si el árbol está vacío, creamos el primer nodo
        if (actual == null) {
            return new Nodo(valor);
        }
        
        // Si el valor es menor, va a la izquierda
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } 
        // Si el valor es mayor, va a la derecha
        else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        
        return actual;
    }
    
    // Método para buscar un valor
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }
    
    private boolean buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return false;
        }
        
        if (valor == actual.valor) {
            return true;
        }
        
        if (valor < actual.valor) {
            return buscarRecursivo(actual.izquierdo, valor);
        } else {
            return buscarRecursivo(actual.derecho, valor);
        }
    }
    
    // Método para eliminar un nodo
    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }
    
    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        
        if (valor == actual.valor) {
            // Caso 1: Nodo sin hijos
            if (actual.izquierdo == null && actual.derecho == null) {
                return null;
            }
            
            // Caso 2: Nodo con un solo hijo
            if (actual.derecho == null) {
                return actual.izquierdo;
            }
            if (actual.izquierdo == null) {
                return actual.derecho;
            }
            
            // Caso 3: Nodo con dos hijos
            int valorMinimo = encontrarMinimo(actual.derecho);
            actual.valor = valorMinimo;
            actual.derecho = eliminarRecursivo(actual.derecho, valorMinimo);
            return actual;
        }
        
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
            return actual;
        }
        
        actual.derecho = eliminarRecursivo(actual.derecho, valor);
        return actual;
    }
    
    private int encontrarMinimo(Nodo raiz) {
        return raiz.izquierdo == null ? raiz.valor : encontrarMinimo(raiz.izquierdo);
    }
    
    // Recorridos del árbol
    
    // Recorrido Inorden
    public void inorden() {
        inordenRecursivo(raiz);
        System.out.println();
    }
    
    private void inordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inordenRecursivo(nodo.derecho);
        }
    }
    
    // Recorrido Preorden 
    public void preorden() {
        preordenRecursivo(raiz);
        System.out.println();
    }
    
    private void preordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preordenRecursivo(nodo.izquierdo);
            preordenRecursivo(nodo.derecho);
        }
    }
    
    // Recorrido Postorden 
    public void postorden() {
        postordenRecursivo(raiz);
        System.out.println();
    }
    
    private void postordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            postordenRecursivo(nodo.izquierdo);
            postordenRecursivo(nodo.derecho);
            System.out.print(nodo.valor + " ");
        }
    }
}


public class Parte1y2 {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        
        // Insertar valores
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        
        System.out.println("Recorrido Inorden:");
        arbol.inorden(); // 20 30 40 50 60 70 80
        
        System.out.println("Recorrido Preorden:");
        arbol.preorden(); // 50 30 20 40 70 60 80
        
        System.out.println("Recorrido Postorden:");
        arbol.postorden(); // 20 40 30 60 80 70 50
        
        // Buscar valores
        System.out.println("¿Existe 40? " + arbol.buscar(40)); 
        System.out.println("¿Existe 100? " + arbol.buscar(100)); 
        
        // Eliminar un nodo
        arbol.eliminar(20); // Elimina nodo sin hijos
        System.out.println("Inorden después de eliminar 20:");
        arbol.inorden(); // 30 40 50 60 70 80
    }
}