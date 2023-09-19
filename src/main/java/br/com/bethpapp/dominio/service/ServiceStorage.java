package br.com.bethpapp.dominio.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.bethpapp.modelo.dto.ArquivoDto;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Service
public class ServiceStorage {

	private Path local;

	@Value("${storage.disco}")
	private String raiz;
	@Value("${storage.foto}")
	private String localfoto;
	@Value("${storage.xml}")
	private String arquivo_xml;
	public List<ArquivoDto> salvar(List<MultipartFile> files) {
		List<ArquivoDto> arquivcos = new ArrayList<>();

		for (MultipartFile file : files) {
			ArquivoDto arquivo = new ArquivoDto();
			arquivo.setContentType(file.getContentType());
			arquivo.setDescricao("thumbnail." + file.getOriginalFilename());
			arquivo.setNomeArquivo(file.getOriginalFilename());
			arquivo.setTamanho(file.getSize());

			caminho();
			criarpasta();
			try {
				//
				// arquivo.setUrl( );
				/// arquivo.setUrl(arquivo.add(WebMvcLinkBuilder.linkTo(ArquivoControler.class).slash(arquivo.getNomeArquivo()).withSelfRel()).toString());

				arquivcos.add(arquivo);
				file.transferTo(new File(local.toAbsolutePath().toString(),
						FileSystems.getDefault().getSeparator() + file.getOriginalFilename()));
				Thumbnails.of(this.local.resolve(file.getOriginalFilename()).toString()).size(400, 280)
						.toFiles(Rename.NO_CHANGE);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return arquivcos;
	}

	private void criarpasta() {
		System.out.println("criou pasta");
		try {

			Files.createDirectories(this.local);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public byte[] carregarFoto(File foto) throws IOException {

		try {
			return Files.readAllBytes(foto.toPath());
		} catch (IOException e) {

			e.printStackTrace();

		}

		return Files.readAllBytes(foto.toPath());
	}

	public List<byte[]> cacrregarImagem(List<File> fotos) throws IOException {

		List<byte[]> files = new ArrayList<>();
		for (int i = 0; i < fotos.size(); i++) {
			byte[] data = new byte[100];
			data = Files.readAllBytes(fotos.get(i).toPath());

			files.add(data);
		}
		return files;

	}

	public File buscarfoto(String foto) {

		File img = transferirouBuscar(foto);

		return img;

	}

	public List<File> pesquisrfoto(List<String> fotos) {
		List<File> aruqivos = new ArrayList<>();
		for (String foto : fotos) {

			aruqivos.add(transferirouBuscar(foto));

		}
		return aruqivos;

	}

	private Path caminho() {

		return local = Paths.get(raiz, localfoto, FileSystems.getDefault().getSeparator());
	}

	private File transferirouBuscar(String nomeArquivo) {

		return new File(caminho().toAbsolutePath().toString(), FileSystems.getDefault().getSeparator() + nomeArquivo);
	}

	public boolean delete(String filename) {
		try {
			Path file = caminho().resolve(filename);
			return Files.deleteIfExists(file);
		} catch (IOException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}



}
