package task5;

import java.io.Serializable;

/**
 * @author julychu
 * @date   2020/5/25
 * @desc   消息对象
 */
public class UserMessage implements Serializable {

    public static final String TYPE_MSG = "msg";
    public static final String TYPE_FILE = "file";
    public static final String TYPE_FILE_END_FLAG = "end:file:\\";
    private String type;
    private User user;
    private String msg;
    private long fileSize;

    public UserMessage(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
