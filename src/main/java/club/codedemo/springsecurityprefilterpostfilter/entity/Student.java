package club.codedemo.springsecurityprefilterpostfilter.entity;

public class Student {
    /**
     * 班主任姓名
     */
    private String teacherName;

    public Student(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
