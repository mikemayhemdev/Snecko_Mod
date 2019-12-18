package sneckomod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import static sneckomod.SneckoMod.SNEKPROOF;


public class SnekproofConfusionPatch {
  //Patches ConfusionPower to not randomize costs on Snekproof cards
  @SpirePatch(
      clz=ConfusionPower.class,
      method = "onCardDraw"
  )
  public static class ConfusionPower_onCardDraw {
    @SpirePrefixPatch
    public static SpireReturn prefix(ConfusionPower __this, AbstractCard card) {

      if (card.hasTag(SNEKPROOF)) {
        return SpireReturn.Return(null);
      }
      return SpireReturn.Continue();
    }
  }
}