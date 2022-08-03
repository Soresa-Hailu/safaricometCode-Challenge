package com.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.StudentEntity;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long>{

	StudentEntity findByStudentId(String studentId);

	StudentEntity findStudentByEmail(String email);

}