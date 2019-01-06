 package sneckomod.powers;

 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import sneckomod.SneckoMod;

 public class MasterEyePower extends AbstractPower
 {
   public static final String POWER_ID = "Snecko:MasterEyePower";
   public static final String NAME = "Potency";
   public static AbstractPower.PowerType POWER_TYPE = AbstractPower.PowerType.BUFF;
   public static final String IMG = "powers/mastereye.png";
   public static final Logger logger = LogManager.getLogger(SneckoMod.class.getName());

   public static String[] DESCRIPTIONS;

   private AbstractCreature source;

   public MasterEyePower(AbstractCreature owner, AbstractCreature source, int amount)
   {
     this.name = "Potency";

     this.ID = "Snecko:MasterEyePower";


     this.owner = owner;

     this.source = source;


     this.img = new com.badlogic.gdx.graphics.Texture(SneckoMod.getResourcePath("powers/mastereye.png"));

     this.type = POWER_TYPE;

     this.amount = amount;
     DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

     this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

     updateDescription();
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

     AbstractCard mostExpensive = null;
     int mostExpensiveCost = -1;

     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c.cost > mostExpensiveCost) {
         mostExpensive = c;
       }
     }

     if (mostExpensive != null) {
       mostExpensive.modifyCostForTurn(-1);
       mostExpensive.isCostModified = true;
       mostExpensive.superFlash(Color.PURPLE.cpy());
     }
   }
 }


