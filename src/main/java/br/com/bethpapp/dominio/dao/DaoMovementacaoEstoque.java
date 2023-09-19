package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.query.EstoqueMovmentoQuery;



public interface DaoMovementacaoEstoque extends JpaRepository<EstoqueMovimento, Long>, EstoqueMovmentoQuery {

}
