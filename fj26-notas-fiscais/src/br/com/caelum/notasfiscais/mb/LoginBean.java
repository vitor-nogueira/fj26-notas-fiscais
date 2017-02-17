package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named //@Named indica que é um managed bean gerenciavel pelo CDI
@RequestScoped //Escopo do CDI
//@ManagedBean //ManagedBean passou a ser controlada pela CDI, através da anotação @NAmed
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@Inject //Indica a dependencia do DAO
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Inject
	Event<Usuario> eventoLogin;
	
	public String efetuaLogin() {
	//	UsuarioDao dao = new UsuarioDao();
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) {
			usuarioLogado.logar(usuario);
			eventoLogin.fire(usuario);
			return "produto?faces-redirect=true";
		} else {
			usuarioLogado.deslogar();
			this.usuario=new Usuario();
			return "login";
		}
	}
	public String deslogarUsuario() {
		usuarioLogado.deslogar();
		return "login?faces-redirect=true";
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

}
