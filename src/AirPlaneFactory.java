import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class AirPlaneFactory {

    private static List<Airplane> pilotList = new ArrayList<>();

    static {
        pilotList.add(new AirplaneX());
        pilotList.add(new AirplaneY());
    }

    public Airplane getAirplane(LocalDate localDate) {
        AirplaneY airplaneY = new AirplaneY();
        if (airplaneY.getWork(localDate)) {
            return airplaneY;
        } else {
            return new AirplaneX();
        }
    }

}
