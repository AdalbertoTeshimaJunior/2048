import java.util.Random;

public class GeradorAleatorio {

    private Random r = new Random();
    private int numero;

    // Gera um número aleatório para uma posição
    public int gerarPosicaoAleatoria() {
        numero = r.nextInt(4);
        return numero;
    }
    // Gera um valor aleatório
    public int gerarNumeroAleatorio() {
        numero = r.nextInt(9);
        if (numero < 8) numero = 2;
        else numero = 4;
        return numero;
    }

}
