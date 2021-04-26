import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int joueur1 = 1;
        int joueur2 = 2;
        // le bot c'est le joueur 2;
        MonteCarlo monteCarlo = new MonteCarlo(joueur1, joueur2);
        Board board = new Board(joueur1, joueur2);
        int joueurEnCours = joueur1;
        while (!board.partieTermine()) {
            System.out.println(board);
            joueurEnCours = 3 - joueurEnCours;
            if (joueurEnCours == joueur1) {
                System.out.println("Coup disponible : ");
                System.out.println(board.getListCoup());
                System.out.println("Rentrez votre coup : ");
                int coup = in.nextInt();
                board.play(coup, joueurEnCours);
            } else {
                board.play(monteCarlo.meilleurCoup(board), joueurEnCours);
            }

        }
        System.out.println("La partie est terminée");
        System.out.println("le gagnant est : " + board.getGagnant());

    }
}
