package com.example.Entries_Project.repository;

import com.example.Entries_Project.entity.Audio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
    @Transactional
    void deleteByName(String filename);
}