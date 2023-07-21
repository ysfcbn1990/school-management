
package com.schoolmanagement.service.user;

import com.schoolmanagement.entity.concretes.user.Admin;
import com.schoolmanagement.entity.concretes.user.UserRole;
import com.schoolmanagement.entity.enums.RoleType;
import com.schoolmanagement.payload.mappers.AdminMapper;
import com.schoolmanagement.payload.request.AdminRequest;
import com.schoolmanagement.payload.response.AdminResponse;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.repository.user.AdminRepository;
import com.schoolmanagement.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final AdminMapper adminMapper;

    public ResponseMessage<AdminResponse> saveAdmin(AdminRequest adminRequest) {

        //!! Girilen username-ssn-phoneNumber unique mi kontrolü
        uniquePropertyValidator.checkDuplicate(adminRequest.getUsername(), adminRequest.getSsn(),
                adminRequest.getPhoneNumber());

        //!!! Dto - POJO
        Admin admin=adminMapper.mapAdminRequestToAdmin(adminRequest);


        admin.setBuilt_in(false);

        if(Objects.equals(adminRequest.getUsername(),"Admin")){
            admin.setBuilt_in(true);
        }

        //!! admin rolü veriliyor
       // admin.setUserRole();


    }
}