package com.api.codificacion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import com.api.codificacion.repository.AnexoRepository;
import com.api.codificacion.model.Anexo;
import com.api.codificacion.model.AnexoProjectionDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnexoService {

	private final AnexoRepository anexoRepository;

	public void save(Anexo anexo) {
		anexoRepository.save(anexo);
	}

	public List<Anexo> findAll() {
		return anexoRepository.findAll();
	}

	public Optional<Anexo> findById(String id) {
		return anexoRepository.findById(id);
	}

	public List<Anexo> findBySaAnexo(String sa_anexo) {
		return anexoRepository.findBySaAnexo(sa_anexo);

	}

	public Anexo encontrarMayorPorPrefijo(String uargnsmod) {
		List<Anexo> registros = anexoRepository.findByUargnsmodStartingWithOrderByUargnsmodDesc(uargnsmod);
		if (registros.isEmpty()) {
			return null;
		} else {
			return registros.get(0);
		}
	}

	public void deleteById(String id) {
		anexoRepository.deleteById(id);
	}

	public void deleteByUargnsmod(String uargnsmod) {
		List<Anexo> anexos = anexoRepository.findByUargnsmodStartingWithOrderByUargnsmodDesc(uargnsmod);
		anexoRepository.deleteAll(anexos);
	}

	public boolean partialUpdate(String id, String key, String value) {

		Optional<Anexo> optional = findById(id);

		if (optional.isPresent()) {
			Anexo anexo = optional.get();

			if (key.equalsIgnoreCase("sa_enviadosap")) {
				System.out.println("Updating sa_enviadosap");
				anexo.setSa_enviadosap(value);
			}
			save(anexo);
		} else {
			System.out.println("Updating aenxo");
		}
		return true;
	}

	//Listar solo campos id, username, sa_codetipoanexo, Sa_enviadosap, Sa_anexo filtardo por username, sa_codetipoanexo
	public List<AnexoProjectionDto> findProjectedByIdAndSa_enviadosap(String username, String sa_codetipoanexo) {
		return anexoRepository.findProjectedByIdAndSa_enviadosap(username, sa_codetipoanexo);
	}

	//Listar anexos por username, sa_codetipoanexo, sa_anexo
	public List<Anexo> findByUsernameAndSaCodetipoanexoAndSaAnexo(String username, String sa_codetipoanexo, String sa_anexo) {
		return anexoRepository.findByUsernameAndSaCodetipoanexoAndSaAnexo(username, sa_codetipoanexo, sa_anexo);
	}

	//Valores unicos de sa_anexo por username y sa_codetipoanexo
	public List<String> findDistinctSa_anexoByUsernameAndSa_codetipoanexo(String username, String sa_codetipoanexo) {
        return anexoRepository.findDistinctSa_anexoByUsernameAndSa_codetipoanexo(username, sa_codetipoanexo);
    }

}
