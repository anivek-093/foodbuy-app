package com.example.dashboard.utility;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MathUtility {
    public static String truncateFloatToTwoDecimalPlaces(Float f) {
        DecimalFormat df = new DecimalFormat("###.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        return df.format(f);
    }
}
