package br.com.drogaria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable{

	private static final long serialVersionUID = 639075960800444340L;

	private Funcionario funcionario;
	
	public void autenticar() {
		try {
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionario = funcionarioDAO.autenticar(funcionario.getCpf(), funcionario.getSenha());
			
			if (funcionario == null) {
				FacesUtil.addMsgError("CPF e/ou Senha inválidos.");
			} else {
				FacesUtil.addMsgInfo("Funcionário autenticado com sucesso.");
			}
			
			// Limpa campos
			
		} catch (RuntimeException e) {
			FacesUtil
					.addMsgError("Ocorreu erro ao tentar autenticar o usuário no sistema."
							+ e.getMessage());
		}
	}
	
	
	public Funcionario getFuncionario() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
