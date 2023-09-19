package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.bethpapp.dominio.entidade.SubCategoria;

public interface DaoSubcategoria  extends JpaRepository<SubCategoria, Long>, JpaSpecificationExecutor<SubCategoria>{

	@Query("from SubCategoria s where s.nomeSubCategoria = :nome")
	SubCategoria  buscar(String nome);
}
