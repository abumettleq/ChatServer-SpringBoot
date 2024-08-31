package com.nova.messengerapi.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "message_content")
    private String messageContent;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    @Column(name = "status")
    private String status;

    public Message() {
    }

    public Message(int messageId, Chat chat, int senderId, String messageContent, Timestamp sentAt, String status) {
        this.messageId = messageId;
        this.chat = chat;
        this.senderId = senderId;
        this.messageContent = messageContent;
        this.sentAt = sentAt;
        this.status = status;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
