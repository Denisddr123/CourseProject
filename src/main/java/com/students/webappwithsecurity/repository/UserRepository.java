package com.students.webappwithsecurity.repository;

import com.students.webappwithsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
