package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bethpapp.dominio.entidade.Estoque;

public interface DaoEstoque  extends JpaRepository<Estoque, Long>{
	
	@Query("from Estoque e where e.produto.id  =:idproduto  ")
	Estoque buscarproduto(Long idproduto);
	

}
