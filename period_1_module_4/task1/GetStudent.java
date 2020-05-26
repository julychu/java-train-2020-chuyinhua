package task1;

import java.util.*;

/**
 * @author julychu
 * @date 2020/5/17
 * @desc 查询学生信息（30 姓名 31学号 32退出）
 */

public class GetStudent {
    private ActionType mode;
    private List<Student> students;
    Scanner scanner = new Scanner(System.in);

    /**
     * 查询学生信息
     * @param students
     * @return
     */
    public boolean get(List<Student> students) throws NumException{
        System.out.println("---请输入查询的模式【30 姓名 31 学号 32 退出】");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if(30 != mode && 31 != mode && 32 != mode) {
            System.out.println("【error】不存在该模式");
            return false;
        }

        // 退出
        if(32 == mode) {
            System.out.println("【hint】退出查询模式!");
            return true;
        }

        // 根据输入模式分别进行查询
        this.students = students;
        if(30 == mode) {
            this.mode = ActionType.TYPE_GET_NAME;
        } else {
            this.mode = ActionType.TYPE_GET_NUM;
        }
        if(ActionType.TYPE_GET_NAME == this.mode) {
            getByName();
        } else {
            getByNum();
        }
        return false;
    }

    /**
     * 根据名字查询
     * @return
     */
    private boolean getByName() {
        System.out.println("---请输入姓名");
        String name = scanner.next();
        if(!Util.checkName(name)) {
            return false;
        }

        List<Student> list = new LinkedList<>();
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if(s.getName().equals(name)) {
                list.add(s);
            }
        }
        if(0 == list.size()) {
            System.out.println("【hint】不存在该姓名的学生");
            return false;
        }
        System.out.println("【姓名为" + name +"的所有学生信息如下：】");
        System.out.println("学号     姓名    年龄");
        for(Student s : list) {
            System.out.println(s.getNum() + "   " + s.getName() + "    " + s.getAge());
        }
        return true;
    }

    /**
     * 根据学号查询
     * @return
     */
    private boolean getByNum() throws NumException{
        System.out.println("---请输入学号");
        int num = scanner.nextInt();
        if(!Util.checkNum(num)) {
            return false;
        }

        List<Student> list = new LinkedList<>();
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if(s.getNum() == num) {
                list.add(s);
            }
        }
        if(0 == list.size()) {
            System.out.println("【error】不存在该学号的学生");
            return false;
        }
        System.out.println("【学号为" + num +"的学生信息如下：】");
        System.out.println("学号     姓名    年龄");
        for(Student s : list) {
            System.out.println(s.getNum() + "   " + s.getName() + "    " + s.getAge());
        }
        return true;
    }
}