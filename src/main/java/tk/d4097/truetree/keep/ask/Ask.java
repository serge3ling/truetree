package tk.d4097.truetree.keep.ask;

import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.ArrayList;
import java.util.List;

public class Ask {
  private final String lstName;
  private final List<Likeness<Lst>> dirLikenesses;
  private final List<Likeness<Lst>> pathLikenesses;
  private final List<Likeness<Rec>> likenesses;
  private final Keep keep;
  private final List<Lst> lsts = new ArrayList<>();

  public Ask(
      List<Likeness<Lst>> dirLikenesses,
      List<Likeness<Lst>> pathLikenesses,
      List<Likeness<Rec>> likenesses,
      String lstName,
      Keep keep) {
    this.dirLikenesses = dirLikenesses;
    this.pathLikenesses = pathLikenesses;
    this.likenesses = likenesses;
    this.lstName = lstName;
    this.keep = keep;
  }

  public Answer find() throws Exception {
    Answer answer = new Answer();
    var noDirLikenesses = dirLikenesses.isEmpty();
    var noLikenesses = likenesses.isEmpty();

    for (Lst lst : keep.lsts()) {
      if (!lstName.isEmpty() && !lst.name().equals(lstName)) {
        continue;
      }

      if (noDirLikenesses) {
        addToLsts(lst);
        continue;
      }

      for (Likeness<Lst> dirLikeness : dirLikenesses) {
        if (dirLikeness.isGood(lst)) {
          addToLsts(lst);
          break;
        }
      }
    }

    for (Lst lst : lsts) {
      for (Rec rec : lst.getRecs().values()) {
        if (noLikenesses) {
          answer.add(new AnswerRec(rec, lst));
          continue;
        }

        for (Likeness<Rec> likeness : likenesses) {
          if (likeness.isGood(rec)) {
            answer.add(new AnswerRec(rec, lst));
          }
        }
      }
    }

    return answer;
  }

  private void addToLsts(Lst lst) {
    if (pathLikenesses.isEmpty()) {
      lsts.add(lst);
      return;
    }

    for (Likeness<Lst> pathLikeness : pathLikenesses) {
      if (pathLikeness.isGood(lst)) {
        lsts.add(lst);
        break;
      }
    }
  }
}
