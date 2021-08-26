package org.ericeagan.vvorlds.services.impl;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.repositories.AccountRepository;
import org.ericeagan.vvorlds.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of Account Service
 * 
 * @author Eric
 *
 */
@Service
public class AccountServiceImpl implements AccountService {
	/**
	 * Repository for accessing DB
	 */
	private AccountRepository accountRepository;
	
	/**
	 * Autowired constructor for injecting Account Repository
	 * 
	 * @param accountRepository
	 */
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account save(Account acc) {
		return accountRepository.save(acc);
	}

	@Override
	public Account getById(Integer id) {
		return accountRepository.getById(id);
	}

	@Override
	public void deleteAccount(Account acc) {
		accountRepository.delete(acc);
	}

}
