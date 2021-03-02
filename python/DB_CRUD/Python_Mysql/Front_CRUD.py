from Mysql_CRUD import Banco
import requests
import mysql.connector

global bank
bank = Banco()

def auth():
    print("Bem vindo ao CRUD Mysql. Por favor, realize sua autenticacao\n")
    user = input("Usuario: ")
    password = input("Senha: ")
    validation = bank.auth(user, password)
    if(validation):
        print("Tudo certo, nosso database possui 3 tabelas: person, address, company\nVoce precisara informar o nome da tabela para algumas operacoes :)\n")
        print("Escolha o que deseja realizar:\nCreate digite: 1\nRead digite: 2\nUpdate digite: 3\nDelete digite: 4\nPreencher o Banco de Dados com o Json digite: 5\nSair: Qualquer numero diferente dos anteriores")
        print("OBS: So preencha o Banco de Dados com Json uma unica vez, na primeira vez que abrir o programa!")
        opcao = int(input("Sua escolha: "))
        if(opcao == 1):
            Create()
        elif(opcao == 2):
            Read()
        elif(opcao == 3):
            Update()
        elif(opcao == 4):
            Delete()
        elif(opcao == 5):
            try:
                db_connection = mysql.connector.connect(host = 'localhost', user=user, password=password, database='trabalho_mysql')
                cursor = db_connection.cursor()
                url = 'https://jsonplaceholder.typicode.com/users'
                parames = {'limit':16, 'country': 'us', 'apikey': 'API-KEY'}
                response = requests.get(url, params=parames)
                data = response.json()  

                for s in data:
                    #personal table
                    id = s['id']
                    personal_name = s['name']
                    username = s['username']
                    email = s['email']
                    phone = s['phone']
                    website = s['website']
                    sql = "INSERT INTO person (id, name, username, email, phone, website) VALUES (%s, %s, %s, %s, %s, %s)"
                    values = (id, personal_name, username, email, phone, website)
                    cursor.execute(sql, values)
                    
                    #company table
                    company_name = s['company']['name']
                    company_catch = s['company']['catchPhrase']
                    company_bs = s['company']['bs']
                    sql = "INSERT INTO company (id, name, catchPhrase, bs) VALUES (%s, %s, %s, %s)"
                    values = (id, company_name, company_catch, company_bs)
                    cursor.execute(sql, values)

                    #address table
                    street = s['address']['street']
                    suite = s['address']['suite']
                    city = s['address']['city']
                    zipcode = s['address']['zipcode']
                    lat = s['address']['geo']['lat']
                    lng = s['address']['geo']['lng']
                    sql = "INSERT INTO address (street, suite, city, zipcode, lat, lng, id) VALUES (%s, %s, %s, %s, %s, %s, %s)"
                    values = (street, suite, city, zipcode, lat, lng, id)
                    cursor.execute(sql, values)
                db_connection.commit()
                print("Insercao realizada com sucesso, o que desejas fazer agora?")
                option = int(input("Create digite: 1\nRead digite: 2\nUpdate digite: 3\nDelete digite: 4\nSair digite: 5\n"))
                if(option == 1):
                    Create()
                elif(option == 2):
                    Read()
                elif(option == 3):
                    Update()
                elif(option == 4):
                    Delete()
                elif(option == 5):
                    print("Good bye my friend!")
                
            except mysql.connector.Error as err:
                print("Algo deu errado: {}".format(err))
        else:
            print("Numero invalido")
    

def Create():
    print("Bem vindo a parte de insercao\n")
    print("Nao aceitamos valores nulos na nossa tabela, por favor entre com todos os valores\n")
    id = int(input("id: "))
    personal_name = input("Name: ")
    username = input("Username: ")
    email = input("Email: ")
    phone = input("Phone: ")
    website = input("Website: ")
    street = input("Street: ")
    suite = input("Suite: ")
    city = input("City: ")
    zipcode = input("Zipcode: ")
    lat = input("Latitude: ")
    lng = input("Longitude: ")
    company_name = input("Company's name: ")
    company_catch = input("Company's catchPhrase: ")
    company_bs = input("Company's Bs: ")
    if(id == "" or personal_name == "" or username == "" or email == "" or website == "" or street == "" or suite == "" or city == "" or zipcode == "" or lat == "" or lng == "" or company_name == "" or company_catch == "" or company_bs == ""):
        print("Voce entrou com um valor nulo e pensou que nao fossemos perceber neh? Acho melhor voce tentar novamente")
        Create()
    else:
        validate = bank.Create(id, personal_name, username, email, phone, website, street, suite, city, zipcode, lat, lng, company_name, company_catch, company_bs)
        if(validate):
            print("Insercao realizada com sucesso\nO que deseja fazer agora?\nRead digite: 1\nUpdate digite: 2\nDelete digite: 3\nCreate digite: 4\nSair digite: 5")
            opcao = int(input("Sua escolha: "))
            if(opcao == 1):
                Read()
            elif(opcao == 2):
                Update()
            elif(opcao == 3):
                Delete()
            elif(opcao == 5):
                print("Good bye friend!")
            elif(opcao == 4):
                Create()
            else:
                print("Numero invalido")

