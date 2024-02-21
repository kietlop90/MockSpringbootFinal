package duongam.training.controller;

import duongam.training.dto.form.LoginForm;
import duongam.training.dto.form.RegisterForm;
import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forcreate.CRequestUser;
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

    @PostMapping("/add")
    @ResponseBody
    public DResponseUser addDatabase(@ModelAttribute("request") CRequestUser request) {
        return httpUser.add(request);
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
        if (dResponseUser == null) {
            return "redirect:/user/login";
        }
        return "redirect:/user/list";
    }
}