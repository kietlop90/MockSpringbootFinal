package com.duongam.demo.service;

import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.entities.Role;
import com.duongam.demo.entities.User;
import com.duongam.demo.entities.enums.ERole;
import com.duongam.demo.repositories.RoleRepository;
import com.duongam.demo.repositories.UserRepository;
import com.duongam.demo.service.template.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DResponseUser login(String userName, String password, HttpServletResponse response) {
		User user = userRepository.findByUsername(userName);
		if (user == null) {
			return null;
		}
		if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return null;
		}

		DResponseUser detailRespondUser = modelMapper.map(user, DResponseUser.class);
		detailRespondUser.setRole(user.roleName());

		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.roleName());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>(List.of(grantedAuthority));

		TokenAuthenticationService.addAuthentication(response, user.getUsername(), grantedAuthorities);
		String authorizationString = response.getHeader("Authorization");
		detailRespondUser.setToken(authorizationString);
		return detailRespondUser;
	}

	@Override
	public DResponseUser create(RegisterModel registerModel) {
//		Role role = roleRepository.findById(registerModel.getRoleId()).get();
		Role role = new Role(ERole.ADMIN);
		User user = new User();
		user.setUsername(registerModel.getUsername());
		user.onPrePersist();
		user.setRole(role);
		user.setPassword(bCryptPasswordEncoder.encode(registerModel.getPassword()));
		return modelMapper.map(userRepository.save(user), DResponseUser.class);
	}

	@Override
	public List<LResponseUser> getAll() {
        return userRepository.findAllBy();
    }
}
