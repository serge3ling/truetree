package tk.d4097.truetree.ui.swing;

import tk.d4097.truetree.Cfg;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;

public class GridFile {
  private final Cfg cfg;
  private final Collection<String> colNames;
  private final String[][] array;

  public GridFile(Cfg cfg, Collection<String> colNames, String[][] array) {
    this.cfg = cfg;
    this.colNames = colNames;
    this.array = array;
  }

  public void save() throws Exception {
    String filename = cfg.topDir() + File.separator + "grid.html";
    File file = new File(filename);
    try (PrintWriter writer = new PrintWriter(file)) {
      write(writer);
    } catch (Exception e) {
      throw new Exception("Could not write to file \"" + filename + "\"", e);
    }
  }

  private void write(PrintWriter writer) {
    writer.println("<!DOCTYPE html>");
    writer.println("<html lang=\"en\" xmlns:th=\"http://www.w3.org/1999/xhtml\">");
    writer.println("<head>");
    writer.println("  <meta charset=\"UTF-8\">");
    writer.println("  <title>Grid</title>");
    writer.println("  <style>");
    writer.println("    table, th, td {");
    writer.println("      border: 1px solid;");
    writer.println("      border-collapse: collapse;");
    writer.println("    }");
    writer.println("    th, td {");
    writer.println("      padding-left: 5px;");
    writer.println("      padding-right: 5px;");
    writer.println("  </style>");
    writer.println("</head>");
    writer.println("<body>");
    writer.println("<table>");
    writer.println("<tr>");

    for (String colName : colNames) {
      writer.println("<th>" + colName + "</th>");
    }

    writer.println("</tr>");

    for (String[] strings : array) {
      writer.println("<tr>");

      for (String s : strings) {
        writer.println("<td>" + s + "</td>");
      }

      writer.println("</tr>");
    }

    writer.println("</table>");
    writer.println("</body>");
    writer.println("</html>");
  }
}
