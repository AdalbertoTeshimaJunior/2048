import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {

    private int game[][] = new int[4][4];

    /**Váriaveis globais*/
    private Random r = new Random();
    private int numero, max, sorte1, sorte2, sorte3, sorte4;
    private String posicao;
    private boolean ocupado, t1, t2, t3, t4;
    List ocupados = new ArrayList();

    /**Scanner*/
    Scanner sc = new Scanner(System.in);

    /**Cria o A tabela. */
    public void populaGame() {
        do {
            sorte1 = aleatorio();
            sorte2 = aleatorio();
            sorte3 = aleatorio();
            sorte4 = aleatorio();
        } while (sorte1 == sorte3 && sorte2 == sorte4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == sorte1 && j == sorte2) || (i == sorte3 && j == sorte4)) {
                    game[i][j] = geraNumero();
                    adiciona(i, j);
                } else game[i][j] = 0;
            }
        }
    }

    /**Funções.*/
    public int aleatorio() {
        numero = r.nextInt(4);
        return numero;
    }
    public int geraNumero() {
        numero = r.nextInt(9);
        if (numero < 8) numero = 2;
        else numero = 4;
        return numero;
    }
    public void colocaNumero() {
        do {
            sorte1 = aleatorio();
            sorte2 = aleatorio();
        } while (verifica(sorte1, sorte2));
        game[sorte1][sorte2] = geraNumero();
        adiciona(sorte1, sorte2);
    }
    public boolean verifica(int num1, int num2) {
        ocupado = false;
        posicao = num1 + "_" + num2;
        for (Object posicoes : ocupados) {
            if (posicao.equals(posicoes)) ocupado = true;
        }
        return ocupado;
    }
    public boolean igual(int num1, int num2, int num3, int num4) {
        if (game[num1][num2] == game[num3][num4]) return true;
        else return false;
    }
    public void adiciona(int num1, int num2) {
        posicao = num1 + "_" + num2;
        ocupados.add(posicao);
    }
    public void retira(int num1, int num2) {
        posicao = num1 + "_" + num2;
        ocupados.remove(posicao);
        game[num1][num2] = 0;
    }
    public void auxCimaBaixo(int n1, int n2, int n3, int n4) {
        for (int i = 0; i < 4; i++) {
            t1 = verifica(n1, i);
            t2 = verifica(n2, i);
            t3 = verifica(n3, i);
            t4 = verifica(n4, i);
            if (t4) {
                if (t3) {
                    if (t2) {
                        if (t1) {
                            if (igual(n1, i, n2, i)) {
                                if (igual(n3, i, n4, i)) {
                                    game[n2][i] = game[n3][i] * 2;
                                    retira(n3, i);
                                } else {
                                    game[n2][i] = game[n3][i];
                                    game[n3][i] = game[n4][i];
                                }
                                game[n1][i] *= 2;
                                retira(n4, i);
                            } else if (igual(n2, i, n3, i)) {
                                game[n2][i] *= 2;
                                game[n3][i] = game[n4][i];
                                retira(n4, i);
                            } else if (igual(n3, i, n4, i)) {
                                game[n3][i] *= 2;
                                retira(n4, i);
                            }
                        } else {
                            if (igual(n2, i, n3, i)) {
                                game[n1][i] = game[n2][i] * 2;
                                game[n2][i] = game[n4][i];
                                retira(n3, i);
                            } else {
                                game[n1][i] = game[n2][i];
                                if (igual(n3, i, n4, i)) {
                                    game[n2][i] = game[n3][i] * 2;
                                    retira(n3, i);
                                } else {
                                    game[n2][i] = game[n3][i];
                                    game[n3][i] = game[n4][i];
                                }
                            }
                            retira(n4, i);
                            adiciona(n1, i);
                        }
                    } else if (t1) {
                        if (igual(n1, i, n3, i)) {
                            game[n1][i] *= 2;
                            game[n2][i] = game[n4][i];
                            retira(n3, i);
                        } else if (igual(n3, i, n4, i)) {
                            game[n2][i] = game[n3][i] * 2;
                            retira(n3, i);
                        } else {
                            game[n2][i] = game[n3][i];
                            game[n3][i] = game[n4][i];
                        }
                        retira(n4, i);
                        adiciona(n2, i);
                    } else {
                        if (igual(n3, i, n4, i)) {
                            game[n1][i] = game[n3][i] * 2;
                        } else {
                            game[n1][i] = game[n3][i];
                            game[n2][i] = game[n4][i];
                            adiciona(n2, i);
                        }
                        retira(n3, i);
                        retira(n4, i);
                        adiciona(n1, i);
                    }
                } else if (t2) {
                    if (t1) {
                        if (igual(n1, i, n2, i)) {
                            game[n1][i] *= 2;
                            game[n2][i] = game[n4][i];
                        } else if (igual(n2, i, n4, i)) {
                            game[n2][i] *= 2;
                        } else {
                            game[n3][i] = game[n4][i];
                            adiciona(n3, i);
                        }
                    } else {
                        if (igual(n2, i, n4, i)) {
                            game[n1][i] = game[n2][i] * 2;
                            retira(n2, i);
                        } else {
                            game[n1][i] = game[n2][i];
                            game[n2][i] = game[n4][i];
                        }
                        adiciona(n1, i);
                    }
                    retira(n4, i);
                } else {
                    if (t1) {
                        if (igual(n1, i, n4, i)) {
                            game[n1][i] *= 2;
                        } else {
                            game[n2][i] = game[n4][i];
                            adiciona(n2, i);
                        }
                    } else {
                        game[n1][i] = game[n4][i];
                        adiciona(n1, i);
                    }
                    retira(n4, i);
                }
            } else if (t3) {
                if (t2) {
                    if (t1) {
                        if (igual(n1, i, n2, i)) {
                            game[n1][i] *= 2;
                            game[n2][i] = game[n3][i];
                            retira(n3, i);
                        } else if (igual(n2, i, n3, i)) {
                            game[n2][i] *= 2;
                            retira(n3, i);
                        }
                    } else {
                        if (igual(n2, i, n3, i)) {
                            game[n1][i] = game[n2][i] * 2;
                            retira(n2, i);
                        } else {
                            game[n1][i] = game[n2][i];
                            game[n2][i] = game[n3][i];
                        }
                        retira(n3, i);
                        adiciona(n1, i);
                    }
                } else if (t1) {
                    if (igual(n1, i, n3, i)) {
                        game[n1][i] *= 2;
                    } else {
                        game[n2][i] = game[n3][i];
                        adiciona(n2, i);
                    }
                    retira(n3, i);
                } else {
                    game[n1][i] = game[n3][i];
                    retira(n3, i);
                    adiciona(n1, i);
                }
            } else if (t2) {
                if (t1) {
                    if (igual(n1, i, n2, i)) {
                        game[n1][i] *= 2;
                        retira(n2, i);
                    }
                } else {
                    game[n1][i] = game[n2][i];
                    retira(n2, i);
                    adiciona(n1, i);
                }
            }
        }
    }
    public void auxDireitaEsquerda(int n1, int n2, int n3, int n4) {
        for (int i = 0; i < 4; i++) {
            t1 = verifica(i, n1);
            t2 = verifica(i, n2);
            t3 = verifica(i, n3);
            t4 = verifica(i, n4);
            if (t4) {
                if (t3) {
                    if (t2) {
                        if (t1) {
                            if (igual(i, n1, i, n2)) {
                                if (igual(i, n3, i, n4)) {
                                    game[i][n2] = game[i][n3] * 2;
                                    retira(i, n3);
                                } else {
                                    game[i][n2] = game[i][n3];
                                    game[i][n3] = game[i][n4];
                                }
                                game[i][n1] *= 2;
                                retira(i, n4);
                            } else if (igual(i, n2, i, n3)) {
                                game[i][n2] *= 2;
                                game[i][n3] = game[i][n4];
                                retira(i, n4);
                            } else if (igual(i, n3, i, n4)) {
                                game[i][n3] *= 2;
                                retira(i, n4);
                            }
                        } else {
                            if (igual(i, n2, i, n3)) {
                                game[i][n1] = game[i][n2] * 2;
                                game[i][n2] = game[i][n4];
                                retira(i, n3);
                            } else {
                                game[i][n1] = game[i][n2];
                                if (igual(i, n3, i, n4)) {
                                    game[i][n2] = game[i][n3] * 2;
                                    retira(i, n3);
                                } else {
                                    game[i][n2] = game[i][n3];
                                    game[i][n3] = game[i][n4];
                                }
                            }
                            retira(i, n4);
                            adiciona(i, n1);
                        }
                    } else if (t1) {
                        if (igual(i, n1, i, n3)) {
                            game[i][n1] *= 2;
                            game[i][n2] = game[i][n4];
                            retira(i, n3);
                        } else if (igual(i, n3, i, n4)) {
                            game[i][n2] = game[i][n3] * 2;
                            retira(i, n3);
                        } else {
                            game[i][n2] = game[i][n3];
                            game[i][n3] = game[i][n4];
                        }
                        retira(i, n4);
                        adiciona(i, n2);
                    } else {
                        if (igual(i, n3, i, n4)) {
                            game[i][n1] = game[i][n3] * 2;
                        } else {
                            game[i][n1] = game[i][n3];
                            game[i][n2] = game[i][n4];
                            adiciona(i, n2);
                        }
                        retira(i, n3);
                        retira(i, n4);
                        adiciona(i, n1);
                    }
                } else if (t2) {
                    if (t1) {
                        if (igual(i, n1, i, n2)) {
                            game[i][n1] *= 2;
                            game[i][n2] = game[i][n4];
                        } else if (igual(i, n2, i, n4)) {
                            game[i][n2] *= 2;
                        } else {
                            game[i][n3] = game[i][n4];
                            adiciona(i, n3);
                        }
                    } else {
                        if (igual(i, n2, i, n4)) {
                            game[i][n1] = game[i][n2] * 2;
                            retira(i, n2);
                        } else {
                            game[i][n1] = game[i][n2];
                            game[i][n2] = game[i][n4];
                        }
                        adiciona(i, n1);
                    }
                    retira(i, n4);
                } else {
                    if (t1) {
                        if (igual(i, n1, i, n4)) {
                            game[i][n1] *= 2;
                        } else {
                            game[i][n2] = game[i][n4];
                            adiciona(i, n2);
                        }
                    } else {
                        game[i][n1] = game[i][n4];
                        adiciona(i, n1);
                    }
                    retira(i, n4);
                }
            } else if (t3) {
                if (t2) {
                    if (t1) {
                        if (igual(i, n1, i, n2)) {
                            game[i][n1] *= 2;
                            game[i][n2] = game[i][n3];
                            retira(i, n3);
                        } else if (igual(i, n2, i, n3)) {
                            game[i][n2] *= 2;
                            retira(i, n3);
                        }
                    } else {
                        if (igual(i, n2, i, n3)) {
                            game[i][n1] = game[i][n2] * 2;
                            retira(i, n2);
                        } else {
                            game[i][n1] = game[i][n2];
                            game[i][n2] = game[i][n3];
                        }
                        retira(i, n3);
                        adiciona(i, n1);
                    }
                } else if (t1) {
                    if (igual(i, n1, i, n3)) {
                        game[i][n1] *= 2;
                    } else {
                        game[i][n2] = game[i][n3];
                        adiciona(i, n2);
                    }
                    retira(n3, i);
                } else {
                    game[i][n1] = game[i][n3];
                    retira(i, n3);
                    adiciona(i, n1);
                }
            } else if (t2) {
                if (t1) {
                    if (igual(i, n1, i, n2)) {
                        game[i][n1] *= 2;
                        retira(i, n2);
                    }
                } else {
                    game[i][n1] = game[i][n2];
                    retira(i, n2);
                    adiciona(i, n1);
                }
            }
        }
    }

    /**Jogo.*/
    public void jogo() {
        System.out.println("Bem Vindo ao 2048, utilize as letras W, A, S, D para jogar:\nCima (W) - Baixo (S) - Esquerda (A) - Direita (D) - Sair (L)\n(Qualquer botão para continuar)");
        sc.next();
        System.out.println("\n");
        populaGame();
    }
    public boolean fim() {
        max = 0;
        for (Object posicoes : ocupados) {
            max++;
        }
        if ( max == 16) {
            return false;
        } else return true;
    }

    /**Movimentos.*/
    public void cima() {
        auxCimaBaixo(0, 1, 2, 3);
    }
    public void baixo() {
        auxCimaBaixo(3, 2, 1, 0);
    }
    public void esquerda() {
        auxDireitaEsquerda(0, 1, 2, 3);
    }
    public void direita() {
        auxDireitaEsquerda(3, 2, 1, 0);
    }

    /**Representação textual da lista.*/
    public String toString(Game matriz) {
        StringBuilder sb = new StringBuilder("\n|-------|-------|-------|-------|\n");

        for (int i = 0; i < 4; i++) {
            sb.append("|   ");
            for (int j = 0; j < 4; j++) {
                if (game[i][j] == 0) {
                    sb.append("    |   ");
                } else if (game[i][j] < 10) {
                    sb.append(game[i][j] + "   |   ");
                } else if (game[i][j] < 100) {
                    sb.append(game[i][j] + "  |   ");
                } else if (game[i][j] < 1000){
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(game[i][j] + "  |   ");
                } else if (game[i][j] < 10000) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(game[i][j] + " |   ");
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(game[i][j] + " |   ");
                }
            }
            sb.append("\n|-------|-------|-------|-------|\n");
        }
        return sb.toString();
    }

}
