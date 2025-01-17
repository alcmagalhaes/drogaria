package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Item;
import br.com.drogaria.util.HibernateUtil;

public class ItemDAO {
	
	public void salvar(Item item) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(item);
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
	public List<Item> listar() {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Item> itens = null;
		
		try {
			Query query = sessao.getNamedQuery("Item.listar");
			itens = query.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return itens;
	}
	
	public Item buscarPorCodigo(Long codigo) {
		
		Item item = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Item.buscarPorCodigo");
			//consulta.setLong(HQL, PARAMETER);
			consulta.setLong("codigo", codigo);
			item = (Item) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return item;
		
	}
	
	public void excluir(Item item) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(item);
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
	
	public void editar(Item item) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(item);
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
