package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		Fabricante f1 = new Fabricante();
		f1.setDescricao("Fabricante 1");
		Fabricante f2 = new Fabricante();
		f2.setDescricao("Fabricante 2");
		Fabricante f3 = new Fabricante();
		f3.setDescricao("Fabricante 3");
		Fabricante f4 = new Fabricante();
		f4.setDescricao("Fabricante 4");
		
		FabricanteDAO dao = new FabricanteDAO();
		//dao.salvar(f1);
		//dao.salvar(f2);
		//dao.salvar(f3);
		dao.salvar(f4);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();
		
		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante);
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante f1 = dao.buscarPorCodigo(1L);
		Fabricante f4 = dao.buscarPorCodigo(4L);
		
		System.out.println(f1);
		System.out.println(f4);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante fabricante = dao.buscarPorCodigo(7L);
		
		dao.excluir(fabricante);
		
	}
	
/*	
 	@Test
	@Ignore
	public void editar() {
		
		Fabricante fabricanteTransiente = new Fabricante();
		fabricanteTransiente.setCodigo(3L);
		fabricanteTransiente.setDescricao("Fabricante 3 alterado");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(fabricanteTransiente);
		
	}
*/
	
	@Test
	@Ignore
	public void editar() {
			
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante fabricante = dao.buscarPorCodigo(3L);
		fabricante.setDescricao("Fabricante 3 alterado");
		
		dao.editar(fabricante);
		
	}
	

}
