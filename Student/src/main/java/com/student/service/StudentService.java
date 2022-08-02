package com.student.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.student.dto.StudentDto;

public interface StudentService extends UserDetailsService {
	
	StudentDto createStudent(StudentDto studentDto);
	StudentDto getStudent(String email);
	StudentDto getStudentByStudentId(String studentId);
	StudentDto updateStudent(String Id, StudentDto studentDto);
	void deleteStudent(String studentId);
	List<StudentDto> getStudents(int page, int limit);
//	boolean verifyEmailToken(String token);
//	boolean requestPasswordReset(String email);
//	boolean passwordReset(String token, String password);

}
