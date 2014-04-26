package org.upiita.spring.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "usuarios")
public class Usuario {
	
	@Id
	@Column(name = "id")
	// name = Es un nombre que sirve solo para Hibernate (JPA)
	// SequenceName es el Nombre real de la secuencia en la base de datos.
	@SequenceGenerator(name="usuariosIDSeq", sequenceName="usuarios_id_seq", allocationSize=1)
	// Este Annotation es el que genera los números del ID
	// Lo genera usando @SequenceGenerator
	@GeneratedValue(generator="usuariosIDSeq", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	// La entidad dueña es la que tiene la llave foránea
	// La entidad dueña usa JoinColumn
	// El atributo name= nombre de la columna de su relación.
	@OneToOne
	@JoinColumn(name="datos_autor_id")
	private DatosUsuario datosUsuario;
	
	// fetch permite cambiar la estrategia de carga de hibernate(JPA)
	// Eager carga prematuramente en memoria ram.
	@OneToMany(mappedBy="usuario",fetch=FetchType.EAGER)
	private List<Post> posts;
		
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public DatosUsuario getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(DatosUsuario datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

	// override del metodo String para que esta clase se imprima bonito :)
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");		
		builder.append("nombre:").append(this.nombre).append(",");
		builder.append("email:").append(this.email).append(",");
		builder.append("password:").append(this.password);		
		builder.append("}");

		return builder.toString();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
