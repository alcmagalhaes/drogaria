package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(fabricante);
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
	public List<Fabricante> listar() {
		
		List<Fabricante> fabricantes = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Fabricante.listar");
			fabricantes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return fabricantes;
		
	}
	
	public Fabricante buscarPorCodigo(Long codigo) {
		
		Fabricante fabricante = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Fabricante.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			fabricante = (Fabricante) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return fabricante;
		
	}
	
	public void excluir(Fabricante fabricante) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fabricante);
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
	
/*	
 	//Fabricante não foi validado antes de editar
	public void editar(Fabricante fabricanteTransiente) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			Fabricante fabricantePersistente = buscarPorCodigo(fabricanteTransiente.getCodigo());
			fabricantePersistente.setDescricao(fabricanteTransiente.getDescricao());
			sessao.update(fabricantePersistente);
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
*/
	
	//Fabricante foi validado antes de editar (Ex: validacao na tela)
	public void editar(Fabricante fabricante) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(fabricante);
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
	
}
