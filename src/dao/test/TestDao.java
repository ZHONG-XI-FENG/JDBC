package dao.test;
/*
@projectName JDBC
@author Joe See Feng
@create 05/09/2021-4:33 PM
@description 
*/

import dao.dao.StudentDao;
import dao.domain.Student;
import org.junit.Test;

import java.util.List;

public class TestDao {
    @Test
    public void testStudentDaoMulti(){
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.queryMulti("select * from student where id >= ?", Student.class, 1);
        for(Student student:students){
            System.out.println(student);
            System.out.println("git commit test");
            System.out.println("git new branch 1 test");
            System.out.println("git new branch 1 2nd test collision");
        }
    }

    @Test
    public void testStudentDaoSingle(){
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.querySingle("select * from student where id = ?", Student.class, 1);
        System.out.println(student);
    }

    @Test
    public void testStudentDaoScalar(){
        StudentDao studentDao = new StudentDao();
        Object o = studentDao.queryScalar("select name from student where id = ?", 1);
        System.out.println(o);
    }

    @Test
    public void testStudentDaoUpdate(){
        StudentDao studentDao = new StudentDao();
        int update = studentDao.update("insert into student values(null,?,?,?)", "Xiaoming", "男", 18);
        System.out.println(update);
    }
}
