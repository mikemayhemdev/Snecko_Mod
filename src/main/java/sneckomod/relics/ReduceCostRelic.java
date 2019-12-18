 package sneckomod.relics;

 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
 import sneckomod.SneckoMod;
 import sneckomod.actions.ReduceMostExpensiveCardAction;
 import sneckomod.characters.SneckoCharacter;

 public class ReduceCostRelic extends CustomRelic
 {
   public static final String ID = "Snecko:ReduceCostRelic";
   public static final String IMG_PATH = "relics/sneckofang.png";
   public static final String OUTLINE_IMG_PATH = "relics/sneckofangOutline.png";
   private static final int HP_PER_CARD = 1;

   public ReduceCostRelic()
   {
     super("Snecko:ReduceCostRelic", new Texture(SneckoMod.getResourcePath("relics/sneckofang.png")), new Texture(SneckoMod.getResourcePath("relics/sneckofangOutline.png")), AbstractRelic.RelicTier.RARE, com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound.FLAT);
   }


   public String getUpdatedDescription()
   {
     return this.DESCRIPTIONS[0];
   }


   public void atTurnStartPostDraw()
   {
     super.atTurnStartPostDraw();
     addToBot(new ReduceMostExpensiveCardAction());
   }

   public boolean canSpawn() {
     return AbstractDungeon.player instanceof SneckoCharacter;
   }


   public AbstractRelic makeCopy()
   {
     return new ReduceCostRelic();
   }
 }


