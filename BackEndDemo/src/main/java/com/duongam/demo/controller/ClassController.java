package com.duongam.demo.controller;

import com.duongam.demo.dto.page.PaginatedResponse;
import com.duongam.demo.dto.request.forcreate.CRequestClass;
import com.duongam.demo.dto.request.forupdate.URequestClass;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.service.template.IClassService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Class", description = "User API's")
@RestController
@RequestMapping("/class")
public class ClassController {
	@Autowired
	private IClassService classService;


	@GetMapping("/list")
	public ResponseEntity<PaginatedResponse<LResponseClass>> list(@RequestParam(defaultValue = "0") int page,
																 @RequestParam(defaultValue = "10") int size,
																 @RequestParam(required = false) String sortField,
																 @RequestParam(defaultValue = "desc") String dir,
																 @RequestParam(defaultValue = "") String keywords) {
		String[] keywordArray = (keywords != null && !keywords.isEmpty()) ? keywords.split(",") : new String[]{};
		Page<LResponseClass> responseUsers = classService.getAll(page, size, sortField, dir, keywordArray);
		PaginatedResponse<LResponseClass> paginatedResponse = new PaginatedResponse<>();
		paginatedResponse.setContent(responseUsers.getContent());
		paginatedResponse.setTotalPages(responseUsers.getTotalPages());
		paginatedResponse.setTotalElements(responseUsers.getTotalElements());
		paginatedResponse.setCurrentPage(responseUsers.getNumber());
		paginatedResponse.setSize(responseUsers.getSize());
		return ResponseEntity.ok().body(paginatedResponse);
	}

	@PostMapping("/add")
	public ResponseEntity<DResponseClass> add(@Valid @RequestBody CRequestClass cRequestClass, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(null);
		}
		DResponseClass dResponseClass = classService.save(cRequestClass);
		return ResponseEntity.ok().body(dResponseClass);
	}

	@PutMapping("/update")
	public ResponseEntity<DResponseClass> update(@Valid @RequestBody URequestClass uRequestClass, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(null);
		}
		DResponseClass dResponseClass = classService.update(uRequestClass);
		return ResponseEntity.ok().body(dResponseClass);
	}

	@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<DResponseClass> getById(@PathVariable(value = "id") Long id) {
		DResponseClass aClass = classService.findById(id);
		return ResponseEntity.ok().body(aClass);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DResponseClass> delete(@PathVariable("id") Long classId) {
		DResponseClass dResponseClass = classService.deleteById(classId);
		return ResponseEntity.ok().body(dResponseClass);
	}
}
