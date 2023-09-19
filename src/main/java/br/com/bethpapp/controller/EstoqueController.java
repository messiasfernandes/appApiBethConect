package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.dominio.dao.DaoEstoque;
import br.com.bethpapp.dominio.entidade.Estoque;

@RequestMapping("/estoque")
@RestController
public class EstoqueController {
	@Autowired
	private DaoEstoque daoEstoque;
	
	@GetMapping
	public ResponseEntity<List<Estoque>> listra() {
		return ResponseEntity.status(HttpStatus.OK).body(daoEstoque.findAll());
	}


}
