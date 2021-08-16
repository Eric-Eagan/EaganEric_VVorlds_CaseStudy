package org.vvorlds.services;

import org.vvorlds.models.Account;

public interface AccountService {
	Account save(Account acc);
	Account getById(Integer id);
}
