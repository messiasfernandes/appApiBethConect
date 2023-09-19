package br.com.bethpapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bethpapp.controller.documentacao.ArquivoControllerOpenapi;
import br.com.bethpapp.dominio.service.ServiceStorageArquivo;
import br.com.bethpapp.modelo.dto.ArquivoXmlDto;
@RestController
@RequestMapping("/arquivos")
public class ArquivoXmlController implements ArquivoControllerOpenapi{
	@Autowired
	ServiceStorageArquivo storagexml;



	@Override
	public ResponseEntity<byte[]> GetArquivo(String arquivo) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Override
	public ResponseEntity<ArquivoXmlDto> upload(  @RequestParam MultipartFile arquivo) {
	
	
		ArquivoXmlDto arquivoxml  = storagexml.importanotaFiscal(arquivo);
		return ResponseEntity.status(HttpStatus.CREATED).body(arquivoxml);
			
		}

}
