package task1;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author julychu
 * @date 2020/5/17
 * @desc 删除学生信息（10 姓名 11学号 12退出）
 */

public class DeleteStudent {
    private ActionType deleteMode;
    private List<Student> students;
    Scanner scanner = new Scanner(System.in);

    public boolean deleteOver(List<Student> students) throws NumException{
        System.out.println("---请输入删除的模式【10 姓名 11 学号 12 退出】");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if(10 != mode && 11 != mode && 12 != mode) {
            System.out.println("【error】不存在该模式");
            return false;
        }

        // 退出
        if(12 == mode) {
            System.out.println("【hint】退出删除模式!");
            return true;
        }

        // 根据输入模式分别进行删除
        this.students = students;
        if(10 == mode) {
            deleteMode = ActionType.TYPE_DELETE_NAME;
        } else {
            deleteMode = ActionType.TYPE_DELETE_NUM;
        }
        if(ActionType.TYPE_DELETE_NAME == deleteMode) {
            deleteName();
        } else {
            deleteNum();
        }
        return false;
    }

    /**
     * 根据名字删除
     * @return
     */
    private boolean deleteName() {
        System.out.println("---请输入姓名");
        String name = scanner.next();
        if(!Util.checkName(name)) {
            return false;
        }

        int count = 0;
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if(s.getName().equals(name)) {
                it.remove();
                count++;
            }
        }
        if(0 == count) {
            System.out.println("【error】不存在该姓名的学生");
            return false;
        }
        System.out.println("【hint】删除" + count + "名姓名为" + name + "的学生!");
        return true;
    }

    /**
     * 根据学号删除
     * @return
     */
    private boolean deleteNum() throws NumException {
        System.out.println("---请输入学号");
        int num = scanner.nextInt();
        if(!Util.checkNum(num)) {
            return false;
        }

        int count = 0;
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if(s.getNum() == num) {
                it.remove();
                count++;
            }
        }
        if(0 == count) {
            System.out.println("【error】不存在该学号的学生");
            return false;
        }
        System.out.println("【hint】删除" + count + "名学号为" + num + "的学生!");
        return true;
    }
}