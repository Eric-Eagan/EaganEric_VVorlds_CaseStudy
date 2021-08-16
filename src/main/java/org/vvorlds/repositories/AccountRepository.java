package org.vvorlds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vvorlds.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account getById(Integer id);
}
