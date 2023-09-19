package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tab_municipio")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long cod_municipio;
	@Column(length = 100, nullable = false)

	private String nome;

	@JsonIgnoreProperties(value = "nomestado", allowGetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_estado")
	private Estado estado;

}
