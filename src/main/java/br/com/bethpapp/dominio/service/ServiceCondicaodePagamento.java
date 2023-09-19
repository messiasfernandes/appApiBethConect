package br.com.bethpapp.dominio.service;


import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.CondicaoPagamento;

@Service
public class ServiceCondicaodePagamento implements ServiceModel<CondicaoPagamento> {

	@Override
	public Page<CondicaoPagamento> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public CondicaoPagamento buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CondicaoPagamento salvar(CondicaoPagamento objeto) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private CondicaoPagamento ValidarCondicao() {
//		return null;
//	}

}
