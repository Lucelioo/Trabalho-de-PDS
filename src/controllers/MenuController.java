package controllers;

import views.MenuView;

import java.util.Scanner;

public class MenuController {
    public static void exibirMenu(Scanner scanner) {
        int opcao = 0;

        while (opcao != 9) {
            MenuView.exibirMenu();
            opcao = MenuView.lerOpcao(scanner);

            switch (opcao) {
                case 1 -> ProdutoController.cadastrarProduto(scanner);
                case 2 -> ProdutoController.alterarProduto(scanner);
                case 3 -> ProdutoController.excluirProduto(scanner);
                case 4 -> ProdutoController.listarProdutos();
                case 5 -> FornecedorController.cadastrarFornecedor();
                case 6 -> FornecedorController.alterarFornecedor();
                case 7 -> FornecedorController.excluirFornecedor();
                case 8 -> FornecedorController.listarFornecedores();
                case 9 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
    
