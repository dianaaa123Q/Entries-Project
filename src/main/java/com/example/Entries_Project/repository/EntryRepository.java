package com.example.Entries_Project.repository;

import com.example.Entries_Project.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByReminderBeforeAndReminderIsNotNull(LocalDateTime now);
}
