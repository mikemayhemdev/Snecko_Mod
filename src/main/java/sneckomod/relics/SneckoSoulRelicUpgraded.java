 package sneckomod.relics;

 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;

 public class SneckoSoulRelicUpgraded extends CustomRelic
 {
   public static final String ID = "Snecko:SneckoSoulRelicUpgraded";
   public static final String IMG_PATH = "relics/SneckoSoulUpgraded.png";
   public static final String OUTLINE_IMG_PATH = "relics/SneckoSoulUpgradedOutline.png";
   private static final int HP_PER_CARD = 1;

   public SneckoSoulRelicUpgraded()
   {
     super("Snecko:SneckoSoulRelicUpgraded", new Texture(SneckoMod.getResourcePath("relics/SneckoSoulUpgraded.png")), new Texture(SneckoMod.getResourcePath("relics/SneckoSoulUpgradedOutline.png")), com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier.BOSS, com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound.FLAT);

     this.counter = 0;
   }

   public String getUpdatedDescription()
   {
     return this.DESCRIPTIONS[0];
   }



   public void onEnterRoom(AbstractRoom room)
   {
     super.onEnterRoom(room);
     this.counter += 1;

     if (this.counter == 8) {
       flash();


       AbstractCard cNew = SneckoMod.getRandomUnknown(true);
       AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect(cNew, Settings.WIDTH / 2, Settings.HEIGHT / 2));
       this.counter = 0;
     }
   }



   public void atBattleStart()
   {
     super.atBattleStart();
     int count = 0;
     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
       if (c.hasTag(SneckoMod.UNKNOWN)) {
         count++;
       }
     }
     AbstractDungeon.actionManager.addToBottom(new com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, count / 2));
   }

   public boolean canSpawn() {
     return AbstractDungeon.player.hasRelic("Snecko:SneckoSoulRelic");
   }

   public void obtain()
   {
     if (AbstractDungeon.player.hasRelic("Snecko:SneckoSoulRelic")) {
       for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
         if (AbstractDungeon.player.relics.get(i).relicId.equals("Snecko:SneckoSoulRelic")) {
             this.counter = AbstractDungeon.player.getRelic("Snecko:SneckoSoulRelic").counter;
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
     return new SneckoSoulRelicUpgraded();
   }
 }


