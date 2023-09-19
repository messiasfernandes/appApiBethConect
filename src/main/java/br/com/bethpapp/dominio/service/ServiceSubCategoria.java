package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import br.com.bethpapp.dominio.dao.DaoSubcategoria;
import br.com.bethpapp.dominio.entidade.SubCategoria;
import br.com.bethpapp.dominio.service.exeption.EntidadeEmUsoExeption;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import br.com.bethpapp.dominio.service.exeption.RegistroNaoEncontrado;
import br.com.bethpapp.query.SubCategoriaSpec;
import jakarta.transaction.Transactional;
@Service
public class ServiceSubCategoria extends ServiceFuncoes implements ServiceModel<SubCategoria>{
	@Autowired
	private DaoSubcategoria daoSubcategoria;
	@Override
	public Page<SubCategoria> buscar(String nome, Pageable pageable) {
		SubCategoriaSpec subCategoriaSpec = new SubCategoriaSpec();
		if ((ehnumero(nome) == true)) {
			Long id = Sonumero(nome);

			return daoSubcategoria.findAll(subCategoriaSpec.buscaid(id), pageable);
		}

		else {
			nome = maiuscula(nome);

			return daoSubcategoria.findAll(subCategoriaSpec.buscar(nome).or(subCategoriaSpec.buscasubCategoriar(nome)),
					pageable);
		}
	}


	@Override
	public void excluir(Long codigo) {
		buccarporid(codigo);
	try {
		daoSubcategoria.deleteById(codigo);
		daoSubcategoria.flush();
	} catch (DataIntegrityViolationException e) {
		throw new EntidadeEmUsoExeption("Operação não permitida!! Este registro pode estar asssociado a outra tabela");
	}
	
		
	}

	@Override
	public SubCategoria buccarporid(Long id) {
		if (daoSubcategoria.findById(id).isEmpty()) {
			throw new RegistroNaoEncontrado("Subcategoria não encotrada");
		}
		return daoSubcategoria.findById(id).get();
	}
	@Transactional
	@Override
	public SubCategoria salvar(SubCategoria objeto) {
		SubCategoria subcategoriaexistente = daoSubcategoria.buscar(objeto.getNomeSubCategoria());
		if (subcategoriaexistente != null && !subcategoriaexistente.equals(objeto)) {
		    throw new NegocioException("Subcategoria cadastrada no banco de dados");
		}

		if (subcategoriaexistente == null) {
		    return daoSubcategoria.save(objeto);
		} else {
		    return subcategoriaexistente;
		}

	}
}
