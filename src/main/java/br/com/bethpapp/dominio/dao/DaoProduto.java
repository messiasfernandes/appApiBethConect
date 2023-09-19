package br.com.bethpapp.dominio.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.query.ProdutoQuery;
//@Repository
public interface DaoProduto extends JpaRepository<Produto, java.lang.Long>, ProdutoQuery {
	@Query("SELECT Count(*)from Produto p where p.codigofabricante =:pfabricante")
	Long isCodigoFabCadastrado(String pfabricante);
	
	Produto findByCodigofabricante(String codigofabricante);
}
