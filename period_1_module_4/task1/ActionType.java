package task1;
/**
 * @author julychu
 * @date   2020/5/16
 * @desc   菜单类型
 */
public enum ActionType {
    TYPE_ADD(0, "增加"),

    TYPE_DEL(1, "删除"),
    TYPE_DELETE_NAME(10, "删除名字"),
    TYPE_DELETE_NUM(11, "删除学号"),

    TYPE_UPDATE(2, "修改"),
    TYPE_UPDATE_NAME(20, "修改名字"),
    TYPE_UPDATE_NUM(21, "修改学号"),

    TYPE_GET(3, "查找"),
    TYPE_GET_NAME(30, "查找名字"),
    TYPE_GET_NUM(31, "查找学号"),

    TYPE_GET_ALL(4, "查看所有");
    ActionType(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }
    private int index;
    private String desc;

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
}
