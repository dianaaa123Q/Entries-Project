package com.example.Entries_Project.dto.entry;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EntryResponse {
    private String author;
    private Long id;
    private String tittle;
    private String summary;
    private LocalDateTime reminder;
    private LocalDate date;
    private List<String> images;
    private List<String> audios;
}
