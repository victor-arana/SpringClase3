package org.upiita.spring.test.daos;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.daos.PostDAO;
import org.upiita.spring.entidades.Post;

public class PostDAOTest {
	
	
	private static ApplicationContext contexto;
	
	private static PostDAO postDAO; 
	
	
	@BeforeClass
	public static void inicializar() {

		contexto = new ClassPathXmlApplicationContext("/dao-context-testing.xml");		
		postDAO = (PostDAO) contexto.getBean("postDAO");
		System.out.println("contexto de spring inicializado");
	}
	
	@Test
	public void testCrearPost(){
		// Creamos un post de pruebas
		
	
		Post post = new Post();
		post.setId(3);
		post.setTitulo("Titulo Victor");
		post.setContenido("Contenido Victor");
		post.setFechaCreacion(new Date(System.currentTimeMillis()));
		
		 postDAO.guardar(post);
		 
		 Post postDB = postDAO.buscarPorId(3);
		 
		 assertNotNull(postDB);		
	}
	
}
