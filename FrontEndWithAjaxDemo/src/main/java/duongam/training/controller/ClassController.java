package duongam.training.controller;

import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forupdate.URequestClass;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.service.HttpClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
	@Autowired
    private HttpClass httpClass;

	@GetMapping("/list")
	public String list(Model model) {
		List<LResponseClass> list = httpClass.getAll();
		model.addAttribute("list", list);
		return "class-list";
	}

	@GetMapping("/detail/{id}")
	@ResponseBody
	public DResponseClass detail(@PathVariable("id") Long requestId) {
		return httpClass.getById(requestId);
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		return "class-create";
	}

	@PostMapping("/add")
	@ResponseBody
	public DResponseClass addDatabase(@ModelAttribute("request") CRequestClass request) {
		return httpClass.add(request);
	}

	@GetMapping("/update/{id}")
	@ResponseBody
	public DResponseClass updateForm(@PathVariable("id") Long requestId) {
		return httpClass.getById(requestId);
	}

	@PostMapping("/update")
	@ResponseBody
	public DResponseClass updateDatabase(@ModelAttribute("request") URequestClass request) {
		return httpClass.update(request);
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public Long delete(@PathVariable("id") Long requestId) {
		httpClass.deleteById(requestId);
		return requestId;
	}
}