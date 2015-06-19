package br.com.drogaria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8174364960156974128L;
	private Produto produtoCadastro;
	
	public void salvar() {
		FacesUtil.addMsgInfo(produtoCadastro.toString());
		FacesUtil.addMsgInfo("Produto salvo com sucesso.");
	}
	
	public Produto getProdutoCadastro() {
		if (produtoCadastro == null) {
			produtoCadastro = new Produto();
		}
		return produtoCadastro;
	}
	
	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}
}
