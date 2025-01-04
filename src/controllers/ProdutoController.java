package controllers;

import models.ProdutoModel;
import models.ProdutoModel.Produto;
import views.ProdutoView;

import java.util.List;
import java.util.Scanner;

public class ProdutoController {

    // Método para cadastrar um produto
    public static void cadastrarProduto(Scanner scanner) {
        String nome = ProdutoView.solicitarNomeProduto();
        boolean sucesso = ProdutoModel.cadastrarProduto(nome); // Agora funciona corretamente
    
        if (sucesso) {
            ProdutoView.exibirMensagemSucesso("Produto cadastrado com sucesso.");
        } else {
            ProdutoView.exibirMensagemErro("Falha ao cadastrar o produto.");
        }
    }

    // Método para alterar um produto
    public static void alterarProduto(Scanner scanner) {
        int id = ProdutoView.solicitarIdProduto();
        String nome = ProdutoView.solicitarNovoNomeProduto();
    
        boolean sucesso = ProdutoModel.alterarProduto(id, nome); // Agora funciona corretamente
    
        if (sucesso) {
            ProdutoView.exibirMensagemSucesso("Produto alterado com sucesso.");
        } else {
            ProdutoView.exibirMensagemErro("Falha ao alterar o produto.");
        }
    }
    

    // Método para excluir um produto
    public static void excluirProduto(Scanner scanner) {
    int id = ProdutoView.solicitarIdProduto();
    boolean sucesso = ProdutoModel.excluirProduto(id); // Agora funciona corretamente

    if (sucesso) {
        ProdutoView.exibirMensagemSucesso("Produto excluído com sucesso.");
    } else {
        ProdutoView.exibirMensagemErro("Falha ao excluir o produto.");
    }
}


    // Método para listar todos os produtos
    public static void listarProdutos() {
        List<Produto> produtos = ProdutoModel.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            System.out.println("ID | Nome do Produto");
            System.out.println("--------------------");
            for (Produto produto : produtos) {
                System.out.println(produto.getId() + " | " + produto.getNome());
            }
        }
    }
}
