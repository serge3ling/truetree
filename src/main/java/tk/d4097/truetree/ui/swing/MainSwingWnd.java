package tk.d4097.truetree.ui.swing;

import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.ask.Answer;
import tk.d4097.truetree.keep.ask.AnswerGridModel;
import tk.d4097.truetree.keep.ask.Ask;
import tk.d4097.truetree.text.StringKeyValArgs;
import tk.d4097.truetree.text.StringsToAsk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

public class MainSwingWnd implements Runnable {
  private final Cfg cfg;
  private final Keep keep;
  private AnswerGridModel gridModel = new AnswerGridModel(new Answer());
  private final JFrame frame;
  private final JPanel panel;
  private final JTextArea txtArea;
  private final JScrollPane txtScrollPane;
  private final JButton seekBtn;
  private final JButton refreshBtn;
  private final JButton saveGridBtn;
  private final JButton hintBtn;
  private final JTable table;
  private final JScrollPane tableScrollPane;
  private final HintsWnd hintsWnd;

  public MainSwingWnd(Cfg cfg, Keep keep) {
    this.cfg = cfg;
    this.keep = keep;

    panel = new JPanel(new BorderLayout(5, 5));
    txtArea = new JTextArea(3, 90);
    txtScrollPane = new JScrollPane(txtArea);
    seekBtn = new JButton("Seek");
    refreshBtn = new JButton("Refresh");
    saveGridBtn = new JButton("Save Grid");
    hintBtn = new JButton("Hints");
    table = new JTable();
    tableScrollPane = new JScrollPane(table);

    String topDirName = "(unknown)";

    try {
      topDirName = cfg.topDir();
    } catch (Exception ignored) {
    }

    frame = new JFrame("TrueTree @ " + topDirName);

    hintsWnd = new HintsWnd(frame);
  }

  public void go() throws Exception {
    seekBtn.addActionListener(new SeekBtnListener());
    refreshBtn.addActionListener(new RefreshBtnListener());
    saveGridBtn.addActionListener(new SaveGridBtnListener());
    hintBtn.addActionListener(new HintBtnListener());

    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(txtScrollPane);
    panel.add(seekBtn);
    panel.add(refreshBtn);
    panel.add(saveGridBtn);
    panel.add(hintBtn);
    panel.add(tableScrollPane);

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

  class SeekBtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      String askStr = txtArea.getText();
      StringKeyValArgs stringKeyValArgs = new StringKeyValArgs(askStr);
      List<String> likenessStrings = stringKeyValArgs.split();
      StringsToAsk stringsToAsk = new StringsToAsk();
      Ask ask = stringsToAsk.makeAsk(likenessStrings, keep);

      try {
        Answer answer = ask.find();
        gridModel = new AnswerGridModel(answer);
        String[][] array = gridModel.getArray();
        Collection<String> colNames = gridModel.getColNames();
        DefaultTableModel model = new DefaultTableModel();

        for (String colName : colNames) {
          model.addColumn(colName);
        }

        for (String[] rowData : array) {
          model.addRow(rowData);
        }

        table.setModel(model);
      } catch (Exception ignored) {
      }
    }
  }

  class RefreshBtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      try {
        keep.go();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
      } catch (Exception ignored) {
      }
    }
  }

  class SaveGridBtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      Collection<String> colNames;
      String[][] array;

      try {
        colNames = gridModel.getColNames();
        array = gridModel.getArray();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
            frame, "Could not have the grid ready.",
            "Grid Error", JOptionPane.ERROR_MESSAGE);

        return;
      }

      GridFile gridFile = new GridFile(cfg, colNames, array);

      try {
        gridFile.save();
        JOptionPane.showMessageDialog(frame, "Saved.",
            "Save", JOptionPane.INFORMATION_MESSAGE);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
            frame,
            "Could not save the grid.\n"
                + e.getMessage(),
            "Save Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  class HintBtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      hintsWnd.setVisible(true);
    }
  }
}
