package duongam.training.controller;

import duongam.training.dto.form.LoginForm;
import duongam.training.dto.form.RegisterForm;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.service.HttpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    private HttpUser httpUser;

	@GetMapping("/list")
	public String list(Model model) {
		List<LResponseUser> list = httpUser.getAll();
		model.addAttribute("list", list);
		return "user-list";
	}

	@GetMapping("/register")
	public String registration(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}

	@PostMapping("/register")
	public String registration(@ModelAttribute("registerForm") @Valid RegisterForm registerForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
        httpUser.register(registerForm);
		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String getLogin(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute("loginModel") LoginForm loginForm) {
		DResponseUser dResponseUser = httpUser.login(loginForm);
		if(dResponseUser == null){
			return "redirect:/user/login";
		}
		return "redirect:/user/list";
	}
}