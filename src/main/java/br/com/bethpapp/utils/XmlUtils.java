package br.com.bethpapp.utils;

import java.math.BigDecimal;

import org.w3c.dom.Element;

public class XmlUtils {
	public static boolean isValueGreaterThanOrEqualToZero(Element parentElement, String tagName) {
		Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);

		if (element != null) {
			try {
				BigDecimal value = new BigDecimal(element.getTextContent());
				return value.compareTo(BigDecimal.ZERO) >= 0;
			} catch (NumberFormatException e) {
				return false; // Não é um valor numérico válido
			}
		}

		return false; // Elemento não encontrado
	}
}
