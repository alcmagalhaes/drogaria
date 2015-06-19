package br.com.drogaria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 3019657694285850986L;
	private Funcionario funcionarioCadastro;
	
	public void salvar() {
		FacesUtil.addMsgInfo(funcionarioCadastro.toString());
		FacesUtil.addMsgInfo("Funcionário salvo com sucesso.");
	}

	public Funcionario getFuncionarioCadastro() {
		if(funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}
	
	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}
	
}
