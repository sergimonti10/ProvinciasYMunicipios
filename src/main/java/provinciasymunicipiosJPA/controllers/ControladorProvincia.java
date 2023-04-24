package provinciasymunicipiosJPA.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import provinciasymunicipiosJPA.models.Provincia;


public class ControladorProvincia {

private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("provinciasymunicipios");
	
	
	/** 
	 * 
	 */
	public static Provincia findById () {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		Provincia l = (Provincia) em.find(Provincia.class, 1);
		System.out.println("Localidad: " + l.getProvincia());
		em.close();
		return l;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Provincia> findAll () {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM provincia;", Provincia.class);
		
		List<Provincia> lista = (List<Provincia>) q.getResultList();
		
		for (Provincia l : lista) {
			System.out.println("Localidad: " + l.getProvincia());
		}
		
		em.close();
		return lista;
	}
	
	/**
	 * 
	 */
	public static List<Provincia> findByDescription (String descripcion) {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM localidad WHERE descripcion LIKE '%" + descripcion + "%'", Provincia.class);

		q.setParameter(1, 2);
		List<Provincia> lista = (List<Provincia>) q.getResultList();
		
		for (Provincia l : lista) {
			System.out.println("Localidad: " + l.getProvincia());
		}
		
		em.close();
		return lista;
	}
	
	/**
	 * 
	 * @param idProvincia
	 * @param idMunicipio
	 * @return
	 */
	public static List<Provincia> findProvinciaByDescripcionMunicipio(String descripcion) {
	    EntityManager em = entityManagerFactory.createEntityManager();

	    Query q = em.createNativeQuery("SELECT p.* FROM provincia p JOIN municipio m ON p.id = m.idProvincia WHERE m.nombre LIKE '" + descripcion + "'", Provincia.class);
	    q.setParameter(1, 2);
	    List<Provincia> lista = (List<Provincia>) q.getResultList();


	    em.close();
	    return lista;
	}

	
	/**
	 * 
	 */
	public static List<Provincia> creacionEntidad (int id, String provincia) {

		EntityManager em = entityManagerFactory.createEntityManager();

		Provincia l = new Provincia();
		l.setId(id);
		l.setProvincia(provincia);
		
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
		
		TypedQuery<Provincia> q = em.createQuery("SELECT l FROM Localidad as l", Provincia.class);
		
		List<Provincia> provincias = q.getResultList();
		
		for (Provincia lEnLista : provincias) {
			System.out.println("Fabricante: " + lEnLista.getId() + " CIF: " + lEnLista.getId() + " Nombre: " + lEnLista.getProvincia());
		}
		
		em.close();
		
		return provincias;
	}
	
	/**
	 * 
	 */
	private static List<Provincia> modificacionEntidad (int id) {

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Provincia> q = em.createQuery("SELECT l FROM Localidad as l where l.id = " + id, Provincia.class);
		
		List<Provincia> provincia = q.getResultList();
		
		em.getTransaction().begin();
		for (Provincia lEnLista : provincia) {
			lEnLista.setProvincia("Modificado");
			em.persist(lEnLista);
		}
		em.getTransaction().commit();
		
		em.close();
		return provincia;
	}
	
	/**
	 * 
	 */
	private static List<Provincia> eliminacionEntidad (int id) {

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Provincia> q = em.createQuery("SELECT l FROM Localidad as l where l.id = " + id, Provincia.class);
		
		List<Provincia> provincia = q.getResultList();
		
		em.getTransaction().begin();
		for (Provincia lEnLista : provincia) {
			em.remove(lEnLista);
		}
		em.getTransaction().commit();
		
		em.close();
		return provincia;
	}

}

