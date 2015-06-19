package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;

public class VendaDAOTest {

	@Test
	@Ignore
	public void inserir() {
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(7L);
		
		Venda venda = new Venda();
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal("230.59"));
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
		
	}

	@Test
	@Ignore
	public void listar() {
		
		VendaDAO dao = new VendaDAO();
		List<Venda> vendas = dao.listar();
		
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		
		VendaDAO dao = new VendaDAO();
		
		Venda venda = dao.buscarPorCodigo(2L);
		
		System.out.println(venda);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		VendaDAO dao = new VendaDAO();
		
		Venda venda = dao.buscarPorCodigo(2L);
		
		dao.excluir(venda);
		
	}
	
	@Test
	@Ignore
	public void editar() {
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		VendaDAO vendaDAO = new VendaDAO();
		
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(6L);
		Venda venda = vendaDAO.buscarPorCodigo(2L);

		//venda.setHorario(new Date());
		venda.setHorario(Calendar.getInstance().getTime());
		venda.setValorTotal(new BigDecimal("223.12"));
		venda.setFuncionario(funcionario);
		
		
		vendaDAO.editar(venda);
		
	}
	
}
