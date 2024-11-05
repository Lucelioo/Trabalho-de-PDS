# importando pacotes
import mysql.connector
import funcoes_loja
import pandas as pd

class ConexaoDb:
    def __init__(self, host, port, database, user, password):
        self.host = host
        self.port = port
        self.database = database
        self.user = user
        self.password = password

        global conn
        conn = mysql.connector.connect(
            host=self.host,
            port=self.port,
            user=self.user,
            password=self.password
        )
        self.criar_banco_de_dados()
        conn = mysql.connector.connect(
            host=self.host,
            port=self.port,
            database=self.database,
            user=self.user,
            password=self.password
        )

    def criar_banco_de_dados(self):
        try:
            cursor = conn.cursor()
            cursor.execute(f"CREATE DATABASE IF NOT EXISTS {self.database}")
            conn.commit()
            print(f"Banco de dados '{self.database}' criado com sucesso")
        except mysql.connector.Error as err:
            print(f"Erro ao criar o banco de dados: {err}")
    
    # Valida se usuario e senha está cadastrado no banco de dados
    def VerificaUsuario(self,usuario,senha):
        print("\t","="*48)
        print("\t | BEM VINDO AO SISTEMA DE CADASTRO DE PRODUTOS |")
        print("\t","="*48)
        print("")
        contador = 3
        verifica = False
        while (contador > 0):
            cursor = conn.cursor()
            cursor.execute("SELECT usuario_acesso, senha_acesso FROM acesso WHERE usuario_acesso = '" + usuario + "' AND senha_acesso = '" + senha + "';")
            result = cursor.fetchall()
            for x in result:
                if ((x[0] == usuario) and (x[1] == senha)):
                    print("Login feito com sucesso")
                    verifica = True
                    contador = 0
                    break
            
            if (not result):
                print(f"Usuario/senha invalido, {contador} tentativa de acesso.")
                contador -= 1
                usuario = input("Login do sistema: ")
                senha = input("Senha do sistema: ")
        
        if (verifica):
            funcoes_loja.MenuPrincipal()
    
    # Opcao 1 do menu cadastrar produto
    def MenuCadastrar(self,produto):
        cursor = conn.cursor()
        cursor.execute("INSERT INTO produtos (nome_produto) VALUES ('"+ produto +"');")
        conn.commit()
        print(f"Produto {produto} cadastrado com sucesso...")

    # Opcao 2 do menu alterar produto
    def MenuAlterar(self,produto,id):
        cursor = conn.cursor()
        cursor.execute("UPDATE produtos SET nome_produto = '" + produto + "' WHERE id_produto = "+ str(id) + ";")
        conn.commit()
        print(f"Produto alterado para {produto} com sucesso...")

    # Opcao 3 do menu excluir produtos
    def MenuExluir(self,id):
        cursor = conn.cursor()
        cursor.execute("DELETE FROM produtos WHERE id_produto = "+ str(id) + ";")
        conn.commit()
        print("Produto excuido com sucesso...")

    # Opcao 4 do menu visualizar produtos
    def MenuVisualizar(self):
        cursor = conn.cursor()
        cursor.execute("SELECT id_produto, nome_produto FROM produtos;")
        resultado = cursor.fetchall()
        print("=================================")
        print(">>> Visualização dos Produtos <<<")
        print("=================================")
        print("")
        print("ID Produtos")
        print("------------")
        for id, result in resultado:
            print(id,result)

class Fornecedor:
    def __init__(self, id_fornecedor, nome_fornecedor, contato_fornecedor, produtos_fornecidos):
        self.id_fornecedor = id_fornecedor
        self.nome_fornecedor = nome_fornecedor
        self.contato_fornecedor = contato_fornecedor
        self.produtos_fornecidos = produtos_fornecidos

    def CadastraFornecedor(nome, contato, produtos):
        cursor = conn.cursor()
        cursor.execute("INSERT INTO fornecedor (nome_fornecedor, contato_fornecedor, produtos_fornecidos) VALUES (%s, %s, %s);",
                       (nome, contato, produtos))
        conn.commit()
        print(f"Fornecedor {nome} cadastrado com sucesso.")

    def EditaFornecedor(id_fornecedor, nome, contato, produtos):
        cursor = conn.cursor()
        cursor.execute("UPDATE fornecedor SET nome_fornecedor = %s, contato_fornecedor = %s, produtos_fornecidos = %s WHERE id_fornecedor = %s;",
                       (nome, contato, produtos, id_fornecedor))
        conn.commit()
        print(f"Fornecedor alterado para {nome} com sucesso.")

    def ExcluiFornecedor(id_fornecedor):
        cursor = conn.cursor()
        cursor.execute("DELETE FROM fornecedor WHERE id_fornecedor = %s;", (id_fornecedor,))
        conn.commit()
        print("Fornecedor excluído com sucesso.")

    def VisualizarFornecedor():
        cursor = conn.cursor()
        cursor.execute("SELECT id_fornecedor, nome_fornecedor, contato_fornecedor, produtos_fornecidos FROM fornecedor;")
        resultado = cursor.fetchall()
        print("====================================")
        print(">>> Visualização dos Fornecedores <<<")
        print("====================================")
        print("")
        print("ID | Nome | Contato | Produtos")
        print("------------------------------------")
        for id_fornecedor, nome_fornecedor, contato_fornecedor, produtos_fornecidos in resultado:
            print(f"{id_fornecedor} | {nome_fornecedor} | {contato_fornecedor} | {produtos_fornecidos}")

##########################################
# Dados conexao com o banco mysql server #
##########################################
db = ConexaoDb(
    "localhost",
    "3306",
    "loja_informatica",
    "root",
    "123"   #MUDAR AQUI DE ACORDO COM SUA SENHA
)

#db.MenuVisualizar()
