package com.schoolmanagement.controller.user;

import com.schoolmanagement.payload.request.DeanRequest;
import com.schoolmanagement.payload.response.DeanResponse;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.service.user.DeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dean")
public class DeanController {

    private final DeanService deanService;


    @PostMapping("/save")
public ResponseMessage<DeanResponse> saveDean(@Valid @RequestBody DeanRequest deanRequet){

        return deanService.save(deanRequet);

    }


}
