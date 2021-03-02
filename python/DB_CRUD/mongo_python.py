import requests
import pymongo
import json

url = 'https://jsonplaceholder.typicode.com/users'
params = {'limit': 16, 'country': 'us', 'apikey': 'API-KEY'}
response = requests.get(url, params=params,stream=True)
myclient = pymongo.MongoClient("mongodb://localhost:27017/")
mydb = myclient["user_python"]
mycol = mydb[""]

for i in response.json():
    x = mycol.insert_one(i)

def create():
    print("Type the document you want to insert")
    dic_aux = input()
    json_acceptable_string = dic_aux.replace("'", "\"")
    try: 
        d = json.loads(json_acceptable_string)
        try:
            x = mycol.insert_one(d)
            print("document created with success, _id: ",x.inserted_id)
        except ValueError as e:
            print('Failed. ',e)          
    except ValueError as e: 
        print ("Not a valid dictionary. ",e) 

def read():
    print('Type your query in dictionary format, empty curly brackets to read all: ')
    query = input()
    json_acceptable_string = query.replace("'", "\"")
    try: 
        query_dic = json.loads(json_acceptable_string)
        for x in mycol.find(query_dic):
            print(x)
    except ValueError as e: 
        print ("Not a valid dictionary. ",e)

def update():
    print("Select the documents you want to update, in query dictionary format: ")
    query = input()
    json_acceptable_string = query.replace("'", "\"")
    try: 
        myquery = json.loads(json_acceptable_string)
        print("Type the new keys and values to update, in dictionary format. New keys will generate new items")
        print("To update nested values, type \'key1.key2\':\'new value\'")
        dic_aux = input()
        json_acceptable_string = dic_aux.replace("'", "\"")
        try:
            d = json.loads(json_acceptable_string)
            newvalues = { "$set": d }
            print("Update all? (y/n)")
            answer = input()
            if answer == 'y':
                x = mycol.update_many(myquery, newvalues)
                print(x.modified_count, "documents updated.")
                return

            x = mycol.update_one(myquery, newvalues)
            print(x.modified_count, "documents updated.")
        except ValueError as e:
            print("not a valid dictionary. ",e)

    except ValueError as e: 
        print ("Not a valid dictionary. ",e)

def delete():
    print("Select the documents you want to delete, in query dictionary format: ")
    query = input()
    json_acceptable_string = query.replace("'", "\"")
    try: 
        d = json.loads(json_acceptable_string)
        print("Delete all? (y/n)")
        answer = input()
        if answer == 'y':
            x = mycol.delete_many(d)
            print(x.deleted_count, " documents deleted.") 
            return
        
        x = mycol.delete_one(d)
        print(x.deleted_count, " document deleted.")
    except ValueError as e:
        print("Not a valid dictionary format. ",e)
        
while True:
    print("Select your action: ")
    print("1 - Create")
    print("2 - Read")
    print("3 - Update")
    print("4 - Delete")
    print("-1 - Sair")

    option = input()

    if option == '1':
        create()
    elif option == '2':
        read()
    elif option == '3':
        update()
    elif option == '4':
        delete()
    elif option =='-1':
        break