def Read():
    print("Bem vindo a parte de consulta\n")
    print("Voce pode consultar uma tabela inteira e colunas especificas")
    print("\nComo atributo vc pode usar: *, nome(s) da(s) tabela(s), assim como o SQL\n")
    atributos = input("Entre com o(s) atributo(s): ")
    table = input("Entre com o nome da(s) tabela(s) referente(s) ao(s) atributo(s) colocados, em ordem: ")
    validate = bank.Read(table, atributos)
    if(validate):
        print("Leitura realizada com sucesso\nO que deseja fazer agora?\nCreate digite: 1\nUpdate digite: 2\nDelete digite: 3\nRead digite: 4\nSair digite: 5")
        opcao = int(input("Sua escolha: "))
        if(opcao == 1):
            Create()
        elif(opcao == 2):
            Update()
        elif(opcao == 3):
            Delete()
        elif(opcao == 5):
            print("Good bye friend!")
        elif(opcao == 4):
            Read()
        else:
            print("Numero invalido")


def Update():
    print("Bem vindo a parte de atualziacao")
    table = input("Diga o nome da tabela que sofrera update: ")
    print("\nComo mudanca voce pode colocar condicoes como: name = 'arroz'. So nao esqueca das aspas simples!!\n")
    atributo_change = input("Diga a mudanca que deseja realizar: ")
    opcao = int(input("Digite 1 se deseja adicionar uma condicao ou, Digite 0 para nao adicionar: "))
    if(opcao == 1):
        print("\nComo condicao voce pode fazer igual a mudanca:  name = 'junior'. Nao esqueca das aspas simples!!\n")
        condicao = input("Digite a condicao que deseja colocar: ")
        if(bank.Update(table, atributo_change, condicao)):
            print("Alteracao realizada com sucesso\nO que deseja fazer agora?\nCreate digite: 1\nRead digite: 2\nDelete digite: 3\nUpdate digite: 4\nSair digite: 5")
            opcao = int(input("Sua escolha: "))
            if(opcao == 1):
                Create()
            elif(opcao == 2):
                Read()
            elif(opcao == 3):
                Delete()
            elif(opcao == 5):
                print("Good bye friend!")
            elif(opcao == 4):
                Update()
            else:
                print("Numero invalido")
    elif(opcao == 0):
        if(bank.Update(table, atributo_change)):
            print("Alteracao realizada com sucesso\nO que deseja fazer agora?\nCreate digite: 1\nRead digite: 2\nDelete digite: 3\nUpdate digite: 4\nSair digite: 5")
            opcao = int(input("Sua escolha: "))
            if(opcao == 1):
                Create()
            elif(opcao == 2):
                Read()
            elif(opcao == 3):
                Delete()
            elif(opcao == 5):
                print("Good bye friend!")
            elif(opcao == 4):
                Update()
            else:
                print("Numero invalido")
        

def Delete():
    print("Bem vindo a parte de eliminacao")
    print("Deletar todas as tuplas da tabela digite: 1\nDeletar linha(s) especifica(s) digite: 2")
    opcao = int(input())
    if(opcao == 1):
        table = input("Entre com o nome da tabela: ")
        if(bank.Delete(table)):
            print("Exclusao realizada com sucesso\nO que deseja fazer agora?\nCreate digite: 1\nRead digite: 2\nUpdate digite: 3\nDelete digite: 5\nSair digite: 4")
            opcao = int(input("Sua escolha: "))
            if(opcao == 1):
                Create()
            elif(opcao == 2):
                Read()
            elif(opcao == 3):
                Update()
            elif(opcao == 5):
                print("Good bye friend!")
            elif(opcao == 4):
                Delete()
            else:
                print("Numero invalido")
    elif(opcao == 2):
        table = input("Entre com o nome da tabela: ")
        print("\nComo condicao voce pode fazer, por exemplo:  name = 'junior'. Nao esqueca das aspas simples!!\n")
        condicao = input("Entre com a condicao que identifica as tuplas desejadas: ")
        if(bank.Delete(table, condicao)):
            print("Exclusao realizada com sucesso\nO que deseja fazer agora?\nCreate digite: 1\nRead digite: 2\nUpdate digite: 3\nDelete digite: 4\nSair digite: 5")
            opcao = int(input("Sua escolha: "))
            if(opcao == 1):
                Create()
            elif(opcao == 2):
                Read()
            elif(opcao == 3):
                Update()
            elif(opcao == 5):
                print("Good bye friend!")
            elif(opcao == 4):
                Delete()
            else:
                print("Numero invalido")

auth()


