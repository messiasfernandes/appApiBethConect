package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoForncedor;
import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.service.exeption.RegistroNaoEncontrado;

@Service
public class ServiceForncedor extends ServiceFuncoes implements ServiceModel<Fornecedor>{
	@Autowired
   private DaoForncedor daoForncedor;
	@Override
	public Page<Fornecedor> buscar(String nome, Pageable pageable) {
		
		return daoForncedor.buscar(nome, pageable);
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fornecedor buccarporid(Long id) {
		if (daoForncedor.findById(id).isEmpty()) {
			throw new RegistroNaoEncontrado("Produto não encotrada");
		}
		return daoForncedor.findById(id).get();
	}


	@Override
	public Fornecedor salvar(Fornecedor objeto) {
		
		return daoForncedor.save(objeto);
	}

}
