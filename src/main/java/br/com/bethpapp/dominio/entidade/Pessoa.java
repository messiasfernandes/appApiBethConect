package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.bethpapp.dominio.enumerado.TipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;




@Data
@MappedSuperclass
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
   @Setter(value = AccessLevel.NONE)
	@Column(length = 100)
	private String nome;

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome.toUpperCase();
		} else {
			this.nome = nome;
		}
}
	@Column(length = 14)
	private String cpfouCnpj;

	@Column(length = 12)
	private String telefone;


	@Email
	@Column(length = 150)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TipoPessoa tipessoa;
//	@Fetch(FetchMode.SUBSELECT)
//	@Embedded
//	private Endereco endereco;
	@Column(length = 30)
     private String rg_Inscricao;
	@Column(length = 60)
	private String logradouro;
    @Size(max = 30 , message = "è permitdo no máximo 30 carecteres!!!")
	@Column(length = 30)
	private String complento;
	private String bairro;
	
	@Column(length = 9)
	private String cep;
	
	@JsonIgnoreProperties(value = { "nome", "estado" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Cidade cidade;


}
