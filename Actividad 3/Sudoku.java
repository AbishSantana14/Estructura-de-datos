public class Sudoku {

    public static boolean resolver(int[][] tablero) {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (tablero[f][c] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (puedePoner(tablero, f, c, num)) {
                            tablero[f][c] = num;
                            if (resolver(tablero)) {
                                return true;
                            }
                            tablero[f][c] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean puedePoner(int[][] tablero, int fila, int col, int num) {
        // Mirar la fila
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num) {
                return false;
            }
        }
        
        // Mirar la columna
        for (int i = 0; i < 9; i++) {
            if (tablero[i][col] == num) {
                return false;
            }
        }
        
        // Mirar el cuadrito 3x3
        int inicioFila = (fila / 3) * 3;
        int inicioCol = (col / 3) * 3;
        
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioCol; j < inicioCol + 3; j++) {
                if (tablero[i][j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public static void mostrar(int[][] tablero) {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(tablero[i][j]);
                }
                if (j == 2 || j == 5 || j == 8) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("+---+---+---+");
            }
        }
        System.out.println("+---+---+---+");
    }

    public static void main(String[] args) {
        
        int[][] miSudoku = {
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
        
        System.out.println("sudoku:");
        mostrar(miSudoku);
        
        System.out.println("Calculando...");
        
        if (resolver(miSudoku)) {
            System.out.println("Listooo");
            mostrar(miSudoku);
        } else {
            System.out.println("No pude :(");
        }
    }
}