package duongam.training.controller;

import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.service.HttpSyllabus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/syllabus")
public class SylabusController {

    @Autowired
    private HttpSyllabus httpSyllabus;

    @GetMapping("/list")
    public String list(Model model) {
        List<LResponseSyllabus> list = httpSyllabus.getAll();
        model.addAttribute("list", list);
        return "syllabus-list";
    }


}
