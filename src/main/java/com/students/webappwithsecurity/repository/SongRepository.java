package com.students.webappwithsecurity.repository;

import com.students.webappwithsecurity.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
