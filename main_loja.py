import classes_DB_loja
import os
from funcoes_loja import MenuPrincipal

def TelaInicial():
    # Limpa a tela
    os.system("cls" if os.name == "nt" else "clear")

    # Tela principal
    print("\t", "="*48)
    print("\t | BEM VINDO AO SISTEMA DE CADASTRO DE PRODUTOS |")
    print("\t", "="*48)
    print("")
    usuario = input("Login do sistema: ")
    senha = input("Senha do sistema: ")

    # Valida usuario e senha com o banco de dados
    if classes_DB_loja.db.VerificaUsuario(usuario, senha):
        MenuPrincipal()
    else:
        print("Falha na autenticação. Verifique seu login e senha.")
        input("Pressione enter para tentar novamente...")
        TelaInicial()

TelaInicial()
