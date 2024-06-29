package com.mido.controllers;

import com.mido.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/image/{path}")
    public ResponseEntity<Resource> getImage(@PathVariable String path) throws IOException {
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);
        Resource image = fileStorageService.downloadImage(decodedPath);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
