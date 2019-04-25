/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public enum Sex {

    MAN("男人"),

    WOMAN("女人");

    private String desc;

    Sex(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
