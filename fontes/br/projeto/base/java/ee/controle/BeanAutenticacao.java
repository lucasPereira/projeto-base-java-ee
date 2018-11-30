package br.projeto.base.java.ee.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.projeto.base.java.ee.Usuario;

@Named
@ViewScoped
public class BeanAutenticacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanSessao beanSessao;

	public String autenticar() {
		beanSessao.setUsuario(new Usuario());
		return "/restrito/index.xhtml?faces-redirect=true";
	}

	public String sair() {
		beanSessao.setUsuario(null);
		return "/index.html?faces-redirect=true";
	}

}
