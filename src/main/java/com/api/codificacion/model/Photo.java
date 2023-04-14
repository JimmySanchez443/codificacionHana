package com.api.codificacion.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "photos")
@Data
public class Photo {
    @Id
    private String id;

    private String title;
    private String codetipoanexo;
    private String username;

    private Binary image;

    public Photo(String title) {
        this.title = title;
    }

}
