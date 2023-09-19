package br.com.bethpapp.modelo.dto;

import br.com.bethpapp.dominio.enumerado.Localizacao;
import br.com.bethpapp.dominio.enumerado.StatusMesa;
import lombok.Data;
@Data
public class MesaDTO {

	private Integer numerodaMesa;

	private Integer capacidade;

	private Localizacao loclizacao;

	private StatusMesa statusMesa;
}
