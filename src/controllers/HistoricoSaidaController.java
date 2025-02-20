package controllers;

import models.HistoricoSaidaModel;
import java.util.List;

public class HistoricoSaidaController {
    public static boolean registrarSaida(int idProduto, String nomeProduto) {
        return HistoricoSaidaModel.registrarSaida(idProduto, nomeProduto);
    }

    public static List<HistoricoSaidaModel.HistoricoSaida> listarHistorico() {
        return HistoricoSaidaModel.listarHistorico();
    }
}