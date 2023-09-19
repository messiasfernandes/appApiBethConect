package br.com.bethpapp.controller.documentacao;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.modelo.dto.EntradaNotaCabecarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Entada de Nota fiscal")
public interface EntradaNotafiscalOpenApi extends ControllerOpenApi{
	@Operation(summary = "Salvar uma nota fiscal e  Movimentação de estoque ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Nota fiscal importada  com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<EntradaNotaCabecarioDTO> importanota( String xml,
			 BigDecimal margen, Long idforma, Integer qtdeparecla) ;
  
	@Operation(summary = "Lista de notas fiscais")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Nota fiscal não  Encotrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EntradaNotaCabecario.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Nota fiscal não  Encotrada", content = @Content) })
	ResponseEntity<Page<EntradaNotaCabecarioDTO>> listar(String nome, Integer pagina, Integer size,
			Pageable page);
	@Operation(summary = "Cancelar  um entrada de nota fiscal ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Status nota fiscal Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<EntradaNotaCabecarioDTO> cancelarNota(@Param(value = "ID de uma Nota") Long id);
}