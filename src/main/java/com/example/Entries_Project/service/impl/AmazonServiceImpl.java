package com.example.Entries_Project.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.Entries_Project.entity.Audio;
import com.example.Entries_Project.entity.Entry;
import com.example.Entries_Project.entity.Image;
import com.example.Entries_Project.exception.CustomException;
import com.example.Entries_Project.repository.AudioRepository;
import com.example.Entries_Project.repository.ImageRepository;
import com.example.Entries_Project.service.AmazonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AmazonServiceImpl implements AmazonService {
    private final ImageRepository imageRepository;
    private final AudioRepository audioRepository;
    private final AmazonS3 s3;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public Image saveImage(MultipartFile file, Entry entry) {
        String fileName = uploadFileToAWS(file);

        Image image = new Image();
        image.setName(fileName);
        image.setPath(s3.getUrl(bucketName, fileName).toString());
        image.setEntry(entry);

        return imageRepository.save(image);
    }

    @Override
    public Audio saveAudio(MultipartFile file, Entry entry) {
        String fileName = uploadFileToAWS(file);

        Audio audio = new Audio();
        audio.setName(fileName);
        audio.setPath(s3.getUrl(bucketName, fileName).toString());
        audio.setEntry(entry);

        return audioRepository.save(audio);
    }

    @Override
    public void deleteImage(String filename) {
        imageRepository.deleteByName(filename);
        s3.deleteObject(bucketName, filename);
    }

    @Override
    public void deleteAudio(String fileName) {
        audioRepository.deleteByName(fileName);
        s3.deleteObject(bucketName, fileName);
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new CustomException("Error while converting multipart file", HttpStatus.BAD_GATEWAY);
        }
        return convertFile;
    }

    private String uploadFileToAWS(MultipartFile file) {
        if (file.isEmpty()) {
            throw new CustomException("Incorrect file", HttpStatus.BAD_REQUEST);
        }
        File convertedFile = convertMultipartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+", "_");;
        s3.putObject(new PutObjectRequest(bucketName, fileName, convertedFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        boolean delete = convertedFile.delete();
        if (!delete) {
            throw new CustomException("Failed to delete file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return fileName;
    }
}