package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.likeness.*;

import java.util.ArrayList;
import java.util.List;

public class StringKeyValArgToLikenesses {
  private final List<Likeness<Lst>> dirLikenesses;
  private final List<Likeness<Lst>> pathLikenesses;
  private final List<Likeness<Rec>> likenesses;
  private final StringKeyValArgDirLikenessMap dirLikenessMap;
  private final StringKeyValArgPathLikenessMap pathLikenessMap;
  private final StringKeyValArgRecLikenessMap likenessMap;
  private String lstName = "";

  private final String assignSplit = "=";
  private final int assignSplitLen = assignSplit.length();

  private final String fldSplit = "--";
  private final int fldSplitLen = fldSplit.length();

  private final String quote = "\"";
  private final int quoteLen = quote.length();

  private final String quote2 = "'";
  private final int quote2Len = quote2.length();

  private final String lstNameKey = "lst-name";

  public StringKeyValArgToLikenesses() {
    this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }

  public StringKeyValArgToLikenesses(
      List<Likeness<Lst>> dirLikenesses,
      List<Likeness<Lst>> pathLikenesses,
      List<Likeness<Rec>> likenesses
  ) {
    this(
        dirLikenesses,
        pathLikenesses,
        likenesses,
        new StringKeyValArgDirLikenessMap(),
        new StringKeyValArgPathLikenessMap(),
        new StringKeyValArgRecLikenessMap()
    );
  }

  public StringKeyValArgToLikenesses(
      List<Likeness<Lst>> dirLikenesses,
      List<Likeness<Lst>> pathLikenesses,
      List<Likeness<Rec>> likenesses,
      StringKeyValArgDirLikenessMap dirLikenessMap,
      StringKeyValArgPathLikenessMap pathLikenessMap,
      StringKeyValArgRecLikenessMap likenessMap
  ) {
    this.dirLikenesses = dirLikenesses;
    this.pathLikenesses = pathLikenesses;
    this.likenesses = likenesses;
    this.dirLikenessMap = dirLikenessMap;
    this.pathLikenessMap = pathLikenessMap;
    this.likenessMap = likenessMap;
  }

  public List<Likeness<Lst>> getDirLikenesses() {
    return dirLikenesses;
  }

  public List<Likeness<Lst>> getPathLikenesses() {
    return pathLikenesses;
  }

  public List<Likeness<Rec>> getLikenesses() {
    return likenesses;
  }

  public String getLstName() {
    return lstName;
  }

  public void handle(List<String> keyValStrings) {
    for (String string : keyValStrings) {
      handle(string);
    }
  }

  public void handle(String stringKeyVal) {
    String val = "";
    String fld = "";
    String likenessName;
    String fldNLikeness = stringKeyVal;
    int eqNd = stringKeyVal.indexOf(assignSplit);

    if (eqNd >= 0) {
      val = replicateVal(stringKeyVal.substring(eqNd + assignSplitLen));
      fldNLikeness = stringKeyVal.substring(0, eqNd);
    }

    int fldSplitNd = fldNLikeness.indexOf(fldSplit);

    if (fldSplitNd >= 0) {
      likenessName = fldNLikeness.substring(fldSplitNd + fldSplitLen);
      fld = fldNLikeness.substring(0, fldSplitNd);
    } else {
      likenessName = fldNLikeness;
    }

    if (dirLikenessMap.has(likenessName)) {
      dirLikenesses.add(dirLikenessMap.get(likenessName).apply(val));
    } else if (pathLikenessMap.has(likenessName)) {
      pathLikenesses.add(pathLikenessMap.get(likenessName).apply(val));
    } else if (likenessMap.has(likenessName)) {
      likenesses.add(likenessMap.get(likenessName).apply(fld, val));
    } else if (likenessName.equals("lst-name")) {
      lstName = val;
    }
  }

  private String replicateVal(String val) {
    String withQuotesStripped = val;

    if (val.startsWith(quote) && val.endsWith(quote)) {
      int valLen = val.length();
      withQuotesStripped = val.substring(quoteLen, valLen - quoteLen);
    } else if (val.startsWith(quote2) && val.endsWith(quote2)) {
      int valLen = val.length();
      withQuotesStripped = val.substring(quote2Len, valLen - quote2Len);
    }

    String quoteEscaped = "\\" + quote;
    String quote2Escaped = "\\" + quote2;
    return withQuotesStripped
        .replaceAll("\\Q" + quoteEscaped + "\\E", quote)
        .replaceAll("\\Q" + quote2Escaped + "\\E", quote2);
  }

  public List<String> hints() {
    List<String> hints = new ArrayList<>();
    hints.add(lstNameKey + "=<lst-name>, like " + lstNameKey + "=Broadcast");
    hints.addAll(dirLikenessMap.hints());
    hints.addAll(pathLikenessMap.hints());
    hints.addAll(likenessMap.hints());

    return hints;
  }
}
