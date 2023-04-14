package com.api.codificacion.service;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.codificacion.model.Photo;
import com.api.codificacion.repository.PhotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepository.insert(photo);
        return photo.getId();
    }

    public Photo getPhoto(String id) {
        // return photoRepository.findById(id).get();
        return photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo not found"));

    }

    public int deletePhotoByTitle(String title) {
        return photoRepository.deleteByTitle(title);
    }

    public Photo getPhotosByTitle(String title) {
        return photoRepository.findByTitle(title);
    }
}