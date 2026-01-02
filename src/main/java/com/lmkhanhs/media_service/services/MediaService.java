package com.lmkhanhs.media_service.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {

    @Value("${media.image-dir}")
    private String imageDir;

    @Value("${media.video-dir}")
    private String videoDir;

    public String save(MultipartFile file) throws IOException {
        String contentType = file.getContentType();

        if (file.isEmpty() || contentType == null ) {
            throw new RuntimeException("Invalid file");
        }
        if (!contentType.startsWith("image/") && !contentType.startsWith("video/")) {
            throw new RuntimeException("Only image or video allowed");
        }

        String folder;

        if (contentType.startsWith("image/")) {
            folder = imageDir;
        } else if (contentType.startsWith("video/")) {
            folder = videoDir;
        } else {
            throw new RuntimeException("Only image or video allowed");
        }

        String ext = file.getOriginalFilename()
                         .substring(file.getOriginalFilename().lastIndexOf("."));

        String filename = UUID.randomUUID() + ext;

        Path path = Paths.get(folder, filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return filename;
    }
}
