package com.api.codificacion.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.codificacion.model.Anexo;
import com.api.codificacion.model.AnexoProjectionDto;

@Repository
public interface AnexoRepository extends MongoRepository<Anexo, String> {
	@Query("{ 'sa_anexo' : ?0 }")
	List<Anexo> findBySaAnexo(String sa_anexo);

	// BUSCAR por modelo
	@Query("{ 'uargnsmod' : ?0 }")
	List<Anexo> findByUargnsmod(String uargnsmod);

	// CANTIDAD, busqueda por modelo
	long countByUargnsmod(String uargnsmod);

	List<Anexo> findByUargnsmodStartingWithOrderByUargnsmodDesc(String uargnsmod);

	// Listar solo campos id, username, sa_codetipoanexo, Sa_enviadosap, Sa_anexo
	// filtardo por username, sa_codetipoanexo
	/*
	 * @Query(value = "{}", fields =
	 * "{id : 1, username:1, sa_codetipoanexo:1, sa_enviadosap : 1, sa_anexo:1}")
	 * List<AnexoProjectionDto> findProjectedByIdAndSa_enviadosap();
	 */
	@Query(value = "{ username: ?0, sa_codetipoanexo: ?1 }", fields = "{id : 1, username:1, sa_codetipoanexo:1, sa_enviadosap : 1, sa_anexo:1}")
	List<AnexoProjectionDto> findProjectedByIdAndSa_enviadosap(String username, String sa_codetipoanexo);

	// Listar anexos por username, sa_codetipoanexo, sa_anexo
	@Query("{ 'username' : ?0, 'sa_codetipoanexo' : ?1, 'sa_anexo' : ?2 }")
	List<Anexo> findByUsernameAndSaCodetipoanexoAndSaAnexo(String username, String sa_codetipoanexo, String sa_anexo);

	// Valores unicos de sa_anexo
	@Aggregation(pipeline = {
			"{$match: {username: ?0, sa_codetipoanexo: ?1}}",
			"{$group: {_id: '$sa_anexo'}}",
			"{$sort: {sa_anexo: -1}}"
	})
	List<String> findDistinctSa_anexoByUsernameAndSa_codetipoanexo(String username, String sa_codetipoanexo);

	// Ultimo codigo de barras enviando prefix 10000014
	@Query(value = "{'barcode': {$regex : '^?0'}}", fields = "{'barcode' : 1}")
	List<Anexo> findMaxBarcodeStartingWith(@Param("0") String digits);

}