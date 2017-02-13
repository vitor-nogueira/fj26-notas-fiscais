package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624882497701448506L;
	
	private Usuario usuario;
	
	public void logar(Usuario usuario) {
		this.usuario=usuario;
	}
	
	public void deslogar() {
		this.usuario=null;
		System.out.println("Deslogou usuário");
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	/*
	 * retorna true se usuário estiver logado. 
	 */
	public boolean isLogado(){		
		return usuario != null; 
	}
}
