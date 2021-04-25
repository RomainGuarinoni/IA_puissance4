import java.util.ArrayList;

public class Board {
    private int joueur1 = 1;
    private int joueur2 = 2;
    private int[][] board;

    public Board() {
        this.board = new int[6][7];
    }

    public Board(int joueur1, int joueur2) {
        this.board = new int[6][7];
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    // Board [ligne][colonne]
    public Board(int[][] board) {
        this.board = this.copyBoard(board);
    }

    // permet de copier le tableau passer en paramètre sans la référence
    private int[][] copyBoard(int[][] board) {
        int[][] copyBoard = new int[7][6];
        for (int i = 0; i < copyBoard.length; i++) {
            for (int j = 0; j < copyBoard[i].length; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        return copyBoard;
    }

    // joue un coup sur le board
    public void play(int x, int joueurEnCours) {
        int hauteur = 0;
        x--;
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][x] == this.joueur1 || this.board[i][x] == this.joueur2) {
                hauteur++;
            }
        }
        try {
            this.board[this.board.length - (hauteur + 1)][x] = joueurEnCours;
        } catch (Exception e) {
            System.out.println("La colonne " + x + " est déjà rempli à fond");
        }

    }

    // retourne la liste de coup possible
    public ArrayList<Integer> getListCoup() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < this.board[0].length; i++) {
            if (this.board[0][i] != this.joueur1 && this.board[0][i] != this.joueur2) {
                result.add(i);
            }
        }
        return result;
    }

    public String toString() {
        String result = new String();
        result += "\n\n";
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] == this.joueur1 || this.board[i][j] == this.joueur2) {
                    result += " | " + this.board[i][j];
                } else {
                    result += " |  ";
                }
            }

            result += " |\n";
        }
        result += "   1   2   3   4   5   6   7 ";
        return result;
    }
}