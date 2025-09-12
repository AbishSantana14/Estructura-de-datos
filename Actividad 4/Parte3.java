import java.util.ArrayList;
class Empleado {
    int id;
    String nombre;
    String puesto;
    
    public Empleado(int id, String nombre, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " - " + nombre + " (" + puesto + ")";
    }
}

class ArbolEmpleados {
    NodoEmpleado raiz;
    
    class NodoEmpleado {
        Empleado empleado;
        NodoEmpleado izquierdo;
        NodoEmpleado derecho;
        
        public NodoEmpleado(Empleado empleado) {
            this.empleado = empleado;
            this.izquierdo = null;
            this.derecho = null;
        }
    }
    
    public ArbolEmpleados() {
        this.raiz = null;
    }
    
    // Insertar empleado
    public void insertarEmpleado(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }
    
    private NodoEmpleado insertarRecursivo(NodoEmpleado actual, Empleado empleado) {
        if (actual == null) {
            return new NodoEmpleado(empleado);
        }
        
        if (empleado.id < actual.empleado.id) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, empleado);
        } else if (empleado.id > actual.empleado.id) {
            actual.derecho = insertarRecursivo(actual.derecho, empleado);
        }
        
        return actual;
    }
    
    // Buscar empleado por ID
    public Empleado buscarEmpleado(int id) {
        return buscarRecursivo(raiz, id);
    }
    
    private Empleado buscarRecursivo(NodoEmpleado actual, int id) {
        if (actual == null) {
            return null;
        }
        
        if (id == actual.empleado.id) {
            return actual.empleado;
        }
        
        if (id < actual.empleado.id) {
            return buscarRecursivo(actual.izquierdo, id);
        } else {
            return buscarRecursivo(actual.derecho, id);
        }
    }
    
    // Mostrar todos los empleados (inorden - ordenados por ID)
    public void mostrarEmpleados() {
        System.out.println("=== LISTA DE EMPLEADOS (Ordenados por ID) ===");
        inordenRecursivo(raiz);
        System.out.println();
    }
    
    private void inordenRecursivo(NodoEmpleado nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.println(nodo.empleado);
            inordenRecursivo(nodo.derecho);
        }
    }
}

class BusquedaSecuencial {
    // Búsqueda secuencial en una lista
    public static Empleado buscarEmpleadoSecuencial(ArrayList<Empleado> lista, int id) {
        for (Empleado emp : lista) {
            if (emp.id == id) {
                return emp;
            }
        }
        return null;
    }
}



public class Parte3 {
    public static void main(String[] args) {
        // Crear lista de empleados
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        ArbolEmpleados arbolEmpleados = new ArbolEmpleados();
        
        // Datos de empleados
        Empleado[] empleados = {
            new Empleado(105, "María López", "Gerente"),
            new Empleado(102, "Carlos Ruiz", "Desarrollador"),
            new Empleado(108, "Ana García", "Diseñadora"),
            new Empleado(101, "Pedro Martínez", "Analista"),
            new Empleado(107, "Laura Díaz", "Recursos Humanos"),
            new Empleado(103, "Juan Pérez", "Contador"),
           
        };
        
        System.out.println("=== SISTEMA DE GESTIÓN DE EMPLEADOS ===\n");
        
        // Insertar empleados en ambos sistemas
        System.out.println("Insertando empleados...");
        for (Empleado emp : empleados) {
            listaEmpleados.add(emp);
            arbolEmpleados.insertarEmpleado(emp);
        }
        
        // Mostrar empleados ordenados
        arbolEmpleados.mostrarEmpleados();
        
        // Realizar búsquedas
        System.out.println("=== BÚSQUEDAS ===");
        int[] idsABuscar = {101, 107, 105, 103};
        
        for (int id : idsABuscar) {
            System.out.println("\nBuscando empleado con ID: " + id);
            
            // Búsqueda en árbol binario
            long inicioArbol = System.nanoTime();
            Empleado resultadoArbol = arbolEmpleados.buscarEmpleado(id);
            long finArbol = System.nanoTime();
            
            // Búsqueda secuencial
            long inicioSecuencial = System.nanoTime();
            Empleado resultadoSecuencial = BusquedaSecuencial.buscarEmpleadoSecuencial(listaEmpleados, id);
            long finSecuencial = System.nanoTime();
            
            // Mostrar resultados
            System.out.println("Árbol binario: " + resultadoArbol);
            System.out.println("Tiempo: " + (finArbol - inicioArbol) + " nanosegundos");
            
            System.out.println("Búsqueda secuencial: " + resultadoSecuencial);
            System.out.println("Tiempo: " + (finSecuencial - inicioSecuencial) + " nanosegundos");
        }
        
        // Demostración de eficiencia con muchas búsquedas
        System.out.println("\n=== COMPARACIÓN DE EFICIENCIA ===");
        compararEficiencia(arbolEmpleados, listaEmpleados);
    }
    
    public static void compararEficiencia(ArbolEmpleados arbol, ArrayList<Empleado> lista) {
        int numeroBusquedas = 10000;
        System.out.println("Realizando " + numeroBusquedas + " búsquedas...");
        
        // Medir tiempo del árbol binario
        long inicioArbol = System.nanoTime();
        for (int i = 0; i < numeroBusquedas; i++) {
            int idAleatorio = (int)(Math.random() * 110) + 100;
            arbol.buscarEmpleado(idAleatorio);
        }
        long finArbol = System.nanoTime();
        
        // Medir tiempo de búsqueda secuencial
        long inicioSecuencial = System.nanoTime();
        for (int i = 0; i < numeroBusquedas; i++) {
            int idAleatorio = (int)(Math.random() * 110) + 100;
            BusquedaSecuencial.buscarEmpleadoSecuencial(lista, idAleatorio);
        }
        long finSecuencial = System.nanoTime();
        
        System.out.println("Tiempo árbol binario: " + (finArbol - inicioArbol) + " nanosegundos");
        System.out.println("Tiempo búsqueda secuencial: " + (finSecuencial - inicioSecuencial) + " nanosegundos");
        
        double diferencia = (double)(finSecuencial - inicioSecuencial) / (finArbol - inicioArbol);
        System.out.printf("El árbol binario es %.2f veces más rápido%n", diferencia);
    }
}