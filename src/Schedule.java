

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangrui on 2019/4/21.
 *
 * @author xiangrui
 * @date 2019/4/21
 */
public class Schedule {

    // 年度安排时间表
    private static LinkedHashMap<LocalDate, DayPlan> yearScheduleMap = new LinkedHashMap<>();


    public static void main(String[] args) {
        System.out.println(printYearPlan());
    }

    private static String printYearPlan() {

        // 处理年度安排
        processYearPlan();

        // 统计行程安排
        List<String> dayList = new ArrayList<>();
        BigDecimal totalHour = BigDecimal.ZERO;
        BigDecimal totalOil = BigDecimal.ZERO;
        for (Map.Entry<LocalDate, DayPlan> entry : yearScheduleMap.entrySet()) {
            LocalDate date = entry.getKey();
            DayPlan value = entry.getValue();
            BigDecimal dayHour = PilotAirplane.getDayHour(value.airplane, value.pilot, value.work, value.weight);
            BigDecimal dayOil = PilotAirplane.getDayOil(value.airplane, value.pilot, value.work, value.weight);
            dayList.add(String.format("本次航班信息：日期:%s，驾驶员性别:%s，工作状态:%s，耗油:%s吨，用时:%s小时。",
                    date.toString(), value.pilot.getSex().getDesc(), value.work.getDesc(), dayOil, dayHour));
            totalHour = totalHour.add(dayHour);
            totalOil = totalOil.add(dayOil);
        }
        // 打印输出
        String name = String.format("航空安排%s-%s.txt", LocalDate.now().toString(), LocalTime.now().toString());
        Util.appendFileTextLine(name, String.format("年度航班驾驶信息耗油:%s吨，用时:%s小时。", totalOil, totalHour));
        for (String s : dayList) {
            Util.appendFileTextLine(name, s);
        }
        return name;
    }

    /**
     * 处理年度安排
     */
    private static void processYearPlan() {
        LocalDate begin = LocalDate.of(2019, 1, 1);
        LocalDate end = LocalDate.of(2019, 12, 31);
        int count = 0;
        while (true) {
            LocalDate nextDay = begin.plusDays(count);
            if (nextDay.isAfter(end)) {
                break;
            }
            DayPlan processPlan = processDayPlan(nextDay);
            yearScheduleMap.put(begin.plusDays(count), processPlan);
            count++;
        }
    }

    /**
     * 处理每天的安排
     *
     * @param localDate 时间
     * @return 行程安排
     */
    private static DayPlan processDayPlan(LocalDate localDate) {

        if (localDate.equals(LocalDate.of(2019, 6, 4))) {
            System.out.println();
        }
        AirPlaneFactory factory = new AirPlaneFactory();
        Airplane workAirplane = factory.getAirplane(localDate);

        PilotFactory pilotFactory = new PilotFactory();
        Pilot workPilot = pilotFactory.getPilot(localDate);
        Work workType = workPilot.getWorkType(localDate);

        pilotFactory.endTodayWork(localDate, workPilot);

        DayPlan dayPlan = new DayPlan();
        dayPlan.weight = AirplaneConfig.getWeight();
        dayPlan.airplane = workAirplane;
        dayPlan.pilot = workPilot;
        dayPlan.work = workType;
        dayPlan.date = localDate;
        return dayPlan;

    }

}

