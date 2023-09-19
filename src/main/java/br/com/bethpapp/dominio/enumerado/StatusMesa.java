package br.com.bethpapp.dominio.enumerado;

public enum StatusMesa {
	Disponível("Disponível"),
	Ocupada("Ocupada");
	private String descricao;

	private StatusMesa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
    
}
