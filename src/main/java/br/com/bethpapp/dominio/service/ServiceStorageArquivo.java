package br.com.bethpapp.dominio.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.bethpapp.modelo.dto.ArquivoXmlDto;

@Service
public class ServiceStorageArquivo {

	public Path localxml;
	public Path local;
	@Value("${storage.disco}")
	private String pastaArquivo;
	@Value("${storage.foto}")
	private String diretoriofoto;
	@Value("${storage.xml}")
	private String arquivo_xml;

	private Path caminho() {

		return local = Paths.get(pastaArquivo, diretoriofoto, FileSystems.getDefault().getSeparator());
	}

	public byte[] carregarFoto(File foto) throws IOException {

		try {
			return Files.readAllBytes(foto.toPath());
		} catch (IOException e) {

			e.printStackTrace();

		}

		return Files.readAllBytes(foto.toPath());
	}

	public File buscarfoto(String foto) {

		File img = transferirouBuscar(foto);

		return img;

	}

	private File transferirouBuscar(String nomeArquivo) {

		return new File(caminho().toAbsolutePath().toString(), FileSystems.getDefault().getSeparator() + nomeArquivo);
	}

	public ArquivoXmlDto importanotaFiscal(MultipartFile arquivo) {
		var arquivolxml = new ArquivoXmlDto();

		arquivolxml.setContentType(arquivo.getContentType());
		arquivolxml.setDescricao(arquivo.getOriginalFilename());
		arquivolxml.setNomeArquivo(arquivo.getOriginalFilename());
		arquivolxml.setLocalarquivo(arquivo_xml);
		System.out.println("aruvio" + arquivo.getOriginalFilename());
		localxml = Paths.get(pastaArquivo, arquivo_xml);

		this.criarpastaxm();

		try {
			arquivo.transferTo(new File(localxml.toAbsolutePath().toString(),
					FileSystems.getDefault().getSeparator() + arquivo.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arquivolxml;
	}

	private void criarpastaxm() {
		try {

			Files.createDirectories(this.localxml);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
