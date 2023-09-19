package br.com.bethpapp.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.entidade.ImpostoNota;
import br.com.bethpapp.dominio.entidade.ItemEntradaNota;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.entidade.TransporteNotafiscal;
import br.com.bethpapp.dominio.enumerado.StatusEntradaNota;
import br.com.bethpapp.dominio.service.exeption.NegocioException;

public class LeituraXml {
	private CalculoCusto calculoCusto = new CalculoCusto();

	public EntradaNotaCabecario lerxml(String xml, NodeList notaentrada) {
		var entrada = new EntradaNotaCabecario();
		for (int i = 0; i < notaentrada.getLength(); i++) {
			Element dadoinit = (Element) notaentrada.item(i);
			entrada.setData_hora_emissao_nota(entrada.converte(dadoinit.getElementsByTagName("dhEmi").item(i).getTextContent()));
			entrada.setNumerodanota(dadoinit.getElementsByTagName("nNF").item(i).getTextContent());
			entrada.setModelo(dadoinit.getElementsByTagName("mod").item(i).getTextContent());
			entrada.setNaturezaopercao(dadoinit.getElementsByTagName("natOp").item(i).getTextContent());
			entrada.setSerie(dadoinit.getElementsByTagName("serie").item(i).getTextContent());
			entrada.setStatusEntradaNota(StatusEntradaNota.Entregue);

		}

		entrada.setData_hora_entrada(LocalDateTime.now());
		entrada.setArquivo_nota(xml);
		return entrada;
	}

	public List<ItemEntradaNota> adicionarProduto(NodeList nodprouto, BigDecimal margem, NodeList total) {

		margem = margem.divide(new BigDecimal(100));

		margem = margem.add(BigDecimal.ONE);

		System.out.println("margem" + margem);

		List<ItemEntradaNota> entradas = new ArrayList<>();
		 try {
		for (int k = 0; k < nodprouto.getLength(); k++) {

			Element produtos = (Element) nodprouto.item(k);

			NodeList produtolista = produtos.getElementsByTagName("prod");

			for (int j = 0; j < produtolista.getLength(); j++) {
				Element produto = (Element) produtolista.item(j);
				ItemEntradaNota intemProduto = new ItemEntradaNota();
				var p = new Produto();

				p.setCodigoEan13(produto.getElementsByTagName("cEAN").item(j).getTextContent());
           
				p.setCodigofabricante(produto.getElementsByTagName("cProd").item(j).getTextContent());
				p.setCodigoEan13(produto.getElementsByTagName("cEAN").item(j).getTextContent());
				p.setNomeproduto(produto.getElementsByTagName("xProd").item(j).getTextContent());
				BigDecimal qte = new BigDecimal((produto.getElementsByTagName("qTrib").item(j).getTextContent()));
				intemProduto.setQtde(Integer.valueOf(qte.intValueExact()));

				var precocusto = (new BigDecimal(produto.getElementsByTagName("vUnTrib").item(j).getTextContent()));

				produto.getElementsByTagName("qTrib").item(j).getTextContent();
				p.setUnidade(produto.getElementsByTagName("uCom").item(j).getTextContent());
				p.setPrecocusto(precocusto);

				p.setAtivo(true);

				intemProduto.setSubtotal(new BigDecimal(intemProduto.getQtde()).multiply(precocusto));

				BigDecimal customedioTotal = p.getPrecocusto();
				customedioTotal = calculoCusto.calcularRateioImpostos(total, intemProduto.getQtde(),
						intemProduto.getSubtotal());
				System.out.println(customedioTotal.setScale(4, RoundingMode.HALF_EVEN) + " cuto medio");
				if (customedioTotal.signum() != 0) {
					p.setCustomedio(customedioTotal.divide(new BigDecimal(intemProduto.getQtde()).setScale(4),
							MathContext.DECIMAL128));
				} else {
				
					p.setCustomedio(p.getPrecocusto());
				}
				p.setPrecovenda(margem.multiply(p.getCustomedio()));
				System.out.println(p.getPrecovenda());
				intemProduto.setProduto(p);

				entradas.add(intemProduto);

			}

		}
		} catch (Exception e) {
			throw new NegocioException("Erro adicionar produto");
		}
		return entradas;
	}

	public TransporteNotafiscal adicionarTranportadora(NodeList nodeTranportadora) {
		TransporteNotafiscal transportadoraNotafiscal = new TransporteNotafiscal();

		for (int k = 0; k < nodeTranportadora.getLength(); k++) {
			Element transportadora = (Element) nodeTranportadora.item(k);
			String modFrete = transportadora.getElementsByTagName("modFrete").item(k).getTextContent();

			System.out.println("modFrete: " + modFrete);

			NodeList nodeTransporte = transportadora.getElementsByTagName("transporta");

			for (int i = 0; i < nodeTransporte.getLength(); i++) {
				Element elementeTrans = (Element) nodeTransporte.item(i);
				tratarTransportaElemento(elementeTrans, transportadoraNotafiscal);
			}

			Element volElement = (Element) transportadora.getElementsByTagName("vol").item(k);
			if (volElement != null) {
				tratarVolElemento(volElement, transportadoraNotafiscal);
			}
			Element veicTransp = (Element) transportadora.getElementsByTagName("veicTransp").item(k);
			if (veicTransp != null) {
				tratarVeiculoTranporte(veicTransp, transportadoraNotafiscal);
			}
		}

		return transportadoraNotafiscal;
	}

