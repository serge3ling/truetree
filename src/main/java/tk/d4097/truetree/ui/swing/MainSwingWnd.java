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
import java.util.List;
import java.util.Set;

public class MainSwingWnd implements Runnable {
  private final Cfg cfg;
  private final Keep keep;
  private final JFrame frame;
  private final JPanel panel;
  private final JTextArea txtArea;
  private final JScrollPane txtScrollPane;
  private final JButton seekBtn;
  private final JButton refreshBtn;
  private final JTable table;
  private final JScrollPane tableScrollPane;

  public MainSwingWnd(Cfg cfg, Keep keep) {
    this.cfg = cfg;
    this.keep = keep;

    panel = new JPanel(new BorderLayout(5, 5));
    txtArea = new JTextArea(3, 90);
    txtScrollPane = new JScrollPane(txtArea);
    seekBtn = new JButton("Seek");
    refreshBtn = new JButton("Refresh");
    table = new JTable();
    tableScrollPane = new JScrollPane(table);

    String topDirName = "(unknown)";

    try {
      topDirName = cfg.topDir();
    } catch (Exception ignored) {
    }

    frame = new JFrame("TrueTree @ " + topDirName);
  }

  public void go() throws Exception {
    seekBtn.addActionListener(new SeekBtnListener());
    refreshBtn.addActionListener(new RefreshBtnListener());

    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(txtScrollPane);
    panel.add(seekBtn);
    panel.add(refreshBtn);
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
        AnswerGridModel gridModel = new AnswerGridModel(answer);
        String[][] array = gridModel.getArray();
        Set<String> colNames = gridModel.getColNames();
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
}
