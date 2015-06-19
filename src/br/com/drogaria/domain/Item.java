package br.com.drogaria.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_itens")
@NamedQueries({
	@NamedQuery(name = "Item.listar", query = "SELECT item FROM Item item"),
	@NamedQuery(name = "Item.buscarPorCodigo", query = "SELECT item FROM Item item WHERE item.codigo = :codigo")
})
public class Item implements Serializable {

	private static final long serialVersionUID = 7836605773273285044L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itm_codigo")
	private Long codigo;

	@Column(name = "itm_quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "itm_valor_parcial", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorParcial;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prd_codigo", referencedColumnName = "prd_codigo", nullable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ven_codigo", referencedColumnName = "ven_codigo", nullable = false)
	private Venda venda;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", quantidade=" + quantidade
				+ ", valorParcial=" + valorParcial + ", produto=" + produto
				+ ", venda=" + venda + "]";
	}

}
