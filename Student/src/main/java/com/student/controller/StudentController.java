package com.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.StudentDto;
import com.student.response.StudentDetailsRequestModel;
import com.student.response.StudentRest;
import com.student.service.StudentService;


@RestController
@RequestMapping("/students") // http://localhost:8080/students
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<StudentRest> geStudents(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		List<StudentRest> returnValue = new ArrayList<>();

		List<StudentDto> student = studentService.getStudents(page, limit);

		for (StudentDto studentDto : student) {
			StudentRest studentModel = new StudentRest();
			BeanUtils.copyProperties(studentDto, studentModel);
			returnValue.add(studentModel);
		}
		return returnValue;
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentRest getStudent(@PathVariable String id) {
		StudentRest returnValue = new StudentRest();

		StudentDto studentDto = studentService.getStudentByStudentId(id);
		BeanUtils.copyProperties(studentDto, returnValue);

		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentRest createUser(@RequestBody StudentDetailsRequestModel studentDetails) throws Exception {

		StudentRest returnValue = new StudentRest();

		if (studentDetails.getFirstName().isEmpty())
			throw new Exception("Error");

//		ModelMapper modelMapper = new ModelMapper();
//		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentDetails, studentDto);

		StudentDto createStudent = studentService.createStudent(studentDto);
	BeanUtils.copyProperties(createStudent, returnValue);

		return returnValue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public StudentRest updateUser(@PathVariable String id, @RequestBody StudentDetailsRequestModel studentDetails) {

		StudentRest returnValue = new StudentRest();

		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentDetails, studentDto);

		StudentDto updateStudent = studentService.updateStudent(id, studentDto);
		BeanUtils.copyProperties(updateStudent, returnValue);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteStudent(@PathVariable String id) {

		String returnValue = "Deleted successfully";

		studentService.deleteStudent(id);
		return returnValue;
	}
}