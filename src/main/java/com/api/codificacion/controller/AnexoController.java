package com.api.codificacion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.codificacion.model.PatchDto;
import com.api.codificacion.model.Anexo;
import com.api.codificacion.service.AnexoService;
import com.api.codificacion.model.AnexoProjectionDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AnexoController {

	private final AnexoService anexoService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping("/anexos")
	public List<Anexo> findAll() {
		return anexoService.findAll();
	}

	@PostMapping("/anexos")
	public void save(@RequestBody Anexo anexo) {
		anexoService.save(anexo);
	}

	@GetMapping("/anexos/{id}")
	public Anexo findById(@PathVariable String id) {
		return anexoService.findById(id).get();
	}

	@GetMapping("/anexos/sa/{sa_anexo}")
	public List<Anexo> findBySaAnexo(@PathVariable String sa_anexo) {
		return anexoService.findBySaAnexo(sa_anexo);
	}

	@GetMapping("/anexos/mayor/{uargnsmod}")
	public ResponseEntity<Anexo> encontrarMayorPorPrefijo(@PathVariable String uargnsmod) {
		Anexo mayor = anexoService.encontrarMayorPorPrefijo(uargnsmod);
		if (mayor == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(mayor);
		}
	}

	@DeleteMapping("/anexos/{id}")
	public void deleteById(@PathVariable String id) {
		anexoService.deleteById(id);
	}

	@DeleteMapping("/anexos/uargnsmod/{uargnsmod}")
	public void deleteByUargnsmod(@PathVariable String uargnsmod) {
		anexoService.deleteByUargnsmod(uargnsmod);
	}

	@PutMapping("/anexos")
	public void update(@RequestBody Anexo persona) {
		anexoService.save(persona);
	}

	// Actualizar un campo del anexo
	/*
	 * @PatchMapping("/anexos/{id}")
	 * public void updatePatch(@PathVariable String id, @RequestBody PatchDto dto) {
	 * anexoService.partialUpdate(id, dto.getKey(), dto.getValue());
	 * }
	 */
	@PatchMapping("/anexos/{id}")
	@ResponseBody
	public ResponseEntity<Void> updatePatch(@PathVariable String id, @RequestBody PatchDto dto) {
		anexoService.partialUpdate(id, dto.getKey(), dto.getValue());
		return ResponseEntity.noContent().build(); // Devuelve una respuesta HTTP 204 No Content con un objeto JSON vacío
	}
	

	// Listar solo campos id, username, sa_codetipoanexo, Sa_enviadosap, Sa_anexo
	// filtardo por username, sa_codetipoanexo
	// Peticion: /api/v1/anexos/projections?username=Stalyn&sa_codetipoanexo=01
	@GetMapping("/anexos/projections")
	public List<AnexoProjectionDto> findProjectedByIdAndSa_enviadosap(@RequestParam String username,
			@RequestParam String sa_codetipoanexo) {
		return anexoService.findProjectedByIdAndSa_enviadosap(username, sa_codetipoanexo);
	}

	// Listar anexos por username, sa_codetipoanexo, sa_anexo
	// peticion:
	// /api/v1/anexos/lpu?username=Stalyn&sa_codetipoanexo=01&sa_anexo=ANEXO01
	@GetMapping("/anexos/lpu")
	public List<Anexo> getAnexos(@RequestParam String username, @RequestParam String sa_codetipoanexo,
			@RequestParam String sa_anexo) {
		return anexoService.findByUsernameAndSaCodetipoanexoAndSaAnexo(username, sa_codetipoanexo, sa_anexo);
	}

	// Valores unicos de sa_anexo por username y sa_codetipoanexo
	// peticion: /api/v1/anexos/sa_anexo?username=Stalyn&sa_codetipoanexo=01
	@GetMapping("/anexos/sa_anexo")
	public List<String> findDistinctSa_anexoByUsernameAndSa_codetipoanexo(@RequestParam String username,
			@RequestParam String sa_codetipoanexo) {
		return anexoService.findDistinctSa_anexoByUsernameAndSa_codetipoanexo(username, sa_codetipoanexo);
	}
}
