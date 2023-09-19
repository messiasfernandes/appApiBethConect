package br.com.bethpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.dominio.dao.DaoCondicaodePagamento;
import br.com.bethpapp.dominio.entidade.CondicaoPagamento;

@RestController
@RequestMapping("/condicoedepagamentos")
public class CondicaoPagamentoController extends ControllerEvent{
	@Autowired
	private DaoCondicaodePagamento daoCondicaodePagamento;
	@GetMapping
	private ResponseEntity<java.util.List<CondicaoPagamento>>listar(){
		return ResponseEntity.status(HttpStatus.OK).body(daoCondicaodePagamento.findAll());
	}

}
