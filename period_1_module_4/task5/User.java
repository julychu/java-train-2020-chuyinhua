package task5;

import java.io.Serializable;

/**
 * @author julychu
 * @date   2020/5/25
 * @desc   用户对象
 */
public class User implements Serializable {
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
