package views;

import java.util.Scanner;

public class MenuView {
    public static void exibirMenu() {
        System.out.println("=============================================");
        System.out.println("|           MENU PRINCIPAL                  |");
        System.out.println("=============================================");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Alterar Produto");
        System.out.println("3 - Excluir Produto");
        System.out.println("4 - Visualizar Produtos");
        System.out.println("5 - Cadastrar Fornecedor");
        System.out.println("6 - Alterar Fornecedor");
        System.out.println("7 - Excluir Fornecedor");
        System.out.println("8 - Listar Fornecedores");
        System.out.println("9 - Sair");
    }

    public static int lerOpcao(Scanner scanner) {
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }
}
