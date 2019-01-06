 package sneckomod.actions;

 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.random.Random;
 import sneckomod.SneckoMod;

 public class RandomizeCost
   extends AbstractGameAction
 {
   public AbstractCard c;

   public RandomizeCost(AbstractCard c)
   {
     this.c = c;
   }



   public void update()
   {
     if ((this.c.cost >= 0) && (!this.c.hasTag(SneckoMod.SNEKPROOF)))
     {
       int newCost = AbstractDungeon.cardRandomRng.random(3);


       if (this.c.cost != newCost) {
         this.c.cost = newCost;
         this.c.costForTurn = this.c.cost;
         this.c.isCostModified = true;
         this.c.superFlash(Color.PURPLE.cpy());
       }
     }

     this.isDone = true;
   }
 }


