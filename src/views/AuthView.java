package views;

import java.util.Scanner;

public class AuthView {
    public static String[] exibirTelaLogin(Scanner scanner) {
        System.out.print("Login do sistema: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha do sistema: ");
        String senha = scanner.nextLine();
        return new String[]{usuario, senha};
    }

    public static void exibirMensagemFalhaLogin() {
        System.out.println("Falha na autenticação. Verifique seu login e senha.");
    }
}
