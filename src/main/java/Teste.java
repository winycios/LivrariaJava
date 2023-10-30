import Conexao.Conexao;
import entites.Interface;
import org.springframework.jdbc.core.JdbcTemplate;

public class Teste {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.execute("DROP TABLE IF EXISTS livro");

        con.execute("""
                CREATE TABLE livro (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nome VARCHAR(60),
                preco Double,
                anoLancamento VARCHAR,
                taxaAlfan Double
                )""");

        Interface face = new Interface();
        face.menu();
    }
}
