package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.Comanda;
import br.com.bethpapp.query.ComandaQuery;

public interface DaoComanda extends JpaRepository<Comanda, Long>,ComandaQuery {

}
