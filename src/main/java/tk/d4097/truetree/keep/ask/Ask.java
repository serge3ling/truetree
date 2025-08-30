package tk.d4097.truetree.keep.ask;

import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.likeness.CanHaveField;
import tk.d4097.truetree.keep.ask.likeness.FieldLikeness;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.List;

public class Ask {
  private final String lstName;
  private final List<Likeness<Rec>> likenesses;
  private final Keep keep;

  public Ask(List<Likeness<Rec>> likenesses, String lstName, Keep keep) {
    this.likenesses = likenesses;
    this.lstName = lstName;
    this.keep = keep;
  }

  public Answer find() throws Exception {
    Answer answer = new Answer();

    for (Lst lst : keep.lsts()) {
      if (!lst.name().equals(lstName)) {
        continue;
      }

      for (Rec rec : lst.getRecs().values()) {
        for (Likeness<Rec> likeness : likenesses) {
          if (likeness.isGood(rec)) {
            answer.add(new AnswerRec(rec, lst));
          }
        }
      }
    }

    return answer;
  }
}
