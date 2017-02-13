package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");

	@Produces //Configura o m�todo como produtor de EntityManager
	@RequestScoped //Escopo de Requisi��o
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close (@Disposes EntityManager manager){ //Fecha o EntityManager criado pelo produtor atrav�s do @Disposes
		manager.close();
	}
}