package centrivaccinali;

public class CvUtil {

    public static String snip(String str) {
        return str.trim().replaceAll("\\s+", " ");
    }

    public static boolean isNumerical(String str) {
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
