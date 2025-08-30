package tk.d4097.truetree.keep.ask;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.likeness.CanHaveField;
import tk.d4097.truetree.keep.ask.likeness.Has;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.io.InputStream;
import java.util.*;

class AskTest {
  @Test
  void find_whenThereAre_thenFound() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("keep-1/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      Keep keep = new Keep(cfg);
      keep.go();
      List<Likeness<Rec>> likenesses = new ArrayList<>();
      likenesses.add(new Has<>("capital", "Washington"));
      List<String> ids = Arrays.asList("cbs", "nbc");
      Ask ask = new Ask(likenesses, "brdcst", keep);
      Answer answer = ask.find();
      Iterator<AnswerRec> iterator = answer.iterator();
      int cnt = 0;

      while (iterator.hasNext()) {
        cnt++;
        AnswerRec answerRec = iterator.next();
        assert ids.contains(answerRec.getRec().id());
      }

      assert (cnt == 2);
    }
  }

  @Test
  void find_whenThereIs_thenFound() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("keep-1/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      Keep keep = new Keep(cfg);
      keep.go();
      List<Likeness<Rec>> likenesses = new ArrayList<>();
      likenesses.add(new Has<>("full-name", "Corporation"));
      List<String> ids = Arrays.asList("bbc");
      Ask ask = new Ask(likenesses, "brdcst", keep);
      Answer answer = ask.find();
      Iterator<AnswerRec> iterator = answer.iterator();
      int cnt = 0;

      while (iterator.hasNext()) {
        cnt++;
        AnswerRec answerRec = iterator.next();
        assert ids.contains(answerRec.getRec().id());
      }

      assert (cnt == 1);
    }
  }
}