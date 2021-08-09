package centrivaccinali;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

    public static String selectRadioButton(RadioButton[] buf) {
        for(RadioButton rb: buf) {
            if(rb.isSelected()) {
                return rb.getText();
            }
        }
        return buf[0].getText();
    }

    public static String[] toStringArray(TextField[] buf) {
        String[] str = new String[buf.length];
        int i;
        for(i = 0; i < buf.length; i++) {
            str[i] = CvUtil.snip(buf[i].getText());
        }
        return str;
    }

}
