package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forupdate.URequestUserPermission;
import com.duongam.demo.dto.response.fordetail.DReponseUserPermission;
import org.springframework.transaction.annotation.Transactional;

public interface IUserPemissionService {

    @Transactional
    DReponseUserPermission update(URequestUserPermission uRequestUserPermission);
}
