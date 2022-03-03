package TMGE;
import javax.swing.*;

public class Notification {

    public static void showErrorMsg(String msg){
        JOptionPane.showMessageDialog(Controller.frame, msg, "Oh No No No",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccessMsg(String msg){
        JOptionPane.showMessageDialog(Controller.frame, msg,
                "Success !",JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showOptionMsg(String msg){
        String [] options = {"YES", "NO"};
        return JOptionPane.showOptionDialog(Controller.frame, msg,"You Sure ?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1] );
    }
}
