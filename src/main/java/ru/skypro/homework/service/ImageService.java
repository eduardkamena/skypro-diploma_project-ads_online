package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String saveImage(MultipartFile image, String name) throws IOException;

    byte[] getImage(String name) throws IOException;

    void deleteImage(String path);

}
