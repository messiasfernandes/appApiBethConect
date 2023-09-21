package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.Famila;

public interface DaoFamilia extends JpaRepository<Famila, Long> {

}
