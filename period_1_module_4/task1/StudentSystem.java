package task1;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author julychu
 * @date 2020/5/16
 * @desc 学生信息管理系统
 */

public class StudentSystem {
    String path = "/data/students.txt";
    // 存储所有学生信息
    List<Student> students = new LinkedList<>();
    ActionType firstMenu;
    ActionType secondMenu;
    AddStudent addStudent;
    DeleteStudent deleteStudent;
    UpdateStudent updateStudent;
    GetStudent getStudent;
    GetAllStudent getAllStudent;

    /**
     * 选择一级菜单
     */
    public void chooseFirstMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("---请输入对应的主菜单数字[0-增, 1-删, 2-改, 3-查, 4-遍历, 5-退出]进行操作:");
        Scanner scanner = new Scanner(System.in);
        int fistI = scanner.nextInt();

        if (fistI < 0 || fistI > 5) {
            System.out.println("---请输入正确的菜单数字[0-增, 1-删, 2-改, 3-查, 4-遍历, 5-退出]");
            chooseFirstMenu();
            return;
        }

        switch (fistI) {
            case 0: //增加
                firstMenu = ActionType.TYPE_ADD;
                if (null == addStudent) {
                    addStudent = new AddStudent();
                }
                loopAction();
                break;
            case 1: // 删除
                firstMenu = ActionType.TYPE_DEL;
                if (null == deleteStudent) {
                    deleteStudent = new DeleteStudent();
                }
                loopAction();
                break;
            case 2: // 更新
                firstMenu = ActionType.TYPE_UPDATE;
                if (null == updateStudent) {
                    updateStudent = new UpdateStudent();
                }
                loopAction();
                break;
            case 3: //查看单个
                firstMenu = ActionType.TYPE_GET;
                if (null == getStudent) {
                    getStudent = new GetStudent();
                }
                loopAction();
                break;
            case 4: //查看所有
                firstMenu = ActionType.TYPE_GET_ALL;
                if (null == getAllStudent) {
                    getAllStudent = new GetAllStudent();
                }
                getAllStudent.getAll(students);
                chooseFirstMenu();
                break;
            case 5: //退出
                saveInfo();
                System.out.println("【退出系统】");
                break;
            default:
                break;
        }
    }

    /**
     * 循环执行操作
     */
    private void loopAction() {
        boolean isOver = false;
        do {
            try {
                if (ActionType.TYPE_ADD == firstMenu) {
                    isOver = addStudent.add(students);
                } else if (ActionType.TYPE_DEL == firstMenu) {
                    isOver = deleteStudent.deleteOver(students);
                } else if (ActionType.TYPE_UPDATE == firstMenu) {
                    isOver = updateStudent.updataOver(students);
                } else if (ActionType.TYPE_GET == firstMenu) {
                    isOver = getStudent.get(students);
                }
            } catch (AgeException | NumException e) {
                e.printStackTrace();
            }
            if (isOver) {
                chooseFirstMenu();
                break;
            }

        } while (!isOver);

    }

    /**
     * 保存学生信息到文件
     */
    private void saveInfo() {
        File file = new File(path);
        BufferedWriter bfWriter = null;
        try {
            bfWriter = new BufferedWriter(new FileWriter(file));
            for (Student s : students) {
                bfWriter.write(s.getNum() + " ");
                bfWriter.write(s.getName() + " ");
                bfWriter.write(s.getAge() + " ");
                bfWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bfWriter) {
                try {
                    bfWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从文件读取学生信息
     */
    private void getInfo() {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        BufferedReader bfReader = null;
        try {
            bfReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bfReader.readLine()) != null) {
                String[] arr = line.split(" ");
                System.out.println(Arrays.toString(arr));
                System.out.println(line);
                if (arr.length >= 3) {
                    Student student = new Student(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]));
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bfReader) {
                try {
                    bfReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 启动系统
     */
    public void startSystem() {
        System.out.println("             【简易学生信息管理系统】");
        System.out.println();
        System.out.println("学生信息格式： 学号  姓名  年龄");
        System.out.println("-----------------------菜单-----------------------------------------");
        System.out.println("|                                                                   ");
        System.out.println("|             0 增加             1 删除（学号、姓名）                 ");
        System.out.println("|             2 修改（学号、姓名）3 查找（学号、姓名）                 ");
        System.out.println("|             4 查看所有          5 退出系统                          ");
        System.out.println("|                                                                   ");
        System.out.println("--------------------------------------------------------------------");

        getInfo();
        chooseFirstMenu();
    }

}