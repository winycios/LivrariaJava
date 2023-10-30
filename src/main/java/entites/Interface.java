package entites;

import entites.livraria.LivroNovo;
import entites.livraria.LivroUsado;

import java.util.Scanner;

import static java.lang.System.exit;

public class Interface {
    Funcoes funcoes = new Funcoes();

    public static void lines() {
        System.out.println("+-------------------------------+");
    }

    public void menu() {
        Scanner ler = new Scanner(System.in);

        System.out.print("""
                +-------------------------------+
                | Livraria SPTECH               |
                +-------------------------------+
                | 1) Cadastrar Livro            |
                | 2) Exibir livros              |
                | 3) Atualizar livros           |
                | 4) Remover livros             |
                | 5) Buscar por nome            |
                | 6) Sair                       |
                +-------------------------------+
                """);
        Integer option = ler.nextInt();

        navegacao(option);
        ler.close();
    }

    public void navegacao(Integer option) {
        Scanner ler = new Scanner(System.in);
        Scanner lerTexto = new Scanner(System.in);

        switch (option) {
            // cadastrar livros
            case 1: {
                lines();
                System.out.print("Seu livro é novo ? (s/n)");
                String estadoLivro = lerTexto.nextLine();

                System.out.print("ID do livro : ");
                Integer id = ler.nextInt();

                System.out.print("Nome do livro : ");
                String nome = lerTexto.nextLine();

                System.out.print("Valor do livro : ");
                Double valor = ler.nextDouble();

                // mudar funcao para caso o livro for usado ou não
                if (estadoLivro.equals("s")) {
                    System.out.print("Ano de lançamento : ");
                    Integer ano = ler.nextInt();

                    if (id > 0 && !nome.isBlank() && valor > 0 && ano > 0) {
                        LivroNovo livro = new LivroNovo(id, nome, valor, ano);
                        funcoes.cadastrarNovo(livro);
                    } else {
                        System.out.println("Dados invalidos, voltando para o menu!");
                    }
                } else {
                    System.out.print("Taxa alfandega : ");
                    Double taxa = ler.nextDouble();

                    if (id > 0 && !nome.isBlank() && valor > 0 && taxa > 0) {
                        LivroUsado livro = new LivroUsado(id, nome, valor, taxa);
                        funcoes.cadastrarUsado(livro);
                    } else {
                        System.out.println("Dados invalidos, voltando para o menu !");
                    }
                }

                menu();
                break;
            }

            // exibir livros
            case 2: {
                lines();

                System.out.println("""
                        Selecione uma das opções abaixo: 
                        1) Ver todos os livros
                        2) Ver somente os livros novos
                        3) Ver somente os livros usados
                        Outro) Sair""");
                Integer op = ler.nextInt();

                lines();
                switch (op) {
                    case 1: {

                        System.out.println("Todos os livros: ");
                        System.out.println(funcoes.exibirLivroNovos());
                        System.out.println(funcoes.exibirLivroUsados());
                        break;
                    }
                    case 2: {

                        System.out.println("Livros encontrados: ");
                        System.out.println(funcoes.exibirLivroNovos());
                        break;
                    }
                    case 3: {

                        System.out.println("Livros encontrados: ");
                        System.out.println(funcoes.exibirLivroUsados());
                        break;
                    }
                    default: {
                        System.out.println("Voltando para o menu !");
                    }
                }
                lines();
                menu();
                break;
            }

            // atualizar livros
            case 3: {
                lines();
                System.out.print("Digite o ID do livro: ");
                Integer id = ler.nextInt();

                System.out.print("Digite o novo nome do livro: ");
                String nome = lerTexto.nextLine();

                System.out.print("Digite o novo preço do livro: ");
                Double valor = ler.nextDouble();

                if (id > 0 && !nome.isBlank() && valor > 0) {
                    funcoes.alterarDados(id, nome, valor);
                } else {
                    System.out.println("Dados invalidos, voltando para o menu");
                }
                lines();
                menu();
                break;
            }

            // remover livros
            case 4: {
                lines();
                System.out.print("Digite o id do livro que você quer deletar: ");
                Integer id = lerTexto.nextInt();

                if (id > 0) {
                    System.out.println(funcoes.deletarLivro(id) ? "Livro Excluido com sucesso !" : "Esse id não existe !");
                } else {
                    System.out.println("Dados invalidos, voltando para o menu");
                }
                lines();
                menu();
                break;
            }

            // buscar livros
            case 5: {
                lines();
                System.out.print("Digite o nome do livro que você procura: ");
                String nomeLivro = lerTexto.nextLine();

                if (!nomeLivro.isBlank()) {
                    System.out.println(funcoes.procurarLivro(nomeLivro) ? "Livro encontrado !" : "Livro inexistente !");
                } else {
                    System.out.println("Dados invalidos, voltando para o menu");
                }
                lines();
                menu();
                break;
            }
            case 6: {
                System.out.println("Fechando sistema");
                exit(0);
            }
            default: {
                System.out.println("opção invalida, escolha novamente");
                menu();
            }
        }
        ler.close();
        lerTexto.close();
    }
}
