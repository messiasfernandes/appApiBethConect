package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoEstoque;
import br.com.bethpapp.dominio.entidade.Estoque;
import jakarta.transaction.Transactional;

@Service
public class ServiceEstoque implements ServiceModel<Estoque> {
	@Autowired
	private DaoEstoque daoEstoque;

	@Override
	public Page<Estoque> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Estoque buccarporid(Long id) {

		return daoEstoque.findById(id).get();
	}

	@Transactional
	@Override
	public Estoque salvar(Estoque objeto) {

		return daoEstoque.save(objeto);
	}

}
