

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public enum AirplaneType {

    X("X飞机"),

    Y("Y飞机");

    private String desc;

    AirplaneType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
