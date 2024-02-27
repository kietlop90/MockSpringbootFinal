package duongam.training.controller;

import duongam.training.dto.request.forupdate.URequestRole;
import duongam.training.dto.response.fordetail.DResponseRole;
import duongam.training.dto.response.forlist.LResponseRole;
import duongam.training.service.HttpRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private HttpRole httpRole;

	@GetMapping("/list")
	public String list(Model model){
		List<LResponseRole> list = httpRole.getAll();
		model.addAttribute("list", list);
		return "user-permission-list";
	}

	@PostMapping("/update")
	@ResponseBody
	public DResponseRole updateDatabase(URequestRole uRequestRole) {
		return httpRole.update(uRequestRole);
	}
}