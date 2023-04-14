package com.api.codificacion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.codificacion.model.Colors;
import com.api.codificacion.repository.ColorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorsService {
	private final ColorsRepository colorsRepository;
	
	public List<Colors> findAll(){
		return colorsRepository.findAll();
	}

}
