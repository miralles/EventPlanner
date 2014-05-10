package ep.dao;

import ep.model.Account;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Collection<Account> findAllPersonnes() {
		Query query = em.createQuery("SELECT p FROM account p");
	    return (Collection<Account>) query.getResultList();
	}
	
	public Account findPersonneByCode(String code) {
		System.out.println("findPersonneByCode : " + code);
		return em.find(Account.class, code);
	}

	public Account findPersonneByIdExt(String idext) {
		Account personne;
		
		Query query = em.createQuery("SELECT e FROM account e WHERE login=?1").setParameter(1, idext);
		
		try
		{
			personne = (Account)query.getSingleResult();
			System.out.println("findPersonneByIdExt idext : " + idext + " log : " + personne.getLogin() + " mdp : " + personne.getPassword());
		}
		catch (NoResultException e)
		{
			System.out.println("Catch NoResultException by findPersonneByIdExt");
			return null;
		}

		return personne;
	}
	
	public void addAccount(Account account, String idext) {
		Account personne;
		boolean isExist = false;
		Query query = em.createQuery("SELECT e FROM account e WHERE login=?1").setParameter(1, idext);
		
		try
		{
			personne = (Account)query.getSingleResult();
			System.out.println("isExist ? log : " + personne.getLogin() + " mdp : " + personne.getPassword());
			isExist=true;
			
		}
		catch (NoResultException e)
		{
			
		}
		
		if(!isExist)
		{
			//TODO ; ajouter account 
		}
		
	}

	public void savePersonne(Account p) {
		em.merge(p);
		em.flush();
	}

	public void deletePersonne(Account p) {
		em.remove(p);
		em.flush();
	}
	
}
