package br.com.bethpapp.controller.documentacao;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import br.com.bethpapp.modelo.dto.ArquivoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Foto Produtos")
public interface ArquivoOpenApi extends ControllerOpenApi{

	@Operation(summary = "Atualiza a foto do produto de um restaurante")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Foto produto  criada com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ArquivoOpenApi.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content)
			 })
	public ResponseEntity<List<ArquivoDto>> upload(@Param(value = "arquivo")  List<MultipartFile>  arquivo);
	
	public ResponseEntity<byte[]> getImage(@PathVariable String arquivo) throws IOException ;
}
