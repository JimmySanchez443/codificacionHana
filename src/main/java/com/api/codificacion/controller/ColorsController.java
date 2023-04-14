package com.api.codificacion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.codificacion.model.Colors;
import com.api.codificacion.service.ColorsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ColorsController {
	private final ColorsService colorsService;
	@GetMapping("/colors")
	public List<Colors> findAll(){
		return colorsService.findAll();
	}

}
