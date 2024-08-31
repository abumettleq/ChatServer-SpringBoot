package com.nova.messengerapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chats")
public class Chat
{
    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    int chatId;

    @Column(name = "user_one")
    int userOne;

    @Column(name = "user_two")
    int userTwo;

    @Column(name = "last_message")
    String lastMessage;

    @Column(name = "slot_name")
    String slotName;

    public Chat() {
    }

    public Chat(int chatId, int userOne, int userTwo,  String lastMessage, String slotName) {
        this.chatId = chatId;
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.lastMessage = lastMessage;
        this.slotName = slotName;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserOne() {
        return userOne;
    }

    public void setUserOne(int senderId) {
        this.userOne = senderId;
    }

    public int getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(int receiverId) {
        this.userTwo = receiverId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }
}
