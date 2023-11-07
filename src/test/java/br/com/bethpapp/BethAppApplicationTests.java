package br.com.bethpapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.dominio.service.ServiceProduto;
import br.com.bethpapp.utils.CalcularDigitoEan;
import br.com.bethpapp.utils.CodigoBarraEAN;
import br.com.bethpapp.utils.Ean;

@SpringBootTest
class BethAppApplicationTests {
public  Integer totalpar =0;
public  Integer totalImpar =0;
@Autowired
private ServiceProduto serviceProduto;
	void contextLoads() {
		Integer n = 5;
		BigDecimal n1 = new BigDecimal(10.0);
		Integer n2 = 0;
		n2 = n1.subtract(new BigDecimal(n)).intValueExact();
		System.out.println(n2);

		EstoqueMovimento estoqueMovimento = new EstoqueMovimento();
		estoqueMovimento.setDatamovimento(LocalDateTime.now());
		System.out.println(estoqueMovimento.getDatamovimento());
	}

	void EmprestimoCalculator() {
		BigDecimal valor = new BigDecimal(1221.89).setScale(4, RoundingMode.HALF_EVEN);
		Integer qtdeparcela = 10;
		BigDecimal taxajuro = new BigDecimal("1.65").setScale(4, RoundingMode.HALF_EVEN);
		var parcela = ValordaParcela(valor, qtdeparcela, taxajuro);
		var total = parcela.setScale(2, RoundingMode.HALF_EVEN);
		var valparcela = total.divide(new BigDecimal(qtdeparcela).setScale(3), MathContext.DECIMAL128);
		var valototal = valparcela.multiply(new BigDecimal(qtdeparcela));
		var totalprova = valototal.setScale(2, RoundingMode.HALF_EVEN);
		System.out.println("valor da Total = " + total);
		System.out.println("valor da parcela = " + valparcela);
		System.out.println("valor da Prova = " + totalprova);

	}

	public BigDecimal ValordaParcela(BigDecimal valoEmprestimo, Integer periodo, BigDecimal taxaJuros) {
		// igDecimal valorparcela= BigDecimal.ZERO;
		var montante = taxaJuros.divide(new BigDecimal(100));
		montante = montante.add(BigDecimal.ONE);
		montante = montante.pow(periodo);
		montante = montante.multiply(valoEmprestimo);
		// montante= montante.setScale(2, RoundingMode.HALF_EVEN);
		return montante;
	}

	void calcularVAlor() {
		BigDecimal valor = new BigDecimal(5000).setScale(4, RoundingMode.HALF_EVEN);
		BigDecimal qtdeparcela = new BigDecimal(24).setScale(4, RoundingMode.HALF_EVEN);
		BigDecimal taxajuro = new BigDecimal("3.79").setScale(4, RoundingMode.HALF_EVEN);

		var montante = taxajuro.divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
		montante = montante.add(BigDecimal.ONE);
		montante = montante.pow(qtdeparcela.intValue());
		montante = montante.multiply(valor);
		montante = montante.setScale(2, RoundingMode.HALF_EVEN);
		// Integer qtde=qtdeparcela.intValue();
		// var valorParcela= montante.divide(new BigDecimal( qtde));

		System.out.println("valor total" + montante);
		// System.out.println("valor parecela"+ valorParcela);
	}

	void CalcularprecovendaJuros() {
		var precovenda = new BigDecimal("1221.89");
		var taxaJurtos = new BigDecimal("3.7");
		var qtdeparcela = 10;
		var dias = 1350;
		var prazoMedio = new BigDecimal(dias).divide(new BigDecimal(qtdeparcela));
		var n = prazoMedio.divide(new BigDecimal(30));
///	 var custoFincaeiroMedio= new BigDecimal("");
		taxaJurtos = taxaJurtos.divide(new BigDecimal(100));
		taxaJurtos = taxaJurtos.add(BigDecimal.ONE);
		var custoFincaeiroMedio = precovenda.multiply(taxaJurtos).pow(qtdeparcela);
		custoFincaeiroMedio.divide(new BigDecimal(1).pow(qtdeparcela).subtract(new BigDecimal(1)));
		System.out.println("Prazo juros" + taxaJurtos);

		System.out.println("Prazo medio" + n);
		System.out.println("Custo Medio" + custoFincaeiroMedio);
	}

