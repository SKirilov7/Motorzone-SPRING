package com.example.motorzone.services.impl;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CloudinaryServiceImpl {

    private static final String TEMP_FILE_PREFIX = "temp-file";
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        File imgFile = File.createTempFile(TEMP_FILE_PREFIX, file.getOriginalFilename());

        file.transferTo(imgFile);

        String url = cloudinary
                .uploader()
                .upload(imgFile, new HashMap<>())
                .get("url").toString();


        imgFile.delete();

        return url;
    }

    public List<String> uploadImages(List<MultipartFile> files) {
        List<CompletableFuture<String>> allUploads = files.stream()
                .map(file -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return uploadImage(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
                    }
                }))
                .toList();

        return allUploads.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

}
