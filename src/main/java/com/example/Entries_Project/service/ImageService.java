package com.example.Entries_Project.service;

import com.example.Entries_Project.entity.Entry;
import com.example.Entries_Project.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image save(MultipartFile image, Entry entry);
    void delete(String filename);
}