	void datahora() {
		String dateString = "2019-03-01T15:00:00-03:00";

		DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, inputFormatter);

		LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();

		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = localDateTime.format(outputFormatter);

		System.out.println("Original OffsetDateTime: " + offsetDateTime);
		System.out.println("Converted LocalDateTime: " + formattedDateTime);
	}


	void geraean() throws Exception {
		String digito = Ean.calculaDigEan13("1808354873641");
		System.out.println(digito);
		System.out.println(Ean.ean13());

		CodigoBarraEAN codigoBarra = new CodigoBarraEAN("789109430012");
		System.out.println("Codigo de barra: " + codigoBarra.validar(codigoBarra));
		System.out.println("Numero do codigo de barras: " + codigoBarra.getCodigoBarra());
		GeradorEan(codigoBarra.getCodigoBarra());
	}

	void GeradorEan(String ean) {
		char[] chars = ean.toCharArray();
		int len = chars.length;
		System.out.println("length of the char array: " + len);
		int contatador=0;
		for (char ch : chars) {
			Integer num=0;
			
			String numero = Character.toString(ch);
			num=Integer.parseInt(numero);
			if(contatador% 2 == 0) {
				somaPar(num);
			}else {
				somaImpar(num);
			}
			System.out.println(num);
			contatador++;
		}
		digitoVerificador(totalpar, totalImpar);
	}
   void	somaPar(Integer par){
	 totalpar+=  par;
	 System.out.println("totalpar"+ totalpar);
		
	}
   void	somaImpar(Integer impar){
		 totalImpar+=  impar;
		 System.out.println("totalimpap"+totalImpar);
			
		}
   void digitoVerificador(Integer totpar, Integer totimpar) {
	   System.out.println("valores"+totimpar+"-"+totalpar);
	Integer  toaldeImparEan=totimpar*3;
     Integer	totalNumeroEan=toaldeImparEan+ totpar;

     System.out.println(toaldeImparEan);
    	 var resultado =totalNumeroEan/10;
    	  int proximoMultiploDe10 = (totalNumeroEan + 9) / 10 * 10;
System.out.println(proximoMultiploDe10);
          // Passo 5: Subtrair a soma total do próximo múltiplo de 10
          int digitoVerificador = proximoMultiploDe10 - toaldeImparEan;

          // Passo 6: Se o digitoVerificador for 10, o 13º dígito será 0
          if (digitoVerificador == 10) {
              digitoVerificador = 0;
          }
        System.out.println("digito verificor"+ digitoVerificador%
        		10);
   }
//@Test
   void geradorEan() {
		String cnpj="10943852000176";
		String codiprodForncedor="0201 7159999";
		String codigopais="789";
		String codigoFornecedor="";
		codigoFornecedor=cnpj.substring(0,5);
		String codigoproduto=CalcularDigitoEan.extraireFormatar(codiprodForncedor);
	   String ean=codigopais+codigoFornecedor+codigoproduto;
	   System.out.println(ean);
	   String ean13 = CalcularDigitoEan.calcularEAN13(ean);
	   CodigoBarraEAN codigoBarra = new CodigoBarraEAN(ean13);
		System.out.println("Codigo de barra: " + codigoBarra.validar(codigoBarra));
		System.out.println("Numero do codigo de barras: " + codigoBarra.getCodigoBarra());
	   
   }
  // @Test
   void buscarid() {
	   Long id= serviceProduto.maxid();
	   System.out.println(id);
   }
  
   void geraean12() {
	   Random random = new Random();
       int numeroAleatorio = random.nextInt(900000000) + 100000000;
       System.out.println(numeroAleatorio);
   }  
   @Test
   void datanumerico() {
	   LocalDateTime datahora= LocalDateTime.now();
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	   String formattedDateTime = datahora.format(formatter);
   String stringConcatenada ="789"+formattedDateTime.substring(5,14);
 
       System.out.println(formattedDateTime);
      System.out.println(stringConcatenada);
      String ean13 = CalcularDigitoEan.calcularEAN13(stringConcatenada);
	   CodigoBarraEAN codigoBarra = new CodigoBarraEAN(ean13);
		System.out.println("Codigo de barra: " + codigoBarra.validar(codigoBarra));
		System.out.println("Numero do codigo de barras: " + codigoBarra.getCodigoBarra());
   
   }
   
}