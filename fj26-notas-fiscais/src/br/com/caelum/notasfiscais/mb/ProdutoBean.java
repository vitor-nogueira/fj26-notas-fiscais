package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

//@ManagedBean
@Named
@RequestScoped
public class ProdutoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private List<Produto> produtos;

	@Inject //Indica a dependencia do DAO
	private ProdutoDao dao;
	
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void grava() {
		System.out.println("Será que passa por aqui??");
		//ProdutoDao dao = new ProdutoDao();
		
		if (produto.getId() == null){
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
			
		}
		this.produtos = dao.listaTodos();
		this.produto = new Produto();
		//return "produto?faces-redirect=true";
	}

	public List<Produto> getProdutos(){
		if (produtos == null){
			System.out.println("Carregando Dados");
			produtos = new ProdutoDao().listaTodos();
			
		}
		return produtos;
	}
	
	public void remove(Produto produto){
		//ProdutoDao dao = new ProdutoDao();
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
}
