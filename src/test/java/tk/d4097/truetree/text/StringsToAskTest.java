package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.Answer;
import tk.d4097.truetree.keep.ask.AnswerRec;
import tk.d4097.truetree.keep.ask.Ask;
import tk.d4097.truetree.keep.ask.likeness.DirIs;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringsToAskTest {

  @Test
  void makeAsk_whenAskForMoneyTagDir_thenFound() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("keep-4/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      Keep keep = new Keep(cfg);
      keep.go();
      List<String> likenessStrings = List.of("dir-has-tag=money");
      StringsToAsk stringsToAsk = new StringsToAsk();
      Ask ask = stringsToAsk.makeAsk(likenessStrings, "", keep);

      Answer answer = ask.find();
      Iterator<AnswerRec> iterator = answer.iterator();
      int cnt = 0;

      while (iterator.hasNext()) {
        cnt++;
        AnswerRec answerRec = iterator.next();
        assert answerRec.getLst().getProp("dir-name").equals("payments");
      }

      assert cnt == 2;
    }
  }
}