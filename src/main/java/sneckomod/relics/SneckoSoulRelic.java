 package sneckomod.relics;

 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Texture;
 import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
 import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.characters.SneckoCharacter;




 public class SneckoSoulRelic
   extends CustomRelic
 {
   public static final String ID = "Snecko:SneckoSoulRelic";
   public static final String IMG_PATH = "relics/SneckoSoul.png";
   public static final String OUTLINE_IMG_PATH = "relics/SneckoSoulOutline.png";
   private static final int HP_PER_CARD = 1;

   public SneckoSoulRelic()
   {
     super("Snecko:SneckoSoulRelic", new Texture(SneckoMod.getResourcePath("relics/SneckoSoul.png")), new Texture(SneckoMod.getResourcePath("relics/SneckoSoulOutline.png")), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.FLAT);
   }


   public String getUpdatedDescription()
   {
     return this.DESCRIPTIONS[0];
   }

   public void onEquip()
   {
     flash();

     for (int i = 0; i < 6; i++)
     {

       AbstractCard cNew = SneckoMod.getRandomUnknown(false);

       AbstractDungeon.player.masterDeck.addToBottom(cNew);
     }



     this.counter = 0;
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
     AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, count / 2));
   }

   public void onEnterRoom(AbstractRoom room)
   {
     super.onEnterRoom(room);
     this.counter += 1;

     if (this.counter == 8) {
       flash();


       AbstractCard cNew = SneckoMod.getRandomUnknown(false);

       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(cNew, Settings.WIDTH / 2, Settings.HEIGHT / 2));
       this.counter = 0;
     }
   }


   public boolean canSpawn()
   {
     return AbstractDungeon.player instanceof SneckoCharacter;
   }


   public AbstractRelic makeCopy()
   {
     return new SneckoSoulRelic();
   }
 }


