package ep.services;

import ep.dao.AccountDao;
import ep.model.Account;
import ep.model.Statut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountManagerImpl implements AccountManager
{
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public Collection<Account> findAllPersonnes()
	{
		return accountDao.findAllPersonnes();
	}

	@Override
	@Transactional
	public Account findPersonByIdExt(String idext)
	{
		return accountDao.findPersonneByIdExt(idext);
	}

	@Override
	@Transactional
	public void savePersonne(Account p)
	{
		p.setPassword(passwordEncoder.encodePassword(p.getPassword(), null));
		accountDao.savePersonne(p);
	}

	@Override
	@Transactional
	public void deletePersonne(Account p)
	{
		accountDao.deletePersonne(p);
	}
	
	@Override
	@Transactional
	public Account findPersonByCode(String code)
	{
		return accountDao.findPersonneByCode(code);
	}

	@Override
	@Transactional
	public Collection<Account> findAllRespoROF()
	{
		Collection<Account> result = new ArrayList<Account>();
		Collection<Account> personnes = this.findAllPersonnes();
		
		Account currentPersonne = new Account();
		
		for(Iterator<Account> it = personnes.iterator(); it.hasNext(); )
		{
			currentPersonne = it.next();
			
			if(currentPersonne.getStatuts().contains(Statut.ROLE_ROF))
				result.add(currentPersonne);
		}
		
		return result;
	}
}
