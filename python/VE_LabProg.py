import requests
from pymongo import MongoClient
from urllib.parse import quote_plus, quote

login="davi"
psw="Bravo@1998"
uri = "mongodb://%s:%s@%s" % (quote_plus(login), quote_plus(psw), quote_plus("localhost:27017"))

client = MongoClient(uri)
banco = client.LabProg
posts = banco.Posts

response = requests.get("https://jsonplaceholder.typicode.com/posts").json()
for res in response:
    res["_id"]=res["id"]
    try:
        posts.insert_one(res)
    except Exception as e:
        print(e)

find=posts.find()
for x in find:
    print(x)