package br.projeto.base.java.ee.controle;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.projeto.base.java.ee.Usuario;

@Named
@ApplicationScoped
public class BeanSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
