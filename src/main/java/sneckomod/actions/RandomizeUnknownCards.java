 package sneckomod.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import java.util.ArrayList;
 import org.apache.logging.log4j.Logger;
 import sneckomod.SneckoMod;
 import sneckomod.cards.AbstractSneckoCard;






 public class RandomizeUnknownCards
   extends AbstractGameAction
 {
   public AbstractCard c;

   public void update()
   {
     ArrayList<AbstractSneckoCard> AL = new ArrayList();

     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
       if (((c instanceof AbstractSneckoCard)) &&
         (c.hasTag(SneckoMod.UNKNOWN))) {
         AL.add((AbstractSneckoCard)c);
         SneckoMod.logger.info("Logging Unknown " + c.name);
       }
     }



     for (AbstractSneckoCard cs : AL) {
       SneckoMod.logger.info("Replacing Unknown " + cs.name);
       cs.replaceUnknown();
     }





     this.isDone = true;
   }
 }


