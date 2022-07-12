package thanhlcpd04359.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import thanhlcpd04359.domain.Account;
import thanhlcpd04359.model.AdminLoginDto;
import thanhlcpd04359.service.AccountService;




@Controller
public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		return "/admin/accounts/loginnn";
	}
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model,
			@Valid @ModelAttribute("account") AdminLoginDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/accounts/loginnn", model);
		}
		System.out.println("c");
		System.out.println(dto.getPassword());
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		
		if (account == null) {
			System.out.println("cc");
			model.addAttribute("message", "Invalid username or password");
			return new ModelAndView("/admin/accounts/loginnn", model);
		}
		System.out.println("ccc");
		session.setAttribute("username", account.getUsername());

		
		Object ruri = session.getAttribute("redirect-uri");
		
		if(ruri != null) {
			System.out.println("cccc");
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		System.out.println("ccccc");
		return new ModelAndView("admin/web/index", model);
	}
	
	
}
