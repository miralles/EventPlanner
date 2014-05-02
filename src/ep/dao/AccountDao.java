package ep.dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ep.model.Account;


@Repository
public interface AccountDao {
	
	Collection<Account> findAllPersonnes();

	Account findPersonneByCode(String code);
	
	Account findPersonneByIdExt(String idext);

	void savePersonne(Account p);

	void deletePersonne(Account p);
	
}
