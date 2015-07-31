package br.com.drogaria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

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
			funcionario = funcionarioDAO.autenticar(funcionario.getCpf(), DigestUtils.md5Hex(funcionario.getSenha()));
			
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
	
	// Metodos void permanecem na mesma tela
	// Metodos String retornam para um destino diferente do atual - similar ao outcome no xhtml
	public String sair() {
		funcionario = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
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
