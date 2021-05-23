import java.io.Serializable;
import java.util.Scanner;

public class Tablero implements Serializable {
    private static Scanner sc = new Scanner(System.in);
    static int Filas = 7;
    static int Columnas = 7;
    boolean matriz[][] = new boolean[Columnas][Filas];
    int Jugador1=1;
    int Jugador2=1;
    Tablero(){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                int Random = (int) ( Math.random() * 2 + 1);
                if (Random==1){
                    matriz[i][j] = false;
                }
                else if (Random==2){
                    matriz[i][j] = true;
                }
            }
        }
    }
    public  void mostrarTablero(boolean matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j]==false){
                    System.out.print(" | " + " ᕙ(`▿´)ᕗ " + " | ");
                }
                else if (matriz[i][j]==true){
                    System.out.print(" | " + " ᕙ(`▿´)ᕗ " + " | ");
                }
            }
            System.out.println();
        }
    }
    public String comprobarTablero(int Y, int X) {
        int Juego=0;
            if (Y >= 0 && Y < matriz.length && X >= 0 && X < matriz.length) {
                if (matriz[Y][X]==false){
                    return "Sigue jugando";
                }
                else if (matriz[Y][X]==true){
                    return" Perdiste ";
                }
            }
        return null;
    }
}
