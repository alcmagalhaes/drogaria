package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 8174364960156974128L;

	private Produto produtoCadastro;

	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;
	private List<Fabricante> fabricantes;

	private String acao;
	private Long codigo;

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);

			// Limpa campos
			produtoCadastro = new Produto();
			FacesUtil.addMsgInfo("Produto salvo com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Ocorreu erro ao tentar incluir um Produto."
					+ e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);

			FacesUtil.addMsgInfo("Produto alterado com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Ocorreu erro ao tentar alterar um Produto."
					+ e.getMessage());
		}

	}

	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);

			// Limpa campos
			produtoCadastro = new Produto();
			FacesUtil.addMsgInfo("Produto excluído com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Ocorreu erro ao tentar excluir um Produto."
					+ e.getMessage());
		}

	}

	public void novo() {
		produtoCadastro = new Produto();
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Ocorreu erro ao tentar listar os Produtos."
					+ e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
			} else {
				produtoCadastro = new Produto();
			}
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar obter os dados do Produto."
							+ e.getMessage());
		}
	}

	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
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

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
