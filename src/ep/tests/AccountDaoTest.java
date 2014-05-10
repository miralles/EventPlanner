package ep.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ep.dao.AccountDao;
import ep.model.Account;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/ep/tests/applicationContext-tests.xml"})
public class AccountDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private AccountDao personneDao;
	
	@Test
	public void findAllPersonnesTest(){
		assertEquals(1,personneDao.findAllPersonnes().size());
	}
			
	@Test
	public void savePersonneTest(){
		
		Account personne = new Account();
		
		personne.setCode("PER03");
		personne.setLogin("3");
		
		personneDao.savePersonne(personne);
		assertEquals(personne.getLogin(),personneDao.findPersonneByIdExt("3").getLogin());
	}
	
	@Test
	public void deletePersonneTest(){
		personneDao.deletePersonne(personneDao.findPersonneByIdExt("1"));
		assertEquals(1,personneDao.findAllPersonnes().size());
	}

}
