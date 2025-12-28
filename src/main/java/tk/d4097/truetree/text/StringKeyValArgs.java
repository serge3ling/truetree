package tk.d4097.truetree.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringKeyValArgs {
  private final String input;

  private final String regex = "\\s+([A-Za-z]+[A-Za-z0-9_-]*)="
      + "(?:"
      + "(\"(?:[^\"]|(?<=\\\\)\")*\")"
      + "|"
      + "('(?:[^\"]|(?<=\\\\)')*')"
      + "|"
      + "[^\\s\"']+"
      + ")";

  private final Pattern pattern = Pattern.compile(regex);
  private boolean made;
  private boolean fit;
  private final List<String> argsSplit = new ArrayList<>();

  public StringKeyValArgs(String input) {
    this.input = input;
  }

  public boolean fits() {
    make();
    return fit;
  }

  public List<String> split() {
    make();
    return argsSplit;
  }

  private void make() {
    if (made) {
      return;
    }

    made = true;

    if (input.trim().isEmpty()) {
      fit = true;
      return;
    }

    String readyInput = " " + input;
    String fitRegex = "(" + regex + ")+";
    fit = readyInput.matches(fitRegex);
    Matcher matcher = pattern.matcher(readyInput);

    while (matcher.find()) {
      argsSplit.add(matcher.group().trim());
    }
  }
}
