package centrivaccinali;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * Questa classe contiene metodi statici di utilit&agrave; che permettono di operare su oggetti di tipi differenti.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class CvUtil {

    /**
     * Ottimizza gli spazi bianchi di una stringa. Spazi bianchi in posizione <em>leading</em> o <em>trailing</em>
     * sono rimossi, mentre spazi bianchi multipli sono sostituiti con spazi bianchi singoli.
     *
     * @param s una stringa da ottimizzare
     * @return una stringa in cui gli spazi bianchi sono ottimizzati
     */
    public static String snip(String s) {
        return s.trim().replaceAll("\\s+", " ");
    }

    /**
     * Controlla se una stringa &egrave; composta solamente da caratteri numerici.
     *
     * @param s una stringa da analizzare
     * @return <code>true</code> se la stringa &egrave; composta da caratteri numerici, <code>false</code> altrimenti.
     */
    public static boolean isNumerical(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Restituisce una stringa contenente il testo del <code>RadioButton</code> selezionato all'interno di un array.
     *
     * @param buf un array di <code>RadioButton</code>
     * @return una stringa contenente il testo del <code>RadioButton</code> selezionato
     */
    public static String selectRadioButton(RadioButton[] buf) {
        for(RadioButton rb: buf) {
            if(rb.isSelected()) {
                return rb.getText();
            }
        }
        return buf[0].getText();
    }

    /**
     * Converte un array di <code>TextField</code> in un array equivalente di stringhe di testo corrispondente.
     *
     * @param buf un array di <code>TextField</code>
     * @return un array di stringhe contenente il testo di ogni <code>TextField</code>
     */
    public static String[] toStringArray(TextField[] buf) {
        String[] str = new String[buf.length];
        int i;
        for(i = 0; i < buf.length; i++) {
            str[i] = CvUtil.snip(buf[i].getText());
        }
        return str;
    }

    /**
     * Controlla se una stringa &egrave; composta solamente da caratteri alfabetici.
     *
     * @param s una stringa da analizzare
     * @return <code>true</code> se la stringa &egrave; composta da caratteri alfabetici, <code>false</code> altrimenti.
     */
    public static boolean isAlphabetical(String s){
        int i;
        for(i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }
        return true;
    }
}
