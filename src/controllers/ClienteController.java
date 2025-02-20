package controllers;

import models.ClienteModel;
import views.ClienteView;

public class ClienteController {
    public static boolean cadastrarCliente(String nome, String telefone, String endereco, String cpf) {
        return ClienteModel.cadastrarCliente(nome, telefone, endereco, cpf);
    }

    public static boolean alterarCliente(int id, String nome, String telefone, String endereco, String cpf) {
        return ClienteModel.alterarCliente(id, nome, telefone, endereco, cpf);
    }

    public static boolean excluirCliente(int id) {
        return ClienteModel.excluirCliente(id);
    }
}