package entites;

import Conexao.Conexao;
import entites.livraria.LivroNovo;
import entites.livraria.LivroUsado;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Funcoes {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();


    // cadastrar um livro novo
    public void cadastrarNovo(LivroNovo livro) {

        if (!procurarLivro(livro.getNome())) {
            if (!procurarId(livro.getId())) {
                con.update("INSERT INTO livro (id, nome, preco, anoLancamento) VALUES (?, ?, ?, ?)",
                        livro.getId(), livro.getNome(), livro.getPreco(), livro.getAnoLancamento());

                System.out.println("Opa, livro inserido com sucesso !");
            } else {
                System.out.println("Esse id já está sendo utilizado");
            }
        } else {
            System.out.println("Livro já encontrado no banco de dados !");
        }

        Interface.lines();
    }

    // cadastrar um livro usado
    public void cadastrarUsado(LivroUsado livro) {

        if (!procurarLivro(livro.getNome())) {
            if (!procurarId(livro.getId())) {
                con.update("INSERT INTO livro (id, nome, preco, taxaAlfan) VALUES (?, ?, ?, ?)",
                        livro.getId(), livro.getNome(), livro.getPreco(), livro.getTaxaAlfan());

                System.out.println("Opa, livro inserido com sucesso !");
            } else {
                System.out.println("Esse id já está sendo utilizado");
            }
        } else {
            System.out.println("Livro já encontrado no banco de dados !");
        }
        Interface.lines();
    }

    // Exibir livros novos
    public List exibirLivroNovos() {

        return con.query("SELECT id, nome, preco, anoLancamento FROM livro where taxaAlfan IS NULL;",
                new BeanPropertyRowMapper<>(LivroNovo.class));
    }

    // exibir livros usados
    public List exibirLivroUsados() {

        return con.query("SELECT id, nome, preco, taxaAlfan FROM livro where anoLancamento IS NULL;",
                new BeanPropertyRowMapper<>(LivroUsado.class));
    }

    // alterar dados do livro
    public void alterarDados(Integer id, String nome, Double valor) {

        if (procurarId(id)) {
            con.update("UPDATE livro SET nome = ?, preco = ? WHERE id = ?",
                    nome, valor, id);
            System.out.println("Dados alterados com sucesso !");
        } else {
            System.out.println("Esse id não existe");
        }

    }

    // deletar livro
    public Boolean deletarLivro(Integer id) {

        if (procurarId(id)) {
            con.update("DELETE FROM livro WHERE id = ?", id);

            return true;
        } else {

            return false;
        }
    }

    // Procurar livro  por nome
    public Boolean procurarLivro(String nomeLivro) {

        List<LivroUsado> livro = con.query("SELECT id FROM livro where nome = ?;",
                new BeanPropertyRowMapper<>(LivroUsado.class), nomeLivro);

        if (livro.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    // Procurar livro por id
    public Boolean procurarId(Integer id) {

        List<LivroUsado> livro = con.query("SELECT id FROM livro where id = ?;",
                new BeanPropertyRowMapper<>(LivroUsado.class), id);

        if (livro.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
