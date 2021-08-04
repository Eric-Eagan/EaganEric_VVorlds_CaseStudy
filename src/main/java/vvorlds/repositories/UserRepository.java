package vvorlds.repositories;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import vvorlds.models.Account;
import vvorlds.models.User;

@Repository
public class UserRepository {
	
	private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("VVorlds");
	
	public User findUserById(int id) {
		EntityManager em = emFactory.createEntityManager();
		
		User user = em.find(User.class, id);
		
		em.close();
		
		return user;
	}
	
	public User findUserByUsername(String uName) {
		EntityManager em = emFactory.createEntityManager();
		
		TypedQuery<User> tq = em.createNamedQuery("FindUserByUsername", User.class);
		tq.setParameter("username", uName);
		List<User> rs = tq.getResultList();
		
		if (rs == null || rs.size() == 0) {
			return null;
		}
		
		em.close();

		return rs.get(0);
	}
	
	public void createUser(User newUser, Account newAcc) {
		EntityManager em=emFactory.createEntityManager();  
		
		em.getTransaction().begin();
		em.persist(newUser);
		em.persist(newUser);
		em.getTransaction().commit();
		
		em.close();
	}
}
