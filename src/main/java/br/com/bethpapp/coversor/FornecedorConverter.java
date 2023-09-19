package br.com.bethpapp.coversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.modelo.dto.FornecedorDTO;
import br.com.bethpapp.modelo.input.ProdutoInput;
@Component
public class FornecedorConverter {
	@Autowired
	private ModelMapper modelMapper;

	public FornecedorDTO toDto(Fornecedor objeto) {

		return modelMapper.map(objeto, FornecedorDTO.class);
	}

	public Produto toEntity(ProdutoInput objeto) {

		return modelMapper.map(objeto, Produto.class);
	}

	public Page<FornecedorDTO> topage(Page<Fornecedor> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

	public List<FornecedorDTO> toCollectionDto(List<Fornecedor> categorias) {
		return categorias.stream().map(this::toDto).collect(Collectors.toList());
	}



}
