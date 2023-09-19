/**
 * 
 */
package br.com.bethpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.controller.documentacao.FornecedorOpenApi;
import br.com.bethpapp.coversor.FornecedorConverter;
import br.com.bethpapp.dominio.service.ServiceForncedor;
import br.com.bethpapp.modelo.dto.FornecedorDTO;
import br.com.bethpapp.modelo.input.FornecedorInput;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author messias
 *
 */
@RestController
@RequestMapping("/forncedores")
public class FornecedorController implements FornecedorOpenApi {
	@Autowired
	private FornecedorConverter fornecedorConverter;
	@Autowired
	private ServiceForncedor serviceForncedor;
    @GetMapping
	@Override
	public ResponseEntity<Page<FornecedorDTO>> listar(	@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		
		return  ResponseEntity.status(HttpStatus.OK)
				.body(fornecedorConverter.topage(serviceForncedor.buscar(paramentro, page)));
	}

	@Override
	public ResponseEntity<Void> remover(Long idfornecedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<FornecedorDTO> buscar(Long idFoncedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<FornecedorDTO> Atualizar(FornecedorInput Foncedor, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<FornecedorDTO> adicionar(FornecedorInput Foncedor, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
