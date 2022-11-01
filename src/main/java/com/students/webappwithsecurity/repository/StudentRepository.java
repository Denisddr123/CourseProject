package com.students.webappwithsecurity.repository;

import com.students.webappwithsecurity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
