package com.example.Entries_Project.mapper;

import com.example.Entries_Project.entity.Entry;
import com.example.Entries_Project.dto.entry.EntryRequest;
import com.example.Entries_Project.dto.entry.EntryResponse;

import java.util.List;

public interface EntryMapper {
    Entry toEntry(EntryRequest request, Entry entry);
    EntryResponse toResponse(Entry entry);
    List<EntryResponse> toResponseList(List<Entry> entries);
}
