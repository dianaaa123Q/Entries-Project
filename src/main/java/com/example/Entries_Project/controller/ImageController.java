package com.example.Entries_Project.controller;

import com.example.Entries_Project.service.AmazonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final AmazonService amazonService;

    @DeleteMapping("/{imageName}")
    public void delete(@PathVariable String imageName) {
        amazonService.deleteImage(imageName);
    }
}
