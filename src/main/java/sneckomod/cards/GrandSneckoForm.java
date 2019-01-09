 package sneckomod.cards;

 import com.badlogic.gdx.graphics.Color;
 import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.defect.ChannelAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
 import sneckomod.SneckoMod;
 import sneckomod.actions.ChannelGrandOrbAction;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.orbs.*;
 import sneckomod.patches.AbstractCardEnum;
 import sneckomod.powers.GrandSneckoPower;
 import sneckomod.powers.MasterEyePower;

 import java.util.ArrayList;


 public class GrandSneckoForm
   extends AbstractSneckoCard
 {
   private static final CardType TYPE = CardType.POWER;
   private static final CardRarity RARITY = CardRarity.RARE;
   private static final CardTarget TARGET = CardTarget.SELF;







   public GrandSneckoForm()
   {
     super("Snecko:GrandSneckoForm", NAME, SneckoMod.getResourcePath("cards/mastereye.png"), 3, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.magicNumber = (this.baseMagicNumber = 10);

     this.tags.add(SneckoMod.SNEKPROOF);
   }


   public void use(AbstractPlayer p, AbstractMonster m) {
       if ((p instanceof SneckoCharacter)) {
           ((SneckoCharacter) p).eyeAnim();
       }
       AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.PURPLE, true), 0.05F, true));
       AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new IntenseZoomEffect(p.hb.cX, p.hb.cY, false), 0.05F));

       int slotGain = 0;
       slotGain = 4 - p.maxOrbs;
       if (slotGain > 0) {
           com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction(slotGain));
       }

       AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p, p, this.magicNumber));

       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GrandSneckoPower(p, p, 1), 1));

       for (int i = 0; i < 4; i++) {

           AbstractDungeon.actionManager.addToBottom(new ChannelGrandOrbAction());


       }
   }



   public AbstractCard makeCopy()
   {
     return new GrandSneckoForm();
   }

   public void triggerWhenDrawn()
   {
     if (this.cost != 3) {
       this.cost = 3;
       this.costForTurn = 3;
       this.isCostModified = false;
     }
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();

       upgradeMagicNumber(6);
     }
   }





   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:GrandSneckoForm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:GrandSneckoForm";
   public static final String IMG_PATH = "cards/mastereye.png";
   private static final int COST = 3;
   private static final int POWER = 6;
   private static final int UPGRADE_BONUS = 3;
 }


