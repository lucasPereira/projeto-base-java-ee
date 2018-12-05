package br.projeto.base.java.ee.controle;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.projeto.base.java.ee.Usuario;

@Named
@SessionScoped
public class BeanSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setPrincipal(Principal principal) {
		System.out.println(principal);
		if (principal != null) {
			usuario = new Usuario();
		} else {
			usuario = null;
		}
	}

	public Boolean usuarioEstaAutenticado() {
		return usuario != null;
	}

}
