 package sneckomod.cards;

 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
 import sneckomod.SneckoMod;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;
 import sneckomod.powers.ChamPowers.ChameleonPower;
 import sneckomod.powers.MasterEyePower;


 public class ColorShift
   extends AbstractSneckoCard
 {
   private static final CardType TYPE = CardType.POWER;
   private static final CardRarity RARITY = CardRarity.RARE;
   private static final CardTarget TARGET = CardTarget.SELF;


   public ColorShift()
   {
     super("Snecko:ColorShift", NAME, SneckoMod.getResourcePath("cards/chameleon.png"), 2, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

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

     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ChameleonPower(p, 1), 1));
   }



   public AbstractCard makeCopy()
   {
     return new ColorShift();
   }

     public void triggerWhenDrawn()
     {
         if (this.upgraded) {
             if (this.cost != 1) this.cost = 1; this.costForTurn = 1;
             this.isCostModified = false;
         } else {
             if (this.cost != 2) this.cost = 2; this.costForTurn = 2;
             this.isCostModified = false;
         }
     }


     public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
        upgradeBaseCost(1);
     }
   }

     private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:ColorShift");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:ColorShift";
   public static final String IMG_PATH = "cards/chameleon.png";
   private static final int COST = 2;
   private static final int POWER = 6;
   private static final int UPGRADE_BONUS = 3;
 }


