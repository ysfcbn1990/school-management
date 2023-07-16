package com.schoolmanagement.contactmessage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactMessages")
@RequiredArgsConstructor
public class ContactMessgeController {
    private  final ContactMessageService contactMessageService;
}
