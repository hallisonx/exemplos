# -*- coding: utf-8 -*-
import pandas as pd
import requests
from bs4 import BeautifulSoup

list = []
dict = {}

arq = open('C:/Users/03906332403/git/mafaldaPy/lista_qse_1000_1999.txt', 'w')

for i in range(1000, 1999, 1):
    placa = str("%04d"%i)

    link = "http://wsdetran.pb.gov.br/DT_DUT_CLIENTE/ConsultaDUT?display=web&placa=QSE" + placa
    print(link)
    
    req = requests.get(link)
    if req.status_code == 200:
        #print('Requisição bem sucedida!')
        content = req.content
        soup = BeautifulSoup(content, 'html.parser')

        try:
            p = soup.find_all(name='p')[2]
            p_str = str(p.text.replace("              ",""))
            lista_atributos = p_str.split("\n")
            
            for s in lista_atributos:
                if ":" in s:
                    linha = s.split(": ")
                    if len(linha) == 2:
                        dict[linha[0].strip()] = linha[1].strip()

            
            arq.write(str(dict) + ",\n")
            print(dict)
            #list.append(dict)

        except IndexError:
            print("pulou ", placa)

arq.close()
