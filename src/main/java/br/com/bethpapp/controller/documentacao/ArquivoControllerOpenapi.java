package br.com.bethpapp.controller.documentacao;

import java.io.IOException;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import br.com.bethpapp.modelo.dto.ArquivoXmlDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Arquivos")
public interface ArquivoControllerOpenapi  extends ControllerOpenApi {
	
	@Operation(summary = "envia nota fiscal servidor ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "xml da nota   criada com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ArquivoXmlDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content)
			 })
	public ResponseEntity<ArquivoXmlDto> upload(@Param(value = "arquvivo")  MultipartFile arquivo);
	
	public ResponseEntity<byte[]> GetArquivo(@PathVariable String arquivo) throws IOException ;

}
