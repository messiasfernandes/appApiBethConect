package br.com.bethpapp.dominio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoProduto;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.entidade.ProdutoFornecedor;
import br.com.bethpapp.dominio.service.exeption.EntidadeEmUsoExeption;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import br.com.bethpapp.dominio.service.exeption.RegistroNaoEncontrado;
import br.com.bethpapp.utils.CalcularDigitoEan;
import br.com.bethpapp.utils.CodigoBarraEAN;
import jakarta.transaction.Transactional;

@Service
public class ServiceProduto extends ServiceFuncoes implements ServiceModel<Produto> {
	@Autowired
	private DaoProduto daoProduto;

	@Override
	public Page<Produto> buscar(String nome, Pageable pageable) {

		return daoProduto.buscar(nome.toUpperCase(), pageable);
	}

	@Transactional()
	@Override
	public void excluir(Long codigo) {

		buccarporid(codigo);
		try {

			daoProduto.deleteById(codigo);
			daoProduto.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					"Operação não permitida!! Este registro pode estar asssociado a outra tabela");
		}

		//daoProduto.deleteById(codigo);

	}

	@Override
	public Produto buccarporid(Long id) {
		if (daoProduto.findById(id).isEmpty()) {
			throw new RegistroNaoEncontrado("Produto não encotrada");
		}
		return daoProduto.findById(id).get();
	}

	@Transactional
	@Override
	public Produto salvar(Produto objeto) {
	   
		Produto produto = null;
		try {
			if (objeto.getAtributos().size() > 0) {
				objeto.setCaracteristica(concatenar(objeto));
			}
			if (objeto.getProdutoFonecedores().size() > 0) {
				System.out.println("ENTROU aqui");
				 objeto.getProdutoFonecedores().forEach(p-> p.setProduto(objeto));
//				 if ("".equals(objeto.getCodigoEan13()) || "SEM GTIN".equals(objeto.getCodigoEan13())) {
//					    // O campo codigoEan13 está vazio ou igual a "SEM GTIN"
//					    // Chame a função geararCodioEan13 para gerar o código EAN 13
//					    objeto.setCodigoEan13(geararCodioEan13(objeto));
//					}
			}

			produto = daoProduto.save(objeto);
		} catch (InvalidDataAccessApiUsageException e) {
			 throw new NegocioException("Registro  de fornecedor duplicado");
		}
	
//	    objeto.getFornecedores().forEach(p-> p.setProduto(objeto));
	

			
	
		return produto;
	}



	private String concatenar(Produto objeto) {
		StringBuilder strBuilder = new StringBuilder();
		var tam = objeto.getAtributos().size() - 1;

		for (int i = 0; i < objeto.getAtributos().size(); i++) {

			if (i == tam) {

				// strBuilder.append(objeto.getAtributos().get(i).getTipo().concat(" : "));
				strBuilder.append(objeto.getAtributos().get(i).getValor().concat(" "));

			} else {

				// strBuilder.append(objeto.getAtributos().get(i).getTipo().concat(" :"));
				strBuilder.append(objeto.getAtributos().get(i).getValor().concat(" | "));
			}

		}

		return strBuilder.toString();

	}

	public Long buscarCodFabricante(String codigofabricante) {
		return daoProduto.isCodigoFabCadastrado(codigofabricante);
	}

	public Produto buscarporCodFabricante(String codigofabricante) {
		return daoProduto.findByCodigofabricante(codigofabricante);
	}
	
	public String  geararCodioEan13(String pcnpj , String codigofabricante) {
		System.out.println(pcnpj);
     System.out.println(codigofabricante);
		String cnpj=pcnpj;

		String codiprodForncedor=codigofabricante;
		String codigopais="789";
		String codigoFornecedor="";
		System.out.println(cnpj);
		codigoFornecedor=cnpj.substring(0,5);
		String codigoproduto=CalcularDigitoEan.extraireFormatar(codiprodForncedor);
	   String ean=codigopais+codigoFornecedor+codigoproduto;
	   System.out.println(ean);
	   String ean13 = CalcularDigitoEan.calcularEAN13(ean);
	   CodigoBarraEAN codigoBarra = new CodigoBarraEAN(ean13);
	///   System.out.println("Codigo de barra: " + codigoBarra.validar(codigoBarra));
		System.out.println("Numero do codigo de barras: " + codigoBarra.getCodigoBarra());
	   return codigoBarra.getCodigoBarra();
	}
    public Produto salvarProdutoNota(Produto objeto) {
     return daoProduto.save(objeto)	;
    }
    
    public Long maxid() {
        Long maxId = daoProduto.findMaxId();
        
        if (maxId == null) {
            return 1L; // Caso não haja registros na tabela, retorne 1 como o próximo ID.
        } else {
            return maxId + 1L;
        }
    }
}
