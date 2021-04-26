import java.util.ArrayList;

public class Board {
    private int joueur1 = 1;
    private int joueur2 = 2;
    private int[][] board;
    private int vainqueur;

    public Board() {
        this.board = new int[6][7];
    }

    public Board(int joueur1, int joueur2) {
        this.board = new int[6][7];
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    // Board [ligne][colonne]
    public Board(int[][] board, int joueur1, int joueur2) {
        this.board = this.copyBoard(board);
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
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
        if (x > 6 || x < 0) {
            System.out.println("coup impossible");
            return;
        }
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][x] == this.joueur1 || this.board[i][x] == this.joueur2) {
                hauteur++;
            }
        }
        try {
            this.board[this.board.length - (hauteur + 1)][x] = joueurEnCours;
        } catch (Exception e) {
            System.out.println("La colonne " + (x + 1) + " est déjà rempli à fond");
        }

    }

    // retourne la liste de coup possible
    public ArrayList<Integer> getListCoup() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < this.board[0].length; i++) {
            if (this.board[0][i] != this.joueur1 && this.board[0][i] != this.joueur2) {
                result.add(i + 1);
            }
        }
        return result;
    }

    private ArrayList<ArrayList<Integer>> getAllDiag() {
        ArrayList<ArrayList<Integer>> diags = new ArrayList<ArrayList<Integer>>();
        for (int i = 3; i <= 5; i++) {
            // System.out.println("new diags " + i);
            int iAux = i;
            ArrayList<Integer> diag1 = new ArrayList<Integer>();
            ArrayList<Integer> diag2 = new ArrayList<Integer>();
            for (int j = 0, k = 6; j < i && k >= 0; j++, k--) {
                // System.out.println("calcul");
                diag1.add(this.board[iAux][j]);
                diag2.add(this.board[iAux][k]);
                iAux--;
            }
            diags.add(diag1);
            diags.add(diag2);
        }
        for (int i = 1; i <= 3; i++) {
            ArrayList<Integer> diag3 = new ArrayList<Integer>();
            ArrayList<Integer> diag4 = new ArrayList<Integer>();
            int iAux = i;
            int j = 5;
            while (iAux <= 6) {
                diag3.add(this.board[j][iAux]);
                diag4.add(this.board[j][6 - iAux]);
                iAux++;
                j--;
            }
            diags.add(diag3);
            diags.add(diag4);
        }
        return diags;
    }

    public boolean partieTermine() {
        ArrayList<ArrayList<Integer>> diags = this.getAllDiag();
        for (int i = 0; i < diags.size(); i++) {
            if (quatreAligneList(diags.get(i))) {
                return true;
            }
        }
        for (int j = 0; j < this.board.length; j++) {
            if (quatreAligneArray(this.board[j])) {
                return true;
            }
        }
        for (int k = 0; k < 7; k++) {
            int[] array = new int[6];
            for (int l = 0; l < this.board.length; l++) {
                array[l] = this.board[l][k];
            }
            if (this.quatreAligneArray(array)) {
                return true;
            }
        }
        return false;
    }

    private boolean quatreAligneArray(int[] array) {
        int joueurEnCours = array[0];
        int currentAlignement = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == joueurEnCours && array[i] != 0) {
                currentAlignement++;
                if (currentAlignement == 4) {
                    this.vainqueur = joueurEnCours;
                    return true;
                }
            } else {
                joueurEnCours = array[i];
                currentAlignement = 1;
            }
        }
        return false;
    }

    private boolean quatreAligneList(ArrayList<Integer> array) {
        int joueurEnCours = array.get(0);
        int currentAlignement = 1;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) == joueurEnCours && array.get(i) != 0) {
                currentAlignement++;
                if (currentAlignement == 4) {
                    this.vainqueur = joueurEnCours;
                    return true;
                }
            } else {
                joueurEnCours = array.get(i);
                currentAlignement = 1;
            }
        }
        return false;
    }

    public int getGagnant() {
        return this.vainqueur;
    }

    public int[][] getBoard() {
        return this.board;
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
        result += "   1   2   3   4   5   6   7 \n";
        return result;
    }
}