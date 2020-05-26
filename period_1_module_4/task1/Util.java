package task1;

public class Util {

    public static boolean checkeAll(int num, String name, int age) throws AgeException, NumException {
        return checkNum(num) && checkName(name) && checkAge(age);
    }

    public static boolean checkNum(int num) throws NumException{
        if(num <= 0) {
            System.out.println("【error】学号是大于0的数字");
            throw new NumException("【error】学号是大于0的数字");
        }
        return true;
    }

    public static boolean checkName(String name) {
        if(name.isEmpty() || name.length() > 20) {
            System.out.println("【error】姓名不为空,且长度小于20");
            return false;
        }
        return true;
    }

    public static boolean checkAge(int age) throws AgeException{
        if(age <= 0 || age > 150) {
            System.out.println("【error】年龄不合理");
            throw new AgeException("【error】年龄不合理");
        }
        return true;
    }
}