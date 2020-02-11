package com.example.crud_demo.controllers;

import com.example.crud_demo.models.ImageFile;
import com.example.crud_demo.payload.UploadFileResponse;
import com.example.crud_demo.repositories.ImageFileRepository;
import com.example.crud_demo.service.ImageFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImageFileController {
    @Autowired
    private ImageFileStorageService imageFileStorageService;

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        ImageFile imageFile = imageFileStorageService.storeFile(file);

        String imageFileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path("" + imageFile.getId())
                .toUriString();

        return "Falha";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        ImageFile imageFile = imageFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageFile.getFileName() + "\"")
                .body(new ByteArrayResource(imageFile.getData()));
    }

    @GetMapping("/allfiles")
    public List<String> getAllFileNames(){
        ArrayList<String> names = new ArrayList<>();
        for(ImageFile file : imageFileStorageService.getAll())
            names.add(file.getFileName());
        return names;
    }
}
