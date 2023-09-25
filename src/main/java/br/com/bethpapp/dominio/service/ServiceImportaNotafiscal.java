package br.com.bethpapp.dominio.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.bethpapp.dominio.dao.DaoEntradaNota;
import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.entidade.ProdutoFornecedor;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import br.com.bethpapp.utils.LeituraXml;
import jakarta.transaction.Transactional;

@Service
public class ServiceImportaNotafiscal {

	@Value("${storage.disco}")
	private String arquivo;
	@Value("${storage.xml}")
	private String local_arquivo_xml;
	@Autowired
	private ServiceProduto serviceProduto;
	@Autowired
	private DaoEntradaNota daoEntradaNota;
	@Autowired
	private ServiceEstoqueMovimento serviceEstoqueMovimento;
	@Autowired
	private ServiceForncedorNotaFiscal serviceForncedorNotaFiscal;
	@Autowired
	private ServiceContasaPagar serviceContasaPagar;
	private LeituraXml leituraXml = new LeituraXml();
	private LocalDateTime dataemisao;

	public EntradaNotaCabecario imporxml(String xml, BigDecimal pMargem, Long idforma, Integer qtdeparcelas) {
		System.out.println(arquivo + local_arquivo_xml + xml);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		var entrada = new EntradaNotaCabecario();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(arquivo + local_arquivo_xml + xml);

			var dados = doc.getElementsByTagName("ide");
			entrada = leituraXml.lerxml(xml, dados);
			dataemisao = entrada.getData_hora_emissao_nota();
            
			Element raiz = doc.getDocumentElement();

			NodeList emitentes = raiz.getElementsByTagName("emit");
			NodeList transportadora= raiz.getElementsByTagName("transp");
			entrada.setTransporteNotafiscal(leituraXml.adicionarTranportadora(transportadora));;
			NodeList infProt = raiz.getElementsByTagName("infProt");
			NodeList totalNotaInposto = raiz.getElementsByTagName("total");
			entrada.setImpostoNota(leituraXml.adicionarValoImposto(totalNotaInposto));
			for (int k = 0; k < infProt.getLength(); k++) {
				Element chaves = (Element) infProt.item(k);
				entrada.setChaveNota(chaves.getElementsByTagName("chNFe").item(k).getTextContent());
				
			}
			 NodeList totalNota= raiz.getElementsByTagName("ICMSTot");
			entrada.setFornecedor(dadosFornecedor(emitentes));
			NodeList produtos = raiz.getElementsByTagName("det");
			entrada.setItems_entrada(leituraXml.adicionarProduto(  produtos, pMargem,totalNota));
			
			
			

		} catch (ParserConfigurationException e) {

			/// e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return salvar(entrada, pMargem, idforma, qtdeparcelas);
	}

	private Fornecedor dadosFornecedor(NodeList emitentes) {

		return serviceForncedorNotaFiscal.adiconafornecedorXml(emitentes);
	}

	@Transactional(rollbackOn = Exception.class)
	public EntradaNotaCabecario salvar(EntradaNotaCabecario entrada, BigDecimal margen, Long idforma,
			Integer qtdeparcelas) {
		var fonecedorsalvo=	serviceForncedorNotaFiscal.salvarfornecedorXml(entrada.getFornecedor());
		System.out.println( "id do fornecedor" +fonecedorsalvo.getId());
		entrada.getItems_entrada().forEach(e -> e.setEntradaNotafiscal(entrada));

		if ((daoEntradaNota.buscarnota(entrada.getFornecedor().getId(), entrada.getNumerodanota(), entrada.getStatusEntradaNota()) == true)) {

			throw new NegocioException("Nota  cadastrada já no banco de dados");
		} else {
			Map<String, Produto> produtosExistentes = new HashMap<>();
		
			System.out.println(fonecedorsalvo.getId());
			for (int i = 0; i < entrada.getItems_entrada().size(); i++) {
				var produtofornecedor = new ProdutoFornecedor();
				produtofornecedor.setDataCompra(LocalDate.now());
				produtofornecedor.setFornecedor(fonecedorsalvo);
				produtofornecedor.setValorProduto(entrada.getItems_entrada().get(i).getProduto().getPrecocusto());
				produtofornecedor.setProduto(entrada.getItems_entrada().get(i).getProduto());
				entrada.getItems_entrada().get(i).getProduto().getProdutoFonecedores().add(produtofornecedor);
				String codigoFabricante = entrada.getItems_entrada().get(i).getProduto().getCodigofabricante();
				long cont = serviceProduto.buscarCodFabricante(codigoFabricante);

				if (cont > 0L) {
					Produto produtoExistente = produtosExistentes.computeIfAbsent(codigoFabricante,
							key -> serviceProduto.buscarporCodFabricante(key));
					entrada.getItems_entrada().get(i).setProduto(produtoExistente);
					
				} else {
					
					serviceProduto.salvarProdutoNota(entrada.getItems_entrada().get(i).getProduto());
				}
			}

			Long titulo = Long.parseLong(entrada.getNumerodanota());
			BigDecimal totalnota = entrada.getImpostoNota().getTotalNota();
			serviceEstoqueMovimento.entradaEstoquue(entrada);
			
			var contas = serviceContasaPagar.addconta(qtdeparcelas, titulo, entrada.getFornecedor(), totalnota, idforma,
					dataemisao.toLocalDate(),entrada.getNumerodanota());
			serviceContasaPagar.salvar(contas);
			daoEntradaNota.save(entrada);
			return entrada;
		}

	}
}
