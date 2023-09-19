package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bethpapp.dominio.entidade.ContasPagar;

public interface DaoContasApagar extends JpaRepository<ContasPagar, java.lang.Long> {

	@Query("SELECT c FROM ContasPagar c JOIN c.contasaPagarDetalhes d " +
		       "WHERE c.fornecedor.cpfouCnpj = :pcnpj " +
		       "AND c.numeroTitulo = :ptitulo " +
		       "AND d.statusPagmaento != 'CANCELADO'")
	ContasPagar buscarconta(String pcnpj, String ptitulo);
}
