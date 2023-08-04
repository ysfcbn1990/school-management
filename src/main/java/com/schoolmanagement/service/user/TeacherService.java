package com.schoolmanagement.service.user;

import com.schoolmanagement.entity.concretes.business.LessonProgram;
import com.schoolmanagement.entity.concretes.user.Teacher;
import com.schoolmanagement.entity.enums.RoleType;
import com.schoolmanagement.exception.ResourceNotFoundException;
import com.schoolmanagement.payload.mappers.TeacherMapper;
import com.schoolmanagement.payload.messages.ErrorMessages;
import com.schoolmanagement.payload.messages.SuccessMessages;
import com.schoolmanagement.payload.request.TeacherRequest;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.payload.response.TeacherResponse;
import com.schoolmanagement.repository.user.TeacherRepository;
import com.schoolmanagement.service.business.LessonProgramService;
import com.schoolmanagement.service.helper.PageableHelper;
import com.schoolmanagement.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LessonProgramService lessonProgramService;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final TeacherMapper teacherMapper;
    private final  UserRoleService userRoleService ;
    private final PasswordEncoder passwordEncoder;
    private final PageableHelper pageableHelper;

    // Not :  Save() *********************************************************
    public ResponseMessage<TeacherResponse> saveTeacher(TeacherRequest teacherRequest) {

        // !!! lessonProgram
        Set<LessonProgram> lessonProgramSet = lessonProgramService.getLessonProgramById(teacherRequest.getLessonsIdList());
        // !!! unique kontrolu
        uniquePropertyValidator.checkDuplicate(teacherRequest.getUsername(), teacherRequest.getSsn(),
                teacherRequest.getPhoneNumber(), teacherRequest.getEmail());
        // !!! DTO --> POJO
        Teacher teacher = teacherMapper.mapTeacherRequestToTeacher(teacherRequest);
        // !!! POJO da olmasi gerekip de DTO da olmayan verileri setliyoruz
        teacher.setUserRole(userRoleService.getUserRole(RoleType.TEACHER));
        teacher.setLessonsProgramList(lessonProgramSet);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        Teacher savedTeacher =  teacherRepository.save(teacher);

        // TODO : AdvisorTeacher Servise yazilinca eklenecek

        return ResponseMessage.<TeacherResponse>builder()
                .message(SuccessMessages.TEACHER_SAVE)
                .httpStatus(HttpStatus.CREATED)
                .object(teacherMapper.mapTeacherToTeacherResponse(savedTeacher))
                .build();


    }


    // Not : getAll() ***********************************************************************
    public List<TeacherResponse> getAllTeacher() {
        return  teacherRepository.findAll()
                .stream()
                .map(teacherMapper::mapTeacherToTeacherResponse)
                .collect(Collectors.toList());
    }

    // Not : getByName() ***********************************************************************
    public List<TeacherResponse> getTeacherByName(String teacherName) {

        //TODO boş list ile denenecek
        return teacherRepository.getTeachersByNameContaining(teacherName) // List<Teacher>
                .stream()
                .map(teacherMapper::mapTeacherToTeacherResponse)
                .collect(Collectors.toList());
    }

    // Not : Delete() ************************************************************************
    public ResponseMessage deleteTeacherById(Long id) {

        isTeacherExist(id);
        teacherRepository.deleteById(id);

        return ResponseMessage.builder()
                .message(SuccessMessages.TEACHER_DELETE)
                .httpStatus(HttpStatus.OK)
                .build();

    }
    private Teacher isTeacherExist(Long id){

        return teacherRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, id)));
    }

    // Not : getById() **********************************************************************
    public ResponseMessage<TeacherResponse> getTeacherById(Long id) {

        return ResponseMessage.<TeacherResponse>builder()
                .object(teacherMapper.mapTeacherToTeacherResponse(isTeacherExist(id)))
                .message(SuccessMessages.TEACHER_FOUND)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    // Not: getAllWithPage ***********************************************************
    public Page<TeacherResponse> getAllTeacherByPage(int page, int size, String sort, String type) {
        Pageable pageable =  pageableHelper.getPageableWithProperties(page, size, sort, type);
        return teacherRepository.findAll(pageable).map(teacherMapper::mapTeacherToTeacherResponse);
    }

    // Not: Update() ***************************************************************************
    public ResponseMessage<TeacherResponse> updateTeacher(TeacherRequest teacherRequest, Long userId) {
        //!!! id kontrol
        Teacher teacher = isTeacherExist(userId);
        //!!! LessonProgram
        Set<LessonProgram> lessonPrograms =  lessonProgramService.getLessonProgramById(teacherRequest.getLessonsIdList());
        // !!! unique kontrol
        uniquePropertyValidator.checkUniqueProperties(teacher, teacherRequest);
        // !!! DTO --> POJO
        Teacher updatedTeacher = teacherMapper.mapTeacherRequestToUpdatedTeacher(teacherRequest,userId);
        // !!! eksik datalar setleniyor
        updatedTeacher.setPassword(passwordEncoder.encode(teacherRequest.getPassword()));
        updatedTeacher.setLessonsProgramList(lessonPrograms);
        updatedTeacher.setUserRole(userRoleService.getUserRole(RoleType.TEACHER));

        Teacher savedTeacher = teacherRepository.save(updatedTeacher);

        // TODO : Advisory Teacher

        return ResponseMessage.<TeacherResponse>builder()
                .message(SuccessMessages.TEACHER_UPDATE)
                .httpStatus(HttpStatus.OK)
                .object(teacherMapper.mapTeacherToTeacherResponse(savedTeacher))
                .build();
    }
}