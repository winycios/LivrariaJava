package entites.livraria;

public class LivroNovo extends Livro {

    private Integer anoLancamento;

    public LivroNovo() {
        super();
    }

    public LivroNovo(Integer id, String nome, Double preco, Integer anoLancamento) {
        super(id, nome, preco);
        this.anoLancamento = anoLancamento;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Nome: %s
                preço: R$%.2f
                Ano de lançamento: %d \n""", getId(), getNome(), getPreco(), getAnoLancamento());
    }
}