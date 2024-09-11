package com.example.motorzone.services.impl;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
}
