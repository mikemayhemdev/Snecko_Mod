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
 import sneckomod.SneckoMod;
 import sneckomod.patches.AbstractCardEnum;





 public abstract class UnknownAbstract
   extends AbstractSneckoCard
 {
   public static AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
   public static AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
   public static AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;


   public UnknownAbstract(AbstractCard.CardRarity rarity, AbstractCard.CardType type)
   {
     super("Snecko:UnknownAbstract", NAME, SneckoMod.getResourcePath("cards/acidgelatin.png"), 0, DESCRIPTION, type, AbstractCardEnum.SNECKO, rarity, TARGET);

     SoulboundField.soulbound.set(this, Boolean.valueOf(true));
   }


   public void use(AbstractPlayer p, AbstractMonster m) {}


   public void upgrade()
   {
     if (!this.upgraded) {
       upgradeName();

       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
     }
   }



   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:UnknownAbstract");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:UnknownAbstract";
   public static final String IMG_PATH = "cards/acidgelatin.png";
   public static final int COST = 0;
 }


