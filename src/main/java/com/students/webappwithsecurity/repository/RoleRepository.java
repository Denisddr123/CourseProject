package com.students.webappwithsecurity.repository;

import com.students.webappwithsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
