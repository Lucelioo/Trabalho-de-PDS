import classes_DB_loja
import os

# Menu principal do sistema
def MenuPrincipal():
    opcao = 0
    try:
        while opcao != 9:
            # Limpa a tela
            os.system("cls" if os.name == "nt" else "clear")
            print(" "*25, "+ ".ljust(45, "-"), "+")
            print(" "*25, "|      SEJA BEM-VINDO AO SISTEMA DA LOJA      |")
            print(" "*25, "+ ".ljust(45, "-"), "+")
            print(" ")
            print("Menu Principal:")
            print("="*54)
            print("| 1 - Cadastrar Produto  |  2 - Alterar Produto      |")
            print("| 3 - Excluir Produto    |  4 - Visualizar Produtos  |")
            print("| 5 - Cadastrar Fornecedor | 6 - Alterar Fornecedor  |")
            print("| 7 - Excluir Fornecedor |  8 - Visualizar Fornecedores |")
            print("|                  9 - Encerrar                      |")
            print("="*54)
            opcao = int(input("Digite a opção desejada: "))
            # Limpa tela
            os.system("cls" if os.name == "nt" else "clear")

            # Produtos
            if opcao == 1:
                produto = input("Qual produto deseja cadastrar: ")
                try:    
                    classes_DB_loja.db.MenuCadastrar(produto)
                    input("Pressione enter para continuar...")
                except:
                    print('Erro ao cadastrar, produto já existe.')
                    input("Pressione enter para continuar...")
            elif opcao == 2:
                id = input("Digite o ID do produto que vai ser alterado: ")
                produto = input("Digite o nome do produto que vai ser alterado: ")
                classes_DB_loja.db.MenuAlterar(produto, id)
                input("Pressione enter para continuar...")
            elif opcao == 3:
                id = input("Digite o ID do produto que será excluído: ")
                classes_DB_loja.db.MenuExluir(id)
                input("Pressione enter para continuar...")
            elif opcao == 4:
                classes_DB_loja.db.MenuVisualizar()
                input("Pressione enter para continuar...")

            # Fornecedores
            elif opcao == 5:
                nome = input("Digite o nome do fornecedor: ")
                contato = input("Digite o contato do fornecedor: ")
                produtos = input("Digite os produtos fornecidos: ")
                classes_DB_loja.Fornecedor.CadastraFornecedor(nome, contato, produtos)
                input("Pressione enter para continuar...")
            elif opcao == 6:
                id_fornecedor = input("Digite o ID do fornecedor que será alterado: ")
                nome = input("Digite o novo nome do fornecedor: ")
                contato = input("Digite o novo contato do fornecedor: ")
                produtos = input("Digite os novos produtos fornecidos: ")
                classes_DB_loja.Fornecedor.EditaFornecedor(id_fornecedor, nome, contato, produtos)
                input("Pressione enter para continuar...")
            elif opcao == 7:
                id_fornecedor = input("Digite o ID do fornecedor que será excluído: ")
                classes_DB_loja.Fornecedor.ExcluiFornecedor(id_fornecedor)
                input("Pressione enter para continuar...")
            elif opcao == 8:
                classes_DB_loja.Fornecedor.VisualizarFornecedor()
                input("Pressione enter para continuar...")
            elif opcao == 9:
                print("Sistema encerrado pelo usuário...")
            else:
                print("Opção inválida...")
                input("Pressione enter para continuar...")
    except:
        print("Opção inválida...")
        input("Pressione enter para continuar...")
        MenuPrincipal()
