package br.com.bethpapp.controller.documentacao;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.modelo.dto.EstoqueProdutoDTO;
import br.com.bethpapp.modelo.input.EstoqueMovimentoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "EstoqueMovimento")
public interface EstoqueMovimentoOpenApi extends ControllerOpenApi {

	@Operation(summary = "Lista de Movimentações")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Movimentação   Encotrada", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueMovimento.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Movimentação não encontrada", content = @Content) })
	ResponseEntity<Page<EstoqueProdutoDTO>> listar(String paramentro,String tipo, LocalDate dataincio, LocalDate datafim,
			Integer pagina, Integer size,  Pageable page);

	@Operation(summary = "Excluid um Movimentação por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Movimentação excluído"),
			@ApiResponse(responseCode = "404", description = "Movimentação não encontrada") })

	void remover(@Param(value = "ID de um Movienteo") Long id);

	ResponseEntity<EstoqueProdutoDTO> buscar(@Param(value = "ID de um Movimentação") Long idproduto);

	@Operation(summary = "Atualizar um Movimentação ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Movimentação Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<EstoqueProdutoDTO> Atualizar( @Param(value = "id") Long id, @Param(value = "corpo")  EstoqueMovimentoInput movimentacao,
			HttpServletResponse response);

	@Operation(summary = "Salvar um Movimentação ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Movimentenção salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<EstoqueProdutoDTO> adicionar( 
			@Param(value = "corpo") EstoqueMovimentoInput movimentcao, HttpServletResponse response);

}
