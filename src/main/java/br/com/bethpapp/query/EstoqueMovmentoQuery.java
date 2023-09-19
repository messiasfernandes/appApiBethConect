package br.com.bethpapp.query;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;

public interface EstoqueMovmentoQuery {
	

	Page<EstoqueMovimento> buscar(String paramentro, String tipo, LocalDate datanicio, LocalDate datafim, Pageable pageable);
}
