package duongam.training.controller;

import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.HttpSyllabus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/syllabus")
public class SylabusController {

    @Autowired
    private HttpSyllabus httpSyllabus;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        PaginatedResponse<LResponseSyllabus> syllabusPage = httpSyllabus.getAll(page, size);
        model.addAttribute("syllabusList", syllabusPage.getContent());
        model.addAttribute("totalPages", syllabusPage.getTotalPages());
        model.addAttribute("currentPage", syllabusPage.getCurrentPage());
        model.addAttribute("pageSize", syllabusPage.getSize());
        return "syllabus-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        httpSyllabus.delete(id);
        return "redirect:/syllabus/list";
    }
}
