package SystudyTest;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Classname Test14
 * @Description
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test14 {



    public static void main(String[] args) {
        double[] cal = {1.5,-1,1.5};
        double v = Test14.standardDiviation(cal);
        System.out.println(v);

    }

    public static double avg(double[] x) {
        if (x.length == 0) {
            return 0.0D;
        } else {
            double sum = 0.0D;
            double[] var3 = x;
            int var4 = x.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                double value = var3[var5];
                sum += value;
            }

            return sum / (double)x.length;
        }
    }

    public static double variance(double[] x) {
        double dAve = avg(x);
        double dVar = 0.0D;
        double[] var5 = x;
        int var6 = x.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            double v = var5[var7];
            dVar += (v - dAve) * (v - dAve);
        }

        return dVar / (double)x.length;
    }

    public static double standardDiviation(double[] x) {
        return Math.sqrt(variance(x));
    }
}