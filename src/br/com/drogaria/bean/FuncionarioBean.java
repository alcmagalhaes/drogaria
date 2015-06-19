package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 3019657694285850986L;

	private Funcionario funcionarioCadastro;

	private List<Funcionario> funcionarios;
	private List<Funcionario> funcionariosFiltrados;

	private String acao;
	private Long codigo;

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionarioCadastro);

			// Limpa campos
			funcionarioCadastro = new Funcionario();
			FacesUtil.addMsgInfo("Funcionário salvo com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar incluir um Funcionário."
							+ e.getMessage());
		}
	}

	public void editar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.editar(funcionarioCadastro);

			FacesUtil.addMsgInfo("Funcionário alterado com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar alterar um Funcionário."
							+ e.getMessage());
		}

	}

	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.editar(funcionarioCadastro);

			// Limpa campos
			funcionarioCadastro = new Funcionario();
			FacesUtil.addMsgInfo("Funcionário excluído com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar excluir um Funcionário."
							+ e.getMessage());
		}

	}

	public void novo() {
		funcionarioCadastro = new Funcionario();
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar listar os Funcionários."
							+ e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarioCadastro = funcionarioDAO.buscarPorCodigo(codigo);
			} else {
				funcionarioCadastro = new Funcionario();
			}

			// FacesUtil.addMsgInfo("Fabricantes carregados com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar obter os dados do Funcionário."
							+ e.getMessage());
		}
	}

	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
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
