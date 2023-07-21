package com.schoolmanagement.controller.user;

import com.schoolmanagement.payload.request.AdminRequest;
import com.schoolmanagement.payload.response.AdminResponse;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.service.user.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Not : save() *************************************************************
    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<AdminResponse>> saveAdmin(@RequestBody @Valid
                                                                    AdminRequest adminRequest){
        return ResponseEntity.ok(adminService.saveAdmin(adminRequest));
    }



}