package com.craftstudio70.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Log4j
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botNamw;
    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botNamw;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var originalMessage  = update.getMessage();
        log.debug(originalMessage.getText());

        var responce = new SendMessage();
        responce.setChatId(originalMessage.getChatId().toString());
        responce.setText("Hello our site https://craftstudio70.com");
        SendAnswerMessage(responce);
    }

    public void SendAnswerMessage(SendMessage message){
        if(message!=null){
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }
}
