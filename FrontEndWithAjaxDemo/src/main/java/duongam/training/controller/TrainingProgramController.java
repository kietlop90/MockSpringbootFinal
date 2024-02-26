package duongam.training.controller;

import duongam.training.dto.request.forcreate.CRequestTrainingProgram;
import duongam.training.dto.request.forupdate.URequestTrainingProgram;
import duongam.training.dto.response.fordetail.DReponseTrainingProgram;
import duongam.training.dto.response.fordetail.DReponseTrainingUnit;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.HttpTrainingProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trainingProgram")
public class TrainingProgramController {
    @Autowired
    private HttpTrainingProgram httpTrainingProgram;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String sortField,
                       @RequestParam(defaultValue = "desc") String dir) {

        PaginatedResponse<DReponseTrainingProgram> trainingProgramPaginatedResponse = httpTrainingProgram.getAll(page, size, sortField, dir);
        model.addAttribute("totalPages", trainingProgramPaginatedResponse.getTotalPages());
        model.addAttribute("currentPage", trainingProgramPaginatedResponse.getCurrentPage());
        model.addAttribute("pageSize", trainingProgramPaginatedResponse.getSize());

        model.addAttribute("sortField", sortField);
        model.addAttribute("dir", dir);
        model.addAttribute("list",trainingProgramPaginatedResponse.getContent());

        List<String> listTagSearch = httpTrainingProgram.getAllSearchTag();
        model.addAttribute("listTagSearch", listTagSearch);
        return "training-program-list";
    }

    @GetMapping("/search")
    public String search(Model model,@RequestParam("name") String name) {
        if (name.isEmpty()) {
            List<DReponseTrainingProgram> list = httpTrainingProgram.searchByName("asdfghjkl");
            model.addAttribute("totalPages", 1);
            model.addAttribute("currentPage", 10);
            model.addAttribute("pageSize", 10);
            model.addAttribute("sortField", "sortField");
            model.addAttribute("dir", "dir");
            List<String> listTagSearch = httpTrainingProgram.getAllSearchTag();
            model.addAttribute("listTagSearch", listTagSearch);
            model.addAttribute("list", list);
            return "training-program-list";
        }

        List<DReponseTrainingProgram> list = httpTrainingProgram.searchByName(name);
        model.addAttribute("totalPages", 1);
        model.addAttribute("currentPage", 10);
        model.addAttribute("pageSize", 10);
        model.addAttribute("sortField", "sortField");
        model.addAttribute("dir", "dir");
        List<String> listTagSearch = httpTrainingProgram.getAllSearchTag();
        model.addAttribute("listTagSearch", listTagSearch);
        model.addAttribute("list", list);
        return "training-program-list";
    }

    @GetMapping("/deleteTagSearch/{tag}")
    public String deleteTagSearch(Model model,@PathVariable("tag") String tagName) {
        httpTrainingProgram.deleteSearchTag(tagName);
        List<DReponseTrainingProgram> list = httpTrainingProgram.searchByName("asdfghjkl");
        model.addAttribute("totalPages", 1);
        model.addAttribute("currentPage", 10);
        model.addAttribute("pageSize", 10);
        model.addAttribute("sortField", "sortField");
        model.addAttribute("dir", "dir");
        List<String> listTagSearch = httpTrainingProgram.getAllSearchTag();
        model.addAttribute("listTagSearch", listTagSearch);
        model.addAttribute("list", list);
        return "training-program-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("request", new CRequestTrainingProgram());
        return "training-program-create";
    }

    @PostMapping("/add")
    @ResponseBody
    public DReponseTrainingProgram addDatabase(@ModelAttribute("request") CRequestTrainingProgram request) {
        return httpTrainingProgram.add(request);
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") String requestId,  Model model) {
        DReponseTrainingProgram dReponseTrainingProgram = httpTrainingProgram.getById(requestId);

        model.addAttribute("training", dReponseTrainingProgram);
        return "training-program-update";
    }

    @PostMapping("/update")
    public String updateDatabase(@ModelAttribute("training") URequestTrainingProgram uRequestTrainingProgram) {
        httpTrainingProgram.update(uRequestTrainingProgram);
        return "redirect:/trainingProgram/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String requestId) {
        httpTrainingProgram.deleteById(requestId);
        return "redirect:/trainingProgram/list";
    }


    @GetMapping("/deleteSearchTag/{id}")
    public String deleteSearchTag(@PathVariable String id) {
        httpTrainingProgram.deleteSearchTag(id);
        return "redirect:/trainingProgram/list";
    }

    @GetMapping("/duplicate/{id}")
    public String duplicate(@PathVariable("id") String requestId) {
        httpTrainingProgram.duplicate(requestId);
        return "redirect:/trainingProgram/list";
    }

    @GetMapping("/getAllTrainingUnit/{code}")
    public String getAllTrainingUnit(@PathVariable String code, Model model) {
        DResponseSyllabus dResponseSyllabusList = httpTrainingProgram.getOneSyllabus(code);
        List<DReponseTrainingUnit> dReponseTrainingUnitList = httpTrainingProgram.getALlTrainingUnit(code);
        model.addAttribute("sylabus", dResponseSyllabusList );
        model.addAttribute("listTrainingUnit", dReponseTrainingUnitList);
        return "training-content-unit-detail";
    }

    @GetMapping("/deActive/{id}")
    public String deActive(@PathVariable("id") String requestId) {
        httpTrainingProgram.deActive(requestId);
        return "redirect:/trainingProgram/list";
    }

    @GetMapping("/getDetailTrainingProgram/{id}")
    public String getDetailTraining(@PathVariable("id") String requestId, Model model) {
        List<DResponseClass> dResponseClassList = httpTrainingProgram.getALlClassTraining(requestId);
        List<DResponseSyllabus> dResponseSyllabusList = httpTrainingProgram.getALlSyllabusTraining(requestId);
        model.addAttribute("classList",dResponseClassList );
        model.addAttribute("sylabusList", dResponseSyllabusList );
        return "training-program-detail";
    }

    @GetMapping("/list-name/{keywords}")
    @ResponseBody
    public List<DReponseTrainingProgram> getProgramForClass(@PathVariable("keywords") String keywords){
        return httpTrainingProgram.searchByNameForClass(keywords);
    }
}
