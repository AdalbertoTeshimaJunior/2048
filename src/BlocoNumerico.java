import java.util.Objects;

public class BlocoNumerico {

    int[] posicao;
    int valor;

    public BlocoNumerico(int[] posicao, int valor) {
        this.posicao = posicao;
        this.valor = valor;
    }

    public int[] getPosicao() {
        return posicao;
    }

    public void setPosicao(int[] posicao) {
        this.posicao = posicao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void duplicateValue(){
        this.valor *= 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlocoNumerico that = (BlocoNumerico) o;
        return valor == that.valor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
