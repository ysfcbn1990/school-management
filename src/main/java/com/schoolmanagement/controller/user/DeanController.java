package com.schoolmanagement.controller.user;

import com.schoolmanagement.payload.request.DeanRequest;
import com.schoolmanagement.payload.response.DeanResponse;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.service.user.DeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dean")
@PreAuthorize("hasAnyAuthority('ADMIN)")
public class DeanController {

    private final DeanService deanService;


    //Not: save() **************************************************************
    @PostMapping("/save")
public ResponseMessage<DeanResponse> saveDean(@Valid @RequestBody DeanRequest deanRequest){

        return deanService.save(deanRequest);

    }

    //Not: UpdateById() ***********************************************************
    @PutMapping("/update/{userId}") // http://localhost:8080/dean/update/1     + PUT
    public ResponseMessage<DeanResponse> update(@RequestBody @Valid DeanRequest deanRequest,@PathVariable Long userId){

        return deanService.update(deanRequest,userId);

    }

    // Not: Delete() ******************************************************************
    @DeleteMapping("/delete/{userId}") // http://localhost:8080/dean/delete/1  + DELETE
    public ResponseMessage<?> deleteDeanById(@PathVariable Long userId){

        return deanService.deleteDeanById(userId);
    }







}
