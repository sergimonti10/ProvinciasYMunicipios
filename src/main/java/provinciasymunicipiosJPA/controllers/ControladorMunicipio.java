package provinciasymunicipiosJPA.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import provinciasymunicipiosJPA.models.Municipio;

public class ControladorMunicipio {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("provinciasymunicipios");

	/** 
	 * 
	 */
	public static Municipio findById() {

		EntityManager em = entityManagerFactory.createEntityManager();
		Municipio l = (Municipio) em.find(Municipio.class, 1);
		System.out.println("Municipio: " + l.getNombre());
		em.close();
		return l;
	}

	/**
	 * 
	 * @return
	 */
	public static List<Municipio> findAll() {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM municipio;", Municipio.class);

		List<Municipio> lista = (List<Municipio>) q.getResultList();

		for (Municipio l : lista) {
			System.out.println("Municipio: " + l.getNombre());
		}

		em.close();
		return lista;
	}

	/**
	 * 
	 */
	public static List<Municipio> findByDescription(String descripcion) {

		EntityManager em = entityManagerFactory.createEntityManager();
		
//		Query q = em.createNativeQuery("SELECT * FROM localidad WHERE descripcion LIKE ?", Localidad.class);
		Query q = em.createNativeQuery("SELECT * FROM municipio WHERE nombre LIKE '%" + descripcion + "%'",
				Municipio.class);

		q.setParameter(1, "%" + descripcion + "%");
		List<Municipio> lista = (List<Municipio>) q.getResultList();

		for (Municipio l : lista) {
			System.out.println("Localidad: " + l.getNombre());
		}

		em.close();
		return lista;
	}

//	/**
//	 * 
//	 */
//	public static List<Municipio> creacionEntidad(int id, String nombre) {
//
//		EntityManager em = entityManagerFactory.createEntityManager();
//
//		Municipio l = new Municipio();
//		l.setId(id);
//		l.setNombre(nombre);
//
//		em.getTransaction().begin();
//		em.persist(l);
//		em.getTransaction().commit();
//
//		TypedQuery<Municipio> q = em.createQuery("SELECT l FROM municipio as l", Municipio.class);
//
//		List<Municipio> municipios = q.getResultList();
//
//		for (Municipio lEnLista : municipios) {
//			System.out.println("Fabricante: " + lEnLista.getId() + " CIF: " + lEnLista.getId() + " Nombre: "
//					+ lEnLista.getNombre());
//		}
//
//		em.close();
//
//		return municipios;
//	}

	/**
	 * 
	 */
	public static List<Municipio> modificacionEntidad(String nombre, String nuevoNombre) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM municipio WHERE nombre LIKE ?", Municipio.class);

		q.setParameter(1, "%" + nombre + "%");
		List<Municipio> municipios = q.getResultList();

		em.getTransaction().begin();
		for (Municipio m : municipios) {
			m.setNombre(nuevoNombre);
			em.persist(m);
		}
		em.getTransaction().commit();

		em.close();

		return municipios;
	}

//	/**
//	 * 
//	 */
//	private static List<Municipio> eliminacionEntidad(int id) {
//
//		EntityManager em = entityManagerFactory.createEntityManager();
//
//		TypedQuery<Municipio> q = em.createQuery("SELECT l FROM municipio as l where l.id = " + id, Municipio.class);
//
//		List<Municipio> municipio = q.getResultList();
//
//		em.getTransaction().begin();
//		for (Municipio lEnLista : municipio) {
//			em.remove(lEnLista);
//		}
//		em.getTransaction().commit();
//
//		em.close();
//		return municipio;
//	}

}
