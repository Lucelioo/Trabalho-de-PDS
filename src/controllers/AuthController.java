package controllers;

import java.util.Scanner;

import models.UserModel;


public class AuthController {
    public static boolean autenticarUsuario(String usuario, String senha) {
        try {
            return UserModel.autenticarUsuario(usuario, senha);
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
            return false;
        }
    }

    public static boolean autenticarUsuario(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autenticarUsuario'");
    }
}
