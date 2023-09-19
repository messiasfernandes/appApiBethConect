package br.com.bethpapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.enumerado.StatusEntradaNota;

public interface EntradasdeNotaRepositoryQuery {
	Boolean buscarnota(Long codigoFonecedor, String numeroNota, StatusEntradaNota status);
	Page<EntradaNotaCabecario> buscar(String paramentro, Pageable pageable);
	Page<EntradaNotaCabecario> buscarCancelada(String paramentro, Pageable pageable);
}
