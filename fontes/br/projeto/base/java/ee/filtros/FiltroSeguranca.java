package br.projeto.base.java.ee.filtros;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;

import br.projeto.base.java.ee.controle.BeanSessao;

@WebFilter("*.xhtml")
public class FiltroSeguranca implements Filter {

	@Inject
	private BeanSessao beanSessao;

	@Override
	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain proxima) throws IOException, ServletException {
		HttpServletRequest requisicaoHttp = (HttpServletRequest) requisicao;
		HttpServletResponse respostaHttp = (HttpServletResponse) resposta;
		String contexto = requisicao.getServletContext().getContextPath();
		if (facesResource(requisicaoHttp)) {
			proxima.doFilter(requisicao, resposta);
		} else if (!usuarioEstaAutenticado() && paginaRestrita(requisicaoHttp)) {
			String redirecionamento = UriBuilder.fromPath(contexto).path("autenticacao.xhtml").build().getPath();
			respostaHttp.sendRedirect(redirecionamento);
		} else if (usuarioEstaAutenticado() && !paginaRestrita(requisicaoHttp)) {
			String redirecionamento = UriBuilder.fromPath(contexto).path("restrito/index.xhtml").build().getPath();
			respostaHttp.sendRedirect(redirecionamento);
		} else {
			proxima.doFilter(requisicao, resposta);
		}
	}

	private Boolean facesResource(HttpServletRequest requisicao) {
		String pagina = UriBuilder.fromUri(requisicao.getRequestURI()).build().getPath();
		String restrito = UriBuilder.fromPath(requisicao.getServletContext().getContextPath()).path("javax.faces.resource").build().getPath();
		return pagina.startsWith(restrito);
	}

	private Boolean paginaRestrita(HttpServletRequest requisicao) {
		String pagina = UriBuilder.fromUri(requisicao.getRequestURI()).build().getPath();
		String restrito = UriBuilder.fromPath(requisicao.getServletContext().getContextPath()).path("restrito").build().getPath();
		return pagina.startsWith(restrito);
	}

	private Boolean usuarioEstaAutenticado() {
		return beanSessao.getUsuario() != null;
	}

}
