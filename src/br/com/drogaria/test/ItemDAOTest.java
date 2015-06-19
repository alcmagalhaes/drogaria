package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;

public class ItemDAOTest {

	@Test
	@Ignore
	public void inserir() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		VendaDAO vendaDAO = new VendaDAO();
		ItemDAO itemDAO = new ItemDAO();
		
		Produto produto = produtoDAO.buscarPorCodigo(3L);
		Venda venda = vendaDAO.buscarPorCodigo(3L);
		
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(2);
		item.setValorParcial(produto.getPreco());
		item.setVenda(venda);
		
		itemDAO.salvar(item);
		
	}

	@Test
	@Ignore
	public void listar() {
		
		ItemDAO dao = new ItemDAO();
		List<Item> itens = dao.listar();
		
		for (Item item : itens) {
			System.out.println(item);
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		
		ItemDAO dao = new ItemDAO();
		
		Item item = dao.buscarPorCodigo(1L);
		
		System.out.println(item);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		ItemDAO dao = new ItemDAO();
		
		Item item = dao.buscarPorCodigo(2L);
		
		dao.excluir(item);
		
	}
	
	@Test
	@Ignore
	public void editar() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		VendaDAO vendaDAO = new VendaDAO();
		ItemDAO itemDAO = new ItemDAO();
		
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		Venda venda = vendaDAO.buscarPorCodigo(1L);
		
		Item item = itemDAO.buscarPorCodigo(1L);
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValorParcial(produto.getPreco());
		item.setVenda(venda);
		
		itemDAO.editar(item);
		
	}
	
}
