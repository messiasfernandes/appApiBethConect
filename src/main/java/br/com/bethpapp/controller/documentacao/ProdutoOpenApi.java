package br.com.bethpapp.controller.documentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.modelo.dto.ProdutoDTO;
import br.com.bethpapp.modelo.dto.ProdutoDtoEditar;
import br.com.bethpapp.modelo.input.ProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Produtos")
public interface ProdutoOpenApi extends ControllerOpenApi {
	
	@Operation(summary = "Lista de Produtos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content) })
	ResponseEntity<Page<ProdutoDTO>> listar(String nome, Integer pagina, Integer size,
			Pageable page);

	@Operation(summary = "Excluir um Produto por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto excluído"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado") })

	ResponseEntity<Void> remover(@Param(value = "ID de um Produto") Long idproduto);

	ResponseEntity<ProdutoDtoEditar> buscar(@Param(value = "ID de um Produto") Long idproduto);
	@Operation(summary = "Atualizar um Produto ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<ProdutoDtoEditar> Atualizar( @Param(value = "id")Long id,  @Param(value = "corpo") ProdutoInput produto, HttpServletResponse response);

	@Operation(summary = "Salvar um Produto ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<ProdutoDTO> criar(@Param(value = "corpo") ProdutoInput produto, HttpServletResponse response);
	
	
}


