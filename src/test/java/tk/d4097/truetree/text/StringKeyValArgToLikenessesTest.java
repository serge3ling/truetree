package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringKeyValArgToLikenessesTest {

  @Test
  void handle_when1DirLikenessAnd1Likeness_thenSo() {
    String strWQuotesInside = "dir-has-tag=\"money \\\"for nothing\\\"\"";
    String strWQuotes2Inside = "dir-has-tag='money \\'for nothing\\''";
    String strW2HyphenKey = "full-name--has=Broadcast";
    List<String> likenessStrings = List.of(strWQuotesInside, strWQuotes2Inside, strW2HyphenKey);
    List<Likeness<Lst>> dirLikenesses = new ArrayList<>();
    List<Likeness<Rec>> likenesses = new ArrayList<>();
    StringKeyValArgToLikenesses mapper = new StringKeyValArgToLikenesses(dirLikenesses, likenesses);
    mapper.handle(likenessStrings);
    assert dirLikenesses.size() == 2;
    assert likenesses.size() == 1;
  }
}