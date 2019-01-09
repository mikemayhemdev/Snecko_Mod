 package sneckomod.powers;

 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.defect.ChannelAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.city.Snecko;
 import com.megacrit.cardcrawl.orbs.AbstractOrb;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import sneckomod.SneckoMod;
 import sneckomod.actions.ChannelGrandOrbAction;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.orbs.*;

 import java.util.ArrayList;

 public class GrandSneckoPower extends AbstractPower
 {
   public static final String POWER_ID = "Snecko:GrandSneckoPower";
   public static final String NAME = "Potency";
   public static PowerType POWER_TYPE = PowerType.BUFF;
   public static final String IMG = "powers/mastereye.png";
   public static final Logger logger = LogManager.getLogger(SneckoMod.class.getName());

   public static String[] DESCRIPTIONS;

   private AbstractCreature source;

   public GrandSneckoPower(AbstractCreature owner, AbstractCreature source, int amount)
   {
     this.name = "Potency";

     this.ID = "Snecko:GrandSneckoPower";


     this.owner = owner;

     this.source = source;


     this.img = new com.badlogic.gdx.graphics.Texture(SneckoMod.getResourcePath("powers/mastereye.png"));

     this.type = POWER_TYPE;

     this.amount = amount;
     DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

     this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

     updateDescription();
   }


     public void onInitialApplication() {
         SneckoMod.spritealtered = true;
         AbstractPlayer p = AbstractDungeon.player;



         AbstractDungeon.effectsQueue.add(new SmokePuffEffect(p.hb.cX, p.hb.cY));
         if (p instanceof SneckoCharacter) {
             SneckoCharacter hero = (SneckoCharacter) p;
             hero.setRenderscale(.5F);
         }





     }

   public void updateDescription()
   {
     if (this.amount == 1) {
       this.description = DESCRIPTIONS[0];
     } else {
       this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
     }
   }




   public void atStartOfTurnPostDraw()
   {
     super.atStartOfTurnPostDraw();


       for (int i = 0; i < this.amount; i++) {

           AbstractDungeon.actionManager.addToBottom(new ChannelGrandOrbAction());

       }
           AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
           AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
       }
   }



