
public class Main {
    public static void main(String[] args) {
        int joueur1 = 1;
        int joueur2 = 2;
        Board board = new Board(joueur1, joueur2);
        System.out.print(board);
        board.play(1, 1);
        board.play(1, 2);
        board.play(1, 2);
        board.play(1, 2);
        board.play(1, 2);
        board.play(1, 2);
        board.play(1, 2);
        board.play(1, 2);

        board.play(1, 2);
        System.out.println(board);
    }
}
