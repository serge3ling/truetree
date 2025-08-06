package tk.d4097.truetree.text;

public class LstStringAcceptors {
  private final StringAccept itemStart;
  private final StringAccept itemStartWithField;
  private final StringAccept itemField;
  private final StringAccept bareField;

  public LstStringAcceptors() {
    this(
        new LstItemStartAccept(),
        new LstItemStartWithFieldAccept(),
        new LstItemFieldAccept(),
        new BareFieldAccept()
    );
  }

  public LstStringAcceptors(StringAccept itemStart, StringAccept itemStartWithField,
                            StringAccept itemField, StringAccept bareField) {
    this.itemStart = itemStart;
    this.itemStartWithField = itemStartWithField;
    this.itemField = itemField;
    this.bareField = bareField;
  }

  public boolean itemStartIsIn(String line) {
    return itemStart.accept(line);
  }

  public boolean itemStartWithFieldIsIn(String line) {
    return itemStartWithField.accept(line);
  }

  public boolean itemFieldIsIn(String line) {
    return itemField.accept(line);
  }

  public boolean bareFieldIsIn(String line) {
    return bareField.accept(line);
  }

  public StringAccept getItemStartWithField() {
    return itemStartWithField;
  }

  public StringAccept getItemField() {
    return itemField;
  }

  public StringAccept getBareField() {
    return bareField;
  }
}
