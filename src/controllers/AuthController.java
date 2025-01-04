package controllers;

import models.UserModel;
import views.AuthView;

import java.util.Scanner;

public class AuthController {
    public static boolean autenticarUsuario(Scanner scanner) {
        String[] credenciais = AuthView.exibirTelaLogin(scanner);
        try {
            if (UserModel.autenticarUsuario(credenciais[0], credenciais[1])) {
                return true;
            } else {
                AuthView.exibirMensagemFalhaLogin();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
            return false;
        }
    }
}
