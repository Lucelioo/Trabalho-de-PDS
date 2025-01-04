import controllers.AuthController;
import controllers.MenuController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (AuthController.autenticarUsuario(scanner)) {
            MenuController.exibirMenu(scanner);
        }

        scanner.close();
    }
}
