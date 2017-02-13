package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import com.sun.xml.rpc.streaming.Event;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener{
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		if ("/login.xhtml".equals(context.getViewRoot().getViewId())){
			return;
		}
		
		//Verificacao
		if (!usuarioLogado.isLogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			System.out.println("Usuario nao está logado");
			//Efetua a Renderizacao da tela.
			context.renderResponse();
			
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
