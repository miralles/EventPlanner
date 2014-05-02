package ep.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ep.model.Account;
import ep.model.Statut;



/**
 * Classe permettant la convertion de la classe Personne en UserDetails afin de pouvoir être traitée par le module Spring security
 * @author Alexandre Monties
 * @version 1.0
 */
@SuppressWarnings("deprecation")
@Service("userDetailsService")
public class CustomUserDetails implements UserDetailsService
{
	/**
	 * Service permettant l'intéraction avec la couche Service.
	 */
	@Autowired
	private AccountManager personneManager;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException
	{
		Account userToLoad = personneManager.findPersonByIdExt(arg0);
		
		if(userToLoad == null)
			throw new UsernameNotFoundException("The user " + arg0 + " has not been found.");
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		Iterator<Statut> it = userToLoad.getStatuts().iterator();
		
		while(it.hasNext())
		{
			authorities.add(new GrantedAuthorityImpl(it.next().toString()));
		}

		User userConverted = new User(userToLoad.getLogin(), userToLoad.getPassword(),authorities);
		
		System.out.println("userToLoad log : " + userToLoad.getLogin());
		System.out.println("userToLoad mdp : " + userToLoad.getPassword());
		
		return userConverted;
	}
	
	/**
	 * @return le nom d'utilisateur de la personne connectée
	 */
	public static String getCurrentUserLogin()
	{
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public static boolean isUserConnected()
	{
		return !getCurrentUserLogin().equals("anonymousUser");
	}
	
	/**
	 * @return true si l'utilisateur connecté possède les droits d'administrateur, false sinon
	 */
	@SuppressWarnings("unchecked")
	public static boolean isCurrentUserAdmin()
	{
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		Iterator<SimpleGrantedAuthority> iterator = authorities.iterator();
		
		while(iterator.hasNext())
		{
			if(iterator.next().getAuthority().equals("ROLE_ADMIN"))
				return true;
		}
			
		return false;
	}
}