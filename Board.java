import java.util.ArrayList;

public class Board {
    private int joueur1 = 0;
    private int joueur2 = 1;
    private int[][] board;

    public Board() {
        this.board = new int[7][6];
    }

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
        for (int i = 0; i < this.board[x].length; i++) {
            if (this.board[x][i] == this.joueur1 || this.board[x][i] == this.joueur2) {
                hauteur++;
            }
        }
        this.board[x][hauteur] = joueurEnCours;
    }

    // retourne la liste de coup possible
    public ArrayList<Integer> getListCoup() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < this.board.length; i++) {
            int caseLibre = 6;
            for (int j = 0; j < this.board[i][j]; j++) {
                if (this.board[i][j] == this.joueur1 || this.board[i][j] == this.joueur2) {
                    caseLibre--;
                }
            }
            if (caseLibre != 0) {
                result.add(i);
            }
        }
        return result;
    }

    public String toString() {
        String result = new String();
        result += "---------------------\n";
        for (int i = 0; i < this.board.length; i++) {
            if (i == 0) {

            }
        }
    }
}