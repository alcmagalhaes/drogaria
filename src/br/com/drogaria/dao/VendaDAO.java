package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO {
	
	public void salvar(Venda venda) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(venda);
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
	public List<Venda> listar() {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;
		
		try {
			Query query = sessao.getNamedQuery("Venda.listar");
			vendas = query.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return vendas;
	}
	
	public Venda buscarPorCodigo(Long codigo) {
		
		Venda venda = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
			//consulta.setLong(HQL, PARAMETER);
			consulta.setLong("codigo", codigo);
			venda = (Venda) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return venda;
		
	}
	
	public void excluir(Venda venda) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(venda);
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
	
	public void editar(Venda venda) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
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
