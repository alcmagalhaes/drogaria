package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	private static final long serialVersionUID = -8329949209316295934L;

	private Fabricante fabricanteCadastro;

	private List<Fabricante> fabricantes;
	private List<Fabricante> fabricantesFiltrados;

	private String acao;
	private Long codigo;

	public void salvar() {
		// FacesUtil.addMsgInfo(fabricanteCadastro.toString());
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);

			// Limpa campos
			fabricanteCadastro = new Fabricante();
			FacesUtil.addMsgInfo("Fabricante salvo com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar incluir um Fabricante."
							+ e.getMessage());
		}

	}

	public void editar() {
		// FacesUtil.addMsgInfo(fabricanteCadastro.toString());
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);

			// Limpa campos
			fabricanteCadastro = new Fabricante();
			FacesUtil.addMsgInfo("Fabricante alterado com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar alterar um Fabricante."
							+ e.getMessage());
		}

	}

	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);

			// Limpa campos
			fabricanteCadastro = new Fabricante();
			FacesUtil.addMsgInfo("Fabricante excluído com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar excluir um Fabricante."
							+ e.getMessage());
		}

	}

	public void novo() {
		fabricanteCadastro = new Fabricante();
	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

			// FacesUtil.addMsgInfo("Fabricantes carregados com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar listar os Fabricantes."
							+ e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigo);
			} else {
				fabricanteCadastro = new Fabricante();
			}

			// FacesUtil.addMsgInfo("Fabricantes carregados com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar obter os dados do Fabricante."
							+ e.getMessage());
		}
	}

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
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

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Fabricante> getFabricantesFiltrados() {
		return fabricantesFiltrados;
	}

	public void setFabricantesFiltrados(List<Fabricante> fabricantesFiltrados) {
		this.fabricantesFiltrados = fabricantesFiltrados;
	}

}
