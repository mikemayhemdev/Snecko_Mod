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


 public class UnknownUncommonSkill
   extends AbstractSneckoCard
 {
   public static AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   public static AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
   public static AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;




   public UnknownUncommonSkill()
   {
     super("Snecko:UnknownUncommonSkill", NAME, SneckoMod.getResourcePath("cards/unknownCS.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     SoulboundField.soulbound.set(this, Boolean.valueOf(true));
     this.tags.add(SneckoMod.UNKNOWN);
   }



   public boolean canUse(AbstractPlayer p, AbstractMonster m)
   {
     return false;
   }

   public AbstractCard makeCopy() {
     return new UnknownUncommonSkill();
   }

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();

       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
     }
   }






   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:UnknownUncommonSkill");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:UnknownUncommonSkill";
   public static final String IMG_PATH = "cards/unknownUS.png";
   public static final int COST = 0;

   public void use(AbstractPlayer p, AbstractMonster m) {}
 }


