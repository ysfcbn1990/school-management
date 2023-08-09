package com.schoolmanagement.repository.business;

import com.schoolmanagement.entity.concretes.business.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<Meet, Long> {
}