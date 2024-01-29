package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.request.forcreate.CRequestClass;
import com.duongam.demo.dto.request.forupdate.URequestClass;
import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IClassService {
	List<LResponseClass> getAll();

	DResponseClass save(CRequestClass CRequestClass);

	DResponseClass update(URequestClass URequestClass);

	DResponseClass findById(Long id);

	DResponseClass deleteById(Long id);
}
