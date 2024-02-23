package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forcreate.CRequestUserPermission;
import com.duongam.demo.dto.request.forupdate.URequestUserPermission;
import com.duongam.demo.dto.request.forupdate.UResponseRole;
import com.duongam.demo.dto.response.fordetail.DReponseUserPermission;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.forlist.LRequestUserPermission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserPemissionService {

    @Transactional
    DReponseUserPermission update(URequestUserPermission uRequestUserPermission);
}
