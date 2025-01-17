package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.HibernateUtil;

public class ProdutoDAO {

	public void salvar(Produto produto) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(produto);
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
	public List<Produto> listar() {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = null;
		
		try {
			Query query = sessao.getNamedQuery("Produto.listar");
			produtos = query.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return produtos;
	}
	
	public Produto buscarPorCodigo(Long codigo) {
		
		Produto produto = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query consulta = sessao.getNamedQuery("Produto.buscarPorCodigo");
			//consulta.setLong(HQL, PARAMETER);
			consulta.setLong("codigo", codigo);
			produto = (Produto) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return produto;
		
	}
	
	public void excluir(Produto produto) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(produto);
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
	
	public void editar(Produto produto) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(produto);
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
