package com.example.Entries_Project.service;

import com.example.Entries_Project.entity.Audio;
import com.example.Entries_Project.entity.Entry;
import com.example.Entries_Project.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonService {
    Image saveImage(MultipartFile image, Entry entry);
    Audio saveAudio(MultipartFile audio, Entry entry);
    void deleteImage(String filename);
    void deleteAudio(String fileName);
}