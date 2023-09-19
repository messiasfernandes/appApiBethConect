package br.com.bethpapp.dominio.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cliente extends Pessoa {

	
	private static final long serialVersionUID = 1L;
    @Column(length = 14)
	private String celular;

	
	

}
