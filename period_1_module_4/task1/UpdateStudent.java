package task1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author julychu
 * @date 2020/5/17
 * @desc 修改学生信息（20 姓名 21学号 22退出）
 */

public class UpdateStudent {

    private ActionType updateMode;
    private List<Student> students;
    Scanner scanner = new Scanner(System.in);

    /**
     * 修改学生信息
     * @param students
     * @return
     */
    public boolean updataOver(List<Student> students) throws NumException, AgeException{
        System.out.println("---请输入修改的模式【20 姓名 21 学号 22 退出】");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if(20 != mode && 21 != mode && 22 != mode) {
            System.out.println("【error】不存在该模式");
            return false;
        }

        // 退出
        if(22 == mode) {
            System.out.println("【hint】退出修改模式!");
            return true;
        }

        // 根据输入模式分别进行修改
        this.students = students;
        if(20 == mode) {
            updateMode = ActionType.TYPE_UPDATE_NAME;
        } else {
            updateMode = ActionType.TYPE_UPDATE_NUM;
        }
        //修改
        update();
        return false;
    }

    /**
     * 根据学号、姓名修改
     * @return
     */
    private boolean update() throws AgeException, NumException {
        String name = "";
        int num = 0;
        if(ActionType.TYPE_UPDATE_NAME == updateMode) {
            System.out.println("---请输入姓名");
            name = scanner.next();
            if(!Util.checkName(name)) {
                return false;
            }
        } else {
            System.out.println("---请输入学号");
            num = scanner.nextInt();
            if(!Util.checkNum(num)) {
                return false;
            }
        }
        //查询学生
        List<Student> list = new LinkedList<>();
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if(ActionType.TYPE_UPDATE_NAME == updateMode) {
                if(s.getName().equals(name)) {
                    list.add(s);
                }
            } else if(s.getNum() == num) {
                list.add(s);
            }
        }
        if(0 == list.size()) {
            System.out.println("【hint】不存在该" + (ActionType.TYPE_UPDATE_NAME == updateMode ? "姓名" : "学号")  + "的学生");
            return false;
        }
        //修改后的姓名、年龄
        System.out.println("---请输入新的姓名  年龄");
        String newName = scanner.next();
        int age = scanner.nextInt();
        if(!Util.checkName(newName) || !Util.checkAge(age)) {
            return false;
        }
        for(Student s: list) {
            s.setName(newName);
            s.setAge(age);
        }
        System.out.println("【hint】修改成功" + list.size() + "名学生!");
        return true;
    }
}