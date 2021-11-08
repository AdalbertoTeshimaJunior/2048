import java.util.*;

public class Tabuleiro {

    /**
     * Váriaveis globais
     */
    private BlocoNumerico tabuleiro[][] = new BlocoNumerico[4][4];
    private int numero, max;
    ArrayList<int[]> posicoesOcupadas = new ArrayList<>();
    GeradorAleatorio geradorAleatorio = new GeradorAleatorio();

    /**
     * Popula o tabuleiro.
     */
    public void populaTabuleiro() {
        int sorte1, sorte2, sorte3, sorte4;
        do {
            sorte1 = geradorAleatorio.gerarPosicaoAleatoria();
            sorte2 = geradorAleatorio.gerarPosicaoAleatoria();
            sorte3 = geradorAleatorio.gerarPosicaoAleatoria();
            sorte4 = geradorAleatorio.gerarPosicaoAleatoria();
        } while (sorte1 == sorte3 && sorte2 == sorte4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == sorte1 && j == sorte2) || (i == sorte3 && j == sorte4)) {
                    tabuleiro[i][j] = new BlocoNumerico(new int[]{i, j}, geradorAleatorio.gerarNumeroAleatorio());
                    adicionarPosicaoOcupada(i, j);
                }else tabuleiro[i][j] = new BlocoNumerico(new int[]{i,j}, 0);
            }
        }
    }

    public void movimentoWS(int n1, int n2, int n3, int n4) {

        boolean t1, t2, t3, t4;

        for (int i = 0; i < 4; i++) {
            t1 = verificaBlocoOcupado(tabuleiro[n1][i]);
            t2 = verificaBlocoOcupado(tabuleiro[n2][i]);
            t3 = verificaBlocoOcupado(tabuleiro[n3][i]);
            t4 = verificaBlocoOcupado(tabuleiro[n4][i]);
            if (t4) {
                if (t3) {
                    if (t2) {
                        if (t1) {
                            if (tabuleiro[n1][i].equals(tabuleiro[n2][i])) {
                                if (tabuleiro[n1][i].equals(tabuleiro[n2][i])) {
                                    tabuleiro[n2][i].setValor(tabuleiro[n3][i].valor * 2);
                                    ;
                                    removerPosicaoOcupada(n3, i);
                                } else {
                                    tabuleiro[n2][i].valor = tabuleiro[n3][i].valor;
                                    tabuleiro[n3][i].valor = tabuleiro[n4][i].valor;
                                }
                                tabuleiro[n1][i].duplicateValue();
                                removerPosicaoOcupada(n4, i);
                            } else if (tabuleiro[n2][i].equals(tabuleiro[n3][i])) {
                                tabuleiro[n2][i].duplicateValue();
                                tabuleiro[n3][i].valor = tabuleiro[n4][i].valor;
                                removerPosicaoOcupada(n4, i);
                            } else if (tabuleiro[n3][i].equals(tabuleiro[n4][i])) {
                                tabuleiro[n3][i].duplicateValue();
                                removerPosicaoOcupada(n4, i);
                            }
                        } else {
                            if (tabuleiro[n2][i].equals(tabuleiro[n3][i])) {
                                tabuleiro[n1][i].setValor(tabuleiro[n2][i].valor * 2);
                                tabuleiro[n2][i].valor = tabuleiro[n4][i].valor;
                                removerPosicaoOcupada(n3, i);
                            } else {
                                tabuleiro[n1][i].valor = tabuleiro[n2][i].valor;
                                if (tabuleiro[n3][i].equals(tabuleiro[n4][i])) {
                                    tabuleiro[n2][i].setValor(tabuleiro[n3][i].valor * 2);
                                    removerPosicaoOcupada(n3, i);
                                } else {
                                    tabuleiro[n2][i].valor = tabuleiro[n3][i].valor;
                                    tabuleiro[n3][i].valor = tabuleiro[n4][i].valor;
                                }
                            }
                            removerPosicaoOcupada(n4, i);
                            adicionarPosicaoOcupada(n1, i);
                        }
                    } else if (t1) {
                        if (tabuleiro[n1][i].equals(tabuleiro[n3][i])) {
                            tabuleiro[n1][i].duplicateValue();
                            tabuleiro[n2][i].valor = tabuleiro[n4][i].valor;
                            removerPosicaoOcupada(n3, i);
                        } else if (tabuleiro[n3][i].equals(tabuleiro[n4][i])) {
                            tabuleiro[n2][i].setValor(tabuleiro[n3][i].valor * 2);
                            removerPosicaoOcupada(n3, i);
                        } else {
                            tabuleiro[n2][i].valor = tabuleiro[n3][i].valor;
                            tabuleiro[n3][i].valor = tabuleiro[n4][i].valor;
                        }
                        removerPosicaoOcupada(n4, i);
                        adicionarPosicaoOcupada(n2, i);
                    } else {
                        if (tabuleiro[n3][i].equals(tabuleiro[n4][i])) {
                            tabuleiro[n1][i].setValor(tabuleiro[n3][i].valor * 2);
                        } else {
                            tabuleiro[n1][i].valor = tabuleiro[n3][i].valor;
                            tabuleiro[n2][i].valor = tabuleiro[n4][i].valor;
                            adicionarPosicaoOcupada(n2, i);
                        }
                        removerPosicaoOcupada(n3, i);
                        removerPosicaoOcupada(n4, i);
                        adicionarPosicaoOcupada(n1, i);
                    }
                } else if (t2) {
                    if (t1) {
                        if (tabuleiro[n1][i].equals(tabuleiro[n2][i])) {
                            tabuleiro[n1][i].duplicateValue();
                            tabuleiro[n2][i].valor = tabuleiro[n4][i].valor;
                        } else if (tabuleiro[n2][i].equals(tabuleiro[n4][i])) {
                            tabuleiro[n2][i].duplicateValue();
                        } else {
                            tabuleiro[n3][i].valor = tabuleiro[n4][i].valor;
                            adicionarPosicaoOcupada(n3, i);
                        }
                    } else {
                        if (tabuleiro[n2][i].equals(tabuleiro[n4][i])) {
                            tabuleiro[n1][i].setValor(tabuleiro[n2][i].valor * 2);
                            removerPosicaoOcupada(n2, i);
                        } else {
                            tabuleiro[n1][i].valor = tabuleiro[n2][i].valor;
                            tabuleiro[n2][i].valor = tabuleiro[n4][i].valor;
                        }
                        adicionarPosicaoOcupada(n1, i);
                    }
                    removerPosicaoOcupada(n4, i);
                } else {
                    if (t1) {
                        if (tabuleiro[n1][i].equals(tabuleiro[n4][i])) {
                            tabuleiro[n1][i].duplicateValue();
                        } else {
                            tabuleiro[n2][i].valor = tabuleiro[n4][i].valor;
                            adicionarPosicaoOcupada(n2, i);
                        }
                    } else {
                        tabuleiro[n1][i].valor = tabuleiro[n4][i].valor;
                        adicionarPosicaoOcupada(n1, i);
                    }
                    removerPosicaoOcupada(n4, i);
                }
            } else if (t3) {
                if (t2) {
                    if (t1) {
                        if (tabuleiro[n1][i].equals(tabuleiro[n2][i])) {
                            tabuleiro[n1][i].duplicateValue();
                            tabuleiro[n2][i].valor = tabuleiro[n3][i].valor;
                            removerPosicaoOcupada(n3, i);
                        } else if (tabuleiro[n2][i].equals(tabuleiro[n3][i])) {
                            tabuleiro[n2][i].duplicateValue();
                            removerPosicaoOcupada(n3, i);
                        }
                    } else {
                        if (tabuleiro[n2][i].equals(tabuleiro[n3][i])) {
                            tabuleiro[n1][i].setValor(tabuleiro[n2][i].valor * 2);
                            removerPosicaoOcupada(n2, i);
                        } else {
                            tabuleiro[n1][i].valor = tabuleiro[n2][i].valor;
                            tabuleiro[n2][i].valor = tabuleiro[n3][i].valor;
                        }
                        removerPosicaoOcupada(n3, i);
                        adicionarPosicaoOcupada(n1, i);
                    }
                } else if (t1) {
                    if (tabuleiro[n1][i].equals(tabuleiro[n3][i])) {
                        tabuleiro[n1][i].duplicateValue();
                    } else {
                        tabuleiro[n2][i].valor = tabuleiro[n3][i].valor;
                        adicionarPosicaoOcupada(n2, i);
                    }
                    removerPosicaoOcupada(n3, i);
                } else {
                    tabuleiro[n1][i].valor = tabuleiro[n3][i].valor;
                    removerPosicaoOcupada(n3, i);
                    adicionarPosicaoOcupada(n1, i);
                }
            } else if (t2) {
                if (t1) {
                    if (tabuleiro[n1][i].equals(tabuleiro[n2][i])) {
                        tabuleiro[n1][i].duplicateValue();
                        removerPosicaoOcupada(n2, i);
                    }
                } else {
                    tabuleiro[n1][i].valor = tabuleiro[n2][i].valor;
                    removerPosicaoOcupada(n2, i);
                    adicionarPosicaoOcupada(n1, i);
                }
            }
        }
    }

    public void movimentoAD(int n1, int n2, int n3, int n4) {

        boolean t1, t2, t3, t4;

        for (int i = 0; i < 4; i++) {
            t1 = verificaBlocoOcupado(tabuleiro[i][n1]);
            t2 = verificaBlocoOcupado(tabuleiro[i][n2]);
            t3 = verificaBlocoOcupado(tabuleiro[i][n3]);
            t4 = verificaBlocoOcupado(tabuleiro[i][n4]);
            if (t4) {
                if (t3) {
                    if (t2) {
                        if (t1) {
                            if (tabuleiro[i][n1].equals(tabuleiro[i][n2])) {
                                if (tabuleiro[i][n3].equals(tabuleiro[i][n4])) {
                                    tabuleiro[i][n2].setValor(tabuleiro[i][n3].valor * 2);
                                    removerPosicaoOcupada(i, n3);
                                } else {
                                    tabuleiro[i][n2].valor = tabuleiro[i][n3].valor;
                                    tabuleiro[i][n3].valor = tabuleiro[i][n4].valor;
                                }
                                tabuleiro[i][n1].duplicateValue();
                                removerPosicaoOcupada(i, n4);
                            } else if (tabuleiro[i][n2].equals(tabuleiro[i][n3])) {
                                tabuleiro[i][n2].setValor(tabuleiro[i][n2].valor * 2);
                                tabuleiro[i][n3].valor = tabuleiro[i][n4].valor;
                                removerPosicaoOcupada(i, n4);
                            } else if (tabuleiro[i][n3].equals(tabuleiro[i][n4])) {
                                tabuleiro[i][n3].duplicateValue();
                                removerPosicaoOcupada(i, n4);
                            }
                        } else {
                            if (tabuleiro[i][n2].equals(tabuleiro[i][n3])) {
                                tabuleiro[i][n1].setValor(tabuleiro[i][n2].valor * 2);
                                tabuleiro[i][n2].valor = tabuleiro[i][n4].valor;
                                removerPosicaoOcupada(i, n3);
                            } else {
                                tabuleiro[i][n1].valor = tabuleiro[i][n2].valor;
                                if (tabuleiro[i][n3].equals(tabuleiro[i][n4])) {
                                    tabuleiro[i][n2].setValor(tabuleiro[i][n3].valor * 2);
                                    removerPosicaoOcupada(i, n3);
                                } else {
                                    tabuleiro[i][n2].valor = tabuleiro[i][n3].valor;
                                    tabuleiro[i][n3].valor = tabuleiro[i][n4].valor;
                                }
                            }
                            removerPosicaoOcupada(i, n4);
                            adicionarPosicaoOcupada(i, n1);
                        }
                    } else if (t1) {
                        if (tabuleiro[i][n1].equals(tabuleiro[i][n3])) {
                            tabuleiro[i][n1].duplicateValue();
                            tabuleiro[i][n2].valor = tabuleiro[i][n4].valor;
                            removerPosicaoOcupada(i, n3);
                        } else if (tabuleiro[i][n3].equals(tabuleiro[i][n4])) {
                            tabuleiro[i][n2].setValor(tabuleiro[i][n3].valor * 2);
                            removerPosicaoOcupada(i, n3);
                        } else {
                            tabuleiro[i][n2].valor = tabuleiro[i][n3].valor;
                            tabuleiro[i][n3].valor = tabuleiro[i][n4].valor;
                        }
                        removerPosicaoOcupada(i, n4);
                        adicionarPosicaoOcupada(i, n2);
                    } else {
                        if (tabuleiro[i][n3].equals(tabuleiro[i][n4])) {
                            tabuleiro[i][n1].setValor(tabuleiro[i][n3].valor * 2);
                        } else {
                            tabuleiro[i][n1].valor = tabuleiro[i][n3].valor;
                            tabuleiro[i][n2].valor = tabuleiro[i][n4].valor;
                            adicionarPosicaoOcupada(i, n2);
                        }
                        removerPosicaoOcupada(i, n3);
                        removerPosicaoOcupada(i, n4);
                        adicionarPosicaoOcupada(i, n1);
                    }
                } else if (t2) {
                    if (t1) {
                        if (tabuleiro[i][n1].equals(tabuleiro[i][n2])) {
                            tabuleiro[i][n1].duplicateValue();
                            tabuleiro[i][n2].valor = tabuleiro[i][n4].valor;
                        } else if (tabuleiro[i][n2].equals(tabuleiro[i][n4])) {
                            tabuleiro[i][n2].duplicateValue();
                        } else {
                            tabuleiro[i][n3].valor = tabuleiro[i][n4].valor;
                            adicionarPosicaoOcupada(i, n3);
                        }
                    } else {
                        if (tabuleiro[i][n2].equals(tabuleiro[i][n4])) {
                            tabuleiro[i][n1].setValor(tabuleiro[i][n2].valor * 2);
                            removerPosicaoOcupada(i, n2);
                        } else {
                            tabuleiro[i][n1].valor = tabuleiro[i][n2].valor;
                            tabuleiro[i][n2].valor = tabuleiro[i][n4].valor;
                        }
                        adicionarPosicaoOcupada(i, n1);
                    }
                    removerPosicaoOcupada(i, n4);
                } else {
                    if (t1) {
                        if (tabuleiro[i][n1].equals(tabuleiro[i][n4])) {
                            tabuleiro[i][n1].duplicateValue();
                        } else {
                            tabuleiro[i][n2].valor = tabuleiro[i][n4].valor;
                            adicionarPosicaoOcupada(i, n2);
                        }
                    } else {
                        tabuleiro[i][n1].valor = tabuleiro[i][n4].valor;
                        adicionarPosicaoOcupada(i, n1);
                    }
                    removerPosicaoOcupada(i, n4);
                }
            } else if (t3) {
                if (t2) {
                    if (t1) {
                        if (tabuleiro[i][n1].equals(tabuleiro[i][n2])) {
                            tabuleiro[i][n1].duplicateValue();
                            tabuleiro[i][n2].valor = tabuleiro[i][n3].valor;
                            removerPosicaoOcupada(i, n3);
                        } else if (tabuleiro[i][n2].equals(tabuleiro[i][n3])) {
                            tabuleiro[i][n2].duplicateValue();
                            removerPosicaoOcupada(i, n3);
                        }
                    } else {
                        if (tabuleiro[i][n2].equals(tabuleiro[i][n3])) {
                            tabuleiro[i][n1].setValor(tabuleiro[i][n2].valor * 2);
                            removerPosicaoOcupada(i, n2);
                        } else {
                            tabuleiro[i][n1].valor = tabuleiro[i][n2].valor;
                            tabuleiro[i][n2].valor = tabuleiro[i][n3].valor;
                        }
                        removerPosicaoOcupada(i, n3);
                        adicionarPosicaoOcupada(i, n1);
                    }
                } else if (t1) {
                    if (tabuleiro[i][n1].equals(tabuleiro[i][n3])) {
                        tabuleiro[i][n1].duplicateValue();
                    } else {
                        tabuleiro[i][n2].valor = tabuleiro[i][n3].valor;
                        adicionarPosicaoOcupada(i, n2);
                    }
                    removerPosicaoOcupada(i, n3);
                } else {
                    tabuleiro[i][n1].valor = tabuleiro[i][n3].valor;
                    removerPosicaoOcupada(i, n3);
                    adicionarPosicaoOcupada(i, n1);
                }
            } else if (t2) {
                if (t1) {
                    if (tabuleiro[i][n1].equals(tabuleiro[i][n2])) {
                        tabuleiro[i][n1].duplicateValue();
                        removerPosicaoOcupada(i, n2);
                    }
                } else {
                    tabuleiro[i][n1].valor = tabuleiro[i][n2].valor;
                    removerPosicaoOcupada(i, n2);
                    adicionarPosicaoOcupada(i, n1);
                }
            }
        }
    }

    public Boolean jogadaPossivel() {
        //for q percorre a matriz 3x3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j].equals(tabuleiro[i + 1][j])) return true;
                if (tabuleiro[i][j].equals(tabuleiro[i][j + 1])) return true;
            }
        }
        //analisar possibilidade de jogadas em pontos especificos
        if (tabuleiro[1][3].equals(tabuleiro[0][3])) return true;
        if (tabuleiro[1][3].equals(tabuleiro[2][3])) return true;
        if (tabuleiro[3][3].equals(tabuleiro[2][3])) return true;
        if (tabuleiro[3][3].equals(tabuleiro[3][2])) return true;
        if (tabuleiro[3][1].equals(tabuleiro[3][0])) return true;
        if (tabuleiro[3][1].equals(tabuleiro[3][2])) return true;
        return false;
    }

    public void adicionarPosicaoOcupada(int linha, int coluna) {
        posicoesOcupadas.add(new int[]{linha, coluna});
    }

    public void removerPosicaoOcupada(int linha, int coluna) {
        posicoesOcupadas.removeIf(item -> Arrays.equals(item, tabuleiro[linha][coluna].posicao));
        tabuleiro[linha][coluna].valor = 0;
    }

    public void adicionarPosicaoAleatoria() {
        int numeroAleatorio1, numeroAleatorio2;
        BlocoNumerico novoBloco;

        do {
            numeroAleatorio1 = new GeradorAleatorio().gerarPosicaoAleatoria();
            numeroAleatorio2 = new GeradorAleatorio().gerarPosicaoAleatoria();
            novoBloco = new BlocoNumerico(new int[]{numeroAleatorio1, numeroAleatorio2}, 0);
        } while (verificaBlocoOcupado(novoBloco));

        tabuleiro[numeroAleatorio1][numeroAleatorio2] = novoBloco;
        tabuleiro[numeroAleatorio1][numeroAleatorio2].setValor(geradorAleatorio.gerarNumeroAleatorio());
        ;
        adicionarPosicaoOcupada(numeroAleatorio1, numeroAleatorio2);
    }

    //Se tiver algum bloco na posição retorna 2/5.
    public boolean verificaBlocoOcupado(BlocoNumerico blocoNumerico) {
        if(blocoNumerico == null) return  false;
        return posicoesOcupadas.stream().anyMatch(item -> Arrays.equals(item, blocoNumerico.posicao));
    }

    public boolean fim() {
        int max = 0;
        for (Object posicoes : posicoesOcupadas) {
            max++;
        }
        if (max == 16) {
            return jogadaPossivel();
        } else return true;
    }

    /**
     * Representação textual da lista.
     */
    public String toString() {

        StringBuilder sb = new StringBuilder("\n|-------|-------|-------|-------|\n");

        for (int i = 0; i < 4; i++) {
            sb.append("|   ");
            for (int j = 0; j < 4; j++) {
                if (tabuleiro[i][j].valor == 0) {
                    sb.append("    |   ");
                } else if (tabuleiro[i][j].getValor() < 10) {
                    sb.append(tabuleiro[i][j].getValor() + "   |   ");
                } else if (tabuleiro[i][j].getValor() < 100) {
                    sb.append(tabuleiro[i][j].getValor() + "  |   ");
                } else if (tabuleiro[i][j].getValor() < 1000) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(tabuleiro[i][j].getValor() + "  |   ");
                } else if (tabuleiro[i][j].getValor() < 10000) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(tabuleiro[i][j].getValor() + " |   ");
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(tabuleiro[i][j] + " |   ");
                }
            }
            sb.append("\n|-------|-------|-------|-------|\n");
        }
        return sb.toString();
    }
}
