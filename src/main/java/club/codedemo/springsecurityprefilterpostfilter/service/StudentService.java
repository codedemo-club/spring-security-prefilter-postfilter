package club.codedemo.springsecurityprefilterpostfilter.service;

import club.codedemo.springsecurityprefilterpostfilter.entity.Student;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @PostFilter("filterObject.teacherName == authentication.principal.username")
    List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("zhangsan"));
        students.add(new Student("lisi"));
        students.add(new Student("wangwu"));
        return students;
    }

    @PostFilter("hasRole('TEACHER') or filterObject.teacherName == authentication.principal.username")
    List<Student> findAllWithRole() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("zhangsan"));
        students.add(new Student("lisi"));
        students.add(new Student("wangwu"));
        return students;
    }

    @PreFilter("hasRole('TEACHER') or filterObject.teacherName == authentication.principal.username")
    List<Student> save(List<Student> students) {
        System.out.println(students.size());
        return students;
    }
}
