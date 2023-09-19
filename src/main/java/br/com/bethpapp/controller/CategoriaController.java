package br.com.bethpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.CategoriaConverter;
import br.com.bethpapp.dominio.service.CategoriaService;
import br.com.bethpapp.modelo.dto.CategoriaDTO;
import br.com.bethpapp.modelo.input.CategoriaInput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/categorias")
@RestController
public class CategoriaController  extends ControllerEvent{

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaConverter categoriaConverter;

	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> listar(
			@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(categoriaConverter.topage(categoriaService.buscar(parametro, page)));

	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> adicionar(@Valid @RequestBody CategoriaInput categoria,
			HttpServletResponse response) {

		var categoriasalva = categoriaService.salvar(categoriaConverter.toEntity(categoria));

		criaevento(categoriasalva.getId(), response);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaConverter.toDto(categoriasalva));

	}


	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		categoriaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
