package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tab_estado")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cod_estado;
	@Column(length = 100)
	private String nome;
	@Column(length = 2)
	private String sigla;

	






}
