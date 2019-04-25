import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class PilotAirplane {

    public static BigDecimal getDayOil(Airplane airplane, Pilot pilot, Work work, BigDecimal weight) {
        if (work == Work.AUTO) {
            return AirplaneConfig.thousandCount.multiply(thousandOilAuto(weight, airplane));
        } else {

            if (pilot.getSex() == Sex.MAN) {
                return dayOilMan(airplane, work, weight);
            } else {
                return dayOilWoman(airplane, weight);
            }
        }
    }

    public static BigDecimal getDayHour(Airplane airplane, Pilot pilot, Work work, BigDecimal weight) {
        if (work == Work.AUTO) {
            return AirplaneConfig.thousandCount.multiply(thousandHourAuto(weight, airplane));
        } else {
            if (pilot.getSex() == Sex.MAN) {
                return dayHourMan(airplane);
            } else {
                return dayHourWoman(airplane);
            }
        }
    }

    /**
     * 使用自动驾驶的千公里耗油
     */
    public static BigDecimal thousandOilAuto(BigDecimal weight, Airplane airplane) {
        BigDecimal unit = weight.divide(AirplaneConfig.ten, 2, RoundingMode.HALF_DOWN);
        if (airplane.getType() == AirplaneType.X) {
            return unit.multiply(BigDecimal.valueOf(1.5));
        }
        if (airplane.getType() == AirplaneType.Y) {
            return unit.multiply(BigDecimal.valueOf(1.4));
        }
        return BigDecimal.ZERO;
    }

    /**
     * 使用自动驾驶的千公里耗时
     */
    public static BigDecimal thousandHourAuto(BigDecimal weight, Airplane airplane) {
        BigDecimal unit = weight.divide(AirplaneConfig.ten, 2, RoundingMode.HALF_DOWN);
        if (airplane.getType() == AirplaneType.X) {
            return unit.multiply(BigDecimal.valueOf(1.7));
        }
        if (airplane.getType() == AirplaneType.Y) {
            BigDecimal divide = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(115), 2, RoundingMode.HALF_DOWN);
            return unit.multiply(BigDecimal.valueOf(1.7).multiply(divide));
        }
        return BigDecimal.ZERO;
    }

    private static BigDecimal dayOilMan(Airplane airplane, Work work, BigDecimal weight) {
        BigDecimal units = thousandOilMan(airplane, work, weight);
        return AirplaneConfig.thousandCount.multiply(units);
    }

    private static BigDecimal dayHourMan(Airplane airplane) {
        return AirplaneConfig.thousandCount.multiply(thousandHourMan(airplane));
    }

    /**
     * 千公里耗油
     */
    private static BigDecimal thousandOilMan(Airplane airplane, Work work, BigDecimal weight) {
        BigDecimal unit = weight.divide(AirplaneConfig.ten, 2, RoundingMode.HALF_DOWN);
        if (airplane.getType() == AirplaneType.X) {
            if (Work.OVERTIME == work) {
                return unit.multiply(BigDecimal.valueOf(1.3));
            } else {
                return unit.multiply(BigDecimal.valueOf(1));
            }
        }
        if (airplane.getType() == AirplaneType.Y) {
            if (Work.OVERTIME == work) {
                return unit.multiply(BigDecimal.valueOf(1.2));
            } else {
                return unit.multiply(BigDecimal.valueOf(0.9));
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * 千公里耗时
     */
    private static BigDecimal thousandHourMan(Airplane airplane) {
        if (airplane.getType() == AirplaneType.X) {
            return BigDecimal.valueOf(1.25);
        }
        if (airplane.getType() == AirplaneType.Y) {
            BigDecimal divide = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(115), 2, RoundingMode.HALF_DOWN);
            return BigDecimal.valueOf(1.25).multiply(divide);
        }
        return BigDecimal.ZERO;
    }


    private static BigDecimal dayOilWoman(Airplane airplane, BigDecimal weight) {
        BigDecimal units = thousandOilWoman(airplane, weight);
        return AirplaneConfig.thousandCount.multiply(units);
    }

    private static BigDecimal dayHourWoman(Airplane airplane) {
        return AirplaneConfig.thousandCount.multiply(thousandHourWoman(airplane));
    }

    /**
     * 千公里耗油
     */
    private static BigDecimal thousandOilWoman(Airplane airplane, BigDecimal weight) {
        // 如果使用自动驾驶
        BigDecimal unit = weight.divide(AirplaneConfig.ten, 2, RoundingMode.HALF_DOWN);
        if (airplane.getType() == AirplaneType.X) {
            return unit.multiply(BigDecimal.valueOf(0.8));
        }
        if (airplane.getType() == AirplaneType.Y) {
            return unit.multiply(BigDecimal.valueOf(0.7));
        }
        return BigDecimal.ZERO;
    }

    /**
     * 千公里耗时
     */
    private static BigDecimal thousandHourWoman(Airplane airplane) {
        // 如果使用自动驾驶
        if (airplane.getType() == AirplaneType.X) {
            return BigDecimal.valueOf(1.6);
        }
        if (airplane.getType() == AirplaneType.Y) {
            BigDecimal divide = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(115), 2, RoundingMode.HALF_DOWN);
            return BigDecimal.valueOf(1.6).multiply(divide);
        }
        return BigDecimal.ZERO;
    }
}
