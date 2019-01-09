 package sneckomod.relics;

 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import java.util.ArrayList;

 import com.megacrit.cardcrawl.relics.SneckoEye;
 import sneckomod.SneckoMod;

 public class Free3CostRelic extends CustomRelic
 {
   public static final String ID = "Snecko:Free3CostRelic";
   public static final String IMG_PATH = "relics/sneckoEyeUpgraded.png";
   public static final String OUTLINE_IMG_PATH = "relics/sneckoEyeUpgradedOutline.png";
   private static final int HP_PER_CARD = 1;
   private boolean usedThisCombat = false;

   public Free3CostRelic() {
     super("Snecko:Free3CostRelic", new com.badlogic.gdx.graphics.Texture(SneckoMod.getResourcePath("relics/sneckoEyeUpgraded.png")), new com.badlogic.gdx.graphics.Texture(SneckoMod.getResourcePath("relics/sneckoEyeUpgradedOutline.png")), com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier.BOSS, com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound.FLAT);
   }


   public String getUpdatedDescription()
   {
     return this.DESCRIPTIONS[0];
   }


   public void onCardDraw(AbstractCard c) {
     super.onCardDraw(c);
     if (!usedThisCombat) {
       if (c.cost >= 3) {
         flash();
         c.cost = 0;
         c.costForTurn = c.cost;
         c.isCostModified = true;
         c.superFlash(Color.PURPLE.cpy());
         this.usedThisCombat = true;
       }
     }
   }




   public void atBattleStart()
   {
     super.atBattleStart();
     this.usedThisCombat = false;
   }

   public boolean canSpawn() {
     return AbstractDungeon.player.hasRelic(SneckoEye.ID);
   }

   public void obtain()
   {
     if (AbstractDungeon.player.hasRelic("Snecko Eye")) {
       for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
         if (AbstractDungeon.player.relics.get(i).relicId.equals("Snecko Eye")) {
           instantObtain(AbstractDungeon.player, i, true);
           break;
         }
       }
     } else {
       super.obtain();
     }
   }

   public AbstractRelic makeCopy()
   {
     return new Free3CostRelic();
   }
 }


