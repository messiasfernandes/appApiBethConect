package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.Classe;

public interface DaoClasse extends JpaRepository<Classe, Long> {

}
