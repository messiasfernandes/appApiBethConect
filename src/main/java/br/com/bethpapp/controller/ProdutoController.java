package br.com.bethpapp.controller;

import java.math.BigDecimal;

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

import br.com.bethpapp.controller.documentacao.ProdutoOpenApi;
import br.com.bethpapp.coversor.ProdutoConverter;
import br.com.bethpapp.dominio.service.ServiceProduto;
import br.com.bethpapp.modelo.dto.CodigoBarraEANDTO;
import br.com.bethpapp.modelo.dto.EntradaNotaCabecarioDTO;
import br.com.bethpapp.modelo.dto.ProdutoDTO;
import br.com.bethpapp.modelo.dto.ProdutoDtoEditar;
import br.com.bethpapp.modelo.input.ProdutoInput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.minidev.json.JSONObject;

@RequestMapping("/produtos")
@RestController
public class ProdutoController implements ProdutoOpenApi {

	@Autowired
	private ProdutoConverter produtoConverter;

	@Autowired
	private ServiceProduto serviceProduto;

	@GetMapping
	@Override
	public ResponseEntity<Page<ProdutoDTO>> listar(

			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoConverter.topage(serviceProduto.buscar(paramentro, page)));
	}

	@GetMapping("/{idproduto}")
	@Override
	public ResponseEntity<ProdutoDtoEditar> buscar(@PathVariable Long idproduto) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoConverter.toDtoEdit(serviceProduto.buccarporid(idproduto)));
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<ProdutoDtoEditar> Atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoInput produto,
			HttpServletResponse response) {
		System.out.println(id);
		produto.setId(id);
		System.out.println(produto.getId());
		var produtoeditado = serviceProduto.salvar(produtoConverter.toEntity(produto));
		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDtoEdit(produtoeditado));
	}

	@PostMapping
	@Override
	public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoInput produto, HttpServletResponse response) {
		System.out.println(produto.getNomeproduto());
		var produtosalvo = serviceProduto.salvar(produtoConverter.toEntity(produto));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoConverter.toDto(produtosalvo));
	}

	@DeleteMapping("/{idproduto}")
	@Override
	public ResponseEntity<Void> remover(@PathVariable Long idproduto) {
		serviceProduto.excluir(idproduto);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/gerarean13")
	public ResponseEntity<CodigoBarraEANDTO > gerarEan13(@RequestParam(value = "cnpj", required = false) String cnpj,
			@RequestParam(value = "codigofabricandte", required = false) String codigofabricandte) {
	//	String ean13 = serviceProduto.geararCodioEan13(cnpj, codigofabricandte);
	    CodigoBarraEANDTO dto = new CodigoBarraEANDTO();
	    dto.setEan13(serviceProduto.geararCodioEan13(cnpj, codigofabricandte));

	    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	//@PostMapping("/gerarean13")
	public ResponseEntity< String> geararCodioEan13(@RequestParam(value = "cnpj", required = false) String cnpj,
			@RequestParam(value = "codigofabricandte", required = false) String codigofabricandte) {
	    // Seu código para gerar o EAN-13
	    
	    String ean13 = serviceProduto.geararCodioEan13(cnpj, codigofabricandte);
	    
	    // Crie um JSON com a chave "ean13" e o valor do EAN-13
	    JSONObject json = new JSONObject();
	    json.put("ean13", ean13);
	    if (ean13 != null) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar o código EAN-13");
	 
	    }
	}
}
