import java.util.Scanner;

public class FibonacciRecursivo {

    public static int fibonacci(int n) {
        // Casos base: si n es 0 o 1, devolvemos n
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        // Caso recursivo: fibonacci(n) = fibonacci(n-1) + fibonacci(n-2)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SERIE DE FIBONACCI RECURSIVA ===");
        System.out.print("Ingrese la posición que desea calcular: ");
        int n = scanner.nextInt();
        
        // Validamos que el número sea no negativo
        if (n < 0) {
            System.out.println("Error: La posición debe ser un número no negativo.");
        } else {
            int resultado = fibonacci(n);
            System.out.println("El número Fibonacci en la posición " + n + " es: " + resultado);
        }
        
        scanner.close();
    }
}