package br.projeto.base.java.ee.controle;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.projeto.base.java.ee.Usuario;

@Named
@SessionScoped
public class BeanSeguranca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpSession sessao;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void atualizarPrincipal(Principal principal) {
		usuario = (principal == null) ? null : new Usuario();
	}

	public Boolean usuarioEstaAutenticado() {
		return usuario != null;
	}

	public String autenticar() {
		return "/restrito/index.xhtml?faces-redirect=true";
	}

	public String sair() {
		sessao.invalidate();
		return "/index.html?faces-redirect=true";
	}

}
