package br.com.bethpapp.coversor;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.SubCategoria;
import br.com.bethpapp.modelo.dto.SubCategoriaDTO;
import br.com.bethpapp.modelo.input.SubCategoriaInput;

@Component
public class SubCategoriaConverter {
	@Autowired
	private ModelMapper modelMapper;

	public SubCategoriaDTO toDto(SubCategoria objeto) {

		return modelMapper.map(objeto, SubCategoriaDTO.class);
	}
	public SubCategoria toEntity(SubCategoriaInput objeto) {

		return modelMapper.map(objeto, SubCategoria.class);
	}
	public List<SubCategoriaDTO> toCollectionDto(List<SubCategoria> subcategorias) {
		return subcategorias.stream().map(this::toDto).collect(Collectors.toList());
	}
	public Page<SubCategoriaDTO> topage(Page<SubCategoria> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
}
