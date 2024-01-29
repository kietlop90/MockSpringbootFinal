package com.duongam.demo.service;

import com.duongam.demo.dto.request.forcreate.CRequestClass;
import com.duongam.demo.dto.request.forupdate.URequestClass;
import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.entities.Class;
import com.duongam.demo.repositories.ClassRepository;
import com.duongam.demo.service.template.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements IClassService {
	
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<LResponseClass> getAll() {
        return classRepository.findAllBy();
    }

	@Override
	@Transactional
	public DResponseClass save(CRequestClass CRequestClass) {
		Class aClass = modelMapper.map(CRequestClass, Class.class);
		return modelMapper.map(classRepository.save(aClass), DResponseClass.class);
	}

	@Override
	@Transactional
	public DResponseClass update(URequestClass URequestClass) {
		Class aClass = modelMapper.map(URequestClass, Class.class);
		return modelMapper.map(classRepository.save(aClass), DResponseClass.class);
	}

	@Override
	@Transactional
	public DResponseClass findById(Long id) {
		Optional<Class> aClass = classRepository.findById(id);
		return aClass.map(value -> modelMapper.map(value, DResponseClass.class)).orElse(null);
	}

	@Override
	@Transactional
	public DResponseClass deleteById(Long id) {
		DResponseClass DResponseClass = findById(id);
		classRepository.deleteById(id);
		return DResponseClass;
	}
}
