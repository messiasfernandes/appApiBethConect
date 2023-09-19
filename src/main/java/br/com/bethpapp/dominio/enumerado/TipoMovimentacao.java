package br.com.bethpapp.dominio.enumerado;



public enum TipoMovimentacao {
	Entrada("Entrada"),
	Saida("Saida"),
	Devolucao("Devolução");
	private String descricao;

	private TipoMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
