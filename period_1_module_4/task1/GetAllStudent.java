package task1;

import java.util.List;

/**
 * @author julychu
 * @date 2020/5/17
 * @desc 查看所有学生信息
 */
public class GetAllStudent {

    /**
     * 查看所有学生信息
     * @param students
     */
    public void getAll(List<Student> students) {
        System.out.println("【所有学生的信息如下：】");
        if(students.isEmpty()) {
            System.out.println("暂无学生信息~");
            return;
        }
        System.out.println("学号     姓名    年龄");
        for(Student s : students) {
            System.out.println(s.getNum() + "   " + s.getName() + "    " + s.getAge());
        }
    }
}