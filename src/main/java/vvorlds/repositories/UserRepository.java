package vvorlds.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import vvorlds.models.User;

@Repository
public class UserRepository {
	public User findEmployeeById(int id) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("VVorlds");
		EntityManager em = emFactory.createEntityManager();
		
		User user = em.find(User.class, id);
		
		em.close();
		emFactory.close();
		
		return user;
	}
}
