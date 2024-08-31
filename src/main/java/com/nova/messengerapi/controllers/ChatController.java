package com.nova.messengerapi.controllers;

import com.nova.messengerapi.entities.Chat;
import com.nova.messengerapi.entities.Message;
import com.nova.messengerapi.entities.User;
import com.nova.messengerapi.repositories.ChatRepository;
import com.nova.messengerapi.repositories.UserRepository;
import com.nova.messengerapi.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final MessageService messageService;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(MessageService messageService, ChatRepository chatRepository, UserRepository userRepository, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        message.setSentAt(Timestamp.valueOf(LocalDateTime.now()));
        message.setStatus("delivered");
        message = messageService.save(message);

        if (message.getChat().getUserTwo() != 0) {
            messagingTemplate.convertAndSend("/user/" + message.getChat().getUserTwo() + "/queue/messages", message);
        }

        return message;
    }

    @MessageMapping("/privateMessage")
    public void sendPrivateMessage(Message message) {
        User userOne = userRepository.findById(message.getChat().getUserOne()).orElse(null);
        User userTwo = userRepository.findById(message.getChat().getUserTwo()).orElse(null);

        if (userOne != null && userTwo != null) {
            message.setSentAt(Timestamp.valueOf(LocalDateTime.now()));
            message.setStatus("delivered");
            messageService.save(message);

            message.getChat().setLastMessage(message.getMessageContent());
            chatRepository.save(message.getChat());

            messagingTemplate.convertAndSend("/user/" + userTwo.getUserId() + "/queue/messages", message);
        } else {
            throw new IllegalArgumentException("Sender or Receiver not found");
        }
    }

    @GetMapping("/userChats/{userId}")
    public ResponseEntity<List<Chat>> getUserChats(@PathVariable int userId) {

        List<Chat> chats = chatRepository.findByUserOneOrUserTwo(userId, userId);

        return ResponseEntity.ok(chats);
    }

    @GetMapping("/userMessages/{chatId}")
    public ResponseEntity<List<Message>> getUserMessages(@PathVariable int chatId)
    {
        List<Message> messages = messageService.getAllMessagesByChatId(chatId);

        return ResponseEntity.ok(messages);
    }
}

