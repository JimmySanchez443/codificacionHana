package com.api.codificacion.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.codificacion.model.Photo;
import com.api.codificacion.service.PhotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    /*
     * @PostMapping(value = "/photos/add", consumes = "multipart/form-data",
     * produces = "application/json")
     * public String addPhoto(@RequestParam("title") String title,
     * 
     * @RequestParam("image") MultipartFile image, Model model)
     * throws IOException {
     * String id = photoService.addPhoto(title, image);
     * return "redirect:/photos/" + id;
     * }
     */

    @PostMapping(value = "/photos/add", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<String> addPhoto(@RequestParam("title") String title,
            @RequestParam("image") MultipartFile image, Model model) {
        try {
            String id = photoService.addPhoto(title, image);
            return ResponseEntity.ok("{\"id\": \"" + id + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /*
     * @GetMapping("/photos/{id}")
     * public String getPhoto(@PathVariable String id, Model model) {
     * Photo photo = photoService.getPhoto(id);
     * model.addAttribute("title", photo.getTitle());
     * model.addAttribute("image",
     * Base64.getEncoder().encodeToString(photo.getImage().getData()));
     * return "photos";
     * }
     */

    /*
     * @GetMapping("/photos/{id}")
     * public ResponseEntity<byte[]> getPhoto(@PathVariable String id) {
     * Photo photo = photoService.getPhoto(id);
     * return ResponseEntity.ok()
     * .contentType(MediaType.IMAGE_PNG)
     * .body(photo.getImage().getData());
     * }
     */

    @GetMapping(value = "/photos/{id}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public ResponseEntity<byte[]> getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        byte[] imageBytes = photo.getImage().getData();

        // determinar el tipo de contenido de la imagen
        MediaType contentType;
        if (photo.getImage().getType() == BsonBinarySubType.BINARY.getValue()) {
            contentType = MediaType.IMAGE_JPEG;
        } else {
            contentType = MediaType.IMAGE_PNG;
        }

        return ResponseEntity.ok().contentType(contentType).body(imageBytes);
    }

    @GetMapping(value = "/photos/sa/{title}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public ResponseEntity<byte[]> getPhotosByTitle(@PathVariable("title") String title) {
        Photo photo = photoService.getPhotosByTitle(title);
        byte[] imageBytes = photo.getImage().getData();

        // determinar el tipo de contenido de la imagen
        MediaType contentType;
        if (photo.getImage().getType() == BsonBinarySubType.BINARY.getValue()) {
            contentType = MediaType.IMAGE_JPEG;
        } else {
            contentType = MediaType.IMAGE_PNG;
        }

        return ResponseEntity.ok().contentType(contentType).body(imageBytes);
    }

    /*
     * @DeleteMapping(value = "/photos", produces = "application/json")
     * public ResponseEntity<String> deletePhotoByTitle(@RequestParam("title")
     * String title) {
     * try {
     * int deletedCount = photoService.deletePhotoByTitle(title);
     * if (deletedCount == 0) {
     * 
     * return ResponseEntity.status(HttpStatus.NOT_FOUND)
     * .body("{\"error\": \"No se encontró ninguna foto con el título especificado.\"}"
     * );
     * }
     * return ResponseEntity.ok("{\"deletedCount\": " + deletedCount + "}");
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
     * .body("{\"error\": \"" + e.getMessage() + "\"}");
     * }
     * }
     */

    @DeleteMapping(value = "/photos/sa/{title}", produces = "application/json")
    public ResponseEntity<String> deletePhotoByTitle(@PathVariable("title") String title) {
        try {
            // int deletedCount = photoService.deletePhotoByTitle(title);
            int deletedCount = photoService.deletePhotoByTitle(title);
            if (deletedCount == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"No se encontró ninguna foto con el título especificado.\"}");
            }
            return ResponseEntity.ok("{\"deletedCount\": " + deletedCount + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
