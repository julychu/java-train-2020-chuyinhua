package task1;

/**
 * @author julychu
 * @date 2020/5/16
 * @desc 学生信息
 */

public class Student {

    private int num;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int num, String name, int age) {
        setNum(num);
        setName(name);
        setAge(age);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        if(num > 0) {
            this.num = num;
        } else {
            System.out.println("【error】学号是大于0的数字!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty() && name.length() <= 20) {
            this.name = name;
        } else {
            System.out.println("【error】名字不为空,且长度小于20!");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("【error】年龄不合理!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (num != student.num) return false;
        if (age != student.age) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}