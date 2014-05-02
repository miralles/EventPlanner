package ep.services;

import ep.model.Account;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public interface AccountManager
{
	Collection<Account> findAllPersonnes();

	Account findPersonByCode(String code);
	
	Account findPersonByIdExt(String idext);

	void savePersonne(Account p);
	
	void deletePersonne(Account p);
	
	Collection<Account> findAllRespoROF();
	
}
