package br.com.bethpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.controller.documentacao.SubCategoriaOpenApi;
import br.com.bethpapp.coversor.SubCategoriaConverter;
import br.com.bethpapp.dominio.service.ServiceSubCategoria;
import br.com.bethpapp.modelo.dto.SubCategoriaDTO;
import br.com.bethpapp.modelo.input.SubCategoriaInput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RequestMapping("/subcategorias")
@RestController
public class SubCategoriaController implements SubCategoriaOpenApi {

	@Autowired
	private ServiceSubCategoria subcategoriaService;
	@Autowired
	private SubCategoriaConverter subCategoriaConverter;

	@Override
	@GetMapping
	public ResponseEntity<Page<SubCategoriaDTO>> listar(
			@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(subCategoriaConverter.topage(subcategoriaService.buscar(parametro, page)));

	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		subcategoriaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<SubCategoriaDTO> buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<SubCategoriaDTO> Atualizar(@PathVariable Long id,
			@Valid @RequestBody SubCategoriaInput subcategoria, HttpServletResponse response) {
		subcategoria.setId(id);
		var subcategoriasalva = subcategoriaService.salvar(subCategoriaConverter.toEntity(subcategoria));
		return ResponseEntity.status(HttpStatus.OK).body(subCategoriaConverter.toDto(subcategoriasalva));
	}

	@PostMapping
	@Override
	public ResponseEntity<SubCategoriaDTO> criar( @Valid @RequestBody SubCategoriaInput subcategoria, HttpServletResponse response) {
		var subcategoriasalva = subcategoriaService.salvar(subCategoriaConverter.toEntity(subcategoria));
		return ResponseEntity.status(HttpStatus.CREATED).body(subCategoriaConverter.toDto(subcategoriasalva));
	}

}
