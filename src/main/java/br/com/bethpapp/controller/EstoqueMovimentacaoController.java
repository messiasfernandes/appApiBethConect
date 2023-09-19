package br.com.bethpapp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.controller.documentacao.EstoqueMovimentoOpenApi;
import br.com.bethpapp.coversor.EstoqueMovimemtoConvereter;
import br.com.bethpapp.dominio.service.ServiceEstoqueMovimento;
import br.com.bethpapp.modelo.dto.EstoqueProdutoDTO;
import br.com.bethpapp.modelo.input.EstoqueMovimentoInput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequestMapping("/movimentacoesestoque")
@RestController
public class EstoqueMovimentacaoController implements EstoqueMovimentoOpenApi {
	@Autowired
	private ServiceEstoqueMovimento serviceEstoqueMovimento;
	@Autowired
	private EstoqueMovimemtoConvereter estoqueMovimemtoConvereter;

	@GetMapping
	@Override
	public ResponseEntity<Page<EstoqueProdutoDTO>> listar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "tipo", required = false, defaultValue = "") String tipo,
			@RequestParam(value = "dataincio", required = false) LocalDate dataincio,
			@RequestParam(value = "datafim", required = false) LocalDate datafim,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(estoqueMovimemtoConvereter
				.topage(serviceEstoqueMovimento.listar(paramentro,tipo, dataincio, datafim, page)));
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResponseEntity<EstoqueProdutoDTO> buscar(Long idproduto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<EstoqueProdutoDTO> Atualizar( Long id,EstoqueMovimentoInput movimentacao,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping
	@Override
	public ResponseEntity<EstoqueProdutoDTO> adicionar(@Valid @RequestBody EstoqueMovimentoInput movimentcao,
			HttpServletResponse response) {
		var movmentacaosalva = serviceEstoqueMovimento.salvar(estoqueMovimemtoConvereter.toEntity(movimentcao));
		return ResponseEntity.status(HttpStatus.CREATED).body(estoqueMovimemtoConvereter.toDto(movmentacaosalva));
	}
		

}
