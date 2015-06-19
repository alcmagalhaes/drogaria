package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void inserir() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(3L);
		
		Produto produto = new Produto();
		produto.setDescricao("Produto 3");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.55"));
		produto.setQuantidade(15);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
	}

	@Test
	@Ignore
	public void listar() {
		
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		
		for (Produto produto : produtos) {
			System.out.println(produto);
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = dao.buscarPorCodigo(2L);
		
		System.out.println(produto);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = dao.buscarPorCodigo(4L);
		
		dao.excluir(produto);
		
	}
	
	@Test
	@Ignore
	public void editar() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		
		Produto produto = produtoDAO.buscarPorCodigo(2L);
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(2L); 
		
		produto.setDescricao("Produto 2");
		produto.setPreco(new BigDecimal("11.99"));
		produto.setFabricante(fabricante);
		
		produtoDAO.editar(produto);
		
	}
	
	
	
}
