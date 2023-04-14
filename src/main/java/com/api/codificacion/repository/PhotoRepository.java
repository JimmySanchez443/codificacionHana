package com.api.codificacion.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.codificacion.model.Photo;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {

    int deleteByTitle(String title);

    Photo findByTitle(String title);

}