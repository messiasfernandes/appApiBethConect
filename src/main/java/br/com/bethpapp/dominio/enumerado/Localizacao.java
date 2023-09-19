package br.com.bethpapp.dominio.enumerado;

public enum Localizacao {
	AreaInterna("Area Interna"),
	AreaExterna("Area Externa");
	private String descricao;

	private Localizacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
