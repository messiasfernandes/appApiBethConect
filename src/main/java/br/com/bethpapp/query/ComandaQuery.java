package br.com.bethpapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.Comanda;

public interface ComandaQuery {
	Page<Comanda> buscar(String paramentro, Pageable pageable);
}
