package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.HibernateUtil;

public class FuncionarioDAO {
	
	public void salvar(Funcionario funcionario) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}	
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		
		List<Funcionario> funcionarios = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return funcionarios;
		
	}
	
	public Funcionario buscarPorCodigo(Long codigo) {
		
		Funcionario funcionario = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			funcionario = (Funcionario) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return funcionario;
		
	}
	
	public void excluir(Funcionario funcionario) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}	
		
	}
	
	//Funcionario foi validado antes de editar (Ex: validacao na tela)
	public void editar(Funcionario funcionario) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}	
		
	}
	
	public Funcionario autenticar(String cpf, String senha) {
		
		Funcionario funcionario = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.autenticar");
			consulta.setString("cpf", cpf);
			consulta.setString("senha", senha);
			funcionario = (Funcionario) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return funcionario;
		
	}
	
}
