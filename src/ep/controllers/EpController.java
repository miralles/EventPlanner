package ep.controllers;

import ep.services.AccountManager;
import ep.services.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@Transactional
public class EpController
{
	@Autowired
	AccountManager personneManager;

	@RequestMapping("/accueil.htm")
	public ModelAndView home(Model model)
	{		
		if(CustomUserDetails.isUserConnected())
			model.addAttribute("loggedPersonne", personneManager.findPersonByIdExt(CustomUserDetails.getCurrentUserLogin()));
		return new ModelAndView("home");
	}
	
	@RequestMapping("/register.htm")
	public ModelAndView register()
	{
		return new ModelAndView("register");
	}

	@RequestMapping("/identification.htm")
	public ModelAndView login()
	{
		return new ModelAndView("login");
	}

	@RequestMapping("/identification-echec.htm")
	public ModelAndView loginFailure(Model model)
	{
		model.addAttribute("error", "true");
		return new ModelAndView("login");
	}

	@RequestMapping("/acces-refuse.htm")
	public ModelAndView accessDenied()
	{
		return new ModelAndView("accessDenied");
	}

}