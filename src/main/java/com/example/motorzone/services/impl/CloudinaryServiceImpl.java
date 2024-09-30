package com.example.motorzone.services.impl;

import com.cloudinary.Cloudinary;
import com.example.motorzone.models.dto.cloudinary.CloudinaryUploadResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public CloudinaryUploadResultDTO uploadImage(MultipartFile file) throws IOException {
        File imgFile = File.createTempFile(TEMP_FILE_PREFIX, file.getOriginalFilename());

        file.transferTo(imgFile);

        Map<?, ?> uploadResult = cloudinary
                .uploader()
                .upload(imgFile, new HashMap<>());

        String publicId = uploadResult.get("public_id").toString();
        String url = uploadResult.get("url").toString();

        imgFile.delete();

        return new CloudinaryUploadResultDTO(publicId, url);
    }

    public boolean deleteImageByPublicId(String publicId) {
        Map<String, String> options = new HashMap<>();
        options.put("invalidate", "true");

        int timesToRetry = 3;

        while (timesToRetry > 0) {
            try {
                Map<?, ?> result = cloudinary.uploader().destroy(publicId, options);

                if ("ok".equals(result.get("result"))) {
                    return true;
                }
            } catch (IOException e) {
                // change when you implement logging mechanism
                System.err.println("Failed to delete image. Attempt: " + (4 - timesToRetry));
                e.printStackTrace();
            }

            --timesToRetry;
        }

        return false;
    }

    public List<CloudinaryUploadResultDTO> uploadImages(List<MultipartFile> files) {
        List<CompletableFuture<CloudinaryUploadResultDTO>> allUploads = files.stream()
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

    public boolean deleteImages(List<String> publicIds) {
        Map<String, String> options = new HashMap<>();
        options.put("invalidate", "true");

        int timesToRetry = 2;

        while (timesToRetry > 0) {
            try {
                Map<?, ?> result = cloudinary.api().deleteResources(publicIds, options);

                if ("ok".equals(result.get("result"))) {
                    return true;
                }
            } catch (Exception e) {
                // change when you implement logging mechanism
                System.err.println("Failed to delete image. Attempt: " + (4 - timesToRetry));
                e.printStackTrace();
            }

            --timesToRetry;
        }

        return false;
    }

}
