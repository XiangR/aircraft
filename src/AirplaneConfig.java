import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 飞机配置
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class AirplaneConfig {

    // 运载重量
    public static int[] weightArr = new int[]{60, 70, 80, 90};

    public static BigDecimal getWeight() {
        int i = weightArr[(int) Math.round(Math.random() * (3))];
        return BigDecimal.valueOf(i);
//        return BigDecimal.valueOf(Math.round(Math.random() * (100 - 60) + 60));
    }

    public static int length = 4700;
    public static int lengthAll = length * 2;

    public static BigDecimal ten = BigDecimal.valueOf(10);
    public static BigDecimal thousand = BigDecimal.valueOf(1000);

    public static BigDecimal thousandCount = BigDecimal.valueOf(lengthAll).divide(thousand, 2, RoundingMode.HALF_DOWN);

}
