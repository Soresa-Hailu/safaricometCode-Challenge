package com.student.service.impl;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.response.ErrorMessage;
import com.student.dto.StudentDto;
import com.student.entity.StudentEntity;
import com.student.exception.StudentServiceException;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;


	@Override
	@Transactional
	public StudentDto createStudent(StudentDto studentDto) {
		// TODO Auto-generated method stub

		StudentDto returnVal = new  StudentDto();
		
		if (studentRepository.findStudentByEmail(studentDto.getEmail()) != null)
			throw new StudentServiceException("Record already exists");

		StudentEntity studentEntity = new StudentEntity();

		BeanUtils.copyProperties(studentDto, studentEntity);

		
		StudentEntity storedUserDetails = studentRepository.save(studentEntity);
    	BeanUtils.copyProperties(storedUserDetails, returnVal);

		return returnVal;
	}


	@Override
	public StudentDto getStudent(String email) {
		StudentEntity studentEntity = studentRepository.findStudentByEmail(email);
		if (studentEntity == null)
			throw new StudentServiceException(email);

		StudentDto returnValue = new StudentDto();
//		ModelMapper modelMapper = new ModelMapper();
//		returnValue = modelMapper.map(userEntity, UserDto.class);

		BeanUtils.copyProperties(studentEntity, returnValue);

		return returnValue;
	}

	@Override
	public StudentDto getStudentByStudentId(String studentId) {
		// TODO Auto-generated method stub

		StudentEntity studentEntity = studentRepository.findByStudentId(studentId);
		if (studentEntity == null)
			throw new StudentServiceException("User with ID: " + studentId + " not found");

		StudentDto returnValue = new StudentDto();
		BeanUtils.copyProperties(studentEntity, returnValue);
		return returnValue;
	}

	@Override
	public StudentDto updateStudent(String studentId, StudentDto student) {

		StudentDto returnVal = new StudentDto();
		StudentEntity studentEntity = studentRepository.findByStudentId(studentId);
		if (studentEntity == null)
			throw new StudentServiceException("Error");

		StudentEntity studentEmail = studentRepository.findStudentByEmail(student.getEmail());

		studentEmail.setEmail(student.getEmail());
		studentEmail.setFirstName(student.getFirstName());
		studentEmail.setLastName(student.getLastName());

		StudentEntity updatedStudentDetails = studentRepository.save(studentEntity);

		BeanUtils.copyProperties(updatedStudentDetails, returnVal);

		return returnVal;
	}

	@Override
	public void deleteStudent(String studentId) {

		StudentEntity studentEntity = studentRepository.findByStudentId(studentId);
		if (studentEntity == null)
			throw new StudentServiceException("Error");

		studentRepository.delete(studentEntity);

	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<StudentDto> getStudents(int page, int limit) {

		List<StudentDto> returnValue = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<StudentEntity> studentPage = studentRepository.findAll(pageableRequest);
		List<StudentEntity> students = studentPage.getContent();

		for(StudentEntity useEntity: students) {
			StudentDto userDto = new StudentDto();
			BeanUtils.copyProperties(useEntity, userDto);
			returnValue.add(userDto);
		}
		
		return returnValue;
	}
	
	
}
