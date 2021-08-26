package org.ericeagan.vvorlds.services;

import org.ericeagan.vvorlds.models.Account;

public interface AccountService {
	/**
	 * Persist Account to DB
	 * 
	 * @param acc Account to be persisted
	 * @return Account after being modified by being persisted
	 */
	Account save(Account acc);
	
	/**
	 * Get Account that has the given ID
	 * 
	 * @param id of Account in DB
	 * @return that Account
	 */
	Account getById(Integer id);
	
	/**
	 * Delete Account from the DB
	 * 
	 * @param acc Account to be deleted
	 */
	void deleteAccount(Account acc);
}
