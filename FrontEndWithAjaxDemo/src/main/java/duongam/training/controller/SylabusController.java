package duongam.training.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.HttpSyllabus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/syllabus")
public class SylabusController {

    @Autowired
    private HttpSyllabus httpSyllabus;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String sortField,
                       @RequestParam(defaultValue = "desc") String dir) {
        PaginatedResponse<LResponseSyllabus> syllabusPage = httpSyllabus.getAll(page, size, sortField, dir);
        model.addAttribute("syllabusList", syllabusPage.getContent());
        model.addAttribute("totalPages", syllabusPage.getTotalPages());
        model.addAttribute("currentPage", syllabusPage.getCurrentPage());
        model.addAttribute("pageSize", syllabusPage.getSize());

        model.addAttribute("sortField", sortField);
        model.addAttribute("dir", dir);
        return "syllabus-list";
    }

    @GetMapping("/list-all")
    @JsonProperty("data")
    @ResponseBody
    public List<LResponseUser> listAll(Model model) {
        List<LResponseUser> test = httpSyllabus.();
        return httpUser.getTrainer();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        httpSyllabus.delete(id);
        return "redirect:/syllabus/list";
    }

    @GetMapping("/create")
    public String createSyllabus(Model model) {

        return "syllabus-create";
    }

    @GetMapping("/details")
    public String syllabusDetails(Model model) {

        return "syllabus-details";
    }
}
