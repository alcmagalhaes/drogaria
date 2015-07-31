package br.com.drogaria.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {

		FuncionarioDAO dao = new FuncionarioDAO();
		
		for (int i = 1; i <= 4; i++) {
			Funcionario funcionario = new Funcionario();
			
			funcionario.setNome("Funcionário " + i);
			funcionario.setCpf("" + i + i + i + "." + i + i + i + "." + i + i + i + "-" + i + i);
			funcionario.setSenha("1q2w3e");
			funcionario.setFuncao("Balconista");
			
			dao.salvar(funcionario);
			//System.out.println(funcionario);
			
		}
		
		
	}
	
	@Test
	@Ignore
	public void listar() {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();
		
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario f1 = dao.buscarPorCodigo(5L);
		Funcionario f4 = dao.buscarPorCodigo(7L);
		
		System.out.println(f1);
		System.out.println(f4);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(3L);
		
		dao.excluir(funcionario);
		
	}
	
	@Test
	@Ignore
	public void editar() {
			
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(7L);

		funcionario.setFuncao("Administrador");
		funcionario.setSenha("8i9o0p");
		
		dao.editar(funcionario);
		
	}
	
	@Test
	@Ignore
	public void autenticar() {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.autenticar("983.160.384-20", "1q2w3e");
		
		Assert.assertNotNull(funcionario);
		
	}

}
