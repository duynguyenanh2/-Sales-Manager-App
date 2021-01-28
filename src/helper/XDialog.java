package helper;

import java.awt.Component;
import javax.swing.JOptionPane;

public class XDialog {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == 0;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Input", JOptionPane.INFORMATION_MESSAGE);
    }
}
