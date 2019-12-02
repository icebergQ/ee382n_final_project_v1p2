package ee382n;

public class GeometryHelper {
    static double EquityTolerance = 0.000000001;

    public static boolean IsEqual(double d1, double d2)
    {

        return (Math.abs(d1-d2) <= EquityTolerance);
    }
}
