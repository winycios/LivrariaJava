package entites.livraria;

public class LivroUsado extends Livro {

    private Double taxaAlfan;

    public LivroUsado() {
        super();
    }

    public LivroUsado(Integer id, String nome, Double preco, Double taxaAlfan) {
        super(id, nome, preco);
        this.taxaAlfan = taxaAlfan;
    }

    public Double getTaxaAlfan() {
        return taxaAlfan;
    }

    public void setTaxaAlfan(Double taxaAlfan) {
        this.taxaAlfan = taxaAlfan;
    }


    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Nome: %s
                preço: R$%.2f
                Taxa da alfandega: R$%.2f \n""", getId(),getNome(), getPreco(), getTaxaAlfan());
    }
}