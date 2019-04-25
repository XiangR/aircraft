import java.time.LocalDate;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public abstract class Airplane {

    public abstract boolean getWork(LocalDate localDate);

    public abstract AirplaneType getType();
}
