package org.vvorlds.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vvorlds.models.Account;
import org.vvorlds.repositories.AccountRepository;
import org.vvorlds.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
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

}
