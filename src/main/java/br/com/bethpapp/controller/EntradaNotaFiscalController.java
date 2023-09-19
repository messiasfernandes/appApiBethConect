package br.com.bethpapp.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.controller.documentacao.EntradaNotafiscalOpenApi;
import br.com.bethpapp.coversor.EntradaNotaFiscalConverter;
import br.com.bethpapp.dominio.service.ServiceImportaNotafiscal;
import br.com.bethpapp.dominio.service.ServiceNotaFiscal;
import br.com.bethpapp.modelo.dto.EntradaNotaCabecarioDTO;

@RequestMapping("/importarnotasfiscais")
@RestController
public class EntradaNotaFiscalController extends ControllerEvent implements EntradaNotafiscalOpenApi {

	@Autowired
	private ServiceImportaNotafiscal importar_notasevice;
	@Autowired
	private EntradaNotaFiscalConverter entradanfConverter;
	@Autowired
	private ServiceNotaFiscal serviceNotaFiscal;

	@Override
	@PostMapping
	public ResponseEntity<EntradaNotaCabecarioDTO> importanota(@RequestParam(value = "xml", required = true) String xml,
			@RequestParam(value = "margem", required = true) BigDecimal margen,
			@RequestParam(value = "idforma", required = true) Long idforma,
			@RequestParam(value = "qtdeparcela", required = true) Integer qtdeparcela) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(entradanfConverter.toDto(importar_notasevice.imporxml(xml, margen, idforma, qtdeparcela)));
	}

	@GetMapping
	@Override
	public ResponseEntity<Page<EntradaNotaCabecarioDTO>> listar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(entradanfConverter.topage(serviceNotaFiscal.buscar(paramentro, page)));
	}
	@GetMapping("/canceladadas")
	
	public ResponseEntity<Page<EntradaNotaCabecarioDTO>> bucarCancelada(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina, 
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(entradanfConverter.topage(serviceNotaFiscal.buscaCcancelada(paramentro, page)));
	}
	@PutMapping
	@Override
	public ResponseEntity<EntradaNotaCabecarioDTO> cancelarNota(@RequestParam Long id) {
		var notaCancelada = serviceNotaFiscal.CancelarNota(id);
		return ResponseEntity.status(HttpStatus.OK).body(entradanfConverter.toDto(notaCancelada));
	}

}
