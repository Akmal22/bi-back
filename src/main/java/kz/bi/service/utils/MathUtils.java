package kz.bi.service.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class MathUtils {
    public static BigDecimal sqrt(BigDecimal value, int scale) {
        BigDecimal TWO = BigDecimal.valueOf(2);
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = new BigDecimal(Math.sqrt(value.doubleValue()));

        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = value.divide(x0, scale, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, scale, RoundingMode.HALF_UP);
        }
        return x1;
    }

    public static BigDecimal getRatio(BigDecimal dividend, BigDecimal divider, int scale) {
        return dividend
                .multiply(new BigDecimal(100))
                .divide(divider, scale, RoundingMode.HALF_UP);
    }
}
