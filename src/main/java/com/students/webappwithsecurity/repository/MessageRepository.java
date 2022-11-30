package com.students.webappwithsecurity.repository;

import com.students.webappwithsecurity.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM MESSAGES WHERE email = ?1 order by date", nativeQuery = true)
    List<Message> findActionByEmail(String email);
}
