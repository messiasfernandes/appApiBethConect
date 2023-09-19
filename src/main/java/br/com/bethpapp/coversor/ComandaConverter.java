package br.com.bethpapp.coversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.Comanda;
import br.com.bethpapp.modelo.dto.ComandaDTO;
@Component
public class ComandaConverter {

	
	@Autowired
	private ModelMapper modelMapper;

	public ComandaDTO  toDto(Comanda objeto) {

		return modelMapper.map(objeto, ComandaDTO.class);
	}
  
	public Page<ComandaDTO> topage(Page<Comanda> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

	
	public List<ComandaDTO> toCollectionDto(List<Comanda> categorias) {
		return categorias.stream().map(this::toDto).collect(Collectors.toList());
	}
}
