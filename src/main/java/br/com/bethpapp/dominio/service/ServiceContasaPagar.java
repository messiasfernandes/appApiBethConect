package br.com.bethpapp.dominio.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoContasApagar;
import br.com.bethpapp.dominio.dao.DaoFormaDePagamento;
import br.com.bethpapp.dominio.entidade.ContasPagar;
import br.com.bethpapp.dominio.entidade.ContasPagarDetalhe;
import br.com.bethpapp.dominio.entidade.FormadePagmamento;
import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.enumerado.StatusPagamento;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import jakarta.transaction.Transactional;


@Service
public class ServiceContasaPagar implements ServiceModel<ContasPagar> {
	@Autowired
    private  DaoContasApagar daoContasApagar;
	@Autowired
	private DaoFormaDePagamento daoFormaDePagamento;
	
	@Transactional
	public ContasPagar addconta(Integer qtepacerla,Long titulo,  Fornecedor fornecedor, BigDecimal valortotalconta, Long idForma, LocalDate  dataEmisao, String numNota) {
		ContasPagar contaContasaPagar = new ContasPagar();
		try {
			contaContasaPagar.setTotatdeParcelas(qtepacerla);
			contaContasaPagar.setFornecedor(fornecedor);
			   contaContasaPagar.setDatalancamento(dataEmisao);
			   contaContasaPagar.setNumeroTitulo(numNota);
			     Integer numeroparcela=0;
			     BigDecimal valoparcela = valortotalconta.divide(new BigDecimal(qtepacerla).setScale(4), MathContext.DECIMAL128);
			    contaContasaPagar.setTotalPagar(valortotalconta);
			FormadePagmamento formadePagmamento = daoFormaDePagamento.findById(idForma).get();
		     for (int i=0; i< qtepacerla;i++) {
		    	 ContasPagarDetalhe contasaPagarDetalhe = new ContasPagarDetalhe();
		    	 numeroparcela=i+1;
		    	 contasaPagarDetalhe.setContasaPagar(contaContasaPagar);
		         contasaPagarDetalhe.setNumparcela(numeroparcela);	
		         contasaPagarDetalhe.setFormadePagamento(formadePagmamento);
		         contasaPagarDetalhe.setStatusPagmaento(StatusPagamento.PENDENTE);
		         contasaPagarDetalhe.setValoparcela(valoparcela);
		         contasaPagarDetalhe.setValorapagar(valoparcela);
		         contasaPagarDetalhe.setValoprago(new BigDecimal(0));
		         contasaPagarDetalhe.setNumtitulo(titulo.toString()+ "-"+numeroparcela.toString()+"/"+qtepacerla.toString());
		         if (formadePagmamento.getId()==1l) {
		          	 contasaPagarDetalhe.setDataVencimento(contaContasaPagar.getDatalancamento().plusDays((numeroparcela* 0)));
		         }else {
		        	 contasaPagarDetalhe.setDataVencimento(contaContasaPagar.getDatalancamento().plusDays((numeroparcela* 30))); 
		         }
		         
		         contaContasaPagar.getContasaPagarDetalhes().add(contasaPagarDetalhe);
			}
		} catch (Exception e) {
			throw new NegocioException("Erro ao adicionar contas a pagar");
		}
		
	     
	     return contaContasaPagar;
	}


	@Override
	public Page<ContasPagar> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ContasPagar buccarporid(Long id) {
	
		return null;
	}

	@Transactional

	@Override
	public ContasPagar salvar(ContasPagar objeto) {
	
		return daoContasApagar.save(objeto);
	}
    @Transactional
	public ContasPagar cancelarConta(String cnpj, String numTitulo) {
		var conta =daoContasApagar.buscarconta(cnpj, numTitulo);
		for (int i = 0; i < conta.getContasaPagarDetalhes().size(); i++) {
			conta.getContasaPagarDetalhes().get(i).setStatusPagmaento(StatusPagamento.CANCELADO);
		}
		return daoContasApagar.save(conta);
	}
	

	



}
