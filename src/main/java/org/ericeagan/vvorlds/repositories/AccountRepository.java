package org.ericeagan.vvorlds.repositories;

import org.ericeagan.vvorlds.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account getById(Integer id);
}
