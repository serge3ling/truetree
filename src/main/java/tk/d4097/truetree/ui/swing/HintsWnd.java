package tk.d4097.truetree.ui.swing;

import tk.d4097.truetree.text.StringKeyValArgToLikenesses;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HintsWnd extends JDialog {
  private final JTextArea txtArea;
  private final JScrollPane txtScrollPane;

  public HintsWnd(Frame frame) {
    super(frame, "Hints");
    txtArea = new JTextArea(9, 60);
    txtArea.setText(hints());
    txtArea.setEditable(false);
    txtScrollPane = new JScrollPane(txtArea);
    this.add(txtScrollPane);
    this.setSize(640, 480);
    this.pack();
  }

  private String hints() {
    StringKeyValArgToLikenesses hintBuild = new StringKeyValArgToLikenesses();
    List<String> hintList = hintBuild.hints();
    return String.join("\n", hintList);
  }
}
