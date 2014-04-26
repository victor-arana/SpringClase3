package org.upiita.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.upiita.spring.daos.PostDAO;
import org.upiita.spring.daos.UsuarioDAO;
import org.upiita.spring.entidades.Post;
import org.upiita.spring.entidades.Usuario;

@Controller
public class BlogControlador {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PostDAO postDAO;
	// Una expresión regular es un mecanismo de todos los lenguajes
	// que les permite reconocer patrones en cadenas
	// Todas las cadenas que sean de uno o más digítos [0-9](+)
	// {PARAMETRO:Expresión regular}
	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}")
	public String muestraUsuario(@PathVariable Integer usuarioId, Model modelo){
		
		Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
		// Model pinta en el JSP
		modelo.addAttribute("usuario", usuario);		
		return "usuario";
	}
	
	@RequestMapping(value="/post/{postId:[0-9]+}")
	public String muestraPost(@PathVariable Integer postId, Model modelo){
		
		Post post = postDAO.buscarPorId(postId);
		// Model pinta en el JSP
		
		// 5 Pasar los datos a la vista
		modelo.addAttribute("post", post);
		return "post";
	}
	
	@RequestMapping(value="/posts")
	public String muestraPosts(Model modelo){
		
		return "posts";
	}
		
}
