package com.example.Entries_Project.dto.entry;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EntryResponse {
    private String author;
    private Long id;
    private String tittle;
    private String summary;
    private LocalDate date;
    private List<String> images;
}
