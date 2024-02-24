package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.request.forcreate.CRequestUser;
import com.duongam.demo.dto.request.forupdate.URequestUser;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService {
	DResponseUser login(String username, String password, HttpServletResponse response);

	DResponseUser create(RegisterModel registerModel);

	Page<LResponseUser> getAll(int page, int size, String sort, String dir, String[] keywords);

	List<LResponseUser> getTrainer(Long idClass);

	List<LResponseUser> getAdmin(Long idClass);

	DResponseUser save(CRequestUser cUser) throws DuplicateMemberException;

	DResponseUser update(URequestUser uUser);

	DResponseUser findById(Long id);

	DResponseUser deleteById(Long id);


}
