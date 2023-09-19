package br.com.bethpapp.controller;

import java.io.File;
import java.io.IOException;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bethpapp.controller.documentacao.ArquivoOpenApi;
import br.com.bethpapp.dominio.service.ServiceStorage;
import br.com.bethpapp.modelo.dto.ArquivoDto;
import jakarta.activation.FileTypeMap;


@RestController
@RequestMapping("/arquivos/fotos")
public class ArquivoControler implements ArquivoOpenApi {
	@Autowired
	private ServiceStorage serviceStorage;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Override
	public ResponseEntity<List<ArquivoDto>> upload(@RequestParam List<MultipartFile> arquivo) {

		return ResponseEntity.status(HttpStatus.CREATED).body(serviceStorage.salvar(arquivo));
	}

	@GetMapping("/{arquivo}")
	@Override
	public ResponseEntity<byte[]> getImage(@PathVariable String arquivo) throws IOException {

		File img = serviceStorage.buscarfoto(arquivo);
		System.out.println(img);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
				.body(serviceStorage.carregarFoto(img));
	}

	@DeleteMapping("/{nomeArquivo}")
	public ResponseEntity<Void> deleteArquivo(@PathVariable String nomeArquivo) {

		serviceStorage.delete(nomeArquivo);

		return ResponseEntity.noContent().build();

	}
	
	
}
