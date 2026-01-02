package com.lmkhanhs.media_service.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lmkhanhs.media_service.services.MediaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor    
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MediaController {

    MediaService mediaService;
    
    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        String filename = mediaService.save(file);

        String type = file.getContentType().startsWith("image/") ? "images" : "videos";
        String base = String.format("%s://%s:%d", 
                request.getScheme(), 
                request.getServerName(), 
                request.getServerPort()
        );
        return Map.of(
                "type", type,
                "filename", filename,
                "url", base + "/" + type + "/" + filename
        );
    }
}
