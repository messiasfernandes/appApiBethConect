package br.com.bethpapp.coversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.modelo.dto.EntradaNotaCabecarioDTO;

@Component
public class EntradaNotaFiscalConverter {
	@Autowired
	private ModelMapper modelMapper;

	public EntradaNotaCabecarioDTO toDto(EntradaNotaCabecario objeto) {

		return modelMapper.map(objeto, EntradaNotaCabecarioDTO.class);
	}
	public List<EntradaNotaCabecarioDTO> toCollectionDto(List<EntradaNotaCabecario> entradas) {
		return entradas.stream().map(this::toDto).collect(Collectors.toList());
	}
	public Page<EntradaNotaCabecarioDTO> topage(Page<EntradaNotaCabecario> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

}
