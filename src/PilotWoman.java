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
public class PilotWoman extends Pilot {

    // 年度安排时间表
    private static LinkedHashMap<LocalDate, Boolean> yearScheduleMap = new LinkedHashMap<>();

    // 第一次的女性生理期
    private static LocalDate firstWomanDate = LocalDate.of(2019, 1, 14);
    // 女性生理周期
    private static int sync = 28;
    // 女性痛经时间
    private static List<LocalDate> womanDateList = new ArrayList<>();

    // 初始化女性痛经时间
    static {
        LocalDate endDate = LocalDate.of(2019, 12, 31);
        LocalDate nextWomanDate = firstWomanDate;
        while (true) {
            if (nextWomanDate.isAfter(endDate)) {
                break;
            }
            womanDateList.add(nextWomanDate);
            womanDateList.add(nextWomanDate.plusDays(1));
            nextWomanDate = nextWomanDate.plusDays(sync);
        }
    }

    @Override
    public Work getWorkType(LocalDate thiDate) {
        // first day
        if (yearScheduleMap.isEmpty()) {
            return Work.NORMAL;
        }
        // 女性生理期使用自动驾驶
        if (womanDateList.contains(thiDate)) {
            return Work.AUTO;
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
    public void endOneDay(LocalDate localDate, Pilot todayPilot) {
        yearScheduleMap.put(localDate, getSex() == todayPilot.getSex());
    }

    @Override
    public Sex getSex() {
        return Sex.WOMAN;
    }
}
