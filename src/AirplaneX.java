

import java.time.LocalDate;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class AirplaneX extends Airplane {

    @Override
    public boolean getWork(LocalDate localDate) {
        return true;
    }

    @Override
    public AirplaneType getType() {
        return AirplaneType.X;
    }
}
