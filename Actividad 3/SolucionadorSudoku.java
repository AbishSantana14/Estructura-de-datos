import java.util.Scanner;

public class SolucionadorSudoku {
    
    // Tamaño del tablero (9x9)
    private static final int TAMAÑO = 9;
   
    public static boolean resolverSudoku(int[][] tablero) {
        // Buscamos la próxima celda vacía (representada por 0)
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                
                // Si encontramos una celda vacía
                if (tablero[fila][columna] == 0) {
                    
                    // Probamos todos los números del 1 al 9
                    for (int numero = 1; numero <= 9; numero++) {
                        
                        // Verificamos si podemos poner este número aquí
                        if (esNumeroValido(tablero, fila, columna, numero)) {
                            
                            // Ponemos el número temporalmente
                            tablero[fila][columna] = numero;
                            
                            // Intentamos resolver el resto del tablero
                            if (resolverSudoku(tablero)) {
                                return true; // ¡Solución encontrada!
                            }
                            
                            // Si llegamos aquí, el número no funcionó
                            // Lo quitamos (backtrack) y probamos el siguiente
                            tablero[fila][columna] = 0;
                        }
                    }
                    
                    // Si ningún número funcionó, el tablero no tiene solución
                    return false;
                }
            }
        }
        
        // Si no hay celdas vacías, el tablero está resuelto
        return true;
    }
    
    private static boolean esNumeroValido(int[][] tablero, int fila, int columna, int numero) {
        // Verificar la fila: que el número no exista en la misma fila
        for (int c = 0; c < TAMAÑO; c++) {
            if (tablero[fila][c] == numero) {
                return false;
            }
        }
        
        // Verificar la columna: que el número no exista en la misma columna
        for (int f = 0; f < TAMAÑO; f++) {
            if (tablero[f][columna] == numero) {
                return false;
            }
        }
        
        // Verificar el cuadrante 3x3
        int inicioFila = (fila / 3) * 3; // Fila inicial del cuadrante
        int inicioColumna = (columna / 3) * 3; // Columna inicial del cuadrante
        
        for (int f = inicioFila; f < inicioFila + 3; f++) {
            for (int c = inicioColumna; c < inicioColumna + 3; c++) {
                if (tablero[f][c] == numero) {
                    return false;
                }
            }
        }
        
        // Si pasó todas las verificaciones, el número es válido
        return true;
    }
    
    /**
     * Imprime el tablero de Sudoku de forma bonita
     * @param tablero el tablero a imprimir
     */
    public static void imprimirTablero(int[][] tablero) {
        System.out.println("┌───────┬───────┬───────┐");
        
        for (int fila = 0; fila < TAMAÑO; fila++) {
            System.out.print("│ ");
            
            for (int columna = 0; columna < TAMAÑO; columna++) {
                if (tablero[fila][columna] == 0) {
                    System.out.print(". "); // Punto para celdas vacías
                } else {
                    System.out.print(tablero[fila][columna] + " ");
                }
                
                // Separadores entre cuadrantes
                if (columna == 2 || columna == 5) {
                    System.out.print("│ ");
                }
            }
            
            System.out.println("│");
            
            // Línea separadora entre cuadrantes
            if (fila == 2 || fila == 5) {
                System.out.println("├───────┼───────┼───────┤");
            }
        }
        
        System.out.println("└───────┴───────┴───────┘");
    }
    
    /**
     * Método principal
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SOLUCIONADOR DE SUDOKU ===");
        System.out.println("Tablero de ejemplo cargado automáticamente");
        
        // Tablero de ejemplo (0 = celda vacía)
        int[][] tablero = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        System.out.println("\nTablero inicial:");
        imprimirTablero(tablero);
        
        System.out.println("\nResolviendo...");
        
        // Intentamos resolver el Sudoku
        if (resolverSudoku(tablero)) {
            System.out.println("¡Sudoku resuelto!");
            System.out.println("\nTablero solución:");
            imprimirTablero(tablero);
        } else {
            System.out.println("Este Sudoku no tiene solución :(");
        }
        
        scanner.close();
    }
}