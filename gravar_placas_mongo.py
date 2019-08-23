import pymongo
from ast import literal_eval


def salvarColecao(coll):
	arq = open('QSA_0001_5999.txt', 'r')
	texto = arq.readlines()
	for linha in texto :
	    print(linha)
	    python_dict = literal_eval(linha)
	    result = coll.insert_one(python_dict)
	arq.close()



client = pymongo.MongoClient("mongodb://localhost:27017/")
db = client['placas']
coll = db['veiculos']

#salvarColecao(coll)

placas = coll.find({'Marca/Modelo': 'FORD/KA SE 1.5'})
for cursor in placas:
    #print(cursor)
	print(cursor['Ano Modelo'])

print(client.list_database_names())

# FONTE: https://realpython.com/introduction-to-mongodb-and-python/
