package task1;

import java.util.List;
import java.util.Scanner;

/**
 * @author julychu
 * @date 2020/5/16
 * @desc 添加学生信息
 */

public class AddStudent {
    /**
     * 检查并添加学生信息
     * @param students
     * @return
     */
    public boolean add(List<Student> students) throws AgeException, NumException{
        System.out.println("---请输入学生信息,并回车。格式如： 1 july 18。");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String name = scanner.next();
        int age = scanner.nextInt();
        // 检查输入值是否合理
        if(!Util.checkNum(num)) {

        }
        if(!Util.checkeAll(num, name, age)) {
            return false;
        }
        // 检查是否存在学号
        boolean existNum = false;
        for(Student s: students) {
            if(s.getNum() == num) {
                existNum = true;
                break;
            }
        }
        if(existNum) {
            System.out.println("【error】学号已经存在。请输入不重复的学号!");
            return false;
        }
        // 添加学生
        Student student = new Student(num, name , age);
        students.add(student);
        System.out.println("【hint】添加学生信息成功：" + student.toString());
        return true;
    }
}
