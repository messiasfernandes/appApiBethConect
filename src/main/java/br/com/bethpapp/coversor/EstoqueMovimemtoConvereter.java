package br.com.bethpapp.coversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.modelo.dto.EstoqueProdutoDTO;
import br.com.bethpapp.modelo.input.EstoqueMovimentoInput;

@Component
public class EstoqueMovimemtoConvereter {

	@Autowired
	private ModelMapper modelMapper;

	public EstoqueProdutoDTO toDto(EstoqueMovimento objeto) {

		return modelMapper.map(objeto, EstoqueProdutoDTO.class);
	}
	public EstoqueMovimento toEntity(EstoqueMovimentoInput objeto) {

		return modelMapper.map(objeto, EstoqueMovimento.class);
	}
	public Page<EstoqueProdutoDTO> topage(Page<EstoqueMovimento> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

	public List<EstoqueProdutoDTO> toCollectionDto(List<EstoqueMovimento> categorias) {
		return categorias.stream().map(this::toDto).collect(Collectors.toList());
	}

}
