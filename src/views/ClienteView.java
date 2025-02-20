package views;

import java.util.Scanner;

public class ClienteView {

    @SuppressWarnings("resource")
    public static String solicitarNome() {
        System.out.print("Nome do cliente: ");
        return new Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static String solicitarTelefone() {
        System.out.print("Telefone: ");
        return new Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static String solicitarEndereco() {
        System.out.print("Endereço: ");
        return new Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static String solicitarCPF() {
        System.out.print("CPF: ");
        return new Scanner(System.in).nextLine();
    }

    @SuppressWarnings("resource")
    public static int solicitarId() {
        System.out.print("ID do cliente: ");
        return new Scanner(System.in).nextInt();
    }

    public static void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public static void listarClientes(java.util.List<models.ClienteModel.Cliente> clientes) {
        System.out.println("ID | Nome | Telefone | Endereço | CPF");
        System.out.println("----------------------------------------");
        for (models.ClienteModel.Cliente c : clientes) {
            System.out.println(c);
        }
    }
}