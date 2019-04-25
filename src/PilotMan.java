import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class PilotMan extends Pilot {

    // 年度安排时间表
    private static LinkedHashMap<LocalDate, Boolean> yearScheduleMap = new LinkedHashMap<>();
    // 男人加班的时间
    private static List<LocalDate> overtimeWorkMan = new ArrayList<>();
    // 男人休息的时间
    private static List<LocalDate> manSleepDay = new ArrayList<>();
    // 男人是否停止工作
    private static boolean manStop = false;

    @Override
    public void endOneDay(LocalDate localDate, Pilot todayPilot) {
        Work workType = getWorkType(localDate);
        if (manStop) {
            manSleepDay.add(localDate);
        }
        // 如果男性加班到达了十一天则男性罢工
        if (!manStop && overtimeWorkMan.size() == 11) {
            manStop = true;
            manSleepDay = new ArrayList<>();
        }
        // 如果男性罢工并且连续休息了十天，则停止罢工，并重新计算男性加班
        if (manStop && manSleepDay.size() == 10) {
            manStop = false;
            overtimeWorkMan = new ArrayList<>();
        }

        boolean todayWork = getSex() == todayPilot.getSex();
        if (todayWork) {
            if (workType == Work.OVERTIME) {
                overtimeWorkMan.add(localDate);
            }
        }
        yearScheduleMap.put(localDate, todayWork);
    }

    @Override
    public Work getWorkType(LocalDate thiDate) {
        // 女性不能驾驶的时间
        if (manStop) {
            return Work.NOT;
        }
        if (yearScheduleMap.isEmpty()) {
            return Work.NORMAL;
        }
        LocalDate beforeDay = thiDate.plusDays(-1);
        Boolean beforeHasWork = yearScheduleMap.get(beforeDay);
        if (Objects.equals(beforeHasWork, Boolean.TRUE)) {
            return Work.OVERTIME;
        } else {
            return Work.NORMAL;
        }
    }

    @Override
    public Sex getSex() {
        return Sex.MAN;
    }
}
