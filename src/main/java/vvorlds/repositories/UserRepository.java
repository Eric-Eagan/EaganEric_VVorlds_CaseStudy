package vvorlds.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import vvorlds.models.User;

@Repository
public class UserRepository {
	public User findUserById(int id) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("VVorlds");
		EntityManager em = emFactory.createEntityManager();
		
		User user = em.find(User.class, id);
		
		em.close();
		emFactory.close();
		
		return user;
	}
	
	public User findUserByUsername(String uName) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("VVorlds");
		EntityManager em = emFactory.createEntityManager();
		
		TypedQuery<User> tq = em.createNamedQuery("FindUserByUsername", User.class);
		tq.setParameter("username", uName);
		List<User> rs = tq.getResultList();
		
		if (rs == null || rs.size() == 0) {
			return null;
		}
		
		em.close();
		emFactory.close();

		return rs.get(0);
	}
	
	public void createUser(User newUser) {
		EntityManagerFactory emFactory=Persistence.createEntityManagerFactory("VVorlds");  
		EntityManager em=emFactory.createEntityManager();  
		
		em.getTransaction().begin();
		em.persist(newUser);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close( );
	}
}
