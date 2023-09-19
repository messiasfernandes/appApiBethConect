package br.com.bethpapp.dominio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceModel<T> {
	public Page<T> buscar(String nome, Pageable pageable);

	public void excluir(Long codigo);

	public T buccarporid(Long id);

	public T salvar(T objeto);

}
