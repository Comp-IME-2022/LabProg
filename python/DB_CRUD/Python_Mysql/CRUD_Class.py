import requests
import mysql.connector

class Banco:

    def auth(self, user, password):
        try:
            self.db_connection = mysql.connector.connect(host = 'localhost', user=user, password=password, database='trabalho_mysql')
            self.cursor= self.db_connection.cursor()
        except mysql.connector.Error as err:
            print("Voce errou alguma coisa: {}".format(err))
            return False
        return True
            
    def Create(self,id, personal_name, username, email, phone, website, street, suite, city, zipcode, lat, lng, company_name, company_catch, company_bs):
        try:
            sql = "INSERT INTO person (id, name, username, email, phone, website) VALUES (%s, %s, %s, %s, %s, %s)"
            values = (id, personal_name, username, email, phone, website)
            self.cursor.execute(sql, values)
            sql2 = "INSERT INTO company (id, name, catchPhrase, bs) VALUES (%s, %s, %s, %s)"
            values2 = (id, company_name, company_catch, company_bs)
            self.cursor.execute(sql2, values2)
            sql3 = "INSERT INTO address (id, street, suite, city, zipcode, lat, lng) VALUES (%s, %s, %s, %s, %s, %s, %s)"
            values3 = (id, street, suite, city, zipcode, lat, lng)
            self.cursor.execute(sql3, values3)
            self.db_connection.commit()
        except:   
            self.db_connection.rollback()
            print("Voce obteve um erro, verifique se nao colocou uma ID repetida ou se colocou todos os valores :)")
            return False
        return True
    
    def Read(self, table, atributo = "*"):
        try:
            if(atributo != "*"):
                sql = "SELECT " + atributo + " from " + table
                self.cursor.execute(sql)
                column = self.cursor.column_names
                result = self.cursor.fetchall()
                print("Nome das colunas: ")
                print(column)
                print("Tuplas: ")
                for i in result:
                    print(i)
                return True
            else:
                sql = "SELECT " + atributo + " from " + table
                self.cursor.execute(sql)
                column = self.cursor.column_names
                result = self.cursor.fetchall()
                print("Nome das colunas: ")
                print(column)
                print("Tuplas: ")
                for i in result:
                    print(i)                   
                return True
        except:   
            print('Tivemos problemas em mostrar sua consulta, acho que voce digitou algo errado :)')
            return False
    
    def Update(self, table, atributo_change, condicao=None):
        try:
            if(condicao != None):
                sql = "UPDATE " + table + " SET " + atributo_change + " WHERE " + condicao
                self.cursor.execute(sql)
                self.db_connection.commit()
                return True
            else:
                sql = "UPDATE " + table + " SET " + atributo_change
                self.cursor.execute(sql)
                self.db_connection.commit()
                return True
        except:     
            self.db_connection.rollback()
            print("Sem update pra voce amigo, algo saiu errado.")
            return False

    def Delete(self, table, condicao=None):
        try:
            if(condicao != None):
                sql = "DELETE FROM " + table + " WHERE " + condicao
                self.cursor.execute(sql)
                self.db_connection.commit()
                return True
            else:
                sql = "DELETE FROM " + table
                self.cursor.execute(sql)
                self.db_connection.commit()
                return True
        except:  
            self.db_connection.rollback()
            print("Desculpe, nao deu pra deletar, tente novamente e reveja os dados que voce colocou")
            return False
            

            

        
