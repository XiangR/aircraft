

import java.time.LocalDate;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public abstract class Pilot {

    public abstract Work getWorkType(LocalDate localDate);

    public abstract void endOneDay(LocalDate localDate, Pilot pilot);

    public abstract Sex getSex();
}
