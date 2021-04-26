import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MonteCarlo {
    Random rnd;
    private int max_iteration;
    private int ennemi;
    private int bot;

    public MonteCarlo(int ennemi, int bot) {
        rnd = new Random();
        this.ennemi = ennemi;
        this.bot = bot;
        this.max_iteration = 5000;
    }

    private int getJoueurEnnemi(int joueurEnCours) {
        if (joueurEnCours == this.ennemi) {
            return this.bot;
        }
        return this.ennemi;
    }

    private Node selection(Node root) {
        ArrayList<Node> childArray = new ArrayList<Node>();
        while (!root.getChildArray().isEmpty() && !root.getBoard().partieTermine()) {
            childArray = root.getChildArray();
            childArray.forEach(node -> {
                node.updateUctScore(2);
            });
            root = Collections.max(childArray);
        }
        return root;
    }

    private Node expension(Node node) {
        if (!node.getBoard().partieTermine()) {
            int joueurEnCours = getJoueurEnnemi(node.getJoueur());
            ArrayList<Integer> listeCoups = node.getBoard().getListCoup();
            ArrayList<Node> newArrayChild = new ArrayList<Node>();
            for (int i = 0; i < listeCoups.size(); i++) {
                Board newBoard = new Board(node.getBoard().getBoard(), this.bot, this.ennemi);
                newBoard.play(listeCoups.get(i), joueurEnCours);
                Node tmpNode = new Node(newBoard, node, joueurEnCours);
                newArrayChild.add(tmpNode);
            }
            node.setChildArray(newArrayChild);
            return newArrayChild.get(0);
        }
        return node;
    }

    private int simulation(Node node) {
        int joueurEnCours = node.getJoueur();

        if (node.getBoard().partieTermine()) {
            if (node.getBoard().getGagnant() == this.ennemi) {
                node.setScoreVictoire(Integer.MAX_VALUE);
                return node.getBoard().getGagnant();
            }
        }
        while (!node.getBoard().partieTermine()) {
            joueurEnCours = getJoueurEnnemi(joueurEnCours);
            ArrayList<Integer> coupPossible = node.getBoard().getListCoup();
            int coupJoue = coupPossible.get(rnd.nextInt(coupPossible.size()));
            node.getBoard().play(coupJoue, joueurEnCours);
        }
        return node.getBoard().getGagnant();
    }

    private void backPropagation(Node node, int joueurGagnant) {
        Node nodeAux = node;
        while (nodeAux != null) {
            nodeAux.incrementNbVisite();
            if (joueurGagnant == this.bot) {
                if (nodeAux.getJoueur() == this.bot) {
                    nodeAux.incrementScore();
                }
            }
            nodeAux = nodeAux.getParent();
        }
    }
}
