 package sneckomod.actions;

 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.defect.ChannelAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import sneckomod.SneckoMod;
 import sneckomod.orbs.*;

 public class ChannelGrandOrbAction
   extends AbstractGameAction {
     public AbstractCard c;

     public ChannelGrandOrbAction() {
         this.c = c;
     }


     public void update() {
         if (!SneckoMod.hasWarpOrb()) {
             AbstractDungeon.actionManager.addToTop(new ChannelAction(new GrandWarp(), true));
         } else {
             Integer o = AbstractDungeon.cardRng.random(4);
             //Integer o = 5;

             switch (o) {
                 case 0:
                     AbstractDungeon.actionManager.addToTop(new ChannelAction(new GrandDraining(), true));
                     break;
                 case 1:
                     AbstractDungeon.actionManager.addToTop(new ChannelAction(new GrandFocusing(), true));
                     break;
                 case 2:
                     AbstractDungeon.actionManager.addToTop(new ChannelAction(new GrandFrost(), true));
                     break;
                 case 3:
                     AbstractDungeon.actionManager.addToTop(new ChannelAction(new GrandLightning(), true));
                     break;
                 case 4:
                     AbstractDungeon.actionManager.addToTop(new ChannelAction(new GrandMiasma(), true));
                     break;

             }
         }
         this.isDone = true;
     }
 }


