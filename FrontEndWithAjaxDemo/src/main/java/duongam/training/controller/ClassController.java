package duongam.training.controller;

import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forupdate.URequestClass;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.HttpClass;
import duongam.training.service.HttpTrainingProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.AttributedString;
import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private HttpClass httpClass;

    @Autowired
    private HttpTrainingProgram httpTrainingProgram;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String sortField,
                       @RequestParam(defaultValue = "desc") String dir,
                       @RequestParam(defaultValue = "")String keywords){
        PaginatedResponse<LResponseClass> lResponseClassPaginatedResponse = httpClass.getAll(page, size, sortField, dir, keywords);
        model.addAttribute("list", lResponseClassPaginatedResponse.getContent());
        model.addAttribute("totalPages", lResponseClassPaginatedResponse.getTotalPages());
        model.addAttribute("currentPage", lResponseClassPaginatedResponse.getCurrentPage());
        model.addAttribute("pageSize", lResponseClassPaginatedResponse.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("dir", dir);
        return "class-list";
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public DResponseClass detail(@PathVariable("id") Long requestId) {
        return httpClass.getById(requestId);
    }

    @GetMapping("/add")
    public String addForm(@RequestParam(defaultValue = "") String nameProgram, Model model) {
        model.addAttribute("aClass", new DResponseClass());
        return "class-create";
    }

    @PostMapping("/add")
    @ResponseBody
    public DResponseClass addDatabase(@ModelAttribute("request") CRequestClass request) {
        return httpClass.add(request);
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable("id") Long requestId) {
        DResponseClass aClass = httpClass.getById(requestId);
        aClass.formatData();
        model.addAttribute("aClass", aClass);
        return "class-create";
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

    @GetMapping("/getById/{id}")
    @ResponseBody
    public DResponseClass getById(@PathVariable("id") Long requestId) {
        return httpClass.getById(requestId);
    }
}