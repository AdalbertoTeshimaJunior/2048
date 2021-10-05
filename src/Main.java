import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char opcao;
        Game game = new Game();
        game.jogo();
        System.out.println(game.toString(game));
        do {
            opcao = sc.next().toUpperCase().charAt(0);
            switch (opcao) {
                case 'W' -> {
                    game.cima();
                    game.colocaNumero();
                }
                case 'A' -> {
                    game.esquerda();
                    game.colocaNumero();
                }
                case 'S' -> {
                    game.baixo();
                    game.colocaNumero();
                }
                case 'D' -> {
                    game.direita();
                    game.colocaNumero();
                }
                case 'L' -> {
                    System.out.println("\nSaindo . . .\n");
                    return;
                }
                default -> System.out.println("Digite uma opção válida! ( W - A - S - D )");
            }
            System.out.println(game.toString(game));
        } while (game.fim());
    }
}