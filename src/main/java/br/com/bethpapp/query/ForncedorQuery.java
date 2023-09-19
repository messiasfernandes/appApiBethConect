package br.com.bethpapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.Fornecedor;

public interface ForncedorQuery {
	Page<Fornecedor> buscar(String paramentro, Pageable pageable);
}
