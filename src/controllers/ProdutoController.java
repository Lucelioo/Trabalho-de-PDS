package controllers;

import models.HistoricoSaidaModel;
import models.ProdutoModel;
import models.ProdutoModel.Produto;

public class ProdutoController {
    // Corrected method signature
    public static boolean cadastrarProduto(String nome) {
        return ProdutoModel.cadastrarProduto(nome);
    }

    // Corrected method signature
    public static boolean alterarProduto(int id, String nome) {
        return ProdutoModel.alterarProduto(id, nome);
    }

    public static boolean excluirProduto(int id) {
    // Get product name from existing data
    String nomeProduto = ProdutoModel.listarProdutos().stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .map(Produto::getNome)
        .orElse("Produto Desconhecido");

    if (ProdutoModel.excluirProduto(id)) {
        return HistoricoSaidaModel.registrarSaida(id, nomeProduto);
    }
    return false;
}

public static boolean registrarSaida(int id, String nome) {
    boolean deleted = ProdutoModel.excluirProduto(id);
    if (deleted) {
        return HistoricoSaidaController.registrarSaida(id, nome);
    }
    return false;
}
}