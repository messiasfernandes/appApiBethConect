package br.com.bethpapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.bethpapp.dominio.entidade.Produto;


public interface ProdutoQuery {
	Page<Produto> buscar(String paramentro, Pageable pageable);
	

}
