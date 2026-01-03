package tk.d4097.truetree.ui.swing;

import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.keep.Keep;

import javax.swing.*;
import java.awt.*;

public class MainSwingWnd implements Runnable {
  private final Cfg cfg;
  private final Keep keep;
  private final JFrame frame;
  private final JPanel panel;
  private final JTextArea txtArea;

  public MainSwingWnd(Cfg cfg, Keep keep) {
    this.cfg = cfg;
    this.keep = keep;

    panel = new JPanel(new BorderLayout(5, 5));
    txtArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(txtArea);
    panel.add(scrollPane, BorderLayout.CENTER);

    String topDirName = "(unknown)";

    try {
      topDirName = cfg.topDir();
    } catch (Exception ignored) {
    }

    frame = new JFrame("TrueTree @ " + topDirName);
  }

  public void go() throws Exception {
    System.out.println("Swing window to come.");
    frame.setContentPane(panel);
    frame.setSize(640, 480);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationByPlatform(true);

    SwingUtilities.invokeLater(this);
  }

  @Override
  public void run() {
    frame.setVisible(true);
  }
}
