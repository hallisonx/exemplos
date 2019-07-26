# -*- coding: utf-8 -*-
import os
import logging
import telegram
from telegram.error import NetworkError, Unauthorized
from time import sleep


update_id = None
solicitouPonto = "N"

def main():
    """Run the bot."""
    global solicitouPonto
    global update_id
    # Telegram Bot Authorization Token
    bot = telegram.Bot('SUA_KEY')

    # get the first pending update_id, this is so we can skip over it in case
    # we get an "Unauthorized" exception.
    try:
        update_id = bot.get_updates()[0].update_id
    except IndexError:
        update_id = None

    logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')

    while True:
        try:
            echo(bot)
        except NetworkError:
            sleep(1)
        except Unauthorized:
            # The user has removed or blocked the bot.
            update_id += 1


def echo(bot):
    """Echo the message the user sent."""
    
    global update_id
    global solicitouPonto
    # Request updates after the last update_id
    for update in bot.get_updates(offset=update_id, timeout=10):
        update_id = update.update_id + 1

        if update.message:  # your bot can receive updates without messages
            
            if update.message.text == '/start' or update.message.text == '/help':
                update.message.reply_text('Estes são os comandos disponíveis: ')
                update.message.reply_text('/ponto')
                update.message.reply_text('/historico')
            elif update.message.text == '/ponto':
                solicitouPonto = "S"
                update.message.reply_text('Você realmente deseja registrar agora?')
            elif solicitouPonto == "S" and (update.message.text == 'Sim' or update.message.text == 'sim'):
                update.message.reply_text('Ok, vou tentar executar agora')
                os.system('C:/Users/XXXXXXXXXXX/Documents/MafaldaProject/executarByTelegram.bat')
                update.message.reply_text('Comando executado. Mas não posso garantir o sucesso da execução de programas externos')
                solicitouPonto = "N"
            else:
                update.message.reply_text('Mais informações digite /help')


if __name__ == '__main__':
    main()

