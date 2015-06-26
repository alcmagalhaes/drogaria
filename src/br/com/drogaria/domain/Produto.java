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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_produtos")
@NamedQueries({
	@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
	@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.codigo = :codigo")
})
public class Produto implements Serializable {

	private static final long serialVersionUID = 8927041115698718217L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_codigo")
	private Long codigo;

	@NotEmpty(message = "O campo DESCRIÇÃO é obrigatório.") 
	@Size(min = 5, max = 50, message = "Tamanho inválido para o campo DESCRIÇÃO. O campo deve conter entre 5 e 50 caracteres.")
	@Column(name = "prd_descricao", length = 50, nullable = false)
	private String descricao;

	@NotNull(message = "O campo PREÇO é obrigatório.")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igual à ZERO para o campo PREÇO.")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 100 mil para o campo PREÇO.")
	@Column(name = "prd_preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;

	@NotNull(message = "O campo QUANTIDADE é obrigatório.")
	@Min(value = 0, message = "Informe um valor maior ou igual à ZERO para o campo QUANTIDADE.")
	@Max(value = 9999, message = "Informe um valor menor que 10 mil para o campo QUANTIDADE.")
	@Column(name = "prd_quantidade", nullable = false)
	private Integer quantidade;

	@NotNull(message = "O campo FABRICANTE é obrigatório.")
	// FechType.EAGER - Carrega produto e fabricante
	// FechType.LAZY - Carrega o produto
	@ManyToOne(fetch = FetchType.EAGER)
	// JoinColumn(name = "FK", referencedColumnName("PK"));
	@JoinColumn(name = "fab_codigo", referencedColumnName = "fab_codigo", nullable = false)
	private Fabricante fabricante;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao
				+ ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fabricante=" + fabricante + "]";
	}

}
