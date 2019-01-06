 package sneckomod.cards;

 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;
 import sneckomod.powers.MasterEyePower;



 public class MasterEye
   extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;







   public MasterEye()
   {
     super("Snecko:MasterEye", NAME, SneckoMod.getResourcePath("cards/mastereye.png"), 2, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.magicNumber = (this.baseMagicNumber = 0);

     this.tags.add(SneckoMod.SNEKPROOF);
   }


   public void use(AbstractPlayer p, AbstractMonster m)
   {
     if ((p instanceof SneckoCharacter)) {
       ((SneckoCharacter)p).eyeAnim();
     }
     AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.PURPLE, true), 0.05F, true));
     AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new IntenseZoomEffect(p.hb.cX, p.hb.cY, false), 0.05F));

     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MasterEyePower(p, p, 1), 1));
   }



   public AbstractCard makeCopy()
   {
     return new MasterEye();
   }

   public void triggerWhenDrawn()
   {
     if (this.cost != 0) {
       this.cost = 2;
       this.costForTurn = 2;
       this.isCostModified = false;
     }
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();

       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
       this.isInnate = true;
     }
   }





   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:MasterEye");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:MasterEye";
   public static final String IMG_PATH = "cards/mastereye.png";
   private static final int COST = 2;
   private static final int POWER = 6;
   private static final int UPGRADE_BONUS = 3;
 }


