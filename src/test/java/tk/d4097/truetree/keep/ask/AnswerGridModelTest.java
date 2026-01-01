package tk.d4097.truetree.keep.ask;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.text.StringsToAsk;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

class AnswerGridModelTest {

  @Test
  void getArray_whenHasTagMoney_then2Rows1Col() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("keep-4/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      Keep keep = new Keep(cfg);
      keep.go();
      List<String> likenessStrings = List.of("dir-has-tag=money");
      StringsToAsk stringsToAsk = new StringsToAsk();
      Ask ask = stringsToAsk.makeAsk(likenessStrings, keep);
      Answer answer = ask.find();
      AnswerGridModel gridModel = new AnswerGridModel(answer);
      String[][] array = gridModel.getArray();
      Set<String> colNames = gridModel.getColNames();
      int colCnt = colNames.size();

      assert colCnt == 1;
      assert colNames.iterator().next().equals("id");
      assert array.length == 2;
      assert array[0][0].equals("bbc");
      assert array[1][0].equals("rai");
    }
  }
}