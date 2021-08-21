package org.ericeagan.vvorlds.services;

import org.ericeagan.vvorlds.models.Account;

public interface AccountService {
	Account save(Account acc);
	Account getById(Integer id);
	void deleteAccount(Account acc);
}
