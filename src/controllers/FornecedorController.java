package controllers;

import models.FornecedorModel;
import views.FornecedorView;
import java.util.Scanner;

@SuppressWarnings("unused")
public class FornecedorController {
    // Updated method with parameters
    public static boolean cadastrarFornecedor(String nome, String contato, String produtos) {
        return FornecedorModel.cadastrarFornecedor(nome, contato, produtos);
    }

    // Updated method with parameters
    public static boolean alterarFornecedor(int id, String nome, String contato, String produtos) {
        return FornecedorModel.alterarFornecedor(id, nome, contato, produtos);
    }

    public static boolean excluirFornecedor(int id) {
        return FornecedorModel.excluirFornecedor(id);
    }
}
