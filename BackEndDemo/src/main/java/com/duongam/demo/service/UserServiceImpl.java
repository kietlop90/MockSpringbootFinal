package com.duongam.demo.service;

import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.request.forcreate.CRequestUser;
import com.duongam.demo.dto.request.forupdate.URequestUser;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.entities.Role;
import com.duongam.demo.entities.User;
import com.duongam.demo.entities.enums.EGender;
import com.duongam.demo.entities.enums.ERole;
import com.duongam.demo.repositories.RoleRepository;
import com.duongam.demo.repositories.UserRepository;
import com.duongam.demo.service.template.IUserService;
import javassist.bytecode.DuplicateMemberException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    @Transactional
    public DResponseUser login(String userName, String password, HttpServletResponse response) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return null;
        }
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        DResponseUser detailRespondUser = modelMapper.map(user, DResponseUser.class);
        detailRespondUser.setRole(user.getRole().getName().name());

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getName().name());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(List.of(grantedAuthority));

        TokenAuthenticationService.addAuthentication(response, user.getUsername(), grantedAuthorities);
        String authorizationString = response.getHeader("Authorization");
        detailRespondUser.setToken(authorizationString);
        return detailRespondUser;
    }


    @Override
    @Transactional
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
    @Transactional
    public Page<LResponseUser> getAll(int page, int size, String sortField, String dir, String[] keywords) {
        Pageable pageable = null;
        if (sortField == null || sortField.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        } else {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString(dir), sortField);
        }
        if (keywords.length == 0) {
            return userRepository.findAllBy(pageable);
        } else {
            return userRepository.findAllByOneKeyword(keywords[keywords.length - 1], pageable).map(entity -> modelMapper.map(entity, LResponseUser.class));
        }
    }

    @Override
    public List<LResponseUser> getTrainer(Long idClass) {
        List<User> userList = userRepository.listTrainerForClass(idClass);
        return userList.stream().map(entity -> modelMapper.map(entity,LResponseUser.class)).collect(Collectors.toList());
    }

    @Override
    public List<LResponseUser> getAdmin(Long idClass) {
        List<User> userList = userRepository.listAdminForClass(idClass);
        return userList.stream().map(entity -> modelMapper.map(entity,LResponseUser.class)).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public DResponseUser save(CRequestUser cUser) throws DuplicateMemberException {
        if (userRepository.findByUsername(cUser.getUsername()) != null) {
            throw new DuplicateMemberException("EM04");
        } else {

        User user = new User();
        user.setName(cUser.getName());
        user.setUsername(cUser.getUsername());
        user.setEmail(cUser.getEmail());
        user.setPhone(cUser.getPhone());
        user.setStatus(cUser.getStatus());
        user.setPassword(bCryptPasswordEncoder.encode(cUser.getPassword()));
        Role role = roleRepository.findByName(ERole.valueOf(cUser.getRole())).orElse(null);
        user.setRole(role);
        String date = cUser.getDob();
        user.setDob(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        user.setGender(EGender.valueOf(cUser.getGender()));
        userRepository.save(user);
        return new DResponseUser(user);}
    }

    @Override
    @Transactional
    public DResponseUser update(URequestUser uUser) {
        User existingUser = userRepository.findById(uUser.getId()).orElse(null);
        String date = uUser.getDob();
        if (existingUser != null) {
            existingUser.setName(uUser.getName());
            existingUser.setPhone(uUser.getPhone());
            existingUser.setDob(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            existingUser.setGender(EGender.valueOf(uUser.getGender()));
            existingUser.setStatus(uUser.getStatus());
            Role role = roleRepository.findByName(ERole.valueOf(uUser.getRole())).orElse(null);
            existingUser.setRole(role);
            userRepository.save(existingUser);
            return new DResponseUser(existingUser);
        }
        return null;
    }

    @Override
    @Transactional
    public DResponseUser findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        return new DResponseUser(user);
    }

    @Override
    @Transactional
    public DResponseUser deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        DResponseUser dResponseUser = new DResponseUser(user);
        userRepository.deleteById(id);
        return dResponseUser;
    }


}