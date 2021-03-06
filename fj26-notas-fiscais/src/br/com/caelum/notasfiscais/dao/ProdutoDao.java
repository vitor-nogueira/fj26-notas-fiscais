package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.JPAUtil;

public class ProdutoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject //Injetando a dependencia do objeto EntityManager
	EntityManager manager = new JPAUtil().getEntityManager();

	public void adiciona(Produto produto) {
		//EntityManager manager = new JPAUtil().getEntityManager();
		//manager.getTransaction().begin();  -- o controle transacional foi transferido para o @Transactional das ManagedBeans

		//persiste o objeto
		manager.persist(produto);
		
		//manager.getTransaction().commit();
		//manager.close();
	}


	public void remove(Produto produto) {
		//EntityManager manager = new JPAUtil().getEntityManager();
		//manager.getTransaction().begin();

		manager.remove(manager.merge(produto));

		//manager.getTransaction().commit();
		//manager.close();
	}

	public void atualiza(Produto produto) {
		//EntityManager manager = new JPAUtil().getEntityManager();
		//manager.getTransaction().begin();

		manager.merge(produto);
		
		//manager.getTransaction().commit();
		//manager.close();
	}

	public List<Produto> buscaPorNome(String nome) {

		//EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select p from Produto p where "
				+ " lower(p.nome) like :nome order by p.nome";

		List<Produto> lista = manager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome + "%").getResultList();

		//manager.close();
		
		return lista; 
	}

	public List<Produto> listaTodos() {
		//System.out.println("Listando Produtos");
		//EntityManager manager = new JPAUtil().getEntityManager();
		//System.out.println("Carregando EMF");
		
		//System.out.println("Criando query com EMF");
		CriteriaQuery<Produto> query = manager.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));
		System.out.println("Query criada com EMF");
		

		List<Produto> lista = manager.createQuery(query).getResultList();

		//manager.close();
		
		return lista; 
	}
	
	public Produto buscaPorId(Long id) {
	//	EntityManager manager = new JPAUtil().getEntityManager();

		Produto produto = manager.find(Produto.class, id);

		//manager.close();

		return produto;
	}
}