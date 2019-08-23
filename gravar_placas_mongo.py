import pymongo
from ast import literal_eval

client = pymongo.MongoClient("mongodb://localhost:27017/")
db = client['placas']
coll = db['veiculos']

#gravarNoBanco(coll)

placa = coll.find_one({'Placa': 'OGA3304'})
print(type(placa))
print(placa['Marca/Modelo'])
print(client.list_database_names())



def gravarNoBanco(coll):
	arq = open('OGE_0001_9999.txt', 'r')
	texto = arq.readlines()
	for linha in texto :
	    print(linha)
	    python_dict = literal_eval(linha)
	    result = coll.insert_one(python_dict)
	arq.close()
