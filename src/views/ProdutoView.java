package views;

import models.ProdutoModel.Produto;

import java.util.List;

public class ProdutoView {

    // Exibir mensagem de sucesso
    public static void exibirMensagemSucesso(String mensagem) {
        System.out.println(mensagem);
    }

    // Exibir mensagem de erro
    public static void exibirMensagemErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    // Exibir lista de produtos
    public static void exibirProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            System.out.println("ID | Nome do Produto");
            System.out.println("--------------------");
            for (Produto produto : produtos) {
                System.out.println(produto.getId() + " | " + produto.getNome());
            }
        }
    }

    // Solicitar dados de um novo produto
    @SuppressWarnings("resource")
    public static String solicitarNomeProduto() {
        System.out.print("Digite o nome do produto: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    // Solicitar o ID do produto
    @SuppressWarnings("resource")
    public static int solicitarIdProduto() {
        System.out.print("Digite o ID do produto: ");
        return new java.util.Scanner(System.in).nextInt();
    }

    // Solicitar novo nome para alteração de produto
    @SuppressWarnings("resource")
    public static String solicitarNovoNomeProduto() {
        System.out.print("Digite o novo nome do produto: ");
        return new java.util.Scanner(System.in).nextLine();
    }
}
