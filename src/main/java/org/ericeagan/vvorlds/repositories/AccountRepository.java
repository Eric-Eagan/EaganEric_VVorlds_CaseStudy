package org.ericeagan.vvorlds.repositories;

import org.ericeagan.vvorlds.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository extended Repository for accessing Accounts in DB
 * 
 * @author Eric
 *
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
	/**
	 * Get Account with this id from DB
	 * @param id of an Account in DB
	 * @return Account associated with this id
	 */
	Account getById(Integer id);
}