	private void tratarVeiculoTranporte(Element element, TransporteNotafiscal transportadoraNotafiscal) {
		String placa = obterTextoElementoOpcional(element, "placa");
		if (!placa.isEmpty()) {
			transportadoraNotafiscal.setPlacaVeiculo(placa);
		}

	}

	private void tratarTransportaElemento(Element element, TransporteNotafiscal transportadoraNotafiscal) {
		String xEnder = element.getElementsByTagName("xEnder").item(0) != null
				? element.getElementsByTagName("xEnder").item(0).getTextContent()
				: "";
		String xNome = element.getElementsByTagName("xNome").item(0) != null
				? element.getElementsByTagName("xNome").item(0).getTextContent()
				: "";
		String cnpj = obterTextoElementoOpcional(element, "CNPJ");
		String inscricao = obterTextoElementoOpcional(element, "IE");
		if (!inscricao.isEmpty()) {
			transportadoraNotafiscal.setIncricaoEstadual(inscricao);
		}
		if (!cnpj.isEmpty()) {
			transportadoraNotafiscal.setCnpj(cnpj);
		}
		if (!xEnder.isEmpty()) {
			System.out.println("xEnder: " + xEnder);
		}
		if (!xNome.isEmpty()) {
			System.out.println("xNome: " + xNome);
		}

		// Configurar atributos em transportadoraNotafiscal
		if (!xEnder.isEmpty()) {
			transportadoraNotafiscal.setEnderreco(xEnder);
		}
		if (!xNome.isEmpty()) {
			transportadoraNotafiscal.setNomeTransporte(xNome);
		}

	}

	private void tratarVolElemento(Element element, TransporteNotafiscal transportadoraNotafiscal) {
		int qVol = element.getElementsByTagName("qVol").item(0) != null
				? Integer.parseInt(element.getElementsByTagName("qVol").item(0).getTextContent())
				: 0;
		BigDecimal pesoB = element.getElementsByTagName("pesoB").item(0) != null
				? new BigDecimal(element.getElementsByTagName("pesoB").item(0).getTextContent())
				: BigDecimal.ZERO;
		BigDecimal pesoL = element.getElementsByTagName("pesoL").item(0) != null
				? new BigDecimal(element.getElementsByTagName("pesoL").item(0).getTextContent())
				: BigDecimal.ZERO;

		String esp = obterTextoElementoOpcional(element, "esp");

		if (!esp.isEmpty()) {
			transportadoraNotafiscal.setTipodeEmbalagem(esp);
		}
		if (qVol != 0) {
			System.out.println("qVol: " + qVol);
		}
		if (!pesoB.equals(BigDecimal.ZERO)) {
			System.out.println("pesoB: " + pesoB);
		}
		if (!pesoL.equals(BigDecimal.ZERO)) {
			System.out.println("pesoL: " + pesoL);
		}

		// Configurar atributos em transportadoraNotafiscal
		if (qVol != 0) {
			transportadoraNotafiscal.setQtevolume(qVol);
		}
		if (!pesoB.equals(BigDecimal.ZERO)) {
			transportadoraNotafiscal.setPesoBruto(pesoB);
		}
		if (!pesoL.equals(BigDecimal.ZERO)) {
			transportadoraNotafiscal.setPesoLiquido(pesoL);
		}
		// ...
	}

	public ImpostoNota adicionarValoImposto(NodeList nodeImposto) {
		ImpostoNota impostoNota = new ImpostoNota();
		for (int k = 0; k < nodeImposto.getLength(); k++) {
			Element imposto = (Element) nodeImposto.item(k);
			trararValoImposto(imposto, impostoNota);
		}
		return impostoNota;
	}

	private void trararValoImposto(Element element, ImpostoNota impostoNota) {
		System.out.println("pasou aqui");
		BigDecimal totalNota = element.getElementsByTagName("vNF").item(0) != null
				? new BigDecimal(element.getElementsByTagName("vNF").item(0).getTextContent())
				: BigDecimal.ZERO;
		System.out.println("total nota" + totalNota);
		BigDecimal icms = element.getElementsByTagName("vICMS").item(0) != null
				? new BigDecimal(element.getElementsByTagName("vICMS").item(0).getTextContent())
				: BigDecimal.ZERO;
		BigDecimal totalTributo = element.getElementsByTagName("vTotTrib").item(0) != null
				? new BigDecimal(element.getElementsByTagName("vTotTrib").item(0).getTextContent())
				: BigDecimal.ZERO;

		BigDecimal valorIpi = element.getElementsByTagName("vIPI").item(0) != null
				? new BigDecimal(element.getElementsByTagName("vIPI").item(0).getTextContent())
				: BigDecimal.ZERO;

		if (!totalNota.equals(BigDecimal.ZERO)) {
			impostoNota.setTotalNota(totalNota);

		}
		if (!icms.equals(BigDecimal.ZERO)) {
			impostoNota.setValorIcms(icms);
		}

		if (!totalTributo.equals(BigDecimal.ZERO)) {
			impostoNota.setValorTributo(totalTributo);
		}

		if (!valorIpi.equals(BigDecimal.ZERO)) {
			impostoNota.setValorIpi(valorIpi);
		}
	}

	private String obterTextoElementoOpcional(Element parentElement, String tagName) {
		Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
		return element != null ? element.getTextContent() : "";
	}
}
