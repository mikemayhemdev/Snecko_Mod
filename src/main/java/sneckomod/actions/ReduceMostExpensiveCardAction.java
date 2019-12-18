package sneckomod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sneckomod.SneckoMod;
import java.util.ArrayList;
import java.util.Collections;

public class ReduceMostExpensiveCardAction extends AbstractGameAction {
  public ReduceMostExpensiveCardAction() {
  }
  public void update() {
    ArrayList<AbstractCard> mostExpensive = new ArrayList();
    int mostExpensiveCost = -1;
    SneckoMod.logger.info("AbstractDungeon.player.hand.group.size() = " + AbstractDungeon.player.hand.group.size());
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
      SneckoMod.logger.info("Card" +c.name +"has cost"+c.cost);
      if (c.cost > mostExpensiveCost) {
        mostExpensive.clear();
        mostExpensive.add(c) ;
        mostExpensiveCost = c.cost;
      } else if (c.cost == mostExpensiveCost) {
        mostExpensive.add(c);
      }
    }
    if (mostExpensive.size() == 0) {
      SneckoMod.logger.info("Tried to reduce most expensive cost with no cards in hand.");
    } else {
      Collections.shuffle(mostExpensive, AbstractDungeon.cardRandomRng.random);
      AbstractCard mostExpensiveCard = mostExpensive.get(0);
      mostExpensiveCard.modifyCostForCombat(-1);
      mostExpensiveCard.isCostModified = true;
      mostExpensiveCard.superFlash(Color.PURPLE.cpy());
    }
    tickDuration();
  }
}
