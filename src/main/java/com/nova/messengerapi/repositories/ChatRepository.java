package com.nova.messengerapi.repositories;

import com.nova.messengerapi.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer>
{
    List<Chat> findByUserOneOrUserTwo(int userId1 ,int userId2);
}
