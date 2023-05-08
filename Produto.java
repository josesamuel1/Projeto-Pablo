public class Produto {
    public String nome;
    public int preco;

    /*Constructor*/
    public Produto(){}
    public Produto(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
    }

    /*Getters&Setters*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
