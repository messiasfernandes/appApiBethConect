package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.bethpapp.dominio.enumerado.StatusPagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
public class Comanda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime data_abertura;
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Mesa mesa;
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemComanda> itemsdaComanda = new ArrayList<>();
}
