package br.com.bethpapp.controller.documentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.modelo.dto.FornecedorDTO;
import br.com.bethpapp.modelo.input.FornecedorInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Forccedores")
public interface FornecedorOpenApi  extends ControllerOpenApi{
	
	@Operation(summary = "Lista de Forncedores")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fornecdor  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Foncedor não encontrado", content = @Content) })
	ResponseEntity<Page<FornecedorDTO>> listar( String nome,Integer pagina,Integer size, Pageable page);

	@Operation(summary = "Exclui um Foncedor por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description  = "Foncedor excluído"),
		@ApiResponse(responseCode =  "404", description  = "Foncedor não encontrada")
	})
	
	ResponseEntity<Void> remover(@Param(value = "ID de um Fornecedor") Long idfornecedor);
	
	ResponseEntity<FornecedorDTO> buscar(
			@Param(value = "ID de um Produto")
			Long idFoncedor);


	ResponseEntity<FornecedorDTO> Atualizar(
			@Param(value = "corpo"  )
			FornecedorInput Foncedor,HttpServletResponse response);

     	public ResponseEntity<FornecedorDTO> adicionar(	@Param(value = "corpo"  ) FornecedorInput Foncedor, HttpServletResponse response) ;


}
