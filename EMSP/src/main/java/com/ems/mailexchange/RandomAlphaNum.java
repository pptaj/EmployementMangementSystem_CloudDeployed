package com.ems.mailexchange;

/**
 * Created by dleti on 3/29/2017.
 */
import static java.lang.Math.round;
        import static java.lang.Math.random;
        import static java.lang.Math.pow;
        import static java.lang.Math.abs;
        import static java.lang.Math.min;
        import static org.apache.commons.lang.StringUtils.leftPad;

public class RandomAlphaNum {
   /* public static  void main(){
        RandomAlphaNum.gen(42);
    }*/
    public static String gen(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = length; i > 0; i -= 12) {
            int n = min(12, abs(i));
           // sb.append((Long.toString(round(random() * pow(36, n)), 36), n, '0');
            sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
        }
        return sb.toString();
    }
}
