import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        char movimento;

        inicioDeJogo(scanner,tabuleiro);
        System.out.println(tabuleiro);

        do {
            movimento = scanner.next().toUpperCase().charAt(0);
            switch (movimento) {
                case 'W' -> {
                    tabuleiro.movimentoWS(0, 1, 2, 3);
                    tabuleiro.adicionarPosicaoAleatoria();
                }
                case 'A' -> {
                    tabuleiro.movimentoAD(0, 1, 2, 3);
                    tabuleiro.adicionarPosicaoAleatoria();
                }
                case 'S' -> {
                    tabuleiro.movimentoWS(3, 2, 1, 0);
                    tabuleiro.adicionarPosicaoAleatoria();
                }
                case 'D' -> {
                    tabuleiro.movimentoAD(3, 2, 1, 0);
                    tabuleiro.adicionarPosicaoAleatoria();
                }
                case 'L' -> {
                    System.out.println("\nSaindo . . .\n");
                    return;
                }
                default -> System.out.println("Digite uma opção válida! ( W - A - S - D - L )");
            }
            System.out.println(tabuleiro);
        } while (tabuleiro.fim());
    }

    public static void inicioDeJogo(Scanner sc, Tabuleiro tabuleiro) {
        System.out.println("Bem Vindo ao 2048, utilize as letras W, A, S, D para jogar:\nCima (W) - Baixo (S) - Esquerda (A) - Direita (D) - Sair (L)\n(Qualquer botão para continuar)");
        sc.next();
        System.out.println("\n");
        tabuleiro.populaTabuleiro();
    }
}