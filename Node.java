import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private int nbVisite;
    private int scoreVictoire;
    private double uctScore;
    private Node parent;
    private int joueur;
    private ArrayList<Node> childArray;
    private Board board;
    private int coup;

    public Node() {
        this.uctScore = 0;
        this.childArray = new ArrayList<>();
    }

    public Node(int joueur, Board board) {
        this.joueur = joueur;
        this.board = board;
        this.uctScore = 0;
        this.childArray = new ArrayList<>();
    }

    public Node(Board board, Node parent, int joueur, int coup) {
        this.uctScore = 0;
        this.joueur = joueur;
        this.board = board;
        this.parent = parent;
        this.childArray = new ArrayList<>();
        this.coup = coup;
    }

    public Node getParent() {
        return this.parent;
    }

    public int getNbVisite() {
        return this.nbVisite;
    }

    public void incrementNbVisite() {
        this.nbVisite++;
    }

    public void incrementScore() {
        this.scoreVictoire++;
    }

    public int getScoreVictoire() {
        return this.scoreVictoire;
    }

    public Board getBoard() {
        return this.board;
    }

    public double getUCT() {
        return this.uctScore;
    }

    public ArrayList<Node> getChildArray() {
        return this.childArray;
    }

    public void setCoup(int coup) {
        this.coup = coup;
    }

    public int getCoup() {
        return this.coup;
    }

    public void setScoreVictoire(int score) {
        this.scoreVictoire = score;
    }

    public void setChildArray(ArrayList<Node> childArray) {
        this.childArray = childArray;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    public int getJoueur() {
        return this.joueur;
    }

    public void updateUctScore(double C) {
        if (this.nbVisite == 0) {
            this.uctScore = Double.MAX_VALUE;
        } else {
            double exploitation = this.scoreVictoire / (double) this.nbVisite;

            double exploration = C * Math.sqrt(Math.log((double) this.parent.getNbVisite()) / (double) this.nbVisite);
            this.uctScore = exploitation + exploration;
        }
    }

    @Override
    public int compareTo(Node o) {
        if (this.getUCT() > o.getUCT()) {
            return 1;
        } else if (this.getUCT() < o.getUCT()) {
            return -1;
        }
        return 0;
    }
}
