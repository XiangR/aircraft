

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class DayPlan {

    /**
     * 当日重量
     */
    public BigDecimal weight;

    /**
     * 当日重量
     */
    public Airplane airplane;

    /**
     * 当日人
     */
    public Pilot pilot;

    /**
     * 工作状态
     */
    public Work work;

    /**
     * 当日时间
     */
    public LocalDate date;
}
