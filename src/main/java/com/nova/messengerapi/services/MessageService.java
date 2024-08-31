package com.nova.messengerapi.services;

import com.nova.messengerapi.entities.Message;
import com.nova.messengerapi.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message)
    {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessagesByChatId(int chatId)
    {
        return messageRepository.findByChat_ChatId(chatId);
    }
}
