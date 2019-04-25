/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public enum Work {

    NORMAL("正常"),

    OVERTIME("加班"),

    AUTO("自动驾驶"),

    NOT("不能工作"),

    ;

    private String desc;

    Work(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
