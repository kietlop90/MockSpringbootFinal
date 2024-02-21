package duongam.training.controller;

import duongam.training.dto.form.LoginForm;
import duongam.training.dto.form.RegisterForm;
import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forcreate.CRequestUser;
import duongam.training.dto.request.forupdate.URequestUser;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.HttpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private HttpUser httpUser;

	@GetMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "0") int page,
					   @RequestParam(defaultValue = "10") int size,
					   @RequestParam(required = false) String sortField,
					   @RequestParam(defaultValue = "desc") String dir,
					   @RequestParam(defaultValue = "")String keywords){
		PaginatedResponse<LResponseUser> lResponseUsers = httpUser.getAll(page, size, sortField, dir, keywords);
		model.addAttribute("userList", lResponseUsers.getContent());
		model.addAttribute("totalPages", lResponseUsers.getTotalPages());
		model.addAttribute("currentPage", lResponseUsers.getCurrentPage());
		model.addAttribute("pageSize", lResponseUsers.getSize());
		model.addAttribute("sortField", sortField);
		model.addAttribute("dir", dir);
		return "user-list";
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
	@ResponseBody
	public DResponseUser postLogin(@ModelAttribute("loginModel") LoginForm loginForm) {
        return httpUser.login(loginForm);
	}

	@PostMapping("/add")
	@ResponseBody
	public DResponseUser addDatabase(@ModelAttribute("user") CRequestUser request) {
		return httpUser.add(request);
	}

	@PostMapping("/update")
	@ResponseBody
	public DResponseUser update(@ModelAttribute("user") URequestUser request) {
		return httpUser.update(request);
	}

	@GetMapping("/getById/{id}")
	@ResponseBody
	public DResponseUser getById(@PathVariable("id") Long requestId) {
		return httpUser.getById(requestId);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long requestId) {
		httpUser.deleteById(requestId);
	}

//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable("id") Long requestId){
//		httpUser.deleteById(requestId);
//		return "redirect:/user/list";
//	}
//
//	@GetMapping("/update/{id}")
//	public String updateForm(Model model, @PathVariable("id") Long requestId){
//		DResponseUser dResponseUser = httpUser.getById(requestId);
//		model.addAttribute("existingUser", dResponseUser);
//		return "user-update";
//	}
}