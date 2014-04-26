package org.upiita.spring.test.daos;

// Hacemos un static import
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.upiita.spring.daos.UsuarioDAO;
import org.upiita.spring.entidades.Usuario;

public class UsuarioDAOTest {
	
	
	private static ApplicationContext contexto;	
	
	// Testing puede ser de 2 tipos:
	// -- Unit Testing (Test Unitario)
	// -- Test de Integracion => es para BD y pruebas complejas
	
	private static UsuarioDAO usuarioDAO;
	
	@BeforeClass // Ejecuta este método antes de cualquier test
				 // Solo lo ejecuta una vez
	public static void inicializar() {

		contexto = new ClassPathXmlApplicationContext("/dao-context-testing.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");

		System.out.println("contexto de spring inicializado");
	}
	
	@Test
	public void testCreaUsuario(){
		// Creamos un usuario de pruebas
		
		Usuario usuario = new Usuario();
//		usuario.setId(3);
		usuario.setNombre("Usuario prueba");
		usuario.setEmail("prueba@email.com");
		usuario.setPassword("123");
		
		usuarioDAO.guardar(usuario);
		
		Usuario usuarioBD = usuarioDAO.buscarPorId(3);
		
		// Revisa que la variable no sea null
		// Si es null detiene el testing y nos informa del error.
		assertNotNull(usuarioBD);
		//Assert.notEmpty(new ArrayList());
	}
	
	@Test
	public void testMapeoUsuarioDatosUsuario(){
		Usuario usuario = usuarioDAO.buscarPorId(1);
		assertNotNull(usuario);
		assertNotNull(usuario.getDatosUsuario());
		System.out.println("usuario:" + usuario);
		System.out.println("datos usuario:" + usuario.getDatosUsuario());
	}
	
	@Test
	public void testMapeoUsuarioPosts(){
		Usuario usuario = usuarioDAO.buscarPorId(1);
		assertNotNull(usuario);
		
		// Checa que la colección 
		Assert.notEmpty(usuario.getPosts());
	}	
}