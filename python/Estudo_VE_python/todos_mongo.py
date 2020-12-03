import requests
from pymongo import MongoClient
from urllib.parse import quote_plus, quote
uri = "mongodb://%s:%s@%s" % (quote_plus("davi"), quote_plus("Bravo@1998"), quote_plus("localhost:27017"))

client = MongoClient(uri)
print(client)
banco = client.test
print(banco)
todos = banco.todos
print(todos)

response = requests.get("https://jsonplaceholder.typicode.com/todos").json()
for res in response:
    res["_id"]=res["id"]
    print(res)
    try:
        todos.insert_one(res)
    except Exception as e:
        print(e)

find=todos.find()
for x in find:
    print(x)