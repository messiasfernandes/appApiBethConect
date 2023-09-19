package br.com.bethpapp.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CalculoCusto {

	public BigDecimal calcularRateioImpostos(NodeList total, Integer qtde, BigDecimal subtotal) {
		BigDecimal rateioImposto = BigDecimal.ZERO;
		BigDecimal rateioFrete = BigDecimal.ZERO;
		BigDecimal rateioSeguro = BigDecimal.ZERO;
		BigDecimal rateioDesconto = BigDecimal.ZERO;
		for (int i = 0; i < total.getLength(); i++) {
			Element element = (Element) total.item(i);
			BigDecimal totalProduto = obterValorElemento(element, "vProd");

			BigDecimal totalImposto = rateioImpostos(element);
			System.out.println(totalImposto + "totalimposot");
			if (totalImposto.signum() > 0) {
				rateioImposto = subtotal.divide(totalProduto.setScale(4), MathContext.DECIMAL128).multiply(totalImposto);
			
				System.out.println(rateioImposto + "rateio");
			}
			BigDecimal frete = rateioFrete(element);
			if (frete.signum() > 0) {
				rateioFrete = subtotal.divide(totalProduto).multiply(frete);
				
			}
			BigDecimal seguro = rateioSeguro(element);
			if (seguro.signum() > 0) {
				rateioSeguro = subtotal.divide(totalProduto).multiply(seguro);
					
			}
			BigDecimal desconto = rateioDesconto(element);
			if (desconto.signum() > 0) {
				rateioDesconto = subtotal.divide(totalProduto.setScale(4), MathContext.DECIMAL128).multiply(desconto) ;
					
				System.out.println(rateioDesconto);
			}
		}
	

		return   	rateioImposto.add(subtotal).add(rateioSeguro).add(rateioFrete).subtract(rateioDesconto);
	}

	private BigDecimal obterValorElemento(Element parentElement, String tagName) {
		Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
		return element != null ? new BigDecimal(element.getTextContent()) : BigDecimal.ZERO;
	}

	private BigDecimal rateioImpostos(Element imposto) {
        BigDecimal vST=obterValorElemento(imposto, "vST");
		BigDecimal vICMSDeson = obterValorElemento(imposto, "vICMSDeson");

		
		BigDecimal vII = obterValorElemento(imposto, "vII");
	
		BigDecimal vIPI = new BigDecimal(imposto.getElementsByTagName("vIPI").item(0).getTextContent());

		return vIPI.add(vII).add(vST).add(vICMSDeson.setScale(4, RoundingMode.HALF_EVEN));
	}

	private BigDecimal rateioFrete(Element imposto) {

		BigDecimal vFrete = new BigDecimal(imposto.getElementsByTagName("vFrete").item(0).getTextContent());

		return vFrete.setScale(4, RoundingMode.HALF_EVEN);

	}

	private BigDecimal rateioSeguro(Element imposto) {

		BigDecimal vSeg = new BigDecimal(imposto.getElementsByTagName("vSeg").item(0).getTextContent());

		return vSeg.setScale(4, RoundingMode.HALF_EVEN);

	}

	private BigDecimal rateioDesconto(Element imposto) {

		BigDecimal vDesc = new BigDecimal(imposto.getElementsByTagName("vDesc").item(0).getTextContent());

		return vDesc.setScale(4, RoundingMode.HALF_EVEN);

	}
}
