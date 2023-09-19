package br.com.bethpapp.controller.documentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bethpapp.dominio.entidade.SubCategoria;
import br.com.bethpapp.modelo.dto.SubCategoriaDTO;
import br.com.bethpapp.modelo.input.SubCategoriaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Subcategorias")
public interface SubCategoriaOpenApi extends ControllerOpenApi {
	@Operation(summary = "Listar Subcategoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Subcategoria  Encotrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SubCategoria.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Subcategoria não encontrada", content = @Content) })
	ResponseEntity<Page<SubCategoriaDTO>> listar(String parametro, Integer pagina, Integer size,
			Pageable page);

	@Operation(summary = "Excluid um Subcategoria por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Subcategoria excluído"),
			@ApiResponse(responseCode = "404", description = "Subcategoria não encontrado") })

	ResponseEntity<Void> remover(@Param(value = "ID de uma Subcategoria") Long id);

	ResponseEntity<SubCategoriaDTO> buscar(@Param(value = "ID de um Subcategoria") Long id);
	@Operation(summary = "Atualizar um Subcategoria ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Subcategoria Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<SubCategoriaDTO> Atualizar( @Param(value = "id")Long id,  @Param(value = "corpo") SubCategoriaInput subcategoria, HttpServletResponse response);

	@Operation(summary = "Salvar um Subcategoria ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Subcategoria salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<SubCategoriaDTO> criar(@Param(value = "corpo") SubCategoriaInput subcategoria, HttpServletResponse response);

}
