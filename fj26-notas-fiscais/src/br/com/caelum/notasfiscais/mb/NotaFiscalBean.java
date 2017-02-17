package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal = new NotaFiscal();

	@Inject
	private NotaFiscalDao notaFiscalDao;

	@Inject
	private ProdutoDao produtoDao;

	private Item item = new Item();
	private Long idProduto;

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public NotaFiscalDao getNotaFiscalDao() {
		return notaFiscalDao;
	}

	public void setNotaFiscalDao(NotaFiscalDao notaFiscalDao) {
		this.notaFiscalDao = notaFiscalDao;
	}

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	@Transactional
	public void gravar() {
		this.notaFiscalDao.adiciona(notaFiscal);

		this.notaFiscal = new NotaFiscal();
	}
	
	@Transactional
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto); //busca o produto por ID
		item.setProduto(produto);							//colocar o produto como item da nota
		item.setValorUnitario(produto.getPreco());			//set o valor do item com o valor do produto
										
		notaFiscal.getItens().add(item);					//adiciona o item na NF
		item.setNotaFiscal(notaFiscal);						//associa o item à NF
		
		item = new Item();									//zera o item para adicionar novos items
	}

}
