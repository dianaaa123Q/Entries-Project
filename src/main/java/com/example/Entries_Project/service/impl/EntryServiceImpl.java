package com.example.Entries_Project.service.impl;

import com.example.Entries_Project.config.JwtService;
import com.example.Entries_Project.exception.CustomException;
import com.example.Entries_Project.mapper.EntryMapper;
import com.example.Entries_Project.entity.Entry;
import com.example.Entries_Project.entity.Image;
import com.example.Entries_Project.entity.User;
import com.example.Entries_Project.dto.entry.EntryRequest;
import com.example.Entries_Project.dto.entry.EntryResponse;
import com.example.Entries_Project.repository.EntryRepository;
import com.example.Entries_Project.service.EntryService;
import com.example.Entries_Project.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;
    private final ImageService imageService;
    private final JwtService jwtService;

    @Override
    public List<EntryResponse> all() {
        return entryMapper.toResponseList(entryRepository.findAll());
    }

    @Override
    public EntryResponse addEntry(EntryRequest entryRequest, List<MultipartFile> images, String token) {
        User user = jwtService.getUserFromToken(token);
        Entry entry = entryRepository.save(entryMapper.toEntry(entryRequest, new Entry()));

        List<Image> entryImages = new ArrayList<>();
        for (MultipartFile image : images) {
            entryImages.add(imageService.save(image, entry));
        }

        entry.setImages(entryImages);
        entry.setAuthor(user);
        return entryMapper.toResponse(entryRepository.save(entry));
    }

    @Override
    public EntryResponse updateEntry(EntryRequest entryRequest, String token, Long id) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new CustomException("Entry not found", HttpStatus.NOT_FOUND));
        if (!entry.getAuthor().equals(jwtService.getUserFromToken(token))) {
            throw new CustomException("You are not authorized to update this entry", HttpStatus.UNAUTHORIZED);
        }

        return entryMapper.toResponse(entryRepository.save(entryMapper.toEntry(entryRequest, entry)));
    }

    @Override
    public EntryResponse getEntry(Long id) {
        return entryMapper.toResponse(entryRepository.findById(id).orElseThrow(() -> new CustomException("Entry not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void deleteEntry(Long id, String token) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new CustomException("Entry not found", HttpStatus.NOT_FOUND));
        if (!entry.getAuthor().equals(jwtService.getUserFromToken(token))) {
            throw new CustomException("You are not authorized to delete this entry", HttpStatus.UNAUTHORIZED);
        }
        for (Image image : entry.getImages()) {
            imageService.delete(image.getName());
        }
        entryRepository.delete(entry);
    }
}
