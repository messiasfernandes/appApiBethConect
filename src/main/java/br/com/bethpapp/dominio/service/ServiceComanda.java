package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoComanda;
import br.com.bethpapp.dominio.entidade.Comanda;
@Service
public class ServiceComanda implements ServiceModel<Comanda> {
	@Autowired
  private DaoComanda daoComanda;
	@Override
	public Page<Comanda> buscar(String nome, Pageable pageable) {
	
		return daoComanda.buscar(nome, pageable);
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		daoComanda.deleteById(codigo);
	}

	@Override
	public Comanda buccarporid(Long id) {
		// TODO Auto-generated method stub
		return daoComanda.findById(id).get();
	}

	@Override
	public Comanda salvar(Comanda objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
