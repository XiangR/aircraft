

import java.time.LocalDate;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class AirplaneY extends Airplane {

    private static LocalDate activeDate = LocalDate.of(2019, 4, 19);

    @Override
    public boolean getWork(LocalDate localDate) {
        return activeDate.isBefore(localDate);
    }

    @Override
    public AirplaneType getType() {
        return AirplaneType.Y;
    }
}
