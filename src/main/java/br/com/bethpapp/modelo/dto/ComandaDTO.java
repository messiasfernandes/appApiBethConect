package br.com.bethpapp.modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.StatusPagamento;
import lombok.Data;
@Data
public class ComandaDTO {
	
	private Long id;
	@DateTimeFormat(pattern = " dd/MM/yyyy  HH:mm:ss")
	@JsonFormat(pattern = "dd/MM/yyyy  HH:mm:ss")
	private LocalDateTime data_abertura;

	private StatusPagamento statusPagamento;
	
	private MesaDTO mesa;

	private List<ItemdaComadaDTO> itemsdaComanda = new ArrayList<>();

}
