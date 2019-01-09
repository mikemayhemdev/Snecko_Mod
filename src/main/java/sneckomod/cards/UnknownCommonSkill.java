 package sneckomod.cards;

 import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
 import com.evacipated.cardcrawl.modthespire.lib.SpireField;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.patches.AbstractCardEnum;


 public class UnknownCommonSkill
   extends AbstractSneckoCard
 {
   public static AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   public static AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
   public static AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;




   public UnknownCommonSkill()
   {
     super("Snecko:UnknownCommonSkill", NAME, SneckoMod.getResourcePath("cards/unknownCS.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     SoulboundField.soulbound.set(this, Boolean.valueOf(true));
     this.tags.add(SneckoMod.UNKNOWN);
      // this.exhaust = true;
   }


   public AbstractCard makeCopy() {
     return new UnknownCommonSkill();
   }

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();

       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
     }
   }


     public void triggerWhenDrawn()
     {
         if (this.cost != 0) {
             this.cost = 0;
             this.costForTurn = 0;
             this.isCostModified = false;
         }
     }



   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:UnknownCommonSkill");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:UnknownCommonSkill";
   public static final String IMG_PATH = "cards/unknownCS.png";
   public static final int COST = 0;

     public void use(AbstractPlayer p, AbstractMonster m) {
         this.replaceUnknown(true);
         this.used = true;
     }
 }

