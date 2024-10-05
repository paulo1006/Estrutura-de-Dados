import java.util.Scanner;

public class Main {

    public static class No<T> {
        private T elemento;
        private No<T> proximo;

        public No(T elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }

        public No(T elemento, No<T> proximo) {
            this.elemento = elemento;
            this.proximo = proximo;
        }

        public T getElemento() {
            return elemento;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }

        public No<T> getProximo() {
            return proximo;
        }

        public void setProximo(No<T> proximo) {
            this.proximo = proximo;
        }

        @Override
        public String toString() {
            return String.valueOf(elemento);
        }
    }

    public static class ListaEncadeada<T extends Comparable<T>> {
        private No<T> inicio;
        private No<T> ultimo;
        private int tamanho;
        private static final int NAO_ENCONTRADO = -1;

        public void adiciona(T elemento) {
            No<T> celula = new No<>(elemento);
            if (tamanho == 0) {
                this.inicio = celula;
            } else {
                this.ultimo.setProximo(celula);
            }
            this.ultimo = celula;
            this.tamanho++;
        }

        public void adicionaInicio(T elemento) {
            No<T> celula = new No<>(elemento);
            if (tamanho == 0) {
                this.inicio = celula;
                this.ultimo = celula;
            } else {
                celula.setProximo(this.inicio);
                this.inicio = celula;
            }
            this.tamanho++;
        }

        public void addPosicao(int posicao, T elemento) {
            if (posicao < 0 || posicao > this.tamanho) {
                throw new IllegalArgumentException("Posição inválida");
            }
            if (posicao == 0) {
                this.adicionaInicio(elemento);
            } else if (posicao == this.tamanho) {
                this.adiciona(elemento);
            } else {
                No<T> noAnterior = this.buscaPorNo(posicao - 1);
                No<T> proximoNo = noAnterior.getProximo();
                No<T> novoNo = new No<>(elemento, proximoNo);
                noAnterior.setProximo(novoNo);
                this.tamanho++;
            }
        }

        public void limpa() {
            this.inicio = null;
            this.ultimo = null;
            this.tamanho = 0;
        }

        private No<T> buscaPorNo(int posicao) {
            if (this.posicaonaoexiste(posicao)) {
                throw new IllegalArgumentException("Posição não existe");
            }
            No<T> noAtual = this.inicio;
            for (int i = 0; i < posicao; i++) {
                noAtual = noAtual.getProximo();
            }
            return noAtual;
        }

        public T busca(int posicao) {
            No<T> no = buscaPorNo(posicao);
            return no != null ? no.getElemento() : null;
        }

        public int buscaElemento(T elemento) {
            No<T> noAtual = this.inicio;
            int pos = 0;
            while (noAtual != null) {
                if (noAtual.getElemento().equals(elemento)) {
                    return pos;
                }
                pos++;
                noAtual = noAtual.getProximo();
            }
            return NAO_ENCONTRADO;
        }

        public T removeInicio() {
            if (this.tamanho == 0) {
                throw new RuntimeException("Lista vazia");
            }
            T removido = this.inicio.getElemento();
            this.inicio = this.inicio.getProximo();
            this.tamanho--;
            if (this.tamanho == 0) {
                this.ultimo = null;
            }
            return removido;
        }

        private boolean posicaonaoexiste(int posicao) {
            return !(posicao >= 0 && posicao < this.tamanho);
        }

        public T removePorPosicao(int posicao) {
            if (this.posicaonaoexiste(posicao)) {
                throw new RuntimeException("Posição não existe");
            }

            if (posicao == 0) {
                return this.removeInicio();
            }
            if (posicao == this.tamanho - 1) {
                return this.removeFinal();
            }
            No<T> noAnterior = this.buscaPorNo(posicao - 1);
            No<T> atual = noAnterior.getProximo();
            No<T> proximo = atual.getProximo();
            noAnterior.setProximo(proximo);
            atual.setProximo(null);
            this.tamanho--;

            return atual.getElemento();
        }

        public int getTamanho() {
            return this.tamanho;
        }

        public T removeFinal() {
            if (this.tamanho == 0) {
                throw new RuntimeException("Lista vazia");
            }
            if (this.tamanho == 1) {
                return this.removeInicio();
            }
            No<T> penultimoNo = this.buscaPorNo(this.tamanho - 2);
            T removido = penultimoNo.getProximo().getElemento();
            penultimoNo.setProximo(null);
            this.tamanho--;
            return removido;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            No<T> atual = inicio;
            while (atual != null) {
                sb.append(atual.getElemento());
                if (atual.getProximo() != null) {
                    sb.append(" ");
                }
                atual = atual.getProximo();
            }
            return "Lista: [" + sb.toString() + "]";
        }
    }

    public static void main(String[] args) {
        // Cria uma instância da classe ListaEncadeada
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();


        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(4);


        System.out.println(lista);
        System.out.println("Tamanho: " + lista.getTamanho());


    }

}
