package br.com.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.FacesUtil;


@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{
	
	private static final long serialVersionUID = -2625452161764510858L;

	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;
	
	private List<Item> items;
	
	private Venda venda;
	
	public void carregarProdutos() {
		try {
			
			if (items == null) {
				items = new ArrayList<>();
			}
			
			if (venda == null) {
				venda = new Venda();
				venda.setValorTotal(new BigDecimal("0.00"));
			}
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Ocorreu erro ao tentar listar os Produtos."
					+ e.getMessage());
		}
	}
	
	public void adicionar(Produto produto) {
		
		int encontrado = -1;
		
		for (int i = 0; i < items.size() && encontrado < 0; i++) {
			Item itemTemp = items.get(i);
			if (itemTemp.getProduto().equals(produto)) {
				encontrado = i;
			}
		}
		
		Item item = new Item();
		item.setProduto(produto);
		
		if (encontrado < 0) {
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			items.add(item);		
		} else {
			Item temp = items.get(encontrado);
			item.setQuantidade(temp.getQuantidade() + 1);
			//Multiplicacao BigDecimal
			item.setValorParcial(
					produto.getPreco().multiply(
							new BigDecimal( item.getQuantidade() )
						)
					);
			items.set(encontrado, item);
		}
		
		//Adicao BigDecimal
		venda.setValorTotal(venda.getValorTotal().add(produto.getPreco()));
		
	}
	
	public void remover(Item item) {
		
		int encontrado = -1;
		
		for (int i = 0; i < items.size() && encontrado < 0; i++) {
			Item itemTemp = items.get(i);
			if (itemTemp.getProduto().equals(item.getProduto())) {
				encontrado = i;
			}
		}
		
		if (encontrado > -1) {
			items.remove(encontrado);
			venda.setValorTotal(venda.getValorTotal().subtract(item.getValorParcial()));
		}
		
	}
	
	public void carregarDadosVenda() {
		venda.setHorario(new Date());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(7L);
		venda.setFuncionario(funcionario);
	}
	
	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			Long codigo = vendaDAO.salvar(venda);
			
			Venda venda = vendaDAO.buscarPorCodigo(codigo);
			
			for(Item item: items) {
				item.setVenda(venda);
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.salvar(item);
			}
			
			// Limpa campos
			venda = new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));
			items = new ArrayList<>();
			FacesUtil.addMsgInfo("Venda salva com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Ocorreu erro ao tentar incluir uma Venda."
					+ e.getMessage());
		}
	}	
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	
	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
