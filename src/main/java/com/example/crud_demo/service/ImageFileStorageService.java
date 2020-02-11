package com.example.crud_demo.service;

import com.example.crud_demo.models.ImageFile;
import com.example.crud_demo.repositories.ImageFileRepository;
import com.example.crud_demo.exceptions.FileStorageException;
import com.example.crud_demo.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageFileStorageService {
    @Autowired
    private ImageFileRepository imageFileRepository;

    public ImageFile storeFile(MultipartFile file){
        String name  = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (name.contains(".."))
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + name);
            ImageFile imageFile = new ImageFile(name, file.getContentType(), file.getBytes());
            System.out.println("Sucess");
            return imageFileRepository.save(imageFile);
        }catch (IOException e) {
            throw new FileStorageException("Could not store file " + name + ". Please try again!", e);
        }
    }

    public ImageFile getFile(Long fileId) {
        return imageFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public List<ImageFile> getAll(){
        return imageFileRepository.findAll();
    }
}
