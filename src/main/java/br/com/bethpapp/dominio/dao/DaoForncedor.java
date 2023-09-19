package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.query.ForncedorQuery;

public interface DaoForncedor extends JpaRepository<Fornecedor, Long>,ForncedorQuery {
	
	@Query(" from Fornecedor f where f.cpfouCnpj =:cpf_cnpj")
	Fornecedor findCpfouCnpj(String cpf_cnpj);

	@Query("SELECT Count(*)from Fornecedor f where f.cpfouCnpj =:cpf_cnpj")
	Long isCppouCnpjExit(String cpf_cnpj);

}
