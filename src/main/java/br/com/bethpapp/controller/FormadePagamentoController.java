package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.dominio.dao.DaoFormaDePagamento;
import br.com.bethpapp.dominio.entidade.FormadePagmamento;

@CrossOrigin
@RestController

@RequestMapping("/formasdepagamentos")
public class FormadePagamentoController extends ControllerEvent{
	@Autowired
	private DaoFormaDePagamento daoFormaDePagamento;
	@GetMapping
	public ResponseEntity<List<FormadePagmamento>>  lista() {

		return ResponseEntity.status(HttpStatus.OK).body(daoFormaDePagamento.findAll());
	}

}
