package com.example.Entries_Project.mapper.impl;

import com.example.Entries_Project.mapper.EntryMapper;
import com.example.Entries_Project.entity.Entry;
import com.example.Entries_Project.entity.Image;
import com.example.Entries_Project.dto.entry.EntryRequest;
import com.example.Entries_Project.dto.entry.EntryResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntryMapperImpl implements EntryMapper {
    @Override
    public Entry toEntry(EntryRequest request, Entry entry) {
        entry.setTitle(request.getTitle());
        entry.setSummary(request.getSummary());
        entry.setDate(LocalDate.now());
        return entry;
    }

    @Override
    public EntryResponse toResponse(Entry entry) {
        EntryResponse response = new EntryResponse();
        response.setAuthor(entry.getAuthor().getEmail());
        response.setId(entry.getId());
        response.setTittle(entry.getTitle());
        response.setSummary(entry.getSummary());
        response.setDate(entry.getDate());

        List<String> imagesPath = new ArrayList<>();
        for (Image image : entry.getImages()) {
            imagesPath.add(image.getPath());
        }
        response.setImages(imagesPath);
        return response;
    }

    @Override
    public List<EntryResponse> toResponseList(List<Entry> entries) {
        List<EntryResponse> responses = new ArrayList<>();
        for (Entry entry : entries) {
            responses.add(toResponse(entry));
        }
        return responses;
    }
}
