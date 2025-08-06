package tk.d4097.truetree.text;

public class LstItemFieldParseStep extends ParseStep {
  public LstItemFieldParseStep(LstParseBundle bundle) {
    super(bundle);
  }

  @Override
  public boolean hasNext() {
    return bundle.lines().hasNext();
  }

  @Override
  public LineParse next() throws Exception {
    Txt lines = bundle.lines();

    if (!lines.hasNext()) {
      bundle.closeRec();
      return this;
    }

    String line = lines.next();
    splitFieldKeyValue(line);

    if (!lines.hasNext()) {
      bundle.closeRec();
      return this;
    }

    line = lines.next();

    if (bundle.getStringAcceptors().itemStartWithFieldIsIn(line)) {
      bundle.closeRec();
      bundle.openNewRec();
      lines.backAtBeforeThisLine();
      return new LstItemFieldParseStep(bundle);
    }

    if (bundle.getStringAcceptors().itemFieldIsIn(line)) {
      lines.backAtBeforeThisLine();
      return this;
    }

    if (bundle.getStringAcceptors().itemStartIsIn(line)) {
      bundle.closeRec();
      bundle.openNewRec();
      return new LstItemFieldParseStep(bundle);
    }

    if (!line.startsWith(" ")) {
      bundle.closeRec();
      lines.backAtBeforeThisLine();
      return new LstRootLevelStep(bundle);
    }

    throw new Exception("Cannot parse line \"" + line + "\", maybe wrong indentation.");
  }

  void splitFieldKeyValue(String line) throws Exception {
    String cut = line;

    if (bundle.getStringAcceptors().itemStartWithFieldIsIn(line)) {
      cut = bundle.getStringAcceptors().getItemStartWithField().remake(line);
    }

    if (bundle.getStringAcceptors().itemFieldIsIn(line)) {
      cut = bundle.getStringAcceptors().getItemField().remake(line);
    }

    String splitter = new BareFieldAccept().splitter();
    String[] split = cut.split(splitter);

    if (split.length < 2) {
      throw new Exception("Could not find key and value in line: \"" + line + "\".");
    }

    if (split[0].isEmpty()) {
      throw new Exception("Could not find key in line: \"" + line + "\".");
    }

    bundle.rec().put(split[0], split[1]);
  }
}
