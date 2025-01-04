package views;

import models.FornecedorModel;
import java.util.List;

public class FornecedorView {

    @SuppressWarnings("resource")
    public static String solicitarNome() {
        System.out.print("Digite o nome do fornecedor: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static String solicitarContato() {
        System.out.print("Digite o contato do fornecedor: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static String solicitarProdutosFornecidos() {
        System.out.print("Digite os produtos fornecidos: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static int solicitarId() {
        System.out.print("Digite o ID do fornecedor: ");
        return new java.util.Scanner(System.in).nextInt();
    }

    public static void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public static void listarFornecedores(List<FornecedorModel.Fornecedor> fornecedores) {
        System.out.println("ID | Nome | Contato | Produtos Fornecidos");
        System.out.println("------------------------------------------");
        for (FornecedorModel.Fornecedor fornecedor : fornecedores) {
            System.out.println(fornecedor);
        }
    }
}
