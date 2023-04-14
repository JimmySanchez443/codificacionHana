package com.api.codificacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.codificacion.model.Colors;


@Repository
public interface ColorsRepository extends MongoRepository<Colors, String> {

}
