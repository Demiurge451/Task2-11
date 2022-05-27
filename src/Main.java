import util.SwingUtils;

import javax.swing.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setLookAndFeelByName("Windows");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }
}
