package task1;

/**
 * @author julychu
 * @date   2020/5/24
 * @desc   年龄异常
 */
public class AgeException extends Exception {

    public AgeException() {
    }

    public AgeException(String message) {
        super(message);
    }
}
