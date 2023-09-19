/**
 * 
 */
package br.com.bethpapp.modelo.input;

import br.com.bethpapp.dominio.entidade.Cidade;
import br.com.bethpapp.dominio.enumerado.TipoPessoa;
import lombok.Data;

/**
 * @author messias
 *
 */
@Data
public class FornecedorInput {

	private Long id;

	private String nome;

	private String cpfouCnpj;

	private String telefone;

	private String email;

	private TipoPessoa tipessoa;

	private String rg_Inscricao;

	private String logradouro;

	private String complento;
	private String bairro;

	private String cep;

	private Cidade cidade;

}
