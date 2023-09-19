package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ContasPagar extends GeradorId implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datalancamento;
	@Column(length = 120)
	private String observacao;
	@Column(length = 50)
	private String numeroTitulo;
	private Integer totatdeParcelas;
    private BigDecimal totalPagar;
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contasaPagar", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContasPagarDetalhe>contasaPagarDetalhes = new ArrayList<>();

	
	
	

}
