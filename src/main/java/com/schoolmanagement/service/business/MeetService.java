package com.schoolmanagement.service.business;

import com.schoolmanagement.repository.business.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetService {

    private final MeetRepository meetRepository;
}
