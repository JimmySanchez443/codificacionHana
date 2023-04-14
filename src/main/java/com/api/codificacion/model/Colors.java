package com.api.codificacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Document(value = "Colors")
@Data
public class Colors {
	@Id
	@JsonIgnore//excluye el id del json de respuesta
	private String id;
	
	private String u_colcode;
	private String u_coldesc;

}
