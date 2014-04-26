package org.upiita.spring.daos.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.daos.UsuarioDAO;
import org.upiita.spring.entidades.Usuario;

@Component("usuarioDAO")
@Transactional	// Habilita transacciones en esta clase en todos los m�todos
public class HibernateUsuarioDAO implements UsuarioDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void guardar(Usuario autor) {

		sessionFactory.getCurrentSession().save(autor);
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		
		Usuario autor = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
		// Cuando usan lazy loading en relaciones 1-n
		// Por default hibernate usa lazy loading
//		Hibernate.initialize(autor.getPosts());
		
		return autor;
	}

	@Override
	public Usuario buscarPorNombreYPassword(String nombre, String password) {
		Usuario autor = null;

		if (nombre != null && password != null) {
			Criteria criterio = sessionFactory.getCurrentSession().createCriteria(Usuario.class);

			criterio.add(Restrictions.eq("nombre", nombre));
			criterio.add(Restrictions.eq("password", password));
			
			autor = (Usuario) criterio.uniqueResult();
		}

		return autor;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario buscaPorNombre(String nombre) {
		Usuario autor = null;
		
		if(nombre != null){
			Criteria criterio = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
			autor = (Usuario) criterio.add(Restrictions.eq("nombre", nombre)).uniqueResult();
		}
		
		return autor;
	}

}
