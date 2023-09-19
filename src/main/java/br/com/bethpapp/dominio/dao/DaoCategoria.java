package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.bethpapp.dominio.entidade.Categoria;



public interface DaoCategoria extends JpaRepository <Categoria, Long>, JpaSpecificationExecutor<Categoria>  {

}
