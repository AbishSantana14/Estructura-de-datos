import java.util.Scanner;

public class SumaSubconjuntos {
   
    public static boolean existeSubconjuntoSuma(int[] conjunto, int n, int sumaObjetivo) {
        // Caso base 1: Si la suma objetivo es 0, siempre existe un subconjunto (el vacío)
        if (sumaObjetivo == 0) {
            return true;
        }
        
        // Caso base 2: Si no hay elementos y la suma no es 0, no existe subconjunto
        if (n == 0) {
            return false;
        }
        
        // Si el último elemento es mayor que la suma objetivo, lo ignoramos
        if (conjunto[n - 1] > sumaObjetivo) {
            return existeSubconjuntoSuma(conjunto, n - 1, sumaObjetivo);
        }
        
        // Probamos dos opciones recursivamente:
        // 1. Incluir el último elemento y reducir la suma objetivo
        // 2. Excluir el último elemento y mantener la suma objetivo
        return existeSubconjuntoSuma(conjunto, n - 1, sumaObjetivo) ||
               existeSubconjuntoSuma(conjunto, n - 1, sumaObjetivo - conjunto[n - 1]);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SUMA DE SUBCONJUNTOS ===");
        
        // Pedimos el tamaño del conjunto
        System.out.print("Ingrese el tamaño del conjunto: ");
        int tamaño = scanner.nextInt();
        
        int[] conjunto = new int[tamaño];
        
        // Leemos los elementos del conjunto
        System.out.println("Ingrese los " + tamaño + " números del conjunto:");
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            conjunto[i] = scanner.nextInt();
        }
        
        // Pedimos la suma objetivo
        System.out.print("Ingrese la suma objetivo: ");
        int sumaObjetivo = scanner.nextInt();
        
        // Verificamos si existe un subconjunto que sume el objetivo
        boolean existe = existeSubconjuntoSuma(conjunto, tamaño, sumaObjetivo);
        
        if (existe) {
            System.out.println(" Sí existe un subconjunto que suma " + sumaObjetivo);
        } else {
            System.out.println(" No existe un subconjunto que suma " + sumaObjetivo);
        }
        
        scanner.close();
    }
}