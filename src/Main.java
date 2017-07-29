import controller.PasswordManagerController;
import view.PasswordManagerGUI;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PasswordManagerGUI window = new PasswordManagerGUI();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
