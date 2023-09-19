package br.com.bethpapp.dominio.enumerado;

public enum StatusEntradaNota{
	
	Entregue("Entregue"),
	Cancelado("Cancelado");
	
	private String descricao;
	private StatusEntradaNota(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
    

}
