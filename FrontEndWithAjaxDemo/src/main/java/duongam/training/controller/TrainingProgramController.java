package duongam.training.controller;

import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forupdate.URequestClass;
import duongam.training.dto.request.forupdate.URequestTrainingProgram;
import duongam.training.dto.response.fordetail.DReponseTrainingProgram;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.service.HttpClass;
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
    public String list(Model model) {
        List<DReponseTrainingProgram> list = httpTrainingProgram.getAll();
        model.addAttribute("list", list);
        return "trainingProgram-list";
    }

    @GetMapping("/search")
    public String search(Model model,@RequestParam("name") String name) {
        if (name.isEmpty()) {
            List<DReponseTrainingProgram> list = httpTrainingProgram.getAll();
            model.addAttribute("list", list);
            return "trainingProgram-list";
        }
        List<DReponseTrainingProgram> list = httpTrainingProgram.searchByName(name);
        model.addAttribute("list", list);
        return "trainingProgram-list";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") String requestId,  Model model) {
        DReponseTrainingProgram dReponseTrainingProgram = httpTrainingProgram.getById(requestId);

        model.addAttribute("training", dReponseTrainingProgram);
        return "update-TrainingProGram";
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

    @GetMapping("/duplicate/{id}")
    public String duplicate(@PathVariable("id") String requestId) {
        httpTrainingProgram.duplicate(requestId);
        return "redirect:/trainingProgram/list";
    }

    @GetMapping("/deActive/{id}")
    public String deActive(@PathVariable("id") String requestId) {
        httpTrainingProgram.deActive(requestId);
        return "redirect:/trainingProgram/list";
    }
}