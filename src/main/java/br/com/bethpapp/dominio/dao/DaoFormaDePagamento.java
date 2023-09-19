package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.FormadePagmamento;

public interface DaoFormaDePagamento extends JpaRepository<FormadePagmamento, Long> {

}
