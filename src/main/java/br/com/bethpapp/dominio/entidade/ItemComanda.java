package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ItemComanda extends GeradorId {
	
	private static final long serialVersionUID = 1L;
   @JsonBackReference
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Produto produto;
///	@JsonIgnore
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Comanda comanda;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal quantidade;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal subtotal;

}
