import requests
import mysql.connector

db_connection = mysql.connector.connect(host='localhost', user='davi', 
password='Bravo@1998', database='test', port=3306)
cursor = db_connection.cursor()

response = requests.get("https://jsonplaceholder.typicode.com/todos").json()
for res in response:
    try:
        cursor.execute(f"INSERT INTO `todos`.`todos` (`id`, `userId`, `title`) VALUES ('{res['id']}', '{res['userId']}', '{res['title']}');")
    except Exception as e:
        print(e)

db_connection.commit()
cursor.close()
db_connection.close()