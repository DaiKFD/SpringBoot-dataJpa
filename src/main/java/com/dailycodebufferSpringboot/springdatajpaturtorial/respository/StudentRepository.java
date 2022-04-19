package com.dailycodebufferSpringboot.springdatajpaturtorial.respository;

import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String guardianName);


    //JPQL
    @Query("SELECT  s from Student s WHERE s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("SELECT s.firstName from Student s WHERE s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);


    //Native
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);


    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName,String emailId);
}
