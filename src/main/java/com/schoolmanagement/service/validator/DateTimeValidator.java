package com.schoolmanagement.service.validator;

import com.schoolmanagement.exception.BadRequestException;
import com.schoolmanagement.payload.messages.ErrorMessages;
import org.springframework.stereotype.Component;
import java.time.LocalTime;

@Component
public class DateTimeValidator {

    private boolean checkTime(LocalTime start, LocalTime stop) {
        return start.isAfter(stop) || start.equals(stop);
    }

    public void checkTimeWithException(LocalTime start, LocalTime stop) {
        if(checkTime(start, stop)) {
            throw new BadRequestException(ErrorMessages.TIME_NOT_VALID_MESSAGE);
        }
    }
}