public class Arbre {
    Node root;

    public Arbre(int joueur, Board board) {
        root = new Node(joueur, board);
    }

    public Node getRoot() {
        return this.root;
    }

    public void addChild(Node parent, Node enfant) {
        parent.getChildArray().add(enfant);
    }
}
