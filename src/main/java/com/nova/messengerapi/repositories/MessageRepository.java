package com.nova.messengerapi.repositories;

import com.nova.messengerapi.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>
{
    List<Message> findByChat_ChatId(int chatId);
}
