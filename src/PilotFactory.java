import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class PilotFactory {

    private static List<Pilot> pilotList = new ArrayList<>();

    static {
        pilotList.add(new PilotMan());
        pilotList.add(new PilotWoman());
    }

    public Pilot getPilot(LocalDate localDate) {

        Map<Work, Pilot> map = new LinkedHashMap<>();
        for (Pilot pilot : pilotList) {
            Work workType = pilot.getWorkType(localDate);
            map.put(workType, pilot);
            if (workType == Work.NORMAL) {
                return pilot;
            }
        }

        if (map.containsKey(Work.OVERTIME)) {
            return map.get(Work.OVERTIME);
        }

        if (map.containsKey(Work.AUTO)) {
            return map.get(Work.AUTO);
        }

        return new PilotWoman();
    }


    public void endTodayWork(LocalDate localDate, Pilot nowPilot) {
        for (Pilot pilot : pilotList) {
            pilot.endOneDay(localDate, nowPilot);
        }
    }

}
