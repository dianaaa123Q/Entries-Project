package com.example.Entries_Project.dto.entry;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntryRequest {
    private String title;
    private String summary;
    private LocalDateTime reminder;
}