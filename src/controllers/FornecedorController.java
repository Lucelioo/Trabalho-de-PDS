package controllers;

import models.FornecedorModel;
import views.FornecedorView;
import java.util.Scanner;

@SuppressWarnings("unused")
public class FornecedorController {

    public static void cadastrarFornecedor() {
        String nome = FornecedorView.solicitarNome();
        String contato = FornecedorView.solicitarContato();
        String produtos = FornecedorView.solicitarProdutosFornecidos();

        boolean sucesso = FornecedorModel.cadastrarFornecedor(nome, contato, produtos);

        if (sucesso) {
            FornecedorView.exibirMensagem("Fornecedor cadastrado com sucesso.");
        } else {
            FornecedorView.exibirMensagem("Erro ao cadastrar fornecedor.");
        }
    }

    public static void alterarFornecedor() {
        int id = FornecedorView.solicitarId();
        String nome = FornecedorView.solicitarNome();
        String contato = FornecedorView.solicitarContato();
        String produtos = FornecedorView.solicitarProdutosFornecidos();

        boolean sucesso = FornecedorModel.alterarFornecedor(id, nome, contato, produtos);

        if (sucesso) {
            FornecedorView.exibirMensagem("Fornecedor alterado com sucesso.");
        } else {
            FornecedorView.exibirMensagem("Erro ao alterar fornecedor.");
        }
    }

    public static void excluirFornecedor() {
        int id = FornecedorView.solicitarId();

        boolean sucesso = FornecedorModel.excluirFornecedor(id);

        if (sucesso) {
            FornecedorView.exibirMensagem("Fornecedor excluído com sucesso.");
        } else {
            FornecedorView.exibirMensagem("Erro ao excluir fornecedor.");
        }
    }

    public static void listarFornecedores() {
        var fornecedores = FornecedorModel.listarFornecedores();

        if (fornecedores.isEmpty()) {
            FornecedorView.exibirMensagem("Nenhum fornecedor encontrado.");
        } else {
            FornecedorView.listarFornecedores(fornecedores);
        }
    }
}
