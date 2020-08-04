package club.codedemo.springsecurityprefilterpostfilter.service;

import club.codedemo.springsecurityprefilterpostfilter.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    /**
     * 使用 zhangsan做为登录用户，则最终只返回班主任为zhangsan的学生
     */
    @WithMockUser("zhangsan")
    @Test
    void findAll() {
        List<Student> students = this.studentService.findAll();
        System.out.println(students);
        Assertions.assertEquals(1, students.size());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void findAllWithRoleWithStudent() {
        List<Student> students = this.studentService.findAllWithRole();
        System.out.println(students);
        Assertions.assertEquals(0, students.size());
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void findAllWithRoleWithTeacher() {
        List<Student> students = this.studentService.findAllWithRole();
        System.out.println(students);
        Assertions.assertEquals(3, students.size());
    }

    @Test
    @WithMockUser(roles = "STUDENT", username = "zhangsan")
    void findAllWithRoleWithStudentAndZhangsan() {
        List<Student> students = this.studentService.findAllWithRole();
        System.out.println(students);
        Assertions.assertEquals(1, students.size());
    }

    @Test
    @WithMockUser(roles = "TEACHER", username = "zhangsan")
    void findAllWithRoleWithTeacherAndZhangsan() {
        List<Student> students = this.studentService.findAllWithRole();
        System.out.println(students);
        Assertions.assertEquals(3, students.size());
    }

    @Test
    @WithMockUser("zhangsan")
    void save() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("zhangsan"));
        students.add(new Student("lisi"));
        students.add(new Student("wangwu"));

        Assertions.assertEquals(1, this.studentService.save(students));
    }

}