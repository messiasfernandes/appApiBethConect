package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.CondicaoPagamento;

public interface DaoCondicaodePagamento extends JpaRepository<CondicaoPagamento, Long>{

}
