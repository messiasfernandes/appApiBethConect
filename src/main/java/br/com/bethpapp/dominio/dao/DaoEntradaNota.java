package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.query.EntradasdeNotaRepositoryQuery;

public interface DaoEntradaNota extends JpaRepository<EntradaNotaCabecario, Long>,EntradasdeNotaRepositoryQuery  {

}
