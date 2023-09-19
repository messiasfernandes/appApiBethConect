package br.com.bethpapp.controller.documentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.bethpapp.dominio.entidade.Comanda;
import br.com.bethpapp.modelo.dto.ComandaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Comandas")
public interface ComandaOpenApi extends ControllerOpenApi{
	
	@Operation(summary = "Lista de Comndas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comanda  Encotrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comanda.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Comanda não encontradaq", content = @Content) })
	ResponseEntity<Page<ComandaDTO>> listar(String nome, Integer pagina, Integer size,
			Pageable page);

}
